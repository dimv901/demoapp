package py.com.aseguradoratajy.tajydemo.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import py.com.aseguradoratajy.tajydemo.R;
import py.com.aseguradoratajy.tajydemo.activities.MainActivity;
import py.com.aseguradoratajy.tajydemo.dialogs.ProgressDialogFragment;
import py.com.aseguradoratajy.tajydemo.models.User;
import py.com.aseguradoratajy.tajydemo.utils.Utiles;

/**
 * Created by Manu0 on 9/23/2017.
 */

public class LoginAccountFragment extends Fragment {

    private EditText inputDocument;
    private EditText inputPassword;
    // VIEW
    private View rootView;


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
            new TaskLogin(document, password).execute();
        }
    }

    private class TaskLogin extends AsyncTask<Void, Void, Void> {
        private String document;
        private String password;
        private User user;
        private ProgressDialogFragment mProgressFragment;


        public TaskLogin(String document, String password) {
            this.document = document;
            this.password = password;
            this.mProgressFragment = ProgressDialogFragment.newInstance(getContext());
            this.mProgressFragment.show(getFragmentManager(), ProgressDialogFragment.TAG);
            this.user = User.getInstance();
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
            if (user.getDocument().equals(document) && (user.getPassword().equals(password))) {
                startActivity(new Intent(getContext(), MainActivity.class));
                getActivity().finish();
            } else {
                Utiles.getToast(getContext(), getString(R.string.error_login));
            }
            super.onPostExecute(aVoid);
        }
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
   /* public interface OnItemAccountListenerSelected {
        void onItemAccountListenerSelected ();
    }*/

}
