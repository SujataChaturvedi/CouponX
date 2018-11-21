package com.kryptoblocks.couponx;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import static com.kryptoblocks.couponx.adapter.DashboardAdapter.ccode;

public class JoinedClubSplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4000;
    ImageView tick_img;
    TextView code_coup;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joined_club_splash);

        code_coup = findViewById(R.id.coupon_code_tv);
        System.out.print("code at redeem activity======="+ccode);
        code_coup.setText(ccode);
        tick_img = findViewById(R.id.image_tick);

        //move(view);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(JoinedClubSplashActivity.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);



    }

    public void move(View view)
    {

        Animation anim_image = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.moving_text_anim);
        tick_img.startAnimation(anim_image);
    }
}
