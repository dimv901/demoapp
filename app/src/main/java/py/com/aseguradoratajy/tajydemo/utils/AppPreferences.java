package py.com.aseguradoratajy.tajydemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Diego on 9/20/2017.
 */

public class AppPreferences {

    private static SharedPreferences appPreferences;
    public static final String APP_PREFERENCES = "APP_PREFERENCES";
    public static final String KEY_LOGGED_IN = "KEY_LOGGED_IN";
    public static final String KEY_USERNAME = "KEY_USERNAME";
    public static final String KEY_PASSWORD = "KEY_PASSWORD";
    public static final String KEY_CREATE_DB = "KEY_CREATE_DB";

    public static SharedPreferences getAppPreferences(Context context) {
        if (appPreferences == null) {
            appPreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        }
        return appPreferences;
    }
}
