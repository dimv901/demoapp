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
import py.com.aseguradoratajy.tajydemo.models.VehicleBrand;

/**
 * Created by Manu0 on 10/12/2017.
 */

public class VehiclesBardAdapter extends ArrayAdapter<VehicleBrand> {

    private List<VehicleBrand> mVehicleasBardList = new ArrayList<>();
    private Context mContext;
    private int layoutResource;

    public VehiclesBardAdapter(Context context, int resource, List<VehicleBrand> data) {
        super(context, resource);
        mVehicleasBardList = data;
        mContext = context;
        layoutResource = resource;
    }

    @Nullable
    @Override
    public VehicleBrand getItem(int position) {
        return mVehicleasBardList.get(position);
    }

    @Override
    public int getPosition(VehicleBrand item) {
        return mVehicleasBardList.indexOf(item);
    }

    @Override
    public int getCount() {
        return mVehicleasBardList.size();
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VehiclesBardAdapter.ViewHolder viewHolder = new VehiclesBardAdapter.ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(layoutResource, parent, false);
            // Configure the view holder
            viewHolder.mCommerceTypeDescription = (TextView) convertView.findViewById(R.id.description);
            convertView.setTag(viewHolder);
        } else {
            // Fill data from the recycled view holder
            viewHolder = (VehiclesBardAdapter.ViewHolder) convertView.getTag();
        }
        VehicleBrand vehicleBrand = mVehicleasBardList.get(position);
        viewHolder.mCommerceTypeDescription.setText(vehicleBrand.getDescription().toUpperCase());
        return convertView;
    }


    private static class ViewHolder {
        TextView mCommerceTypeDescription;

    }
}
