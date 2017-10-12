package py.com.aseguradoratajy.tajydemo.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import py.com.aseguradoratajy.tajydemo.R;
import py.com.aseguradoratajy.tajydemo.adapters.InsuranceAdapter;
import py.com.aseguradoratajy.tajydemo.adapters.InsuranceDetailsAdapter;
import py.com.aseguradoratajy.tajydemo.dialogs.ProgressDialogFragment;
import py.com.aseguradoratajy.tajydemo.entities.Insurance;
import py.com.aseguradoratajy.tajydemo.models.MapPolicyAccount;
import py.com.aseguradoratajy.tajydemo.network.NetworkQueue;
import py.com.aseguradoratajy.tajydemo.repositories.InsuranceRepository;
import py.com.aseguradoratajy.tajydemo.utils.AppPreferences;
import py.com.aseguradoratajy.tajydemo.utils.JsonObjectRequest;
import py.com.aseguradoratajy.tajydemo.utils.RecyclerItemClickListener;
import py.com.aseguradoratajy.tajydemo.utils.URLS;
import py.com.aseguradoratajy.tajydemo.utils.Utiles;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // view
    private Toolbar toolbar;
    private CoordinatorLayout mCoordinatorLayoutView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private InsuranceAdapter mAdapter;
    private ProgressDialogFragment progressDialogFragment;

    // Request
    private RequestQueue mRequestQueue = null;

    // UTILITARIAN VARIABLE
    private boolean isData = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationDrawerView();
        mCoordinatorLayoutView = (CoordinatorLayout) findViewById(R.id.main_coordinator_layout);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        progressDialogFragment = ProgressDialogFragment.newInstance(this);

        RecyclerView mInsuranceRecyclerView = (RecyclerView) findViewById(R.id.insurance_list);
        mInsuranceRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mInsuranceRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mInsuranceRecyclerView.setHasFixedSize(true);
        mAdapter = new InsuranceAdapter(this, new ArrayList<Insurance>());
        mInsuranceRecyclerView.setAdapter(mAdapter);
        checkInitialData();

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                sendRequest();
            }
        });

    }


    private void checkInitialData() {


        long countRegisters = InsuranceRepository.getDao().count();
        if (countRegisters == 0) {
            progressDialogFragment.show(getSupportFragmentManager(), ProgressDialogFragment.TAG);
            sendRequest();
        } else {
            isData = true;
            mAdapter.setData(InsuranceRepository.groupData());
        }


    }

    private void navigationDrawerView() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView mUserName = (TextView) headerView.findViewById(R.id.username_text_view);
        mUserName.setText("USUARIO: " + AppPreferences.getAppPreferences(this).getString(AppPreferences.KEY_USERNAME, null));

        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_account) {
            // Handle the camera action
        } else if (id == R.id.nav_close_session) {
            finish();
            startActivity(new Intent(MainActivity.this, NavigationActivity.class));
            Utiles.clearAppData(this);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void sendRequest() {


        String REQUEST_TAG = "SEND_REQUEST_ALL_DATA";
        if (!Utiles.checkNetworkConnection(this)) {
            Utiles.getToast(this, getString(R.string.tag_not_internet));
            return;
        }
        mAdapter.setData(new ArrayList<Insurance>());
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(REQUEST_TAG);
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                URLS.POLICY_ACCOUNT_URL,
                buildParams(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (!isData) progressDialogFragment.dismiss();
                        mSwipeRefreshLayout.setRefreshing(false);
                        responseHandler(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (!isData) progressDialogFragment.dismiss();
                        mSwipeRefreshLayout.setRefreshing(false);
                        String message = NetworkQueue.handleError(error, MainActivity.this);
                        Utiles.getToast(MainActivity.this, message);
                    }
                });
        jsonObjectRequest.setRetryPolicy(Utiles.getRetryPolicy());
        jsonObjectRequest.setTag(REQUEST_TAG);
        NetworkQueue.getInstance(this).addToRequestQueue(jsonObjectRequest, this);

    }

    private JSONObject buildParams() {
        JSONObject params = new JSONObject();
        try {
            params.put("usuario", AppPreferences.getAppPreferences(this).getString(AppPreferences.KEY_USERNAME, null));
            params.put("password", AppPreferences.getAppPreferences(this).getString(AppPreferences.KEY_PASSWORD, null));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return params;
    }

    private void responseHandler(JSONObject response) {

        JSONArray mInsuranceJsonArray;
        int status = 0;
        String message = "";

        if (response == null) {
            Utiles.getToast(this, getString(R.string.volley_parse_error));
            return;
        }

        try {
            if (response.has("codigoRetorno")) status = response.getInt("codigoRetorno");
            if (response.has("desRetorno")) message = response.getString("desRetorno");

            if (status != 0) {
                Utiles.getToast(this, message);
                return;
            } else {
                if (response.has("data")) {
                    mInsuranceJsonArray = response.getJSONArray("data");
                    if (mInsuranceJsonArray.length() > 0) {
                        AsyncTaskInsurace asyncTaskInsurace = new AsyncTaskInsurace(new AsyncTaskInsurace.AsyncResponse() {
                            @Override
                            public void processFinish(Integer countReqister) {
                                if (countReqister == 0) {
                                    Utiles.getToast(MainActivity.this, getString(R.string.error_empty_insurance_policy));
                                    mSwipeRefreshLayout.setRefreshing(false);
                                    if (!isData) progressDialogFragment.dismiss();
                                } else {
                                    if (!isData) progressDialogFragment.dismiss();
                                    mSwipeRefreshLayout.setRefreshing(false);
                                    mAdapter.setData(InsuranceRepository.groupData());
                                }
                            }
                        }, mInsuranceJsonArray);
                        asyncTaskInsurace.execute();
                    } else {
                        Utiles.getToast(MainActivity.this, getString(R.string.error_empty_insurance_policy));
                    }
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    private static class AsyncTaskInsurace extends AsyncTask<Void, Void, Integer> {
        // AsyncTask implement for insert much simcard details
        // you may separate this or combined to caller class.

        interface AsyncResponse {
            void processFinish(Integer insuranceCount);
        }

        AsyncResponse delegate = null;

        private JSONArray mInsuranceArray = new JSONArray();


        AsyncTaskInsurace(AsyncResponse delegate, JSONArray insuranceArray) {
            this.mInsuranceArray = insuranceArray;
            this.delegate = delegate;

        }

        @Override
        protected void onPreExecute() {
            InsuranceRepository.clearAll();
        }

        @Override
        protected Integer doInBackground(Void... params) {
            int count = 0;
            for (int i = 0; i < mInsuranceArray.length(); i++) {
                try {
                    count++;
                    JSONObject jsonObject = (JSONObject) mInsuranceArray.get(i);
                    Insurance insuranceObject = MapPolicyAccount.getInsuranceFromJson(jsonObject);
                    InsuranceRepository.getDao().insertOrReplace(insuranceObject);
                } catch (JSONException jsx) {
                    jsx.printStackTrace();
                }
            }

            return count;
        }

        @Override
        protected void onPostExecute(Integer count) {
            delegate.processFinish(count);
        }

    }
}
