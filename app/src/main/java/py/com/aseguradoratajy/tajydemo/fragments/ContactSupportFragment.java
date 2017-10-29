package py.com.aseguradoratajy.tajydemo.fragments;

import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

import py.com.aseguradoratajy.tajydemo.R;
import py.com.aseguradoratajy.tajydemo.activities.MainActivity;
import py.com.aseguradoratajy.tajydemo.adapters.SinisterAdapter;
import py.com.aseguradoratajy.tajydemo.dialogs.AlertDialogFragment;
import py.com.aseguradoratajy.tajydemo.dialogs.ProgressDialogFragment;
import py.com.aseguradoratajy.tajydemo.entities.Insurance;
import py.com.aseguradoratajy.tajydemo.models.Sinisters;
import py.com.aseguradoratajy.tajydemo.network.NetworkQueue;
import py.com.aseguradoratajy.tajydemo.network.RequestApp;
import py.com.aseguradoratajy.tajydemo.utils.Constants;
import py.com.aseguradoratajy.tajydemo.utils.JsonObjectRequest;
import py.com.aseguradoratajy.tajydemo.utils.URLS;
import py.com.aseguradoratajy.tajydemo.utils.Utiles;

/**
 * Created by Manu0 on 9/23/2017.
 */

public class ContactSupportFragment extends Fragment {

    private OnItemContactSupportListenerSelected mListener;
    // VIEW
    private View rootView;
    private AppCompatEditText mNameAndLastNameEditText;
    private AppCompatEditText mPhoneEditText;
    private AppCompatEditText mEmailEditText;
    private AppCompatEditText mIssuesEditText;
    private AppCompatEditText mMessageEditText;
    private FloatingActionButton mSendFabButton;
    private AppCompatButton mSinisterButton;
    private ProgressDialogFragment mProgressFragment;
    private TaskContactSupport taskContactSupport;

    public ContactSupportFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TransactionFragment.
     */

    public static ContactSupportFragment newInstance() {
        ContactSupportFragment fragment = new ContactSupportFragment();
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
        rootView = inflater.inflate(R.layout.contact_support_fragment, container, false);
        mNameAndLastNameEditText = (AppCompatEditText) rootView.findViewById(R.id.name_and_last_name_edit_text);
        mPhoneEditText = (AppCompatEditText) rootView.findViewById(R.id.phone_edit_text);
        mEmailEditText = (AppCompatEditText) rootView.findViewById(R.id.mail_edit_text);
        mIssuesEditText = (AppCompatEditText) rootView.findViewById(R.id.issues_edit_text);
        mMessageEditText = (AppCompatEditText) rootView.findViewById(R.id.message_edit_text);
        mSendFabButton = (FloatingActionButton) rootView.findViewById(R.id.send_fab_button);
        mSinisterButton = (AppCompatButton) rootView.findViewById(R.id.call_emergency_button);
        mSendFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateFields();
            }
        });
        mSinisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSinisterDialog();
            }
        });
        mProgressFragment = ProgressDialogFragment.newInstance(getContext());
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemContactSupportListenerSelected) {
            mListener = (OnItemContactSupportListenerSelected) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnItemContactSupportListenerSelected");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    private void showSinisterDialog() {
        GridView gridView;
        View rootView;
        final TextView mCallTextView;
        FloatingActionButton mFloatingCallButton;
        final SinisterAdapter mGridSinisterAdapter;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        rootView = inflater.inflate(R.layout.report_sinister_dialog, null);
        gridView = (GridView) rootView.findViewById(R.id.sinisterGridView);
        mCallTextView = (TextView) rootView.findViewById(R.id.number_call);
        mFloatingCallButton = (FloatingActionButton) rootView.findViewById(R.id.call_fab_button);
        mGridSinisterAdapter = new SinisterAdapter(getContext(), R.layout.item_sinister, Sinisters.setupSinisterList());
        gridView.setAdapter(mGridSinisterAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCallTextView.setText(mGridSinisterAdapter.getItemAtPosition(position).getNumberPhone());
            }
        });

        mFloatingCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + mCallTextView.getText().toString().trim()));
                startActivity(callIntent);

            }
        });
        builder.setPositiveButton(getString(R.string.label_accept), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.setView(rootView);
        builder.create();
        builder.setCancelable(false);
        builder.show();

    }

    private void validateFields() {
        mNameAndLastNameEditText.setError(null);
        mPhoneEditText.setError(null);
        mEmailEditText.setError(null);
        mIssuesEditText.setError(null);
        mMessageEditText.setError(null);

        String mNameAndLastName = mNameAndLastNameEditText.getText().toString().trim();
        String mPhone = mPhoneEditText.getText().toString().trim();
        String mEmail = mEmailEditText.getText().toString().trim();
        String mIssues = mIssuesEditText.getText().toString().trim();
        String mMessage = mMessageEditText.getText().toString().trim();
        View focusView = null;
        boolean cancel = false;

        if (TextUtils.isEmpty(mNameAndLastName)) {
            mNameAndLastNameEditText.setError(getString(R.string.error_empty_field));
            focusView = mNameAndLastNameEditText;
            focusView.requestFocus();
            cancel = true;
        }

        if (TextUtils.isEmpty(mPhone)) {
            mPhoneEditText.setError(getString(R.string.error_empty_field));
            focusView = mPhoneEditText;
            focusView.requestFocus();
            cancel = true;
        }

        if (TextUtils.isEmpty(mEmail)) {
            mEmailEditText.setError(getString(R.string.error_empty_field));
            focusView = mEmailEditText;
            focusView.requestFocus();
            cancel = true;
        }

        if (TextUtils.isEmpty(mIssues)) {
            mIssuesEditText.setError(getString(R.string.error_empty_field));
            focusView = mIssuesEditText;
            focusView.requestFocus();
            cancel = true;
        }

        if (TextUtils.isEmpty(mMessage)) {
            mMessageEditText.setError(getString(R.string.error_empty_field));
            focusView = mMessageEditText;
            focusView.requestFocus();
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            clearFields();
            taskContactSupport = new TaskContactSupport(getContext(), mNameAndLastName, mPhone, mEmail, mIssues, mMessage);
            taskContactSupport.execute();
        }

    }


    private void clearFields() {
        mNameAndLastNameEditText.getText().clear();
        mPhoneEditText.getText().clear();
        mEmailEditText.getText().clear();
        mIssuesEditText.getText().clear();
        mMessageEditText.getText().clear();
    }

    public interface OnItemContactSupportListenerSelected {
        // TODO: Update argument type and name
        void onItemContactSupportListenerSelected();
    }


    private class TaskContactSupport extends RequestApp {
        private String mNameAndLastName;
        private String mPhone;
        private String mEmail;
        private String mIssues;
        private String mMessage;
        private Context mContext;


        TaskContactSupport(Context context, String nameAndLastName, String phone, String email, String issues, String message) {
            this.mContext = context;
            this.mNameAndLastName = nameAndLastName;
            this.mPhone = phone;
            this.mEmail = email;
            this.mIssues = issues;
            this.mMessage = message;

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
                    taskContactSupport = null;
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
            ContactSupportRequest mNosaleRequest = new ContactSupportRequest(mNameAndLastName, mPhone, mEmail, mIssues, mMessage);
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
            jsonObjectRequest.setTag("CONTACT_REQUEST");
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

        class ContactSupportRequest extends RequestObject {
            private final String TAG_CLASS = ContactSupportRequest.class.getName();

            private String mNameAndLastName;
            private String mPhone;
            private String mEmail;
            private String mIssues;
            private String mMessage;

            ContactSupportRequest(String nameAndLastName, String phone, String email, String issues, String message) {
                this.mNameAndLastName = nameAndLastName;
                this.mPhone = phone;
                this.mEmail = email;
                this.mIssues = issues;
                this.mMessage = message;

            }

            @Override
            public JSONObject getParams() {
                JSONObject params = new JSONObject();
                try {
                    params.put("name_and_surname", mNameAndLastName);
                    params.put("phone", mPhone);
                    params.put("email", mEmail);
                    params.put("issue", mIssues);
                    params.put("message", mMessage);

                } catch (JSONException jEX) {
                    Log.w(TAG_CLASS, "Error while create JSONObject " + jEX.getMessage());
                }
                return params;
            }
        }

        private void showDialog(String type, String message) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
            dialog.setTitle(getString(R.string.label_contact_title));
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


}
