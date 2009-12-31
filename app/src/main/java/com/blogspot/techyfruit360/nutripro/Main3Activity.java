package com.blogspot.techyfruit360.nutripro;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
private TabLayout tabLayout;
private AppBarLayout appBarLayout;
private ViewPager viewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        tabLayout =(TabLayout)findViewById(R.id.tablayou);
        viewPager=(ViewPager)findViewById(R.id.viewpager_id);
        appBarLayout=(AppBarLayout)findViewById(R.id.appbarid);


        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager());

        //addinf fragments
        adapter.AddFragment(new FragmentWheat(),"wheat");
        adapter.AddFragment(new FragmentRice(),"Rice");
        adapter.AddFragment(new FragmentMaize(),"Maize");

        //adapter setup
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition())
        {
            case 0:
                tabLayout.getTabAt(0);
                ImageView imageView=(ImageView)findViewById(R.id.far);
                imageView.setImageResource(R.drawable.wheat);
                break;
            case 1:
                tabLayout.getTabAt(1);
                ImageView imageView1=(ImageView)findViewById(R.id.far);
                imageView1.setImageResource(R.drawable.rice);
                break;
            case 2:
                tabLayout.getTabAt(2);
                ImageView imageView2=(ImageView)findViewById(R.id.far);
                imageView2.setImageResource(R.drawable.maize);
                break;
            default:

                ImageView imageView3=(ImageView)findViewById(R.id.far);
                imageView3.setImageResource(R.drawable.wheat);
                break;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
});

        }
    }

