package py.com.aseguradoratajy.tajydemo.activities;

import android.app.Service;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import py.com.aseguradoratajy.tajydemo.R;
import py.com.aseguradoratajy.tajydemo.fragments.ContactSupportFragment;
import py.com.aseguradoratajy.tajydemo.fragments.LoginAccountFragment;
import py.com.aseguradoratajy.tajydemo.fragments.ProductsFragments;
import py.com.aseguradoratajy.tajydemo.fragments.ReportSinisterFragment;
import py.com.aseguradoratajy.tajydemo.fragments.ServicesFragments;
import py.com.aseguradoratajy.tajydemo.models.Products;
import py.com.aseguradoratajy.tajydemo.utiles.ViewPagerAdapter;

public class ManagementDataActivity extends AppCompatActivity implements
        ProductsFragments.OnItemProductsListenerSelected,
        ServicesFragments.OnItemServicesListenerSelected,
        LoginAccountFragment.OnItemAccountListenerSelected,
        ContactSupportFragment.OnItemContactSupportListenerSelected,
        ReportSinisterFragment.OnItemSinisterListenerSelected{

    private Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    private CoordinatorLayout mCoordinatorLayoutView;
    private ViewPager mViewPager;
    private MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_data);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
                            case R.id.action_services:
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


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(ProductsFragments.newInstance());
        adapter.addFrag(ServicesFragments.newInstance());
        adapter.addFrag(LoginAccountFragment.newInstance());
        adapter.addFrag(ContactSupportFragment.newInstance());
        adapter.addFrag(ReportSinisterFragment.newInstance());
        viewPager.setAdapter(adapter);
    }


    @Override
    public void onItemProductsListenerSelected(Products products) {

    }

    @Override
    public void onItemServicesListenerSelected(Service service) {

    }

    @Override
    public void onItemAccountListenerSelected() {

    }

    @Override
    public void onItemContactSupportListenerSelected() {

    }

    @Override
    public void onItemSinisterListenerSelected(Products products) {

    }
}
