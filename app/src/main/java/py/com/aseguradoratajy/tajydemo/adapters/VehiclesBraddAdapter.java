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
import py.com.aseguradoratajy.tajydemo.entities.Marks;

/**
 * Created by Manu0 on 10/12/2017.
 */

public class VehiclesBraddAdapter extends ArrayAdapter<Marks> {

    private List<Marks> mMarksItem = new ArrayList<>();
    private Context mContext;
    private int layoutResource;

    public VehiclesBraddAdapter(Context context, int resource, List<Marks> data) {
        super(context, resource);
        mMarksItem = data;
        mContext = context;
        layoutResource = resource;
    }

    @Nullable
    @Override
    public Marks getItem(int position) {
        return mMarksItem.get(position);
    }

    @Override
    public int getPosition(Marks item) {
        return mMarksItem.indexOf(item);
    }

    @Override
    public int getCount() {
        return mMarksItem.size();
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VehiclesBraddAdapter.ViewHolder viewHolder = new VehiclesBraddAdapter.ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(layoutResource, parent, false);
            // Configure the view holder
            viewHolder.mCommerceTypeDescription = (TextView) convertView.findViewById(R.id.description);
            convertView.setTag(viewHolder);
        } else {
            // Fill data from the recycled view holder
            viewHolder = (VehiclesBraddAdapter.ViewHolder) convertView.getTag();
        }
        Marks marks = mMarksItem.get(position);
        viewHolder.mCommerceTypeDescription.setText(marks.getDescription().toUpperCase());
        return convertView;
    }


    private static class ViewHolder {
        TextView mCommerceTypeDescription;

    }
}
