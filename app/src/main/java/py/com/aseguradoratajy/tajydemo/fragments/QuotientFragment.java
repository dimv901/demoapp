package py.com.aseguradoratajy.tajydemo.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import py.com.aseguradoratajy.tajydemo.R;
import py.com.aseguradoratajy.tajydemo.adapters.VehiclesBradAdapter;
import py.com.aseguradoratajy.tajydemo.adapters.VehiclesModelAdapter;
import py.com.aseguradoratajy.tajydemo.dialogs.ProgressDialogFragment;
import py.com.aseguradoratajy.tajydemo.entities.Marks;
import py.com.aseguradoratajy.tajydemo.entities.VehiclesModel;
import py.com.aseguradoratajy.tajydemo.network.NetworkQueue;
import py.com.aseguradoratajy.tajydemo.network.RequestApp;
import py.com.aseguradoratajy.tajydemo.repositories.MarksRepositories;
import py.com.aseguradoratajy.tajydemo.repositories.VehiclesModelRepository;
import py.com.aseguradoratajy.tajydemo.utils.Constants;
import py.com.aseguradoratajy.tajydemo.utils.JsonObjectRequest;
import py.com.aseguradoratajy.tajydemo.utils.URLS;
import py.com.aseguradoratajy.tajydemo.utils.Utiles;

/**
 * Created by mavalos on 11/10/17.
 */

public class QuotientFragment extends Fragment {

    // VIEW
    private View rootView;
    private AppCompatEditText mNameAndLastNameEditText;
    private AppCompatEditText mAddressEditText;
    private AppCompatEditText mEmailEditText;
    private AppCompatSpinner mVehiclesBrandSpinner;
    private AppCompatSpinner mVehiclesModelSpinner;
    private AppCompatEditText mYearEditText;
    private VehiclesBradAdapter mVehiclesBraddAdapter;
    private VehiclesModelAdapter mVehiclesModelAdapter;
    private FloatingActionButton mSendButton;
    private TaskQuotien mQuotienTask;
    private String REQUEST_TAG = "QUOTIEN_REQUEST";


    public QuotientFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BranchFragment.
     */
    public static QuotientFragment newInstance() {
        QuotientFragment fragment = new QuotientFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.quotient_fragment, container, false);
        mNameAndLastNameEditText = (AppCompatEditText) rootView.findViewById(R.id.name_and_last_name_edit_text);
        mAddressEditText = (AppCompatEditText) rootView.findViewById(R.id.address_edit_text);
        mEmailEditText = (AppCompatEditText) rootView.findViewById(R.id.mail_edit_text);
        mYearEditText = (AppCompatEditText) rootView.findViewById(R.id.year_edit_text);
        mVehiclesBrandSpinner = (AppCompatSpinner) rootView.findViewById(R.id.vehicle_brand_spinner);
        mVehiclesModelSpinner = (AppCompatSpinner) rootView.findViewById(R.id.model_car_spinner);
        mSendButton = (FloatingActionButton) rootView.findViewById(R.id.send_fab_button);
        setDataSpinners();
        setupSpinnerListeners();
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateFields();
            }
        });
        return rootView;
    }

    private void setupSpinnerListeners() {
        mVehiclesBrandSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Marks marks = (Marks) mVehiclesBrandSpinner.getSelectedItem();
                mVehiclesModelAdapter = new VehiclesModelAdapter(getContext(), R.layout.item_spinner_description, VehiclesModelRepository.getModelsByMarks(marks.getId().intValue()));
                mVehiclesModelSpinner.setAdapter(mVehiclesModelAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void setDataSpinners() {

        mVehiclesBraddAdapter = new VehiclesBradAdapter(getContext(), R.layout.item_spinner_description, MarksRepositories.getAllMarks());
        mVehiclesBrandSpinner.setAdapter(mVehiclesBraddAdapter);

    }

    private void validateFields() {
        mNameAndLastNameEditText.setError(null);
        mAddressEditText.setError(null);
        mEmailEditText.setError(null);
        mYearEditText.setError(null);


        String mNameAndLastName = mNameAndLastNameEditText.getText().toString().trim();
        String mAddress = mAddressEditText.getText().toString().trim();
        String mEmail = mEmailEditText.getText().toString().trim();
        String mYear = mYearEditText.getText().toString().trim();
        String mModelDescription = "";
        String mVehicleBrandDescription = "";


        View focusView = null;
        boolean cancel = false;

        if (TextUtils.isEmpty(mNameAndLastName)) {
            mNameAndLastNameEditText.setError(getString(R.string.error_empty_field));
            focusView = mNameAndLastNameEditText;
            focusView.requestFocus();
            cancel = true;
        }

        if (TextUtils.isEmpty(mAddress)) {
            mAddressEditText.setError(getString(R.string.error_empty_field));
            focusView = mAddressEditText;
            focusView.requestFocus();
            cancel = true;
        }


        if (TextUtils.isEmpty(mEmail)) {
            mEmailEditText.setError(getString(R.string.error_empty_field));
            focusView = mEmailEditText;
            focusView.requestFocus();
            cancel = true;
        }

        if (TextUtils.isEmpty(mYear)) {
            mYearEditText.setError(getString(R.string.error_empty_field));
            focusView = mYearEditText;
            focusView.requestFocus();
            cancel = true;
        }

        Marks marks = (Marks) mVehiclesBrandSpinner.getSelectedItem();
        if (marks != null) {
            if (marks.getBrandCode() == 0) {
                Utiles.getToast(getContext(), "Debes seleccionar una marca de vehiculo");
                focusView = mVehiclesBrandSpinner;
                focusView.requestFocus();
                cancel = true;
            } else {
                mVehicleBrandDescription = marks.getDescription();
            }
        }

        VehiclesModel vehiclesModel = (VehiclesModel) mVehiclesModelSpinner.getSelectedItem();
        if (vehiclesModel != null) {
            if (vehiclesModel.getBrandId() == 0) {
                Utiles.getToast(getContext(), "Debes seleccionar un modelo de vehiculo");
                focusView = mVehiclesModelSpinner;
                focusView.requestFocus();
                cancel = true;
            } else {
                mModelDescription = vehiclesModel.getDescription();
            }
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            clearFields();
            TaskQuotien taskQuotien = new TaskQuotien(getContext(), mNameAndLastName, mAddress, mEmail, mVehicleBrandDescription, mModelDescription);
            taskQuotien.confirm();
        }

    }


    private class TaskQuotien extends RequestApp {
        private String mNameAndLastName;
        private String mAddress;
        private String mEmail;
        private String mVehiclesBrandDescription;
        private String mModelDescription;
        private Context mContext;


        TaskQuotien(Context context, String nameAndLastName, String address, String email, String vehiclesBrand, String modelDescription) {
            this.mContext = context;
            this.mNameAndLastName = nameAndLastName;
            this.mAddress = address;
            this.mEmail = email;
            this.mVehiclesBrandDescription = vehiclesBrand;
            this.mModelDescription = modelDescription;

        }

        @Override
        protected void confirm() {
            final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setIcon(R.mipmap.ic_error_outline_black_36dp);
            builder.setTitle(R.string.dialog_confirmation_title);
            builder.setMessage(R.string.dialog_confirmation_message);
            builder.setPositiveButton(R.string.label_accept, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    execute();
                }
            });
            builder.setNegativeButton(R.string.tag_cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mQuotienTask = null;
                }
            });
            AlertDialog confirmDialog = builder.create();
            confirmDialog.setCanceledOnTouchOutside(false);
            confirmDialog.show();
        }

        @Override
        protected void execute() {

            progressDialog = ProgressDialogFragment.newInstance(mContext);
            progressDialog.show(getFragmentManager(), ProgressDialogFragment.TAG);
            QuotienRequest mNosaleRequest = new QuotienRequest(mNameAndLastName, mAddress, mEmail, mVehiclesBrandDescription, mModelDescription);
            jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    URLS.CONTACT_SUPPORT_URL,
                    mNosaleRequest.getParams(),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            progressDialog.dismiss();
                            handleResponse(response);
                            jsonObjectRequest.cancel();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            String message = NetworkQueue.handleError(error, mContext);
                            jsonObjectRequest.cancel();
                            showDialog("ERROR", message);
                        }
                    });

            jsonObjectRequest.setRetryPolicy(Utiles.getRetryPolicy());
            jsonObjectRequest.setTag(REQUEST_TAG);
            NetworkQueue.getInstance(mContext).addToRequestQueue(jsonObjectRequest, mContext);

        }

        @Override
        protected void handleResponse(JSONObject response) {

            String message = null;
            int status = -1;

            if (response == null) {
                Utiles.getToast(mContext, (message == null) ? getString(R.string.volley_default_error) : message);
                return;
            }

            Log.i("TAG", "REQUEST" + " | Response: " + response.toString());

            try {
                if (response.has("status")) status = response.getInt("status");
                if (response.has("message")) message = response.getString("message");

                if (status != Constants.RESPONSE_OK) {
                    Utiles.getToast(mContext, (message == null) ? getString(R.string.volley_default_error) : message);
                    return;
                } else {
                    showDialog("OK", message);
                }


            } catch (JSONException e) {
                e.printStackTrace();
                Utiles.getToast(mContext, getString(R.string.error_parsing_json));
            }
        }

        class QuotienRequest extends RequestObject {
            private final String TAG_CLASS = QuotienRequest.class.getName();

            private String mNameAndLastName;
            private String mAddress;
            private String mEmail;
            private String mVehiclesBrandDescription;
            private String mModelDescription;

            public QuotienRequest(String nameAndLastName, String address, String email, String vehiclesBrand, String modelDescription) {
                this.mNameAndLastName = nameAndLastName;
                this.mAddress = address;
                this.mEmail = email;
                this.mVehiclesBrandDescription = vehiclesBrand;
                this.mModelDescription = modelDescription;

            }

            @Override
            public JSONObject getParams() {
                JSONObject params = new JSONObject();
                try {
                    params.put("name_and_surname", mNameAndLastName);
                    params.put("address", mAddress);
                    params.put("email", mEmail);
                    params.put("vehicle_brand", mVehiclesBrandDescription);
                    params.put("model", mModelDescription);

                } catch (JSONException jEX) {
                    Log.w(TAG_CLASS, "Error while create JSONObject " + jEX.getMessage());
                }
                return params;
            }
        }

        private void showDialog(String type, String message) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
            dialog.setTitle(getString(R.string.label_quotien_title));
            dialog.setMessage(message);
            switch (type) {
                case "OK":
                    dialog.setIcon(R.mipmap.ic_done_black_36dp);
                    break;
                case "ERROR":
                    dialog.setIcon(R.mipmap.ic_error_outline_black_36dp);
                    break;
            }
            dialog.setPositiveButton(getString(R.string.tag_accept), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            dialog.create();
            dialog.setCancelable(false);
            dialog.show();
        }

    }


    private void clearFields() {
        mNameAndLastNameEditText.getText().clear();
        mAddressEditText.getText().clear();
        mEmailEditText.getText().clear();
        mYearEditText.getText().clear();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
