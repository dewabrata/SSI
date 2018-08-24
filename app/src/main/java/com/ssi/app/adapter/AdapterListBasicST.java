package com.ssi.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.ssi.app.R;
import com.ssi.app.datamodel.NewOrder.Dataorder;
import com.ssi.app.datamodel.SuratTugas.Content;
import com.ssi.app.datamodel.SuratTugas.DetailSt;
import com.ssi.app.datamodel.SuratTugas.DetailSt_Table;
import com.ssi.app.datamodel.inputCITData.CITData;

import java.util.ArrayList;
import java.util.List;



public class AdapterListBasicST extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Content> items = new ArrayList<>();

    private Context ctx;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, Content obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public AdapterListBasicST(Context context, List<Content> items) {
        this.items = items;
        ctx = context;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView desc1,desc2,desc3;
        public TextView description;
        public View lyt_parent;

        public OriginalViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.image);
            desc1 = (TextView) v.findViewById(R.id.desc1);
            desc2 = (TextView) v.findViewById(R.id.desc2);
            desc3 = (TextView) v.findViewById(R.id.desc3);
            description = (TextView) v.findViewById(R.id.description);
            lyt_parent = (View) v.findViewById(R.id.lyt_parent);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_doc, parent, false);
        vh = new OriginalViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof OriginalViewHolder) {
            OriginalViewHolder view = (OriginalViewHolder) holder;

            Content p = items.get(position);
            view.desc1.setText(p.getCitStNo());
            view.desc2.setText(p.getCitWilayahName() +"-"+p.getCitStWilayah());
            view.desc3.setVisibility(View.GONE);
/* int jmlOrder = (int) SQLite.select(count(DetailSt_Table.citWilayahId))
                    .from(DetailSt.class)
                    .where(DetailSt_Table.parentId.eq(p.getCitStId()))
                    .count();
                    */
            List<CITData> lstActivity =  (ArrayList) SQLite.select().from(CITData.class).queryList();

            List<String> arrayActivity = new ArrayList<>();

            for (int x =0 ; x < lstActivity.size();x++){
                arrayActivity.add(lstActivity.get(x).getCitDataCode());
            }


            List<DetailSt> lstContent =  (ArrayList) SQLite.select().from(DetailSt.class)
                    .where(DetailSt_Table.parentId.eq(p.getCitStId()))
                    .and(DetailSt_Table.unikId.notIn(arrayActivity))
                    .queryList();

            view.description.setText(lstContent.size() +" Order");
           // Tools.displayImageRound(ctx, view.image, p.image);
            view.image.setImageResource(R.drawable.img_code_verification);
            view.lyt_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(view, items.get(position), position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}