package com.ssi.app.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.rxgps.RxGps;
import com.ssi.app.R;
import com.ssi.app.application.AppController;
import com.ssi.app.datamodel.inputCITData.CITData;
import com.ssi.app.datamodel.inputCITData.CITDataResult;
import com.ssi.app.restutil.APIClient;
import com.ssi.app.restutil.APIInterfacesRest;
import com.ssi.app.util.AppUtil;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FormDetailActivity extends AppCompatActivity {

    //image multi

    private static final String TAG = FormDetailActivity.class.getSimpleName();

    private final int REQ_IMAGE = 1433;

    private int counterImage =0;

    Bitmap bitmap;

    public Uri fileUri;
    private int CAMERA_REQUEST = 100;
    private int Gallary_REQUEST = 101;








    private int screenWidth;




 //   private Button btnImg1,btnQR ;
    private Button btnSubmit, btnCancel,btnOption,btnNominal;
    private EditText nosegel,nokarung,nosppu;
    private  ImageView imgCapture;

    private String shipNo, orderNo;
    private CITData citData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formdetail);



        shipNo = getIntent().getStringExtra("shipno");
        orderNo = getIntent().getStringExtra("orderno");



        citData = getIntent().getExtras().getParcelable("data");

        nosegel = (EditText) findViewById(R.id.nosegel);


        nokarung = (EditText) findViewById(R.id.nokarung);
        nosppu = (EditText) findViewById(R.id.nosppu);


        if (citData.getCitDataSegel()!=null){
            nosegel.setText(citData.getCitDataSegel());
        }

        if (citData.getCitDataNoKarung()!=null){
            nokarung.setText(citData.getCitDataNoKarung());
        }
        if (citData.getCitDataSppu()!=null){
            nosppu.setText(citData.getCitDataSppu());
        }


       imgCapture  = (ImageView) findViewById(R.id.imgCapture);

        screenWidth = getWindowManager().getDefaultDisplay().getWidth();

        setupData();
        setDisable();



    //    btnImg1 = (Button) findViewById(R.id.btnImg1);


        btnSubmit = (Button) findViewById(R.id.submit);
        btnCancel = (Button) findViewById(R.id.cancel);
      //  btnQR =(Button)findViewById(R.id.btnQR);

        btnNominal = (Button) findViewById(R.id.btnNominal);
        btnNominal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog();
            }
        });
        btnOption =(Button)findViewById(R.id.spn_pickup);
        btnOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showOptionChoiceDialog((Button)view);
            }
        });



        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCitData();
                Intent intent = new Intent();
                intent.putExtra("data", (Parcelable) citData);
                setResult(999, intent);
                finish();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (nokarung.getText().toString().equals("") || nosegel.getText().toString().equals("") || nosppu.getText().toString().equals("")){
                    Snackbar.make(view,"Lengkapi data terlebih dahulu",Snackbar.LENGTH_SHORT).show();
                }else {
                    sendsaveOrder(citData);
                }

            }
        });




    }



    public void setDisable(){



    }






    public void setupData(){

      

        setGPS();
    }




    public void setGPS(){
        final RxGps rxGps = new RxGps(this);

        rxGps.lastLocation()


                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(location -> {
                    // .setLongitude(String.valueOf(location.getLongitude()));
                    //order.setLangitude(String.valueOf(location.getLatitude()));

                    String latlon = String.valueOf(location.getLongitude()) + String.valueOf(location.getLatitude());



                }, throwable -> {
                    if (throwable instanceof RxGps.PermissionException) {
                        displayError(throwable.getMessage());
                    } else if (throwable instanceof RxGps.PlayServicesNotAvailableException) {
                        displayError(throwable.getMessage());
                    }
                });
    }

    public void displayError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }




    public Bitmap getBitmap  (File file){

        Bitmap bitmap = null;

        FileInputStream streamIn = null;
        try {
            streamIn = new FileInputStream(file);


            bitmap = BitmapFactory.decodeStream(streamIn); //This gets the image

            streamIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

       return bitmap;
    }

    ProgressDialog progressDialog;


    public void sendsaveOrder(CITData data){



        APIInterfacesRest apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        //  RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), byteArray);




        progressDialog = new ProgressDialog(FormDetailActivity.this);
        progressDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_PANEL);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        Call<CITDataResult> updateService = apiInterface.sendCIT(data);





        updateService.enqueue(new Callback<CITDataResult>() {
            @Override
            public void onResponse(Call<CITDataResult> call, Response<CITDataResult> response) {

                if (response != null) {
                    if(response.isSuccessful()) {
                        if (response.body() != null) {
                            Log.d("Test", response.message());

                            citData.save();

                            Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();
                            setResult(666);
                            finish();



                        }
                    }
                }else{

                    //  datax.setStatus("pending");
                    //  datax.save();
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(getApplicationContext(), jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

                //finish();

            }

            @Override
            public void onFailure(Call<CITDataResult> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Koneksi bermasalah", Toast.LENGTH_LONG).show();

                progressDialog.dismiss();
                //   datax.setStatus("pending");
                //finish();
            }
        });


    }



    public RequestBody toRequestBody(String value) {
        if (value==null){
            value="";
        }
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), value);
        return body;
    }




    public static byte[] compressCapture(byte[] capture, int maxSizeKB) {

        // This should be different based on the original capture size
        int compression = 12;

        Bitmap bitmap  = BitmapFactory.decodeByteArray(capture, 0, capture.length);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, compression, outputStream);
        return outputStream.toByteArray();
    }

    public Bitmap rotateImage(Bitmap source, float angle) {
        Bitmap retVal;

        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        retVal = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);

        return retVal;
    }

    void openCamera() {
        File directory = new File(
                Environment.getExternalStorageDirectory(), "iCollector" + "/" + getPackageName());
        try {
            SimpleDateFormat sdfPic = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String currentDateandTime = sdfPic.format(new Date()).replace(" ", "");
            File imagesFolder = new File(directory.getAbsolutePath());
            imagesFolder.mkdirs();

            String fname = "IMAGE_" + currentDateandTime + ".jpg";
            File file = new File(imagesFolder, fname);
            fileUri = Uri.fromFile(file);
            Intent cameraIntent = new Intent(
                    MediaStore.ACTION_IMAGE_CAPTURE);
//            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        if(requestCode == AppController.REQUEST_DETAIL){
            if(resultCode == AppController.CLOSE_CODE){
                setResult(AppController.CLOSE_CODE);
                finish();
            }
        }

        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            bitmap = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, baos);
            imgCapture.setImageBitmap(bitmap);

        } else if (requestCode == Gallary_REQUEST && resultCode == Activity.RESULT_OK) {
            Uri selectedImageUri = data.getData();

            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImageUri, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            bitmap = BitmapFactory.decodeFile(picturePath);
            ExifInterface ei = null;
            try {
                ei = new ExifInterface(picturePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotateImage(bitmap, 90);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotateImage(bitmap, 180);
                    break;
                // etc.
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, baos);

            imgCapture.setImageBitmap(bitmap);

        }


    }

    private void showOptionChoiceDialog(final Button bt) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        List<String> lstOption = new ArrayList<String>();

        lstOption.add("Pickup / Delivery");
        lstOption.add("Supply / Setor");
        final CharSequence[] charSequence = new CharSequence[] {"Pickup / Delivery","Supply / Setor"};
        int checked = 0;
        if(citData.getCitDataIsremise()!=null) {
            if (citData.getCitDataIsremise().equalsIgnoreCase("1")) {
                checked = 0;
            } else {
                checked = 1;
            }
        }else{
            checked = 0;
        }

        builder.setSingleChoiceItems(charSequence, checked, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which){
                  case  0 :
                      citData.setCitDataIsremise("1");
                      citData.setCitDataJenisPickup("1");
                    case 1 :
                        citData.setCitDataIsremise("0");
                        citData.setCitDataJenisPickup("0");
                }


                dialog.dismiss();
                bt.setTextColor(Color.BLACK);
                bt.setText(lstOption.get(which));



            }
        });
        builder.show();
    }


    private void showCustomDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_nominal);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;


        final EditText nominal_aud = (EditText) dialog.findViewById(R.id.nominal_aud);
        final EditText nominal_cad = (EditText) dialog.findViewById(R.id.nominal_cad);
        final EditText nominal_chf = (EditText) dialog.findViewById(R.id.nominal_chf);
        final EditText nominal_cny = (EditText) dialog.findViewById(R.id.nominal_cny);
        final EditText nominal_eur = (EditText) dialog.findViewById(R.id.nominal_eur);
        final EditText nominal_gbp = (EditText) dialog.findViewById(R.id.nominal_gbp);
        final EditText nominal_hkd = (EditText) dialog.findViewById(R.id.nominal_hkd);
        final EditText nominal_jpy = (EditText) dialog.findViewById(R.id.nominal_jpy);
        final EditText nominal_idr = (EditText) dialog.findViewById(R.id.nominal_idr);
        final EditText nominal_myr = (EditText) dialog.findViewById(R.id.nominal_myr);
        final EditText nominal_nzd = (EditText) dialog.findViewById(R.id.nominal_nzd);
        final EditText nominal_sar = (EditText) dialog.findViewById(R.id.nominal_sar);
        final EditText nominal_usd = (EditText) dialog.findViewById(R.id.nominal_usd);
        final EditText nominal_sgd = (EditText) dialog.findViewById(R.id.nominal_sgd);
        final EditText nominal_yen = (EditText) dialog.findViewById(R.id.nominal_yen);

         nominal_aud.setText(citData.getCitDataNominalAud());
         nominal_cad.setText(citData.getCitDataNominalCad());
         nominal_chf.setText(citData.getCitDataNominalChf());
         nominal_cny.setText( citData.getCitDataNominalCny());
         nominal_eur.setText(citData.getCitDataNominalEur());
         nominal_gbp.setText(citData.getCitDataNominalGbp());
         nominal_hkd.setText(citData.getCitDataNominalHkd());
         nominal_jpy.setText(citData.getCitDataNominalJpy());
         nominal_idr.setText(citData.getCitDataNominalIdr());
         nominal_myr.setText(citData.getCitDataNominalMyr());
         nominal_nzd.setText(citData.getCitDataNominalNzd());
         nominal_sar.setText(citData.getCitDataNominalSar());
         nominal_usd.setText(citData.getCitDataNominalUsd());
         nominal_sgd.setText(citData.getCitDataNominalSgd());
         nominal_yen.setText(citData.getCitDataNominalYen());





        ((ImageButton) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        ((Button) dialog.findViewById(R.id.bt_save)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                citData.setCitDataNominalAud(nominal_aud.getText().toString());
                citData.setCitDataNominalCad(nominal_cad.getText().toString());
                citData.setCitDataNominalChf(nominal_chf.getText().toString());
                citData.setCitDataNominalCny(nominal_cny.getText().toString());
                citData.setCitDataNominalEur(nominal_eur.getText().toString());
                citData.setCitDataNominalGbp(nominal_gbp.getText().toString());
                citData.setCitDataNominalHkd(nominal_hkd.getText().toString());
                citData.setCitDataNominalIdr(nominal_idr.getText().toString());
                citData.setCitDataNominalJpy(nominal_jpy.getText().toString());
                citData.setCitDataNominalMyr(nominal_myr.getText().toString());
                citData.setCitDataNominalNzd(nominal_nzd.getText().toString());
                citData.setCitDataNominalSar(nominal_sar.getText().toString());
                citData.setCitDataNominalSgd(nominal_sgd.getText().toString());
                citData.setCitDataNominalUsd(nominal_usd.getText().toString());
                citData.setCitDataNominalYen(nominal_yen.getText().toString());
               

                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }


    @Override
    public void onBackPressed() {
        getCitData();
        Intent intent = new Intent();
        intent.putExtra("data", (Parcelable) citData);
        setResult(999, intent);
        finish();
        super.onBackPressed();
    }


    public void getCitData (){
        citData.setCitDataNoKarung(nokarung.getText().toString());
        citData.setCitDataSegel(nosegel.getText().toString());
        citData.setCitDataSppu(nosppu.getText().toString());
        citData.setCitDataTimeOut(AppUtil.Now());

    }


}
