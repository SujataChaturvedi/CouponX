package com.kryptoblocks.couponx.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.kryptoblocks.couponx.R;
import com.kryptoblocks.couponx.adapter.DashboardAdapter;
import com.kryptoblocks.couponx.apiInterface.API_Services;
import com.kryptoblocks.couponx.apiInterface.ApiClient;
import com.kryptoblocks.couponx.pojo.DashBoardOutput;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.kryptoblocks.couponx.SocialLogInActivity.email_id_user;
import static com.kryptoblocks.couponx.adapter.DashboardAdapter.redeemval;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {

    RecyclerView recycle;
    // Retrofit retrofit;
    List<DashBoardOutput> examplesPojo;
    RecyclerView.Adapter adapter;
    TextView redeem_text;

    static private String uuid, code;

    public DashboardFragment() {
        // Required empty public constructor
    }

    private void RedeemedSplashFragment() {

        RedeemedSplashFragment dashboardFragment = new RedeemedSplashFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // fragmentTransaction.setCustomAnimations(R.anim.enter_anim, R.anim.exit_anim);
        fragmentTransaction.replace(R.id.frame_content, dashboardFragment);
        fragmentTransaction.commit();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        recycle = view.findViewById(R.id.recycle_dashboard);
        redeem_text = view.findViewById(R.id.redeem_coupon_et);

       /* if(redeemval) {
            redeem_text.setVisibility(View.INVISIBLE);
        }*/

        API_Services apiService =
                ApiClient.getClient().create(API_Services.class);
        //Api_Service api_service = retrofit.create(Api_Service.class);
        Call<List<DashBoardOutput>> call = apiService.getValues(email_id_user);

        call.enqueue(new Callback<List<DashBoardOutput>>() {
            @Override
            public void onResponse(Call<List<DashBoardOutput>> call, Response<List<DashBoardOutput>> response) {

                //new Gson().fromJson(response.message(), DashBoardOutput.class);
                System.out.println("Response+++++++++++"+response.body());
                examplesPojo = response.body();
                adapter = new DashboardAdapter(getContext(),examplesPojo);
                recycle.setAdapter(adapter);
                recycle.setHasFixedSize(false);
                recycle.setNestedScrollingEnabled(false);
                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
                recycle.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
              //  Toast.makeText(getContext(), "Success dashboard+++++++++", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<DashBoardOutput>> call, Throwable t) {
                System.out.println("++++++++++++Onfailure of dashboard+++++++++++");
                t.printStackTrace();
                Toast.makeText(getContext(), "Failed dashboard+++++++++", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

}
