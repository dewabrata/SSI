package com.ssi.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ssi.app.R;
import com.ssi.app.datamodel.NewOrder.Dataorder;
import com.ssi.app.datamodel.SuratTugas.DetailSt;

import java.util.ArrayList;
import java.util.List;


public class AdapterListBasicTASK extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<DetailSt> items = new ArrayList<>();

    private Context ctx;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, DetailSt obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public AdapterListBasicTASK(Context context, List<DetailSt> items) {
        this.items = items;
        ctx = context;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView desc1;
        public TextView desc2;
        public TextView desc3;
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

            DetailSt p = items.get(position);
            view.desc3.setText(p.getCitWilayahId()+" - "+p.getCitWilayahIdInduk());
            view.description.setText(p.getCitWilayahLokasiDari() +" - "+p.getCitWilayahLokasiTujuan());
            view.desc1.setText(p.getCitLokasiDariName() +" - "+p.getCitLokasiTujuanName());
            view.desc2.setText(p.getCitWilayahName() +" - "+p.getCitWilayahIndukName());
           // Tools.displayImageRound(ctx, view.image, p.image);
            view.image.setImageResource(R.drawable.img_wizard_2);
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