package com.prometteur.wallofhumanitiy.fragments;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.prometteur.wallofhumanitiy.R;
import com.prometteur.wallofhumanitiy.activity.SelectShareFriendsActivity;
import com.prometteur.wallofhumanitiy.other.FriendListResp;

import java.util.List;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.MyViewHolder> {

    private Context mContext;
    private List<FriendListResp.Message> singletonFriendList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView friendName, friendSubName;
        public ImageView friendThumbnail,imgSelectFriend;

        public MyViewHolder(View view) {
            super(view);
            friendName = view.findViewById(R.id.friendName);
            friendSubName = view.findViewById(R.id.friendSubName);
            friendThumbnail = view.findViewById(R.id.friendThumbnail);
            imgSelectFriend = view.findViewById(R.id.imgSelectFriend);
        }
    }


    public FriendsAdapter(Context mContext, List<FriendListResp.Message> singletonFriendList) {
        this.mContext = mContext;
        this.singletonFriendList = singletonFriendList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_friend_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final FriendListResp.Message singletonFriend = singletonFriendList.get(position);


        if(singletonFriend.isSelected()){
            holder.imgSelectFriend.setImageDrawable(mContext.getResources().getDrawable(R.drawable.checked_green));
        }else {
            holder.imgSelectFriend.setImageDrawable(null);
        }

        holder.imgSelectFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(singletonFriendList.get(position).isSelected()){
                    singletonFriendList.get(position).setSelected(false);
                    ((SelectShareFriendsActivity)mContext).friendList.get(position).setSelected(false);

                }else {
                    singletonFriendList.get(position).setSelected(true);
                    ((SelectShareFriendsActivity)mContext).friendList.get(position).setSelected(true);

                }
                notifyDataSetChanged();
            }
        });


        holder.friendName.setText(singletonFriend.getUserFname() +" "+singletonFriend.getUserLname());
        //holder.friendSubName.setText(singletonFriend.getUserFname());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(16));

        Glide.with(mContext).load(singletonFriend.getUserProfileImg())
                .apply(requestOptions)
                .into(holder.friendThumbnail);


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
        return singletonFriendList.size();
    }
}