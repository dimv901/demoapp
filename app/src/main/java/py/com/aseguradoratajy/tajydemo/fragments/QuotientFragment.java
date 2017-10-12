package py.com.aseguradoratajy.tajydemo.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import py.com.aseguradoratajy.tajydemo.R;

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

        return rootView;
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
