package com.kryptoblocks.couponx.apiInterface;

import com.kryptoblocks.couponx.pojo.DashBoardOutput;
import com.kryptoblocks.couponx.pojo.DataFB;
import com.kryptoblocks.couponx.pojo.FBSignInInput;
import com.kryptoblocks.couponx.pojo.FbSignInOut;
import com.kryptoblocks.couponx.pojo.GoogleSignInInput;
import com.kryptoblocks.couponx.pojo.GoogleSingInOutt;
import com.kryptoblocks.couponx.pojo.RedeemInput;
import com.kryptoblocks.couponx.pojo.RedeemOutput;
import com.kryptoblocks.couponx.pojo.RefreshTokenData;
import com.kryptoblocks.couponx.pojo.RefreshTokenInput;
import com.kryptoblocks.couponx.pojo.RefreshTokenOutput;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import static com.kryptoblocks.couponx.SocialLogInActivity.email_id_user;

public interface API_Services {


    @POST("auth/login/native/google")
    Call<GoogleSingInOutt> googleOut(@Body GoogleSignInInput googleSignInInput);

    @POST("auth/login/native/facebook")
    Call<FbSignInOut> facebookOut(@Body FBSignInInput fbSignInInput);

    @POST("auth/refresh-token")
    Call<RefreshTokenData> refreshOut(@Body RefreshTokenInput refreshTokenInput);


    @GET("portfolio/getMyCoupon/{email_id_user}")
    Call<List<DashBoardOutput>> getValues(@Path("email_id_user") String email_id_user);

    @POST("/portfolio/redeemCoupon/")
    Call<RedeemOutput> redeem(@Body RedeemInput redeemInput);

}
