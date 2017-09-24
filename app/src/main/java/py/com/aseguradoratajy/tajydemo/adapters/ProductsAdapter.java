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
import py.com.aseguradoratajy.tajydemo.models.Products;

/**
 * Created by Manu0 on 9/23/2017.
 */

public class ProductsAdapter extends ArrayAdapter {
    private Context context;
    private int layoutResourceId;
    private List<Products> data = new ArrayList();

    public ProductsAdapter(Context context, int layoutResourceId, List<Products> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    public Products getItemAtPosition(int position) {
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
            holder.imageTitle = (TextView) row.findViewById(R.id.product_description);
            holder.image = (ImageView) row.findViewById(R.id.image_product);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }


        int icon = 0;
        Products products = data.get(position);
        switch (products.getProductId()) {
            case 1:
                // accidentes_personales
                icon = R.mipmap.ic_accidentes_personales_resource;
                break;
            case 2:
                // transporte
                icon = R.mipmap.ic_transporte;
                break;
            case 3:
                // multiriesgo_comercio
                icon = R.mipmap.ic_multiriesgo_comercio;
                break;
            case 4:
                // multiriesgo hogares
                icon = R.mipmap.ic_multiriesgo_hogares;
                break;
            case 5:
                // cauccion
                icon = R.mipmap.caucion;
                break;
            case 6:
                // equipos electronicos
                icon = R.mipmap.equipos_electronicos;
                break;
            case 7:
                // vida colectiva
                icon = R.mipmap.vida_colectiva;
                break;
            case 8:
                // seguro agricola
                icon = R.mipmap.seguro_agricola;
                break;

        }


        holder.imageTitle.setText(products.getProductDescription());
        holder.image.setImageResource(icon);
        return row;
    }

    static class ViewHolder {
        TextView imageTitle;
        ImageView image;
    }
}

