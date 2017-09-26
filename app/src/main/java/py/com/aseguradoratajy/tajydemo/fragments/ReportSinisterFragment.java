package py.com.aseguradoratajy.tajydemo.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import py.com.aseguradoratajy.tajydemo.R;
import py.com.aseguradoratajy.tajydemo.adapters.ProductsAdapter;
import py.com.aseguradoratajy.tajydemo.adapters.SinisterAdapter;
import py.com.aseguradoratajy.tajydemo.models.Products;
import py.com.aseguradoratajy.tajydemo.models.Sinisters;

/**
 * Created by Manu0 on 9/25/2017.
 */

public class ReportSinisterFragment extends Fragment {

    private OnItemSinisterListenerSelected mListener;
    // VIEW
    private GridView gridView;
    private View rootView;
    private TextView mCallTextView;
    private FloatingActionButton mFloatingCallButton;
    // ADAPTERS
    private SinisterAdapter mGridSinisterAdapter;

    public ReportSinisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TransactionFragment.
     */

    public static ReportSinisterFragment newInstance() {
        ReportSinisterFragment fragment = new ReportSinisterFragment();
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
        rootView = inflater.inflate(R.layout.report_sinister_fragment, container, false);
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
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSinisterListenerSelected) {
            mListener = (OnItemSinisterListenerSelected) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnItemSinisterListenerSelected");
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
    public interface OnItemSinisterListenerSelected {
        // TODO: Update argument type and name
        void onItemSinisterListenerSelected(Products products);
    }
}