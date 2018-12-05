package com.example.leekexi.myapplication;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private  FirstFragment firstFragment;
    private PhotoFragment photoFragment;
    private MeFragment meFragment;
    private Fragment[] fragments;
    private int lastfragment;//用于记录上个选择的Fragment


    private Menu mMenu;//顶部菜单

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if(lastfragment != 0)
                    {
                        lastfragment=0;
                        setTitle(R.string.app_name);
                        switchFragment(lastfragment,0);

                        mMenu.findItem(R.id.add_print).setVisible(true);
                        mMenu.findItem(R.id.search_print).setVisible(true);
                        mMenu.findItem(R.id.photo_list).setVisible(false);
                        mMenu.findItem(R.id.user_set).setVisible(false);
                    }
                    return true;
                case R.id.navigation_dashboard:
                    if(lastfragment != 1)
                    {
                        lastfragment=1;
                        setTitle(R.string.title_dashboard);
                        switchFragment(lastfragment,1);

                        mMenu.findItem(R.id.add_print).setVisible(true);
                        mMenu.findItem(R.id.search_print).setVisible(false);
                        mMenu.findItem(R.id.photo_list).setVisible(true);
                        mMenu.findItem(R.id.user_set).setVisible(false);
                    }
                    return true;
                case R.id.navigation_notifications:
                    if(lastfragment != 2)
                    {
                        lastfragment=2;
                        setTitle(R.string.title_notifications);
                        switchFragment(lastfragment,2);

                        mMenu.findItem(R.id.add_print).setVisible(false);
                        mMenu.findItem(R.id.search_print).setVisible(false);
                        mMenu.findItem(R.id.photo_list).setVisible(false);
                        mMenu.findItem(R.id.user_set).setVisible(true);
                    }
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();


    }


    //初始化fragment和fragment数组
    private void initFragment() {

        firstFragment = new FirstFragment();
        photoFragment = new PhotoFragment();
        meFragment = new MeFragment();

        fragments = new Fragment[]{firstFragment,photoFragment,meFragment};

        lastfragment = 0;

        getSupportFragmentManager().beginTransaction().replace(R.id.mainview, firstFragment).show(firstFragment).commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    //切换Fragment
    private void switchFragment(int lastfragment,int index)
    {
        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastfragment]);//隐藏上个Fragment
        if(fragments[index].isAdded()==false)
        {
            transaction.add(R.id.mainview, fragments[index]);

        }
        transaction.show(fragments[index]).commitAllowingStateLoss();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        mMenu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.first_top_menu, menu);

        mMenu.findItem(R.id.add_print).setVisible(true);
        mMenu.findItem(R.id.search_print).setVisible(true);
        mMenu.findItem(R.id.photo_list).setVisible(false);
        mMenu.findItem(R.id.user_set).setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.add_print://监听菜单按钮
                Toast.makeText(this,"add_print", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
