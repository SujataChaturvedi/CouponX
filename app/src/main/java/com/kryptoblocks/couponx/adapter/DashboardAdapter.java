package com.kryptoblocks.couponx.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.kryptoblocks.couponx.JoinedClubSplashActivity;
import com.kryptoblocks.couponx.R;
import com.kryptoblocks.couponx.apiInterface.API_Services;
import com.kryptoblocks.couponx.apiInterface.ApiClient;
import com.kryptoblocks.couponx.pojo.DashBoardOutput;
import com.kryptoblocks.couponx.pojo.RedeemInput;
import com.kryptoblocks.couponx.pojo.RedeemOutput;

import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.MyViewHolder> {

    public Context mContext;
    private List<DashBoardOutput> dashBoardOutputList;
    public static String uuid, ccode;
    API_Services services_api;

    public static Boolean redeemval;




    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public  TextView batch_name, discount, code, redeem;
        public  ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            batch_name = view.findViewById(R.id.batch_name_et);
            discount = view.findViewById(R.id.batch_discount_et);
            code = view.findViewById(R.id.batch_coupon_code_et);
            redeem = view.findViewById(R.id.redeem_coupon_et);
            //thumbnail = view.findViewById(R.id.);

        }

        @Override
        public void onClick(View view) {
            String pos = String.valueOf(getAdapterPosition());
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            switch (pos) {
               /* case "0":

                    EatOutFragment eatOutFragment = new EatOutFragment();
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_frame, eatOutFragment)
                            .addToBackStack(null).commit();
                                break; */
            }
        }
    }

    public DashboardAdapter(Context mContext, List<DashBoardOutput> dashBoardOutputs) {
        this.mContext = mContext;
        this.dashBoardOutputList = dashBoardOutputs;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dashboard_card_view, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final DashBoardOutput dash = dashBoardOutputList.get(position);
        System.out.println("Position==========================="+position);
        holder.batch_name.setText(dash.getName());
        holder.discount.setText(dash.getSummary());
        holder.code.setText(dash.getCode());

        // holder.redeem.setText(dash.getRedeem());
        //Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

        holder.redeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uuid = dash.getUuid();
                ccode = dash.getCode();

                    sendPost();
            }
        });
    }

    /* public void setList(List<DashBoardOutput> list){
         this. dashBoardOutputList = list;
       //  mContext.notifyDataSetChanges();
     }*/
    @Override
    public int getItemCount() {

        return dashBoardOutputList.size();
        //return 0;
    }

//tryyyyyyyyyyyyyyyyyyyyyyyy/////////////////

    public void sendPost() {

        //tryyy/////////////

        services_api = ApiClient.getClient().create(API_Services.class);

        RedeemInput redeemInput = new RedeemInput();
        redeemInput.setCode(ccode);
        redeemInput.setUuid(uuid);

        System.out.println("UUID======"+uuid);
        System.out.println("Code======"+ccode);

        System.out.println("Input code======"+ redeemInput.getCode());
        System.out.println("Input uuid======"+redeemInput.getUuid());

        System.out.println("Input======"+redeemInput);


        //Callback<List<RedeemOutput>> tryy = services_api.dashOut(title, body);
        Call<RedeemOutput> call1 = services_api.redeem(redeemInput);

        System.out.println("callll====="+call1);

        call1.enqueue(new Callback<RedeemOutput>() {


            @Override
            public void onResponse(Call<RedeemOutput> call, Response<RedeemOutput> response) {

                if(response.isSuccessful()) {
                    int statusCode = response.code();

                    System.out.println("Code"+statusCode);

                    System.out.println("body"+response.body());

                    if(response.code()==200) {
                        redeemval = response.body().getRedeem();
                        System.out.println("redeem======="+redeemval);


                        Intent i = new Intent(mContext, JoinedClubSplashActivity.class);
                        mContext.startActivity(i);
                        //RedeemedSplashFragment();

                        Log.i(TAG, "post submitted to redeem API." + response);

                        //Toast.makeText(mContext, "Success redeem+++++++++", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Log.i(TAG, "post not submitted to API." + response);
                        Toast.makeText(mContext, "Unsuccess redeem+++++++++", Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<RedeemOutput> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to redeem API.");
                Toast.makeText(mContext, "Failed+++++++++", Toast.LENGTH_LONG).show();
            }
        });



        /*services_api.dashOut(title, body).enqueue(new Callback<RedeemOutput>() {
            @Override
            public void onResponse(Call<RedeemOutput> call, Response<RedeemOutput> response) {

                if(response.isSuccessful()) {
                   // showResponse(response.body().toString());
                    Log.i(TAG, "post submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<RedeemOutput> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }
        });*/
    }

    }

