package py.com.aseguradoratajy.tajydemo.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import py.com.aseguradoratajy.tajydemo.R;
import py.com.aseguradoratajy.tajydemo.adapters.ProductsAdapter;
import py.com.aseguradoratajy.tajydemo.entities.Products;
import py.com.aseguradoratajy.tajydemo.repositories.ProductsRepository;

/**
 * Created by Manu0 on 9/23/2017.
 */

public class ProductsFragments extends Fragment {

    private OnItemProductsListenerSelected mListener;
    // VIEW
    private GridView gridView;
    private View rootView;

    // ADAPTERS
    private ProductsAdapter mGridProductsAdapter;

    public ProductsFragments() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TransactionFragment.
     */

    public static ProductsFragments newInstance() {
        ProductsFragments fragment = new ProductsFragments();
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
        rootView = inflater.inflate(R.layout.products_fragment, container, false);
        gridView = (GridView) rootView.findViewById(R.id.gridView);
        mGridProductsAdapter = new ProductsAdapter(getContext(), R.layout.item_products, ProductsRepository.getAll());
        gridView.setAdapter(mGridProductsAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                buildDialog(R.style.DialogAnimation_2, mGridProductsAdapter.getItemAtPosition(position));
            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ProductsFragments.OnItemProductsListenerSelected) {
            mListener = (ProductsFragments.OnItemProductsListenerSelected) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnItemProductsListener");
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
    public interface OnItemProductsListenerSelected {
        // TODO: Update argument type and name
        void onItemProductsListenerSelected(Products products);
    }

    private void buildDialog(int animationSource, Products mProductsObject) {
        String title = "";
        String productDescription = "";
        switch (mProductsObject.getProductId()) {
            case 1:
                title = "Accidentes Personales";
                productDescription = mProductsObject.getProductDetails();
                break;
            case 2:
                title = "Transporte";
                productDescription = mProductsObject.getProductDetails();
                break;
            case 3:
                title = "Mutiriesgo Comercio";
                productDescription = mProductsObject.getProductDetails();
                break;
            case 4:
                title = "Multiriesgo Hogar";
                productDescription = mProductsObject.getProductDetails();
                break;
            case 5:
                title = "1. Cacucción";
                productDescription = mProductsObject.getProductDetails();
                break;
            case 6:
                title = "Equipos Electrónicos";
                productDescription = mProductsObject.getProductDetails();
                break;
            case 7:
                title = "Vida Colectiva";
                productDescription = mProductsObject.getProductDetails();
                break;
            case 8:
                title = "Seguro Agricola";
                productDescription = mProductsObject.getProductDetails();
                break;

        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(title);
        builder.setMessage(productDescription);
        builder.setNegativeButton(getString(R.string.tag_accept), null);
        AlertDialog dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations = animationSource;
        dialog.show();
    }

}