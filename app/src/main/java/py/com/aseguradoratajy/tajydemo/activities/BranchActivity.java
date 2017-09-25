package py.com.aseguradoratajy.tajydemo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import py.com.aseguradoratajy.tajydemo.R;
import py.com.aseguradoratajy.tajydemo.adapters.BranchAdapter;
import py.com.aseguradoratajy.tajydemo.models.Branches;

public class BranchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RecyclerView mBranchRecycler = (RecyclerView) findViewById(R.id.branch_list);
        mBranchRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mBranchRecycler.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));
        mBranchRecycler.setItemAnimator(new DefaultItemAnimator());
        mBranchRecycler.setHasFixedSize(true);
        BranchAdapter branchAdapter = new BranchAdapter(Branches.getInstance(), getApplicationContext());
        mBranchRecycler.setAdapter(branchAdapter);
    }

}
