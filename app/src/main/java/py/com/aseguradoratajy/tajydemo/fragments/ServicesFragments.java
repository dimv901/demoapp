package py.com.aseguradoratajy.tajydemo.fragments;

import android.app.Service;
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
import py.com.aseguradoratajy.tajydemo.adapters.ServiceAdapter;
import py.com.aseguradoratajy.tajydemo.models.Services;

/**
 * Created by Manu0 on 9/23/2017.
 */

public class ServicesFragments extends Fragment {

    private OnItemServicesListenerSelected mListener;
    // VIEW
    private GridView gridView;
    private View rootView;

    // ADAPTERS
    private ServiceAdapter mServiceAdapter;

    public ServicesFragments() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TransactionFragment.
     */

    public static ServicesFragments newInstance() {
        ServicesFragments fragment = new ServicesFragments();
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
        rootView = inflater.inflate(R.layout.services_fragment, container, false);
        gridView = (GridView) rootView.findViewById(R.id.gridView);
        mServiceAdapter = new ServiceAdapter(getContext(), R.layout.item_services, Services.setupServiceList());
        gridView.setAdapter(mServiceAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                buildDialog(R.style.DialogAnimation_2, mServiceAdapter.getItemAtPosition(position));
            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemServicesListenerSelected) {
            mListener = (OnItemServicesListenerSelected) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnItemServicesListenerSelected");
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
    public interface OnItemServicesListenerSelected {
        // TODO: Update argument type and name
        void onItemServicesListenerSelected(Service service);
    }

    private void buildDialog(int animationSource, Services mServicesObject) {
        StringBuilder sb = new StringBuilder();
        String title = null;
        switch (mServicesObject.getServiceId()) {
            case 1:
                title = "Accidentes al Hogar";
                sb.append("Servicios ofrecidos para el Hogar:")
                        .append("1. Plomería").append("\n")
                        .append("2. Electricidad").append("\n")
                        .append("3. Cerrajería").append("\n")
                        .append("4. Vidriería").append("\n")
                        .append("5. Gastos por inhabitabilidad de la vivienda familiar").append("\n")
                        .append("6. Gastos de Servicio Doméstico por Accidentes").append("\n")
                        .append("7. Transmisión de mensajes urgentes").append("\n")
                        .append("8. Con una póliza de hogar de Tajy accedés a los servicios exclusivos con los que cuentan solo los asegurados de Tajy.");
                break;
            case 2:
                title = "Asistencia Legal";
                sb.append("Coberturas: ").append("\n")
                        .append("El servicio de asistencia legal de Tajy funciona las 24 horas y es rápido por un plantel de profesionales abogados con cobertura legal en todo el territorio nacional.");
                break;
            case 3:
                title = "Asistencia Vehicular";
                sb.append("Coberturas").append("\n")
                        .append("1. Accidente").append("\n")
                        .append("2. Avería (cambio de neumáticos y problemas de batería)").append("\n")
                        .append("3. Vehículo atrapado (en zanja, arroyo, barro").append("\n")
                        .append("4. Mecánica ligera").append("\n")
                        .append("5. Remolque de vehiculo").append("\n")
                        .append("6. Rescate del vehículo").append("\n")
                        .append("7. Estadía en hotel por inmovilizacion o robo del vechículo en viaje").append("\n")
                        .append("8. Desplazamiento de los asegurados hasta el domicilio por inmovilización o robo del vehículo en viaje").append("\n")
                        .append("9. Traslado en ambulancia de heridos en accidente de tránsito").append("\n")
                        .append("10. Orientación jurídica en caso de accidente de tránsito").append("\n");
                break;
            case 4:
                title = "Asistencia al viajero";
                sb.append("Coberturas").append("\n")
                        .append("1. Gastos médicos de urgencia").append("\n")
                        .append("2. Gastos odontológicos de urgencia").append("\n")
                        .append("3. Transporte y repatriación en caso de lesiones o enfermedad").append("\n")
                        .append("4. Prolongación de la estadía del asegurado en el extranjero por lesión o enfermedad").append("\n")
                        .append("5. Desplazamiento y estancia de un familiar del asegurado").append("\n")
                        .append("6. Regreso de acompañantes del asegurado").append("\n")
                        .append("7. Repatriación de menores acompañantes del asegurado").append("\n")
                        .append("8. Transporte o repatriación del asegurado fallecido o de los demás acompañantes del asegurados").append("\n")
                        .append("9. Desplazamiento urgente del asegurado por siniestro en el domicilio").append("\n")
                        .append("10. Trasmisión de mensajes urgentes").append("\n")
                        .append("11. Envío de medicamentos fuera de la República del Paraguay").append("\n")
                        .append("12. Localización y transporte de los equipajes y efectos personales").append("\n")
                        .append("13. Extravío de equipaje");
                break;
            case 5:
                title = "Red de pagos";
                sb.append("Podés realizar los pagos de tu seguro de Tajy, más rápido, fácil y seguro en:");
                break;
            case 6:
                title = "Carta Verde";
                sb.append("Con tu seguro de Automóvil de Tajy, accedés sin costo a tu Carta Verde, y si no la encontrás al momento de viajar realizamos su impresión y entrega o envío por medio digital o fax de la misma.");
                break;


        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(title);
        builder.setMessage(sb.toString());
        builder.setNegativeButton(getString(R.string.tag_accept), null);
        if (mServicesObject.getServiceId() == 5) {
            builder.setView(R.layout.payment_network_layout);
        }
        AlertDialog dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations = animationSource;
        dialog.show();
    }

}