package py.com.aseguradoratajy.tajydemo.activities;

import android.app.NotificationManager;
import android.content.Context;
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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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
import py.com.aseguradoratajy.tajydemo.models.Insurance;
import py.com.aseguradoratajy.tajydemo.models.MapPolicyAccount;
import py.com.aseguradoratajy.tajydemo.network.NetworkQueue;
import py.com.aseguradoratajy.tajydemo.utils.AppPreferences;
import py.com.aseguradoratajy.tajydemo.utils.JsonObjectRequest;
import py.com.aseguradoratajy.tajydemo.utils.URLS;
import py.com.aseguradoratajy.tajydemo.utils.Utiles;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // view
    private Toolbar toolbar;
    private CoordinatorLayout mCoordinatorLayoutView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private InsuranceAdapter mAdapter;

    // Request
    private RequestQueue mRequestQueue = null;

    // UTILITARIAN VARIABLE
    private List<Insurance> mInsuranceList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationDrawerView();
        mCoordinatorLayoutView = (CoordinatorLayout) findViewById(R.id.main_coordinator_layout);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);

        RecyclerView mInsuranceRecyclerView = (RecyclerView) findViewById(R.id.insurance_list);
        mInsuranceRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mInsuranceRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));
        mInsuranceRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mInsuranceRecyclerView.setHasFixedSize(true);
        mAdapter = new InsuranceAdapter(getApplicationContext(), new ArrayList<Insurance>());
        mInsuranceRecyclerView.setAdapter(mAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                sendRequest();
            }
        });

    }


    private void navigationDrawerView() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_account) {
            // Handle the camera action
        } else if (id == R.id.nav_close_session) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void sendRequest() {

        String REQUEST_TAG = "SEND_REQUEST_ALL_DATA";
        boolean mIsConnected;

        mIsConnected = Utiles.checkNetworkConnection(this);
        if (!mIsConnected) {
            Utiles.getToast(this, getString(R.string.tag_not_internet));
            return;
        }
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(REQUEST_TAG);
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                URLS.POLICY_ACCOUNT_URL,
                buildParams(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        mSwipeRefreshLayout.setRefreshing(false);
                        responseHandler(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
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
                            public void processFinish(List<Insurance> insuranceList) {
                                if (insuranceList.size() == 0) {
                                    Utiles.getToast(MainActivity.this, getString(R.string.error_empty_insurance_policy));
                                    mSwipeRefreshLayout.setRefreshing(false);
                                } else {
                                    mSwipeRefreshLayout.setRefreshing(false);
                                    mAdapter.setData(insuranceList);
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

    private static class AsyncTaskInsurace extends AsyncTask<Void, Void, List<Insurance>> {
        // AsyncTask implement for insert much simcard details
        // you may separate this or combined to caller class.
        interface AsyncResponse {
            void processFinish(List<Insurance> insurances);
        }

        AsyncResponse delegate = null;

        private JSONArray mInsuranceArray = new JSONArray();
        private List<Insurance> mInsuranceList = new ArrayList<>();


        AsyncTaskInsurace(AsyncResponse delegate, JSONArray insuranceArray) {
            this.mInsuranceArray = insuranceArray;
            this.delegate = delegate;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Insurance> doInBackground(Void... params) {
            for (int i = 0; i < mInsuranceArray.length(); i++) {
                try {
                    JSONObject jsonObject = (JSONObject) mInsuranceArray.get(i);
                    Insurance insuranceObject = MapPolicyAccount.getInsuranceFromJson(jsonObject);
                    mInsuranceList.add(insuranceObject);
                } catch (JSONException jsx) {
                    jsx.printStackTrace();
                }
            }

            return mInsuranceList;
        }

        @Override
        protected void onPostExecute(List<Insurance> insurance) {
            delegate.processFinish(insurance);
        }

    }


}
