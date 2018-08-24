package com.ssi.app.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.queriable.StringQuery;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;
import com.ssi.app.R;
import com.ssi.app.adapter.AdapterListBasicST;
import com.ssi.app.application.AppController;
import com.ssi.app.datamodel.NewOrder.Dataorder;
import com.ssi.app.datamodel.SuratTugas.Content;
import com.ssi.app.datamodel.SuratTugas.DetailSt;
import com.ssi.app.datamodel.SuratTugas.Order;
import com.ssi.app.restutil.APIClient;
import com.ssi.app.restutil.APIInterfacesRest;
import com.ssi.app.util.AppUtil;
import com.ssi.app.util.CreateModelData;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuratTugasActivity extends AppCompatActivity {

    private View parent_view;

    private RecyclerView recyclerView;
    private AdapterListBasicST mAdapter;
    private String shipmentNo;
    private String shipTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surat_tugas);
        parent_view = findViewById(android.R.id.content);


        getOrder();


  /*      shipmentNo = getIntent().getStringExtra("shipno");
        shipTo = getIntent().getStringExtra("shipto");
        if (shipmentNo!=null && shipTo!=null){
            sqlQueryList();
        }
*/



    }

    @Override
    protected void onResume() {
        super.onResume();
        getOrder();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Surat Tugas");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initComponent() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

   /*     List<Dataorder> items = DataGenerator.getPeopleData(this);
        items.addAll(DataGenerator.getPeopleData(this));
        items.addAll(DataGenerator.getPeopleData(this));*/

        //set data and list adapter


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }






  /*      ArrayList<Dataorder> data = (ArrayList)SQLite.select().from(Dataorder.class)
                .queryList();


        setupAdapterList(data);
*/


    public void setupAdapterList(){


        List<Content> lstContent =  (ArrayList) SQLite.select().from(Content.class)
                .queryList();

        mAdapter = new AdapterListBasicST(this, lstContent);
             recyclerView.setAdapter(mAdapter);

        // on item list clicked
        mAdapter.setOnItemClickListener(new AdapterListBasicST.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Content obj, int position) {


                Intent intent = new Intent(SuratTugasActivity.this,TaskActivity.class);
                intent.putExtra("id",obj.getCitStId());
          //      intent.putExtra("orderno",obj.getOrderNo());
                startActivityForResult(intent,999);

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
/*
        if (requestCode == 999){
            sqlQueryList();
        }
*/
    }


    APIInterfacesRest apiInterface;
    ProgressDialog progressDialog;


    private void getOrder(){
        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        if(progressDialog == null) {
            progressDialog = new ProgressDialog(SuratTugasActivity.this);
            progressDialog.setCancelable(false);
        }
        progressDialog.setTitle("Loading");
        progressDialog.show();


        JSONObject paramObject = new JSONObject();

        try {
            paramObject.put("param01", AppController.getCONTENTS().getUserNpp());
            paramObject.put("param02", AppUtil.NowX());
            paramObject.put("param03", "");
            paramObject.put("param04", "");
            paramObject.put("param05", "");
            paramObject.put("param06", "");
            paramObject.put("param07", "");
            paramObject.put("param08", "");
            paramObject.put("param09", "");
            paramObject.put("param10", "");
        }catch (Exception e){

        }

        Call<Order> OrderCall = apiInterface.getOrder(paramObject.toString());
        OrderCall.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {

                progressDialog.dismiss();
               Order order = response.body();

                if (order !=null) {

                    if (order.getMessage().equalsIgnoreCase("Data Ok")){

                    //    startActivity(new Intent(LoginActivity.this,SuratTugasActivity.class));
                        savedbContent(order);
                    }






                }else{
                    progressDialog.dismiss();
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        //     Toast.makeText(ShoppingProductGrid.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                        Toast.makeText(SuratTugasActivity.this, "Get Order Failed ,  "+response.errorBody().string(), Toast.LENGTH_LONG).show();

                    } catch (Exception e) {
                        try {
                            Toast.makeText(SuratTugasActivity.this, "Get Order Failed, "+response.errorBody().string(), Toast.LENGTH_LONG).show();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }

                        //    Toast.makeText(ShoppingProductGrid.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });

    }



    public void savedbContent(Order order){



        FlowManager.getDatabase(AppController.class)
                .beginTransactionAsync(new ProcessModelTransaction.Builder<>(
                        new ProcessModelTransaction.ProcessModel<Content>() {
                            @Override
                            public void processModel(Content orderItem, DatabaseWrapper wrapper) {

                                //          orderItem.setVendorId(dataVendor.getId());
                                orderItem.save();
                                savedbDetailST(orderItem);


                            }

                        }).addAll(order.getContents()).build())  // add elements (can also handle multiple)
                .error(new Transaction.Error() {
                    @Override
                    public void onError(Transaction transaction, Throwable error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                })
                .success(new Transaction.Success() {
                    @Override
                    public void onSuccess(Transaction transaction) {
                        //           Toast.makeText(getApplicationContext(),"Data Tersimpan",Toast.LENGTH_LONG).show();
                        initToolbar();
                        initComponent();
                        setupAdapterList();
                    }
                }).build().execute();


    }

    public void savedbDetailST(Content content){



        FlowManager.getDatabase(AppController.class)
                .beginTransactionAsync(new ProcessModelTransaction.Builder<>(
                        new ProcessModelTransaction.ProcessModel<DetailSt>() {
                            @Override
                            public void processModel(DetailSt orderItem, DatabaseWrapper wrapper) {

                                //          orderItem.setVendorId(dataVendor.getId());
                                orderItem.setUnikId(String.valueOf(content.getCitStId())+String.valueOf(orderItem.getCitWilayahId()));
                                orderItem.setParentId(content.getCitStId());
                                orderItem.save();


                            }

                        }).addAll(content.getDetailSt()).build())  // add elements (can also handle multiple)
                .error(new Transaction.Error() {
                    @Override
                    public void onError(Transaction transaction, Throwable error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                })
                .success(new Transaction.Success() {
                    @Override
                    public void onSuccess(Transaction transaction) {
                        //           Toast.makeText(getApplicationContext(),"Data Tersimpan",Toast.LENGTH_LONG).show();


                    }
                }).build().execute();


    }



}
