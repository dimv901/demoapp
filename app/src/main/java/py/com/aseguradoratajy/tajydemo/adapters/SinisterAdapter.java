package py.com.aseguradoratajy.tajydemo.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import py.com.aseguradoratajy.tajydemo.R;
import py.com.aseguradoratajy.tajydemo.models.Services;
import py.com.aseguradoratajy.tajydemo.models.Sinisters;

/**
 * Created by Manu0 on 9/25/2017.
 */

public class SinisterAdapter extends ArrayAdapter {
    private Context context;
    private int layoutResourceId;
    private List<Sinisters> data = new ArrayList();

    public SinisterAdapter(Context context, int layoutResourceId, List<Sinisters> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    public Sinisters getItemAtPosition(int position) {
        return data.get(position);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.imageTitle = (TextView) row.findViewById(R.id.sinister_description);
            holder.image = (ImageView) row.findViewById(R.id.image_sinister);
            holder.numberDescription = (TextView) row.findViewById(R.id.sinister_call_number);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }


        int icon = 0;
        Sinisters sinisters = data.get(position);
        switch (sinisters.getSinisterId()) {
            case 1:
                // policia
                icon = R.mipmap.ic_police;
                break;
            case 2:
                // incendio
                icon = R.mipmap.ic_fire_man;
                break;
            case 3:
                // call center
                icon = R.mipmap.ic_call_support;
                break;
            case 4:
                // Ambulacia
                icon = R.mipmap.ic_medical;
                break;
        }


        holder.imageTitle.setText(sinisters.getDescription());
        holder.image.setImageResource(icon);
        holder.numberDescription.setText(sinisters.getNumberPhone());
        return row;
    }

    static class ViewHolder {
        TextView imageTitle;
        ImageView image;
        TextView numberDescription;
    }
}


