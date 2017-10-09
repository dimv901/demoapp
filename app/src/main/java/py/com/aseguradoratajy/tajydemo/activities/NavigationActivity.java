package py.com.aseguradoratajy.tajydemo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import py.com.aseguradoratajy.tajydemo.R;
import py.com.aseguradoratajy.tajydemo.entities.Products;
import py.com.aseguradoratajy.tajydemo.fragments.BranchFragment;
import py.com.aseguradoratajy.tajydemo.fragments.ContactSupportFragment;
import py.com.aseguradoratajy.tajydemo.fragments.LoginAccountFragment;
import py.com.aseguradoratajy.tajydemo.fragments.ProductsFragments;
import py.com.aseguradoratajy.tajydemo.fragments.ReportSinisterFragment;
import py.com.aseguradoratajy.tajydemo.models.Sinisters;
import py.com.aseguradoratajy.tajydemo.utils.AppPreferences;
import py.com.aseguradoratajy.tajydemo.utils.ViewPagerAdapter;

public class NavigationActivity extends AppCompatActivity implements
        ProductsFragments.OnItemProductsListenerSelected,
        ReportSinisterFragment.OnItemSinisterListenerSelected,
        ContactSupportFragment.OnItemContactSupportListenerSelected {

    private Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    private CoordinatorLayout mCoordinatorLayoutView;
    private ViewPager mViewPager;
    private MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        checkInSession();
        mCoordinatorLayoutView = (CoordinatorLayout) findViewById(R.id.main_coordinator_layout);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        //Initializing the bottomNavigationView
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_account:
                                mViewPager.setCurrentItem(0);
                                break;
                            case R.id.action_product:
                                mViewPager.setCurrentItem(1);
                                break;
                            case R.id.action_branches:
                                mViewPager.setCurrentItem(2);
                                break;
                            case R.id.action_contact:
                                mViewPager.setCurrentItem(3);
                                break;
                            case R.id.action_report_sinister:
                                mViewPager.setCurrentItem(4);
                                break;
                        }
                        return false;
                    }
                });
        setupViewPager(mViewPager);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private void checkInSession(){
        boolean isLogged = AppPreferences.getAppPreferences(this).getBoolean(AppPreferences.KEY_LOGGED_IN, false);
        if (isLogged) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        } else {
            AppPreferences.getAppPreferences(this).edit().clear().apply();
        }

    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(LoginAccountFragment.newInstance());
        adapter.addFrag(ProductsFragments.newInstance());
        adapter.addFrag(BranchFragment.newInstance());
        adapter.addFrag(ContactSupportFragment.newInstance());
        adapter.addFrag(ReportSinisterFragment.newInstance());
        viewPager.setAdapter(adapter);
    }


    @Override
    public void onItemProductsListenerSelected(Products products) {

    }


    @Override
    public void onItemContactSupportListenerSelected() {

    }

    @Override
    public void onItemSinisterListenerSelected(Sinisters sinisters) {

    }
}
