package com.kryptoblocks.couponx;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.kryptoblocks.couponx.apiInterface.API_Services;
import com.kryptoblocks.couponx.apiInterface.ApiClient;
import com.kryptoblocks.couponx.pojo.DataFB;
import com.kryptoblocks.couponx.pojo.DataGmail;
import com.kryptoblocks.couponx.pojo.FBSignInInput;
import com.kryptoblocks.couponx.pojo.FbSignInOut;
import com.kryptoblocks.couponx.pojo.GoogleSignInInput;
import com.kryptoblocks.couponx.pojo.GoogleSingInOutt;
import com.kryptoblocks.couponx.pojo.RefreshTokenData;
import com.kryptoblocks.couponx.pojo.RefreshTokenInput;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SocialLogInActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {

    Button gmail_button;

    private static final String TAG = SocialLogInActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 007;
    static String tokenOutput;
    public static String uid, email_id_user;

    CallbackManager callbackManager;
    private static final String EMAIL = "email";
    LoginButton loginButton;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;
    String FBId;
    String FBtoken;
    String FB_user_id, FB_user_name ,FB_user_email;
    Button fb_btn;

  public  String personName, email , id , token , auth ;

    private GoogleApiClient mGoogleApiClient;

    API_Services api_services;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ////////////////////////FB Start/////////////////////////////
        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });

        ////////////////////////FB End/////////////////////////////

        setContentView(R.layout.activity_social_log_in);

        ////////////////////////Gmail/////////////////////////

        gmail_button = findViewById(R.id.gmail_login_btn);

        gmail_button.setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestServerAuthCode(getString(R.string.server_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        ////////////////////////Gmail End////////////////////////


        /////////////////////////FB Start/////////////////////////////
        fb_btn = findViewById(R.id.fb_custom_button);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(java.util.Arrays.asList("email"));
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(com.facebook.login.LoginResult loginResult) {
                // App code

                Toast.makeText(getApplication(), "*********sucessfull**********", Toast.LENGTH_SHORT).show();

                // fb id and token
                FBId = loginResult.getAccessToken().getUserId();
                FBtoken = loginResult.getAccessToken().getToken();

                Log.d("FB ID-----", FBId);
                Log.d("FB Token-----", FBtoken);

                //user profile data
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                // Insert your code here
                                FB_user_id = object.optString("id");
                                FB_user_name = object.optString("name");
                                FB_user_email = object.optString("email");
                                //login_id_value = FB_user_id;

                                System.out.println("FB user id-----   " + FB_user_id);
                                System.out.println("FB firstname --------   " + FB_user_name);
                                System.out.println("FB email id-----   " + FB_user_email);

                                Log.d("Format response----", response.toString());

                                if (!FBId.isEmpty()) {

                                    sendFaceBook();
                                }
                            }
                        });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email");
                request.setParameters(parameters);
                request.executeAsync();



            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
//////////////////FB End//////////////////////////////

        });
    }

    /////////////////////////////////////////GMAIL Start////////////////////////////////////////////////////


    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Log.e(TAG, "display name: " + acct.getDisplayName());

             personName = acct.getDisplayName();
            //  String personPhotoUrl = acct.getPhotoUrl().toString();
             email = acct.getEmail();
             id = acct.getId();
             token = acct.getIdToken();
             auth = acct.getServerAuthCode();

            //login_id_value = id;

            System.out.println("Google tryyy " + personName);
            System.out.println("Google email " + email);
            System.out.println("Google token " + token);
            System.out.println("Google ID " + id);
            System.out.println("Google auth " + auth);


            if (!personName.isEmpty()) {

                sendGoogle();

            }

        } else {

        }
        /*Intent i = new Intent(getApplication(), MainActivity.class);
        startActivity(i);*/
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            //Task<GoogleSignInAccount> gg = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(result);
        }
       /* //fb
            else  if (resultCode == RESULT_OK) {
           *//* Intent secondActivityIntent = new Intent(this, HomeActivity.class);
            startActivity(secondActivityIntent);*//*
           callbackManager.onActivityResult(requestCode, resultCode, data);

            }*/
    }


    @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            //  showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    //  hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.gmail_login_btn:
                signIn();
                break;
            case R.id.fb_custom_button:
                loginButton.performClick();
                break;
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    //////////////////////////////Gmail End/////////////////////////////////////////



    public void sendGoogle() {

        api_services = ApiClient.getClient().create(API_Services.class);

        final GoogleSignInInput googleSignInInput = new GoogleSignInInput();

        googleSignInInput.setGoogleID(id);
        googleSignInInput.setEmail(email);
        googleSignInInput.setName(personName);
        googleSignInInput.setGoogleToken(token);

        System.out.println("Inside API call " );
        System.out.println("Google tryyy " + personName);
        System.out.println("Google email " + email);
        System.out.println("Google token " + token);
        System.out.println("Google ID " + id);


        Call<GoogleSingInOutt> call1 = api_services.googleOut(googleSignInInput);

        System.out.println("callll====="+call1);

        call1.enqueue(new Callback<GoogleSingInOutt>() {


            @Override
            public void onResponse(Call<GoogleSingInOutt> call, Response<GoogleSingInOutt> response) {

                int statusCode = response.code();

                System.out.println("Code"+statusCode);

                String res = response.body().toString();
                System.out.println("res======="+res);

                if(response.body().getStatus()==200) {

                    try {

                        System.out.println("Insde try----------");
                        System.out.println("Code" + statusCode);

                        System.out.println("body-----------" + response.body().toString());

                       GoogleSingInOutt googleSingInOutt = response.body();


                       tokenOutput = googleSingInOutt.getData().getAccessToken();
                         uid = googleSingInOutt.getData().getUuid();
                         email_id_user = googleSingInOutt.getData().getEmail();

                        System.out.println("************inside google************");
                        System.out.println("Tokennn with data----------" + tokenOutput);
                        System.out.println("Uuiddd----------" + uid);
                        System.out.println("email----------" + email_id_user);


                        Log.i(TAG, "post submitted to google API." + response);
                    }
                    catch (Exception e) {
                        System.out.println("Insde catch----------");
                        e.printStackTrace();

                    }

                    sendRefresh();

                       // Toast.makeText(getApplication(), "Success google+++++++++", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Log.i(TAG, "post not submitted to google API." + response);
                        Toast.makeText(getApplication(), "Unsuccess google+++++++++", Toast.LENGTH_LONG).show();
                    }
                }



            @Override
            public void onFailure(Call<GoogleSingInOutt> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to google API.");

                System.out.println("Error failure");
                t.printStackTrace();
                Toast.makeText(getApplication(), "Failed google+++++++++", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void sendFaceBook() {

        //tryyy/////////////

        api_services = ApiClient.getClient().create(API_Services.class);

        FBSignInInput fbSignInInput = new FBSignInInput();

        fbSignInInput.setFacebookID(FBId);
        fbSignInInput.setEmail(FB_user_email);
        fbSignInInput.setName(FB_user_name);
        fbSignInInput.setFacebookToken(FBtoken);

        System.out.println("fb tryyy " + FB_user_name);
        System.out.println("fb email " + FB_user_email);
        System.out.println("fb token " + FBtoken);
        System.out.println("fb ID " + FBId);


        Call<FbSignInOut> call1 = api_services.facebookOut(fbSignInInput);

        call1.enqueue(new Callback<FbSignInOut>() {

            @Override
            public void onResponse(Call<FbSignInOut> call, Response<FbSignInOut> response) {

                if(response.isSuccessful()) {
                    int statusCode = response.code();

                    System.out.println("Code"+statusCode);


                    if(response.isSuccessful()) {

                        Log.i(TAG, "post submitted to fb API." + response);

                        FbSignInOut fbSignInOut = response.body();

                        tokenOutput = fbSignInOut.getData().getAccessToken();
                        uid = fbSignInOut.getData().getUuid();
                        email_id_user = fbSignInOut.getData().getEmail();

                        System.out.println("************inside fb************");
                        System.out.println("Tokennn with data----------" + tokenOutput);
                        System.out.println("Uuiddd----------" + uid);
                        System.out.println("email----------" + email_id_user);

                        sendRefresh();

                      //  Toast.makeText(getApplication(), "Success Fb+++++++++", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Log.i(TAG, "post not submitted to fb API." + response);
                        Toast.makeText(getApplication(), "Unsuccess fb+++++++++", Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<FbSignInOut> call, Throwable t) {
                Log.e(TAG, "Unable to submit fb post to API.");
                t.printStackTrace();
                Toast.makeText(getApplication(), "Failed fb+++++++++", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void sendRefresh() {

        //tryyy/////////////

        api_services = ApiClient.getClient().create(API_Services.class);

        RefreshTokenInput refreshTokenInput = new RefreshTokenInput();


        System.out.println("Tokennn refresh----------"+tokenOutput);
        System.out.println("Uuiddd refresh----------"+uid);

        refreshTokenInput.setRefreshToken(tokenOutput);
        refreshTokenInput.setUuid(uid);


        Call<RefreshTokenData> call1 = api_services.refreshOut(refreshTokenInput);


        call1.enqueue(new Callback<RefreshTokenData>() {


            @Override
            public void onResponse(Call<RefreshTokenData> call, Response<RefreshTokenData> response) {

                if(response.isSuccessful()) {
                    int statusCode = response.code();

                    System.out.println("Code"+statusCode);

                    //System.out.println("body"+response.body());

                    if(response.isSuccessful()) {

                        Intent i = new Intent(getApplication(), MainActivity.class);
                        startActivity(i);


                        Log.i(TAG, "post submitted to token refresh API." + response);
                        //  new Gson().fromJson(response.message(), RedeemOutput.class);
                       // Toast.makeText(getApplication(), "Success token+++++++++", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Log.i(TAG, "post not submitted to API." + response);
                        Toast.makeText(getApplication(), "Unsuccess token+++++++++", Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<RefreshTokenData> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to token refresh API.");
                Toast.makeText(getApplication(), "Failed token refresh+++++++++", Toast.LENGTH_LONG).show();
            }
        });

    }

}
