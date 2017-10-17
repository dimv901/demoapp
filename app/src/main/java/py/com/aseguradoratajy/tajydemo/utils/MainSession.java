package py.com.aseguradoratajy.tajydemo.utils;

import android.app.Application;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.io.IOException;

import py.com.aseguradoratajy.tajydemo.entities.DaoMaster;
import py.com.aseguradoratajy.tajydemo.entities.DaoSession;

/**
 * Created by Manu0 on 22/1/2017.
 */

public class MainSession extends Application {
    private DaoSession daoSession;
    private static MainSession INSTANCE = null;
    private RequestQueue mRequestQueue;


    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        setupDatabase();
    }

    private void setupDatabase() {

        boolean hasData = AppPreferences.getAppPreferences(this).getBoolean(AppPreferences.KEY_CREATE_DB, false);
        if (!hasData) {
            DataBaseHelper dh = new DataBaseHelper(this);
            try {
                //create database
                dh.createDataBase();

                //start session in database
                DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "tajybd.sqlite", null);
                SQLiteDatabase db = helper.getWritableDatabase();
                DaoMaster daoMaster = new DaoMaster(db);
                daoSession = daoMaster.newSession();
                //save database status
                AppPreferences.getAppPreferences(this).edit().putBoolean(AppPreferences.KEY_CREATE_DB, true).apply();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //start session in database
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "tajybd.sqlite", null);
            SQLiteDatabase db = helper.getWritableDatabase();
            DaoMaster daoMaster = new DaoMaster(db);
            daoSession = daoMaster.newSession();

            /*DatabaseUpgradeHelper databaseUpgradeHelper = new DatabaseUpgradeHelper(this, "momodb.sqlite", null);
            SQLiteDatabase dbu = databaseUpgradeHelper.getWritableDatabase();
            DaoMaster daoMasteru = new DaoMaster(dbu);
            daoSession = daoMasteru.newSession();
            databaseUpgradeHelper.onUpgrade(dbu, 2, 3);*/
        }
    }

    public static MainSession getInstance() {
        return INSTANCE;
    }

    public static DaoSession getDaoSession() {
        return getInstance().daoSession;
    }


}


