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
import com.prometteur.wallofhumanitiy.other.MemoryListResp;

import java.util.ArrayList;
import java.util.List;

/*Created by Mahesh Swami on 09/05/19.
 */
public class MemoriesAdapter extends RecyclerView.Adapter<MemoriesAdapter.MyViewHolder> {

    private Context mContext;
    private List<MemoryListResp.Result> memoryListRespList;

    public interface OnItemClickListener {
        void onItemClick(MemoryListResp.Result item);
    }

    int i = 0;

    private final OnItemClickListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView memoTitle, memoNumOfComment, memoNumOfShare;
        public ImageView memoThumbnail;
        CardView card_view_last, card_view;
        ImageView imgExpandMemories;
        LinearLayout llShareMemory, llMemoryComments;
        ImageView imgSlideLeft, imgSlideRight;

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
            imgSlideLeft = view.findViewById(R.id.imgSlideLeft);
            imgSlideRight = view.findViewById(R.id.imgSlideRight);
        }
    }


    public MemoriesAdapter(Context mContext, List<MemoryListResp.Result> memoryListRespList, OnItemClickListener listener) {
        this.mContext = mContext;
        this.memoryListRespList = memoryListRespList;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_memory_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final MemoryListResp.Result memoryListResp = memoryListRespList.get(position);

        final List<String> memoryList = new ArrayList<>();

        memoryList.add(memoryListResp.getMemoryThumbOne());
        memoryList.add(memoryListResp.getMemoryThumbTwo());
        memoryList.add(memoryListResp.getMemoryThumbThree());


        if (position + 1 == memoryListRespList.size()) {
            holder.card_view_last.setVisibility(View.VISIBLE);
            holder.card_view.setVisibility(View.GONE);
        } else {
            holder.card_view_last.setVisibility(View.GONE);
            holder.card_view.setVisibility(View.VISIBLE);
        }


        holder.memoTitle.setText(memoryListResp.getMemoryTitle());
        holder.memoNumOfComment.setText("15");
        holder.memoNumOfShare.setText("50");

        // loading memoryListResp cover using Glide library
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(16));
        final RequestOptions finalRequestOptions = requestOptions;
        final RequestOptions finalRequestOptions1 = requestOptions;

        Glide.with(mContext).load(memoryListResp.getMemoryThumbOne())
                .apply(requestOptions)
                .into(holder.memoThumbnail);


        holder.imgExpandMemories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog openDialog = new Dialog(mContext);
                openDialog.setContentView(R.layout.custom_memory_dialog);
                openDialog.setTitle("");
                final ImageView memoDialogThumbnail = openDialog.findViewById(R.id.memoThumbnail);
                // loading memoryListResp cover using Glide library
                RequestOptions requestOptions = new RequestOptions();
                requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(16));
                i = 0;

                Glide.with(mContext).load(memoryListResp.getMemoryThumbOne())
                        .apply(requestOptions)
                        .into(memoDialogThumbnail);
                ImageView dialogCloseButton = openDialog.findViewById(R.id.dialogCloseButton);
                ImageView imgSlideLeft = openDialog.findViewById(R.id.imgSlideLeft);
                ImageView imgSlideRight = openDialog.findViewById(R.id.imgSlideRight);

                TextView dlgMemoTitle, dlgMemoTime, dlgMemoDesc, dlgMemoCompliment, dlgMemoComments;
                dlgMemoTitle = openDialog.findViewById(R.id.dlgMemoTitle);
                dlgMemoTime = openDialog.findViewById(R.id.dlgMemoTime);
                dlgMemoDesc = openDialog.findViewById(R.id.dlgMemoDesc);
                dlgMemoCompliment = openDialog.findViewById(R.id.dlgMemoCompliment);
                dlgMemoComments = openDialog.findViewById(R.id.dlgMemoComments);

                dlgMemoTitle.setText(memoryListResp.getMemoryTitle());
                dlgMemoTime.setText(memoryListResp.getMemoryCreateDate());
                dlgMemoDesc.setText(memoryListResp.getMemoryDesc());

                imgSlideLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (i > 0) {
                            i--;
                            if (null != memoryList.get(i) && !memoryList.get(i).equalsIgnoreCase(""))
                                Glide.with(mContext).load(memoryList.get(i))
                                        .apply(finalRequestOptions1)
                                        .into(memoDialogThumbnail);
                        }
                    }
                });
                imgSlideRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (i < memoryList.size() - 1) {
                            i++;
                            if (null != memoryList.get(i) && !memoryList.get(i).equalsIgnoreCase(""))
                                Glide.with(mContext).load(memoryList.get(i))
                                        .apply(finalRequestOptions1)
                                        .into(memoDialogThumbnail);
                        }
                    }
                });
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
                listener.onItemClick(memoryListResp);

            }
        });


        holder.imgSlideLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i > 0) {
                    i--;
                    if (null != memoryList.get(i) && !memoryList.get(i).equalsIgnoreCase(""))
                        Glide.with(mContext).load(memoryList.get(i))
                                .apply(finalRequestOptions1)
                                .into(holder.memoThumbnail);
                }
            }
        });
        final RequestOptions finalRequestOptions2 = requestOptions;
        holder.imgSlideRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i < memoryList.size() - 1) {
                    i++;
                    if (null != memoryList.get(i) && !memoryList.get(i).equalsIgnoreCase(""))
                        Glide.with(mContext).load(memoryList.get(i))
                                .apply(finalRequestOptions1)
                                .into(holder.memoThumbnail);
                }
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
        return memoryListRespList.size();
    }
}