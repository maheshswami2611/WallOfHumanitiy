package com.prometteur.wallofhumanitiy.adapters;

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
import com.prometteur.wallofhumanitiy.activity.MainActivity;
import com.prometteur.wallofhumanitiy.R;
import com.prometteur.wallofhumanitiy.Singleton.SingletonNews;
import com.prometteur.wallofhumanitiy.fragments.FragmentNewsDetails;

import java.util.List;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private Context mContext;
    private List<SingletonNews> singletonNewsList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView newsTitle, newsSubName,txtReadNewsStory;
        public ImageView newsThumbnail;

        public MyViewHolder(View view) {
            super(view);
            newsTitle = view.findViewById(R.id.newsTitle);
            newsSubName = view.findViewById(R.id.newsTime);
            newsThumbnail = view.findViewById(R.id.newsThumbnail);
            txtReadNewsStory = view.findViewById(R.id.txtReadNewsStory);
        }
    }


    public NewsAdapter(Context mContext, List<SingletonNews> singletonNewsList) {
        this.mContext = mContext;
        this.singletonNewsList = singletonNewsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_news_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final SingletonNews singletonNews = singletonNewsList.get(position);



        holder.newsTitle.setText(singletonNews.getNewsTitle());
        holder.newsSubName.setText(singletonNews.getNewsTime());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(16));

        Glide.with(mContext).load(singletonNews.getNewsThumbnail())
                .apply(requestOptions)
                .into(holder.newsThumbnail);


        holder.txtReadNewsStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)mContext).pushFragment(new FragmentNewsDetails());

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
        return singletonNewsList.size();
    }
}