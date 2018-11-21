package com.kryptoblocks.couponx;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.kryptoblocks.couponx.fragment.DashboardFragment;

public class MainActivity extends AppCompatActivity {

    Button btn;

    private void DashboardFragment() {

        DashboardFragment dashboardFragment = new DashboardFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
       // fragmentTransaction.setCustomAnimations(R.anim.enter_anim, R.anim.exit_anim);
        fragmentTransaction.replace(R.id.frame_content, dashboardFragment);
        fragmentTransaction.commit();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DashboardFragment();

    }
}
