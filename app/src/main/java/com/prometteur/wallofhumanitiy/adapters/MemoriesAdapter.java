package com.prometteur.wallofhumanitiy.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.button.MaterialButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import com.prometteur.wallofhumanitiy.Singleton.SingletonMemories;

import java.util.List;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class MemoriesAdapter extends RecyclerView.Adapter<MemoriesAdapter.MyViewHolder> {

    private Context mContext;
    private List<SingletonMemories> singletonMemoriesList;

    public interface OnItemClickListener {
        void onItemClick(SingletonMemories item);
    }
    private final OnItemClickListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView memoTitle, memoNumOfComment, memoNumOfShare;
        public ImageView memoThumbnail;
        CardView card_view_last, card_view;
        ImageView imgExpandMemories;
        LinearLayout llShareMemory, llMemoryComments;

        public MyViewHolder(View view) {
            super(view);
            memoTitle = view.findViewById(R.id.memoTitle);
            memoNumOfComment = view.findViewById(R.id.memoNumOfComment);
            memoNumOfShare = view.findViewById(R.id.memoNumOfShare);
            memoThumbnail = view.findViewById(R.id.memoThumbnail);
            card_view_last = view.findViewById(R.id.card_view_last);
            card_view = view.findViewById(R.id.card_view);
            imgExpandMemories = view.findViewById(R.id.imgExpandMemories);
            llShareMemory = view.findViewById(R.id.llShareMemory);
            llMemoryComments = view.findViewById(R.id.llMemoryComments);
        }
    }


    public MemoriesAdapter(Context mContext, List<SingletonMemories> singletonMemoriesList, OnItemClickListener listener) {
        this.mContext = mContext;
        this.singletonMemoriesList = singletonMemoriesList;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_memory_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final SingletonMemories singletonMemories = singletonMemoriesList.get(position);


        if (position + 1 == singletonMemoriesList.size()) {
            holder.card_view_last.setVisibility(View.VISIBLE);
            holder.card_view.setVisibility(View.GONE);
        } else {
            holder.card_view_last.setVisibility(View.GONE);
            holder.card_view.setVisibility(View.VISIBLE);
        }


        holder.memoTitle.setText(singletonMemories.getMemoTitle());
        holder.memoNumOfComment.setText(singletonMemories.getMemoNumOfComment());
        holder.memoNumOfShare.setText(singletonMemories.getMemoNumOfShare());

        // loading singletonMemories cover using Glide library
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(16));

        Glide.with(mContext).load(singletonMemories.getMemoThumbnail())
                .apply(requestOptions)
                .into(holder.memoThumbnail);

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


                Glide.with(mContext).load(singletonMemories.getMemoThumbnail())
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
                openDialog.setContentView(R.layout.custom_question_dialog);
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

        holder.card_view_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(singletonMemories);

            }
        });


    }


    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return singletonMemoriesList.size();
    }
}