package py.com.aseguradoratajy.tajydemo.fragments;

import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import py.com.aseguradoratajy.tajydemo.R;
import py.com.aseguradoratajy.tajydemo.dialogs.AlertDialogFragment;
import py.com.aseguradoratajy.tajydemo.dialogs.ProgressDialogFragment;

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
        mSendFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateFields();
            }
        });
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
            TaskContactSupport taskContactSupport = new TaskContactSupport(mNameAndLastName, mPhone, mEmail, mIssues, mMessage);
            taskContactSupport.execute();
        }

    }


    public interface OnItemContactSupportListenerSelected {
        // TODO: Update argument type and name
        void onItemContactSupportListenerSelected();
    }


    private class TaskContactSupport extends AsyncTask<Void, Void, Void> {
        private String mNameAndLastName;
        private String mPhone;
        private String mEmail;
        private String mIssues;
        private String mMessage;
        private ProgressDialogFragment mProgressFragment;


        public TaskContactSupport(String nameAndLastName, String phone, String email, String issues, String message) {
            this.mNameAndLastName = nameAndLastName;
            this.mPhone = phone;
            this.mEmail = email;
            this.mIssues = issues;
            this.mMessage = message;
            this.mProgressFragment = ProgressDialogFragment.newInstance(getContext());
            this.mProgressFragment.show(getActivity().getSupportFragmentManager(), ProgressDialogFragment.TAG);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                // Simulate network access.
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            } //3 SECONDS
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mProgressFragment.dismiss();
            AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
            dialog.setTitle(getString(R.string.label_contact_title));
            dialog.setMessage(getString(R.string.success_message));
            dialog.setIcon(R.mipmap.ic_done_black_36dp);
            dialog.setPositiveButton(getString(R.string.tag_accept), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            dialog.create();
            dialog.setCancelable(false);
            dialog.show();
            super.onPostExecute(aVoid);
        }
    }

}
