package py.com.aseguradoratajy.tajydemo.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import py.com.aseguradoratajy.tajydemo.R;

/**
 * Created by Manu0 on 9/23/2017.
 */

public class ContactSupportFragment extends Fragment {

    private OnItemContactSupportListenerSelected mListener;
    // VIEW
    private View rootView;


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
    public interface OnItemContactSupportListenerSelected {
        // TODO: Update argument type and name
        void onItemContactSupportListenerSelected();
    }

}
