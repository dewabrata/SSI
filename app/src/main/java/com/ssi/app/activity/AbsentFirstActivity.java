package com.ssi.app.activity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.florent37.rxgps.RxGps;

import com.raizlabs.android.dbflow.sql.queriable.StringQuery;
import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;
import com.ssi.app.R;
import com.ssi.app.application.AppController;
import com.ssi.app.datamodel.NewOrder.Dataorder;
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
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ssi.app.application.AppController.MY_PERMISSIONS_REQUEST_CAMERA;


public class AbsentFirstActivity extends AppCompatActivity {

    //image multi

    private static final String TAG = AbsentFirstActivity.class.getSimpleName();

    private final int REQ_IMAGE = 1433;

    private int counterImage =0;
    private String lan,lon;

    Bitmap bitmap;

    public Uri fileUri;
    private int CAMERA_REQUEST = 100;
    private int Gallary_REQUEST = 101;

    CITData data;






    private int screenWidth;




    private Button btnImg1,btnQR ;
    private Button btnSubmit, btnCancel;
    private EditText stno,txtlocation,txtQR;
    private  ImageView imgCapture;

    private String id, orderNo,status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absent);



        id = getIntent().getStringExtra("id");
        orderNo= getIntent().getStringExtra("orderno");
        status= getIntent().getStringExtra("status");


        stno = (EditText) findViewById(R.id.stno);


        txtlocation = (EditText) findViewById(R.id.location);
        txtQR = (EditText) findViewById(R.id.qrcode);

       imgCapture  = (ImageView) findViewById(R.id.imgCapture);

        screenWidth = getWindowManager().getDefaultDisplay().getWidth();

        setupData();
        setDisable();



        btnImg1 = (Button) findViewById(R.id.btnImg1);


        btnSubmit = (Button) findViewById(R.id.submit);
        btnCancel = (Button) findViewById(R.id.cancel);
        btnQR =(Button)findViewById(R.id.btnQR);

        btnQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AbsentFirstActivity.this, QRActivity.class);
                startActivityForResult(intent,111);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // finish();


            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                if (data == null){
                     data = new CITData();
                    data.setCitDataCode(stno.getText().toString());
                    data.setCitDataDate(AppUtil.NowX());
                    data.setCitDataTimeIn(AppUtil.Now());
                    data.setCitDataOrderNumber(orderNo);
                    data.setCitDataPelanggan("0");
                    data.setCitDataPetugas(AppController.getCONTENTS().getUserNpp());
                    data.setCitDataModifiedUser(AppController.getCONTENTS().getUserName());
                    data.setCitDataStatus(status);
                    data.setCitDataSentra(AppController.getCONTENTS().getUserSentra().toString());
                }





                    data.setCitDataKcu("0");
                    data.setCitDataKeterangan("0");
                    data.setCitDataLatitude(lan);
                    data.setCitDataLokasiPickup("0");
                    data.setCitDataLongitude(lon);
                    data.setCitDataModified(AppUtil.Now());




                    data.setCitDataPictureIn("0");
                    data.setCitDataPictureOut("0");

                    if (txtQR.getText().toString().equalsIgnoreCase("")) {
                        data.setCitDataQrcode("0");
                    } else {
                        data.setCitDataQrcode(txtQR.getText().toString());
                    }




                    data.setCitDataSppuPic("0");


                    data.setCitDataTujuan("0");

            //    sendsaveOrder(data);



                Intent intent = new Intent(AbsentFirstActivity.this, FormDetailActivity.class);
                intent.putExtra("data",(Parcelable)data);
                startActivityForResult(intent,333);


            }
        });


        btnImg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



               openCamera();


            }
        });

    }



    public void setDisable(){

        txtlocation.setEnabled(false);
        txtQR.setEnabled(false);


    }






    public void setupData(){

        stno.setText(id);
        stno.setEnabled(false);


       /* String rawQuery = "SELECT  * FROM `Dataorder` where  driver ='"+ AppController.username+"' and shipmentNo ='"+shipNo+"' and orderNo ='"+orderNo+"' order by shipmentNo";
        StringQuery<Dataorder> stringQuery = new StringQuery<>(Dataorder.class, rawQuery);
        stringQuery
                .async()
                .error(new Transaction.Error() {
                    @Override
                    public void onError(@android.support.annotation.NonNull Transaction transaction, @android.support.annotation.NonNull Throwable error) {

                    }
                })
                .queryListResultCallback(new QueryTransaction.QueryResultListCallback<Dataorder>() {
                                             @Override
                                             public void onListQueryResult(QueryTransaction transaction, @NonNull List<Dataorder> models) {


                                                if (models.size()>0){
                                                    orderno.setText(models.get(0).getOrderNo());
                                                    shipto.setText(models.get(0).getShipTo());
                                                    shipname.setText(models.get(0).getShipName());
                                                    if (models.get(0).getRecievedate().equalsIgnoreCase("")){
                                                        recievedate.setText(AppUtil.Now());
                                                        models.get(0).setRecievedate(recievedate.getText().toString());
                                                    }else{
                                                        recievedate.setText( models.get(0).getRecievedate());
                                                    }

                                                    if (models.get(0).getPoddate().equalsIgnoreCase("")){
                                                        poddate.setText(AppUtil.Now());
                                                        models.get(0).setPoddate(poddate.getText().toString());
                                                    }else{
                                                        poddate.setText( models.get(0).getPoddate());
                                                    }



                                                    datax = models.get(0);
                                                    datax.save();


                                                    setGPS();

                                                }

                                             }


                                         }


                )
                .execute(); */

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
                    lan = String.valueOf(location.getLatitude());
                    lon = String.valueOf(location.getLongitude());

                    txtlocation.setText(latlon);


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
                    android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent datax) {
        super.onActivityResult(requestCode, resultCode, datax);

        if(requestCode == 333 && resultCode == 999){

            data = datax.getExtras().getParcelable("data");

        }

        if(requestCode == 333 && resultCode == 666){

            finish();

        }

        if (requestCode == 111 && resultCode==222 ){
            if (datax!=null){


                              txtQR.setText( datax.getStringExtra("barcode"));


            }
        }

        if(requestCode == AppController.REQUEST_DETAIL){
            if(resultCode == AppController.CLOSE_CODE){
                setResult(AppController.CLOSE_CODE);
                finish();
            }
        }

        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            bitmap = (Bitmap) datax.getExtras().get("data");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, baos);
            imgCapture.setImageBitmap(bitmap);

        } else if (requestCode == Gallary_REQUEST && resultCode == Activity.RESULT_OK) {
            Uri selectedImageUri = datax.getData();

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


}
