package com.prometteur.wallofhumanitiy.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.prometteur.wallofhumanitiy.R;
import com.prometteur.wallofhumanitiy.Singleton.SingletonChat;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {

    private Context mContext;
    private List<SingletonChat> singletonChatList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtGreenMessageDate, txtGreenSenderName, txtMessage;
        public ImageView senderThumbnail;

        public MyViewHolder(View view) {
            super(view);
            txtGreenMessageDate = view.findViewById(R.id.txtGreenMessageDate);
            txtGreenSenderName = view.findViewById(R.id.txtGreenSenderName);
            senderThumbnail = view.findViewById(R.id.chatThumbnail);
            txtMessage = view.findViewById(R.id.txtMessage);
        }
    }


    public ChatAdapter(Context mContext, List<SingletonChat> singletonChatList) {
        this.mContext = mContext;
        this.singletonChatList = singletonChatList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_chat_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final SingletonChat singletonChat = singletonChatList.get(position);


        holder.txtGreenMessageDate.setText(singletonChat.getMessageDate());
        holder.txtGreenSenderName.setText(singletonChat.getSenderName());
        holder.txtMessage.setText(singletonChat.getMessage());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(16));

        Glide.with(mContext).load(singletonChat.getSenderThumbnail())
                .apply(requestOptions)
                .into(holder.senderThumbnail);

    }


    @Override
    public int getItemCount() {
        return singletonChatList.size();
    }
}