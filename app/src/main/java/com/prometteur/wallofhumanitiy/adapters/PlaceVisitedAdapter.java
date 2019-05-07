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

import com.prometteur.wallofhumanitiy.R;
import com.prometteur.wallofhumanitiy.Singleton.SingletonVisitedPlaces;

import java.util.List;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class PlaceVisitedAdapter extends RecyclerView.Adapter<PlaceVisitedAdapter.MyViewHolder> {

    private Context mContext;
    private List<SingletonVisitedPlaces> singletonPlaceList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView placeTitle, placeLocation,placeDate,placeTime;

        public MyViewHolder(View view) {
            super(view);
            placeTitle = view.findViewById(R.id.placeTitle);
            placeLocation = view.findViewById(R.id.placeLocation);
            placeDate = view.findViewById(R.id.placeDate);
            placeTime = view.findViewById(R.id.placeTime);
        }
    }


    public PlaceVisitedAdapter(Context mContext, List<SingletonVisitedPlaces> singletonPlaceList) {
        this.mContext = mContext;
        this.singletonPlaceList = singletonPlaceList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_places_visited_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final SingletonVisitedPlaces singletonVisitedPlaces = singletonPlaceList.get(position);

        holder.placeTitle.setText(singletonVisitedPlaces.getPlaceTitle());
        holder.placeLocation.setText(singletonVisitedPlaces.getPlaceLocation());
        holder.placeDate.setText(singletonVisitedPlaces.getPlaceDate());
        holder.placeTime.setText(singletonVisitedPlaces.getPlaceTime());


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
        return singletonPlaceList.size();
    }
}