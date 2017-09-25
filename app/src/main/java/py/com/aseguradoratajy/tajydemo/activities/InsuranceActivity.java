package py.com.aseguradoratajy.tajydemo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.ArrayList;
import java.util.List;

import py.com.aseguradoratajy.tajydemo.R;
import py.com.aseguradoratajy.tajydemo.adapters.InsuranceAdapter;
import py.com.aseguradoratajy.tajydemo.models.Insurance;

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
        final InsuranceAdapter insuranceAdapter = new InsuranceAdapter(getApplicationContext(), load());
        mInsuranceRecyclerView.setAdapter(insuranceAdapter);

    }

    private List<ParentListItem> load() {
        List<ParentListItem> parentListItems = new ArrayList<>();
        Insurance mInsurance = Insurance.getInstance();
        parentListItems.add(mInsurance);

        return parentListItems;
    }

}
