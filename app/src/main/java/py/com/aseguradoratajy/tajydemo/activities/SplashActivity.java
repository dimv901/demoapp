package py.com.aseguradoratajy.tajydemo.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import py.com.aseguradoratajy.tajydemo.R;
import py.com.aseguradoratajy.tajydemo.repositories.ProductsRepository;
import py.com.aseguradoratajy.tajydemo.utils.AppPreferences;
import py.com.aseguradoratajy.tajydemo.utils.SetupInitialData;

public class SplashActivity extends AppCompatActivity {

    private static final long DELAY = 3000;
    private boolean scheduled = false;
    private Timer splashTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        splashTimer = new Timer();
        if (ProductsRepository.getDao().count() == 0) {
            SetupInitialData.insertData();
        }
        splashTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                SplashActivity.this.finish();
                checkInSession();
            }
        }, DELAY);
        scheduled = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (scheduled)
            splashTimer.cancel();
        splashTimer.purge();
    }

    private void checkInSession() {
        boolean isLogged = AppPreferences.getAppPreferences(this).getBoolean(AppPreferences.KEY_LOGGED_IN, false);
        if (isLogged) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        } else {
            AppPreferences.getAppPreferences(this).edit().clear().apply();
        }

    }
}
