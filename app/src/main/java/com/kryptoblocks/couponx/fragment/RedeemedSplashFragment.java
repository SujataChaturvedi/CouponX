package com.kryptoblocks.couponx.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.kryptoblocks.couponx.JoinedClubSplashActivity;
import com.kryptoblocks.couponx.MainActivity;
import com.kryptoblocks.couponx.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RedeemedSplashFragment extends Fragment {

    private static int SPLASH_TIME_OUT = 3000;
    ImageView tick_img;
    View view;


    public RedeemedSplashFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_redeemed_splash, container, false);

        tick_img = view.findViewById(R.id.image_tick);

        //move(view);

        /*new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(getContext(), MainActivity.class);
                startActivity(i);

                // close this activity
               finish();
            }
        }, SPLASH_TIME_OUT);
*/
        return view;
    }

    public void move(View view)
    {

        Animation anim_image = AnimationUtils.loadAnimation(getContext(),R.anim.moving_text_anim);
        tick_img.startAnimation(anim_image);
    }
}
