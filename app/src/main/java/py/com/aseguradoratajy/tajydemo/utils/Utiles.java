package py.com.aseguradoratajy.tajydemo.utils;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import py.com.aseguradoratajy.tajydemo.R;

/**
 * Created by Diego on 9/23/2017.
 */

public class Utiles {

    public static void getSnackBar(CoordinatorLayout coordinatorLayout, String message) {
        final Snackbar snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setMaxLines(3);
        final View.OnClickListener clickListener = new View.OnClickListener() {
            public void onClick(View v) {
                snackbar.dismiss();
            }
        };
        snackbar.setAction(R.string.prompt_close, clickListener);
        snackbar.show();
    }

    public static String formatNumber(String number) {
        int data = 0;
        try {
            data = Integer.parseInt(number);
        } catch (Exception e) {
            e.printStackTrace();
            //Fail in silence
        }
        NumberFormat nf = NumberFormat.getNumberInstance();
        DecimalFormat df = (DecimalFormat) nf;
        df.applyPattern("###,###.###");
        String output = df.format(data);
        return output;
    }

    public static void getToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static DefaultRetryPolicy getRetryPolicy() {
        int TIME_OUT_MS = 15000;


        return new DefaultRetryPolicy(
                TIME_OUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, // 0 Max retries
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    }

    public static String volleyErrorHandler(Object err, Context context) {
        VolleyError error = (VolleyError) err;
        NetworkResponse response = error.networkResponse;
        String message = "No message";
        if (response == null) {
            if (error instanceof NetworkError) {
                //message = (error.getMessage() == null) ? context.getResources().getString(R.string.volley_network_error) : error.getMessage();
                message = context.getResources().getString(R.string.volley_network_error);
            } else if (error instanceof ServerError) {
                //message = (error.getMessage() == null) ? context.getResources().getString(R.string.volley_server_error) : error.getMessage();
                message = context.getResources().getString(R.string.volley_server_error);
            } else if (error instanceof AuthFailureError) {
                //message = (error.getMessage() == null) ? context.getResources().getString(R.string.volley_auth_error) : error.getMessage();
                message = context.getResources().getString(R.string.volley_auth_error);
            } else if (error instanceof ParseError) {
                //message = (error.getMessage() == null) ? context.getResources().getString(R.string.volley_parse_error) : error.getMessage();
                message = context.getResources().getString(R.string.volley_parse_error);
            } else if (error instanceof NoConnectionError) {
                //message = (error.getMessage() == null) ? context.getResources().getString(R.string.volley_no_connection_error) : error.getMessage();
                message = context.getResources().getString(R.string.volley_no_connection_error);
            } else if (error instanceof TimeoutError) {
                //message = (error.getMessage() == null) ? context.getResources().getString(R.string.volley_time_out_error) : error.getMessage();
                message = context.getResources().getString(R.string.volley_time_out_error);
            }
        } else {
            message = new String(response.data);
            Log.d("TAG","RESPOSNSE_MESSAGE: "+message);
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(new String(response.data));
                if (jsonObject.has("desRetorno")) {
                    message = jsonObject.getString("desRetorno");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        return message;
    }

}
