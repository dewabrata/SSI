package com.ssi.app.activity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.ssi.app.R;
import com.ssi.app.application.AppController;
import com.ssi.app.datamodel.Login.LoginRest;
import com.ssi.app.restutil.APIClient;
import com.ssi.app.restutil.APIInterfacesRest;
import com.ssi.app.util.Tools;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ProgressBar progress_bar;
    private FloatingActionButton fab;
    private View parent_view;
    private TextInputEditText txtUsername, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parent_view = findViewById(android.R.id.content);
        progress_bar = (ProgressBar) findViewById(R.id.progress_bar);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        Tools.setSystemBarColor(this, R.color.cyan_800);

        checkAndRequestPermissions(LoginActivity.this);

        txtUsername = (TextInputEditText)findViewById(R.id.txtUsername);
      //  txtUsername.setText("spocit");

        txtPassword = (TextInputEditText)findViewById(R.id.txtPassword);
    //    txtPassword.setText("123456");


        (findViewById(R.id.sign_up_for_account)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(parent_view, "Sign up for an account", Snackbar.LENGTH_SHORT).show();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                searchAction();
            }
        });
    }

    private void searchAction() {
        progress_bar.setVisibility(View.VISIBLE);
        fab.setAlpha(0f);

  /*      new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

              // startActivity(new Intent(LoginActivity.this,SuratTugasActivity.class));
                getData();
            }
        }, 1000);*/

      getData();
    }



    public  boolean checkAndRequestPermissions(Context context) {

        List<String> listPermissionsNeeded = new ArrayList();
        int locationFine = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
        int locationAccess = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION);
        int writefilePermission = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE);
        int cameraPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
        if (locationFine != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (locationAccess != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if (writefilePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions((Activity)context, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 6969);
            return false;
        }
        return true;
    }


    APIInterfacesRest apiInterface;
    ProgressDialog progressDialog;
    LoginRest login;

    private void getData(){
        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);


        JSONObject paramObject = new JSONObject();

        try {
        paramObject.put("entryId", txtUsername.getText().toString());
        paramObject.put("entryKey", txtPassword.getText().toString());
        paramObject.put("kodeCabang", "");
        paramObject.put("userType", "");
        }catch (Exception e){

        }

        Call<LoginRest> merchantCall = apiInterface.getLogin(paramObject.toString());
        merchantCall.enqueue(new Callback<LoginRest>() {
            @Override
            public void onResponse(Call<LoginRest> call, Response<LoginRest> response) {
                progress_bar.setVisibility(View.GONE);
                fab.setAlpha(1f);
                login = response.body();

                if (login !=null) {

                    if (login.getMessage().equalsIgnoreCase("Data Ok")){

                        AppController.setCONTENTS(login.getContents());
                        startActivity(new Intent(LoginActivity.this,SuratTugasActivity.class));

                    }






                }else{
                    progress_bar.setVisibility(View.GONE);
                    fab.setAlpha(1f);
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        //     Toast.makeText(ShoppingProductGrid.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                        Toast.makeText(LoginActivity.this, "Login Failed ,  "+response.errorBody().string(), Toast.LENGTH_LONG).show();

                    } catch (Exception e) {
                        try {
                            Toast.makeText(LoginActivity.this, "Login Failed, "+response.errorBody().string(), Toast.LENGTH_LONG).show();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }

                        //    Toast.makeText(ShoppingProductGrid.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<LoginRest> call, Throwable t) {
                progress_bar.setVisibility(View.GONE);
                fab.setAlpha(1f);
                Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });

    }


}
