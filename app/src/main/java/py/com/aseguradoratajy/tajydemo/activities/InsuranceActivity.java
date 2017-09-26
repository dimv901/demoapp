package py.com.aseguradoratajy.tajydemo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import py.com.aseguradoratajy.tajydemo.R;

public class InsuranceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView mInsuranceRecyclerView = (RecyclerView) findViewById(R.id.insurance_list);
        mInsuranceRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mInsuranceRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));
        mInsuranceRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mInsuranceRecyclerView.setHasFixedSize(true);

    }


}
