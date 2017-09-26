package py.com.aseguradoratajy.tajydemo.utils;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
}
