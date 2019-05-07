package com.prometteur.wallofhumanitiy.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.prometteur.wallofhumanitiy.R;
import com.prometteur.wallofhumanitiy.Singleton.SingletonTransmissionMemories;

import java.util.List;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class TransmissionMemoriesAdapter extends RecyclerView.Adapter<TransmissionMemoriesAdapter.MyViewHolder> {

    private Context mContext;
    private List<SingletonTransmissionMemories> singletonMemoriesTransmissionList;
    ImageView imgAddVisitedPlace;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView memoTitle;
        public ImageView memoThumbnail;
        CardView  card_view;

        CheckBox chkSelect;

        public MyViewHolder(View view) {
            super(view);
            memoTitle = view.findViewById(R.id.memoTitle);
            memoThumbnail = view.findViewById(R.id.memoThumbnail);
            card_view = view.findViewById(R.id.card_view);
            chkSelect = view.findViewById(R.id.chkSelect);
        }
    }


    public TransmissionMemoriesAdapter(Context mContext, List<SingletonTransmissionMemories> singletonMemoriesList, ImageView imgAddVisitedPlace) {
        this.mContext = mContext;
        this.singletonMemoriesTransmissionList = singletonMemoriesList;
        this.imgAddVisitedPlace = imgAddVisitedPlace;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_transmission_memory_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final SingletonTransmissionMemories singletonTransmissionMemories = singletonMemoriesTransmissionList.get(position);

        holder.chkSelect.setVisibility(View.GONE);

        holder.memoTitle.setText(singletonTransmissionMemories.getMemoTitle());

        // loading singletonMemories cover using Glide library
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(16));

        Glide.with(mContext).load(singletonTransmissionMemories.getMemoThumbnail())
                .apply(requestOptions)
                .into(holder.memoThumbnail);


        holder.card_view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                holder.chkSelect.setVisibility(View.VISIBLE);
                imgAddVisitedPlace .setVisibility(View.VISIBLE);
                holder.chkSelect.setChecked(true);
                Toast.makeText(mContext, ""+singletonTransmissionMemories.getMemoTitle(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

         }

         @Override
    public int getItemCount() {
        return singletonMemoriesTransmissionList.size();
    }
}