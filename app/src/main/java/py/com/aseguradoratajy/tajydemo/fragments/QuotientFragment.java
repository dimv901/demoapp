package py.com.aseguradoratajy.tajydemo.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import py.com.aseguradoratajy.tajydemo.R;
import py.com.aseguradoratajy.tajydemo.adapters.VehiclesBardAdapter;
import py.com.aseguradoratajy.tajydemo.adapters.VehiclesModelAdapter;
import py.com.aseguradoratajy.tajydemo.models.VehicleBrand;
import py.com.aseguradoratajy.tajydemo.models.VehiclesModels;

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
    private VehiclesBardAdapter mVehiclesBardAdapter;
    private VehiclesModelAdapter mVehiclesModelAdapter;
    private FloatingActionButton mSendButton;


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

        return rootView;
    }


    private void setDataSpinners() {

        mVehiclesBardAdapter = new VehiclesBardAdapter(getContext(), R.layout.item_spinner_description, VehicleBrand.getVehiclesBrandList());
        mVehiclesBrandSpinner.setAdapter(mVehiclesBardAdapter);

        mVehiclesModelAdapter = new VehiclesModelAdapter(getContext(), R.layout.item_spinner_description, VehiclesModels.getVehiclesList());
        mVehiclesBrandSpinner.setAdapter(mVehiclesModelAdapter);


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


        if (cancel) {
            focusView.requestFocus();
        } else {
            clearFields();
            /*TaskContactSupport taskContactSupport = new TaskContactSupport(mNameAndLastName, mPhone, mEmail, mIssues, mMessage);
            taskContactSupport.execute();*/
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
