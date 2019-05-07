package com.prometteur.wallofhumanitiy.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.prometteur.wallofhumanitiy.R;
import com.prometteur.wallofhumanitiy.activity.SelectShareFriendsActivity;
import com.prometteur.wallofhumanitiy.Singleton.SingletonLegacyMemories;

import java.util.List;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class LegacyMemoriesAdapter extends RecyclerView.Adapter<LegacyMemoriesAdapter.MyViewHolder> {

    private Context mContext;
    private List<SingletonLegacyMemories> singletonMemoriesLegacyList;
    ImageView imgAddVisitedPlace;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView memoTitle, memoNumOfComment, memoNumOfShare;
        public ImageView memoThumbnail;
        CardView  card_view;
        ImageView imgExpandMemories,img_profile;
        LinearLayout llShareMemory, llMemoryComments;
        CheckBox chkSelect;

        public MyViewHolder(View view) {
            super(view);
            memoTitle = view.findViewById(R.id.memoTitle);
            memoNumOfComment = view.findViewById(R.id.memoNumOfComment);
            memoNumOfShare = view.findViewById(R.id.memoNumOfShare);
            memoThumbnail = view.findViewById(R.id.memoThumbnail);
            card_view = view.findViewById(R.id.card_view);
            imgExpandMemories = view.findViewById(R.id.imgExpandMemories);
            llShareMemory = view.findViewById(R.id.llShareMemory);
            llMemoryComments = view.findViewById(R.id.llMemoryComments);
            img_profile = view.findViewById(R.id.img_profile);
            chkSelect = view.findViewById(R.id.chkSelect);
        }
    }


    public LegacyMemoriesAdapter(Context mContext, List<SingletonLegacyMemories> singletonMemoriesList, ImageView imgAddVisitedPlace) {
        this.mContext = mContext;
        this.singletonMemoriesLegacyList = singletonMemoriesList;
        this.imgAddVisitedPlace = imgAddVisitedPlace;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_legacy_memory_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final SingletonLegacyMemories singletonLegacyMemories = singletonMemoriesLegacyList.get(position);

        holder.chkSelect.setVisibility(View.GONE);

        holder.memoTitle.setText(singletonLegacyMemories.getMemoTitle());
        holder.memoNumOfComment.setText(singletonLegacyMemories.getMemoNumOfComment());
        holder.memoNumOfShare.setText(singletonLegacyMemories.getMemoNumOfShare());

        // loading singletonMemories cover using Glide library
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(16));

        Glide.with(mContext).load(singletonLegacyMemories.getMemoThumbnail())
                .apply(requestOptions)
                .into(holder.memoThumbnail);

        Glide.with(mContext).load(singletonLegacyMemories.getMemoThumbnail())
                .apply(requestOptions)
                .into(holder.img_profile);


        holder.imgExpandMemories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog openDialog = new Dialog(mContext);
                openDialog.setContentView(R.layout.custom_memory_dialog);
                openDialog.setTitle("");
                ImageView memoDialogThumbnail = openDialog.findViewById(R.id.memoThumbnail);
                // loading singletonMemories cover using Glide library
                RequestOptions requestOptions = new RequestOptions();
                requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(16));


                Glide.with(mContext).load(singletonLegacyMemories.getMemoThumbnail())
                        .apply(requestOptions)
                        .into(memoDialogThumbnail);
                ImageView dialogCloseButton = openDialog.findViewById(R.id.dialogCloseButton);
                dialogCloseButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        openDialog.dismiss();
                    }
                });
                openDialog.show();
            }
        });



        holder.llShareMemory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SelectShareFriendsActivity.class);
                mContext.startActivity(intent);
            }
        });

        holder.llMemoryComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog openDialog = new Dialog(mContext);
                openDialog.setContentView(R.layout.custom_comment_dialog);
                openDialog.setTitle("");

                ImageView dialogCloseButton = openDialog.findViewById(R.id.dialogCloseButton);
                dialogCloseButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        openDialog.dismiss();
                    }
                });
                openDialog.show();
            }
        });

        holder.card_view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                holder.chkSelect.setVisibility(View.VISIBLE);
                imgAddVisitedPlace .setVisibility(View.VISIBLE);
                holder.chkSelect.setChecked(true);
                Toast.makeText(mContext, ""+singletonLegacyMemories.getMemoTitle(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

         }

         @Override
    public int getItemCount() {
        return singletonMemoriesLegacyList.size();
    }
}