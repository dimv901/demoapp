package py.com.aseguradoratajy.tajydemo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import py.com.aseguradoratajy.tajydemo.R;
import py.com.aseguradoratajy.tajydemo.entities.VehiclesModel;

/**
 * Created by Manu0 on 10/12/2017.
 */

public class VehiclesModelAdapter extends ArrayAdapter<VehiclesModel> {

    private List<VehiclesModel> mVehicleasModelList = new ArrayList<>();
    private Context mContext;
    private int layoutResource;

    public VehiclesModelAdapter(Context context, int resource, List<VehiclesModel> data) {
        super(context, resource);
        mVehicleasModelList = data;
        mContext = context;
        layoutResource = resource;
    }

    @Nullable
    @Override
    public VehiclesModel getItem(int position) {
        return mVehicleasModelList.get(position);
    }


    @Override
    public int getCount() {
        return mVehicleasModelList.size();
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VehiclesModelAdapter.ViewHolder viewHolder = new VehiclesModelAdapter.ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(layoutResource, parent, false);
            // Configure the view holder
            viewHolder.mCommerceTypeDescription = (TextView) convertView.findViewById(R.id.description);
            convertView.setTag(viewHolder);
        } else {
            // Fill data from the recycled view holder
            viewHolder = (VehiclesModelAdapter.ViewHolder) convertView.getTag();
        }
        VehiclesModel vehiclesModels = mVehicleasModelList.get(position);
        viewHolder.mCommerceTypeDescription.setText(vehiclesModels.getDescription().toUpperCase());
        return convertView;
    }


    private static class ViewHolder {
        TextView mCommerceTypeDescription;

    }
}
