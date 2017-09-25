package py.com.aseguradoratajy.tajydemo.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import py.com.aseguradoratajy.tajydemo.R;
import py.com.aseguradoratajy.tajydemo.dialogs.ProgressDialogFragment;
import py.com.aseguradoratajy.tajydemo.models.User;
import py.com.aseguradoratajy.tajydemo.utils.Utiles;

public class LoginActivity extends AppCompatActivity {

    private EditText inputDocument;
    private EditText inputPassword;
    private CoordinatorLayout mCoordinator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mCoordinator = (CoordinatorLayout) findViewById(R.id.login_coordinator);
        inputDocument = (EditText) findViewById(R.id.login_input_document);
        inputPassword = (EditText) findViewById(R.id.login_input_password);
        Button loginButton = (Button) findViewById(R.id.login_button_login);
        Button registerButton = (Button) findViewById(R.id.login_button_register);
        Button resetPasswordButton = (Button) findViewById(R.id.login_button_reset_password);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
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
            this.mProgressFragment = ProgressDialogFragment.newInstance(getApplicationContext());
            this.mProgressFragment.show(getSupportFragmentManager(), ProgressDialogFragment.TAG);
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

            } else {
                Utiles.getSnackBar(mCoordinator, getString(R.string.error_login));
            }
            super.onPostExecute(aVoid);
        }
    }

}
