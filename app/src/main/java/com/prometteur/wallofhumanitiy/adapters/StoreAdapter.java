package com.prometteur.wallofhumanitiy.adapters;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.prometteur.wallofhumanitiy.activity.MainActivity;
import com.prometteur.wallofhumanitiy.R;
import com.prometteur.wallofhumanitiy.Singleton.SingletonStore;
import com.prometteur.wallofhumanitiy.fragments.FragmentStorePieChart;
import com.prometteur.wallofhumanitiy.other.StoreResponce;

import java.util.List;


public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.MyViewHolder> {

    private Context mContext;
    private List<StoreResponce.TotalPlansGb> singletonStoreList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView storeTitle;
        Button btnBuy;

        public MyViewHolder(View view) {
            super(view);
            storeTitle = view.findViewById(R.id.storeTitle);
            btnBuy = view.findViewById(R.id.btnBuy);
        }
    }


    public StoreAdapter(Context mContext, List<StoreResponce.TotalPlansGb> singletonStoreList) {
        this.mContext = mContext;
        this.singletonStoreList = singletonStoreList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_store_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final StoreResponce.TotalPlansGb singletonStore = singletonStoreList.get(position);



        holder.storeTitle.setText(singletonStore.getPlansGbValue() +" GB = "+singletonStore.getPlansGbRupee()+" $");

        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(16));


        holder.btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)mContext).pushFragment(new FragmentStorePieChart());

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
        return singletonStoreList.size();
    }
}