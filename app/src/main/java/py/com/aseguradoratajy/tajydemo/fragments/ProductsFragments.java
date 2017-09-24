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
import py.com.aseguradoratajy.tajydemo.models.Products;

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
        mGridProductsAdapter = new ProductsAdapter(getContext(), R.layout.item_products, Products.setupDataProducts());
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
        StringBuilder sb = new StringBuilder();
        String title = null;
        switch (mProductsObject.getProductId()) {
            case 1:
                title = "Accidentes Personales";
                sb.append("1. Gastos Médicos").append("\n")
                        .append("2. Incapacidad Premanente").append("\n")
                        .append("3. Muerte accidental(incluye motociclistas");
                break;
            case 2:
                title = "Transporte";
                sb.append("Cobertura de daños materiales a consecuencia de:").append("\n")
                        .append("1. Accidentes").append("\n")
                        .append("2. Vuelvo").append("\n")
                        .append("3. Descarrilamiento").append("\n")
                        .append("4. Derrumbe").append("\n")
                        .append("5. Incendio").append("\n")
                        .append("6. Explosión").append("\n")
                        .append("7. Robo");
                break;
            case 3:
                title = "Mutiriesgo Comercio";
                sb.append("1. Incendio").append("\n")
                        .append("2. Robo de mercaderías/contenido general").append("\n")
                        .append("3. Robo en ventanilla y/o caja fuerte").append("\n")
                        .append("4. Robo de valores en tránsito").append("\n")
                        .append("5. Cristales").append("\n")
                        .append("6. Responsibilidad").append("\n")
                        .append("7. Equipos Electrónicos").append("\n");
                break;
            case 4:
                title = "Multiriesgo Hogar";
                sb.append("1. Incendio").append("\n")
                        .append("2. Robo").append("\n")
                        .append("3. Cristales").append("\n")
                        .append("4. Daños por agua").append("\n")
                        .append("5. Responsabilidad civil").append("\n")
                        .append("6. Asistencia al hogar").append("\n")
                        .append("7. Asistencia jurídica").append("\n");
                break;
            case 5:
                title = "1. Cacucción";
                sb.append("2. Garantía de mantenimiento de oferta").append("\n")
                        .append("3. Fiel cumplimiento de contrato").append("\n")
                        .append("4. Garantía de alquiler");
                break;
            case 6:
                title = "Equipos Electrónicos";
                sb.append("1. Cobertura en caso de").append("\n")
                        .append("2. Incendio").append("\n")
                        .append("3. Extinción de incendios").append("\n")
                        .append("4. Explosión").append("\n")
                        .append("5. Rayos").append("\n")
                        .append("6. Combustión espontánea").append("\n")
                        .append("7. Robos");
                break;
            case 7:
                title = "Vida Colectiva";
                sb.append("1. Seguro de ahorro y aporte, deudores").append("\n")
                        .append("2. Protección de préstamos").append("\n")
                        .append("3. Seguro p/ directivos y empleados");
                break;
            case 8:
                title = "Seguro Agricola";
                sb.append("1. Sequía").append("\n")
                        .append("2. Granizo, helada").append("\n")
                        .append("3. Vientos fuertes").append("\n")
                        .append("4. Inundaciones").append("\n")
                        .append("5. Incendio");
                break;

        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(title);
        builder.setMessage(sb.toString());
        builder.setNegativeButton(getString(R.string.tag_accept), null);
        AlertDialog dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations = animationSource;
        dialog.show();
    }

}