package com.prometteur.wallofhumanitiy.adapters;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import com.prometteur.wallofhumanitiy.activity.MainActivity;
import com.prometteur.wallofhumanitiy.R;
import com.prometteur.wallofhumanitiy.Singleton.SingletonNotification;
import com.prometteur.wallofhumanitiy.fragments.FragmentFriendReqDetails;

import java.util.List;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {

    private Context mContext;
    private List<SingletonNotification> singletonNotificationList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView notifTitle, notifTime;
        public ImageView notifThumbnail,notifAdd,notifRemove;

        public MyViewHolder(View view) {
            super(view);
            notifTitle = view.findViewById(R.id.notifTitle);
            notifTime = view.findViewById(R.id.notifTime);
            notifThumbnail = view.findViewById(R.id.notifThumbnail);
            notifAdd = view.findViewById(R.id.notifAdd);
            notifRemove = view.findViewById(R.id.notifRemove);
        }
    }


    public NotificationAdapter(Context mContext, List<SingletonNotification> singletonFriendList) {
        this.mContext = mContext;
        this.singletonNotificationList = singletonFriendList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_notification_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final SingletonNotification singletonNotification = singletonNotificationList.get(position);

        if(position==0){
            holder.notifAdd.setVisibility(View.VISIBLE);
            holder.notifRemove.setVisibility(View.VISIBLE);
            holder.notifAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity)mContext).pushFragment(new FragmentFriendReqDetails());

                }
            });
        }else {
            holder.notifAdd.setVisibility(View.GONE);
            holder.notifRemove.setVisibility(View.GONE);
        }

        holder.notifTitle.setText(singletonNotification.getNotifTitle());
        holder.notifTitle.setSelected(true);
        holder.notifTitle.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        holder.notifTitle.setSingleLine(true);
        holder.notifTitle.setMarqueeRepeatLimit(1);
        holder.notifTime.setText(singletonNotification.getNotifTime());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(16));

        Glide.with(mContext).load(singletonNotification.getNotifThumbnail())
                .apply(requestOptions)
                .into(holder.notifThumbnail);


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
        return singletonNotificationList.size();
    }
}