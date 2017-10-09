package py.com.aseguradoratajy.tajydemo.utils;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import py.com.aseguradoratajy.tajydemo.entities.DaoMaster;
import py.com.aseguradoratajy.tajydemo.entities.DaoSession;

/**
 * Created by Manu0 on 22/1/2017.
 */

public class MainSession extends Application {
    private static MainSession INSTANCE = null;
    public DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        setupDatabase();
    }

    private void setupDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "tajydb", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public static MainSession getInstance(){
        return INSTANCE;
    }
    public static  DaoSession getDaoSession() {
        return getInstance().daoSession;
    }
}

