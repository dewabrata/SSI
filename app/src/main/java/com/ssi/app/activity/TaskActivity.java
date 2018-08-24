package com.ssi.app.activity;

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

import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.queriable.StringQuery;
import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;
import com.ssi.app.R;
import com.ssi.app.adapter.AdapterListBasicST;
import com.ssi.app.adapter.AdapterListBasicTASK;
import com.ssi.app.application.AppController;
import com.ssi.app.datamodel.NewOrder.Dataorder;
import com.ssi.app.datamodel.NewOrder.Dataorder_Table;
import com.ssi.app.datamodel.SuratTugas.Content;
import com.ssi.app.datamodel.SuratTugas.Content_Table;
import com.ssi.app.datamodel.SuratTugas.DetailSt;
import com.ssi.app.datamodel.SuratTugas.DetailSt_Table;
import com.ssi.app.datamodel.inputCITData.CITData;
import com.ssi.app.util.CreateModelData;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;

public class TaskActivity extends AppCompatActivity {

    private View parent_view;

    private RecyclerView recyclerView;
    private AdapterListBasicTASK mAdapter;
    private Integer id;
    private String shipTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surat_tugas);
        parent_view = findViewById(android.R.id.content);


        initToolbar();
        initComponent();

        id = getIntent().getIntExtra("id",0);

        if (id!=null){
            sqlQueryList();
        }






    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Task");
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




    public void sqlQueryList(){
        List<CITData> lstActivity =  (ArrayList) SQLite.select().from(CITData.class).queryList();

        List<String> arrayActivity = new ArrayList<>();

        for (int x =0 ; x < lstActivity.size();x++){
            arrayActivity.add(lstActivity.get(x).getCitDataCode());
        }


        List<DetailSt> lstContent =  (ArrayList) SQLite.select().from(DetailSt.class)
                .where(DetailSt_Table.parentId.eq(id))
                .and(DetailSt_Table.unikId.notIn(arrayActivity))
                .queryList();
        setupAdapterList(lstContent);
    }

    public void setupAdapterList(List<DetailSt>items){


             mAdapter = new AdapterListBasicTASK(this, items);
             recyclerView.setAdapter(mAdapter);

        // on item list clicked
        mAdapter.setOnItemClickListener(new AdapterListBasicTASK.OnItemClickListener() {
            @Override
            public void onItemClick(View view, DetailSt obj, int position) {
                List<Content> lstOrder =  (ArrayList) SQLite.select().from(Content.class)
                        .where(Content_Table.citStId.eq(obj.getParentId()))
                        .queryList();

               Intent intent = new Intent(getApplicationContext(),AbsentFirstActivity.class);
               intent.putExtra("id",obj.getUnikId());
               intent.putExtra("orderno",lstOrder.get(0).getCitStNo());
               intent.putExtra("status","0");
               startActivityForResult(intent,999);

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        sqlQueryList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
/*
        if (requestCode == 999){
            sqlQueryList();
        }
*/
    }

}
