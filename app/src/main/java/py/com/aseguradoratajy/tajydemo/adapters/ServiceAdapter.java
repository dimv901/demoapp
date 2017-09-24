package py.com.aseguradoratajy.tajydemo.adapters;

import android.app.Activity;
import android.app.Service;
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

/**
 * Created by Manu0 on 9/23/2017.
 */

public class ServiceAdapter extends ArrayAdapter {
    private Context context;
    private int layoutResourceId;
    private List<Services> data = new ArrayList();

    public ServiceAdapter(Context context, int layoutResourceId, List<Services> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    public Services getItemAtPosition(int position) {
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
            holder.imageTitle = (TextView) row.findViewById(R.id.services_description);
            holder.image = (ImageView) row.findViewById(R.id.image_services);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }


        int icon = 0;
        Services services = data.get(position);
        switch (services.getServiceId()) {
            case 1:
                // asistencia al hogar
                icon = R.mipmap.asistencia_al_hogar;
                break;
            case 2:
                // asistencia legal
                icon = R.mipmap.asistencia_legal;
                break;
            case 3:
                // asistencia vehicular
                icon = R.mipmap.asistencia_vehicular;
                break;
            case 4:
                // asistencia al viajero
                icon = R.mipmap.asistencia_al_viajero;
                break;
            case 5:
                // red de pagos
                icon = R.mipmap.red_de_pagos;
                break;
            case 6:
                // carta verde
                icon = R.mipmap.carta_verde;
                break;
        }


        holder.imageTitle.setText(services.getServiceDescription());
        holder.image.setImageResource(icon);
        return row;
    }

    static class ViewHolder {
        TextView imageTitle;
        ImageView image;
    }
}

