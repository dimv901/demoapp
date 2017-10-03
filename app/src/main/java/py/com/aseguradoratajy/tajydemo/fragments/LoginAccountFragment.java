package py.com.aseguradoratajy.tajydemo.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import py.com.aseguradoratajy.tajydemo.R;
import py.com.aseguradoratajy.tajydemo.activities.MainActivity;
import py.com.aseguradoratajy.tajydemo.dialogs.ProgressDialogFragment;
import py.com.aseguradoratajy.tajydemo.network.NetworkQueue;
import py.com.aseguradoratajy.tajydemo.network.RequestApp;
import py.com.aseguradoratajy.tajydemo.utils.AppPreferences;
import py.com.aseguradoratajy.tajydemo.utils.Constants;
import py.com.aseguradoratajy.tajydemo.utils.JsonObjectRequest;
import py.com.aseguradoratajy.tajydemo.utils.URLS;
import py.com.aseguradoratajy.tajydemo.utils.Utiles;

/**
 * Created by Manu0 on 9/23/2017.
 */

public class LoginAccountFragment extends Fragment {
    private final String TAG_CLASS = LoginAccountFragment.class.getName();


    // VIEW
    private View rootView;
    private EditText inputDocument;
    private EditText inputPassword;
    private LoginTask mLoginTask;

    public LoginAccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TransactionFragment.
     */

    public static LoginAccountFragment newInstance() {
        LoginAccountFragment fragment = new LoginAccountFragment();
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

        rootView = inflater.inflate(R.layout.login_account_fragment, container, false);
        inputDocument = (EditText) rootView.findViewById(R.id.login_input_document);
        inputPassword = (EditText) rootView.findViewById(R.id.login_input_password);
        Button loginButton = (Button) rootView.findViewById(R.id.login_button_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnItemAccountListenerSelected) {
            mListener = (OnItemAccountListenerSelected) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnItemAccountListenerSelected");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        // mListener = null;
    }

    private void validate() {

        inputDocument.setError(null);
        inputPassword.setError(null);

        boolean cancel = false;
        View focusView = null;

        String document = inputDocument.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();

        if (TextUtils.isEmpty(document)) {
            inputDocument.setError(getString(R.string.error_empty_field));
            focusView = inputDocument;
            cancel = true;
        }

        if (TextUtils.isEmpty(password)) {
            inputPassword.setError(getString(R.string.error_empty_field));
            focusView = inputPassword;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            new LoginTask(document, password).execute();
        }
    }

    private class LoginTask extends RequestApp {
        public static final String REQUEST_TAG = "LoginTask";

        private String mUsername;
        private String mPassword;

        public LoginTask(String username, String password) {
            mUsername = username;
            mPassword = password;
        }

        @Override
        protected void confirm() {

        }

        @Override
        protected void execute() {
            progressDialog = ProgressDialogFragment.newInstance(getContext());
            progressDialog.show(getFragmentManager(), ProgressDialogFragment.TAG);


            LoginRequest mLoginRequest = new LoginRequest(mUsername, mPassword);
            jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    URLS.LOGIN_URL,
                    mLoginRequest.getParams(),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            progressDialog.dismiss();
                            handleResponse(response);
                            mLoginTask = null;
                            jsonObjectRequest.cancel();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            String message = Utiles.volleyErrorHandler(error, getContext());
                            jsonObjectRequest.cancel();
                            Utiles.getToast(getContext(), message);
                        }
                    });

            jsonObjectRequest.setRetryPolicy(Utiles.getRetryPolicy());
            jsonObjectRequest.setTag(REQUEST_TAG);
            NetworkQueue.getInstance(getContext()).addToRequestQueue(jsonObjectRequest, getContext());

        }

        @Override
        protected void handleResponse(JSONObject response) {
            String message = null;
            String user;
            int status = -1;

            if (response == null) {
                Utiles.getToast(getContext(), getString(R.string.volley_parse_error));
                return;
            }

            Log.i(TAG_CLASS, REQUEST_TAG + " | Response: " + response.toString());

            try {
                if (response.has("codigoRetorno")) status = response.getInt("codigoRetorno");
                if (response.has("desRetorno")) message = response.getString("desRetorno");


                if (status != Constants.RESPONSE_OK) {
                    Utiles.getToast(getContext(), (message == null) ? getString(R.string.volley_default_error) : message);
                } else {

                    if (response.has("usuario")) {
                        user = response.getString("usuario");
                    } else {
                        Utiles.getToast(getContext(), getString(R.string.error_invalid_response));
                        return;
                    }


                    AppPreferences.getAppPreferences(getContext()).edit().putBoolean(AppPreferences.KEY_LOGGED_IN, true).apply();
                    AppPreferences.getAppPreferences(getContext()).edit().putString(AppPreferences.KEY_USERNAME, user).apply();

                    Utiles.getToast(getContext(), getString(R.string.label_message_welcome));
                    getActivity().finish();
                    startActivity(new Intent(getContext(), MainActivity.class));
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Utiles.getToast(getContext(), getString(R.string.error_parsing_json));
            } catch (Exception ex) {
                ex.printStackTrace();
                Utiles.getToast(getContext(), getString(R.string.volley_default_error));
            }

        }


        class LoginRequest extends RequestObject {

            private final String TAG_CLASS = LoginTask.LoginRequest.class.getName();

            private String mUserName;
            private String mPassword;


            public LoginRequest(String username, String password) {
                mUserName = username;
                mPassword = password;

            }

            @Override
            public JSONObject getParams() {
                JSONObject params = new JSONObject();
                try {
                    params.put("usuario", mUserName);
                    params.put("password", mPassword);
                } catch (JSONException jEX) {
                    Log.w(TAG_CLASS, "Error while create JSONObject " + jEX.getMessage());
                }
                return params;
            }
        }

    }

}
