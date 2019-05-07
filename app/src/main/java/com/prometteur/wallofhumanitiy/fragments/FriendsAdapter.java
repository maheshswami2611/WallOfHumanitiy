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
import com.prometteur.wallofhumanitiy.Singleton.SingletonFriend;

import java.util.List;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.MyViewHolder> {

    private Context mContext;
    private List<SingletonFriend> singletonFriendList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView friendName, friendSubName;
        public ImageView friendThumbnail;

        public MyViewHolder(View view) {
            super(view);
            friendName = view.findViewById(R.id.friendName);
            friendSubName = view.findViewById(R.id.friendSubName);
            friendThumbnail = view.findViewById(R.id.friendThumbnail);
        }
    }


    public FriendsAdapter(Context mContext, List<SingletonFriend> singletonFriendList) {
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
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final SingletonFriend singletonFriend = singletonFriendList.get(position);



        holder.friendName.setText(singletonFriend.getFriendName());
        holder.friendSubName.setText(singletonFriend.getFriendSubName());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(16));

        Glide.with(mContext).load(singletonFriend.getFriendThumbnail())
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