package com.prometteur.wallofhumanitiy.adapters;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.prometteur.wallofhumanitiy.R;
import com.prometteur.wallofhumanitiy.Singleton.SingletonStore;
import com.prometteur.wallofhumanitiy.other.StoreResponce;

import java.util.List;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class StoreQPurchaseAdapter extends RecyclerView.Adapter<StoreQPurchaseAdapter.MyViewHolder> {

    private Context mContext;
    private List<StoreResponce.TotalPlansMonth> singletonStoreList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView storeTitle;

        public MyViewHolder(View view) {
            super(view);
            storeTitle = view.findViewById(R.id.storeTitle);
        }
    }


    public StoreQPurchaseAdapter(Context mContext, List<StoreResponce.TotalPlansMonth> singletonStoreList) {
        this.mContext = mContext;
        this.singletonStoreList = singletonStoreList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_store_q_purchase_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final StoreResponce.TotalPlansMonth singletonStore = singletonStoreList.get(position);



        holder.storeTitle.setText(singletonStore.getPlansMonthValue() +" GB = "+singletonStore.getPlansMonthRupee()+" $");

        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(16));


       /* holder.txtReadStoreStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)mContext).pushFragment(new FragmentStoreDetails());

            }
        });*/
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
        return singletonStoreList.size();
    }
}