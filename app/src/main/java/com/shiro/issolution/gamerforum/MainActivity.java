package com.shiro.issolution.gamerforum;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.shiro.issolution.gamerforum.fragment.FragmentArticle;
import com.shiro.issolution.gamerforum.fragment.FragmentHome;
import com.shiro.issolution.gamerforum.fragment.FragmentPerson;

import java.util.List;

import cn.bmob.v3.Bmob;


public class MainActivity extends AppCompatActivity {
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private Fragment fh, fa, fp;
    private List<Fragment> lf;
    private long startTime = 0;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            transaction = manager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    hideAll();
                    transaction.show(fh);
                    transaction.commit();
                    return true;
                case R.id.navigation_article:
                    if (fa == null) {
                        fa = new FragmentArticle();
                        transaction.add(R.id.layout_frg,fa);
                    }
                    hideAll();
                    transaction.show(fa);
                    transaction.commit();
                    return true;
                case R.id.navigation_person_details:
                    if (fp == null) {
                        fp = new FragmentPerson();
                        transaction.add(R.id.layout_frg,fp);
                    }
                    hideAll();
                    transaction.show(fp);
                    transaction.commit();
                    return true;
            }
            transaction.commit();
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(((Toolbar) findViewById(R.id.toolbar)));

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //权限检查
        permissionGrand();

        Bmob.initialize(this, "c81c70281118ea87f1d0ee934249cda1");

        fh = new FragmentHome();
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.layout_frg, fh);
        transaction.commit();

    }
    protected void hideAll(){
        lf = manager.getFragments();
        if(lf.contains(fa)){
            transaction.hide(fa);
        }
        if(lf.contains(fh)){
            transaction.hide(fh);
        }
        if(lf.contains(fp)){
            transaction.hide(fp);
        }
    }


    private void permissionGrand() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.INTERNET
            }, 1);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.READ_PHONE_STATE
            }, 1);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.ACCESS_WIFI_STATE
            }, 1);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.ACCESS_NETWORK_STATE
            }, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    finish();
                }
        }
    }

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - startTime) >= 2000) {
            Toast.makeText(MainActivity.this, "在按一次返回键退出", Toast.LENGTH_SHORT).show();
            startTime = currentTime;
        } else {
            finish();
        }
    }


}
