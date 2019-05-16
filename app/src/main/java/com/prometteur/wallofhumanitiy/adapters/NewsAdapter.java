package com.prometteur.wallofhumanitiy.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.prometteur.wallofhumanitiy.Interface.APIInterface;
import com.prometteur.wallofhumanitiy.R;
import com.prometteur.wallofhumanitiy.Singleton.NewsListData;
import com.prometteur.wallofhumanitiy.Singleton.NewsListDetails;
import com.prometteur.wallofhumanitiy.Singleton.SingletonNews;
import com.prometteur.wallofhumanitiy.activity.MainActivity;
import com.prometteur.wallofhumanitiy.fragments.FragmentNewsDetails;
import com.prometteur.wallofhumanitiy.helper.APIClient;
import com.prometteur.wallofhumanitiy.other.StatusResultResponce;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private Context mContext;
    private List<SingletonNews> singletonNewsList;
    APIInterface apiInterface;
    SharedPreferences preferences;
    MyViewHolder myViewHolder;


    private List<NewsListDetails> newsListDetailsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView newsTitle, newsSubName,txtReadNewsStory, reactCountText, reactCount;
        public ImageView newsThumbnail;

        public MyViewHolder(View view) {
            super(view);
            newsTitle = view.findViewById(R.id.newsTitle);
            newsSubName = view.findViewById(R.id.newsTime);
            newsThumbnail = view.findViewById(R.id.newsThumbnail);
            txtReadNewsStory = view.findViewById(R.id.txtReadNewsStory);
            reactCountText = (TextView) view.findViewById(R.id.react_count_text);
            reactCount = (TextView) view.findViewById(R.id.react_count);
        }
    }


/*
    public NewsAdapter(Context mContext, List<SingletonNews> singletonNewsList) {
        this.mContext = mContext;
        this.singletonNewsList = singletonNewsList;
    }
*/

    public NewsAdapter(Context mContext, List<NewsListDetails> newsListDetailsList, SharedPreferences preferences) {
        this.mContext = mContext;
        //this.singletonNewsList = singletonNewsList;
        this.newsListDetailsList = newsListDetailsList;
        apiInterface = APIClient.getClient().create(APIInterface.class);
        this.preferences = preferences;
    }

    @Override
    public int getItemCount() {
        //return singletonNewsList.size();
        return newsListDetailsList.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_news_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
/*
        final SingletonNews singletonNews = singletonNewsList.get(position);

        holder.newsTitle.setText(singletonNews.getNewsTitle());
        holder.newsSubName.setText(singletonNews.getNewsTime());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(16));

        Glide.with(mContext).load(singletonNews.getNewsThumbnail())
                .apply(requestOptions)
                .into(holder.newsThumbnail);
*/

        myViewHolder = holder;
        holder.newsTitle.setText(newsListDetailsList.get(position).getNewsTitle());
        holder.newsSubName.setText(newsListDetailsList.get(position).getNewsUpdateDate());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(16));

        Glide.with(mContext).load(newsListDetailsList.get(position).getNewsImage())
                .apply(requestOptions)
                .into(holder.newsThumbnail);


        holder.txtReadNewsStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle arguments = new Bundle();
                arguments.putSerializable("newsDataList", (Serializable) newsListDetailsList);
                arguments.putInt("newsDataPosition", position);
                FragmentNewsDetails fragmentNewsDetails = new FragmentNewsDetails();
                fragmentNewsDetails.setArguments(arguments);

                //((MainActivity)mContext).pushFragment(new FragmentNewsDetails());
                ((MainActivity)mContext).pushFragment(fragmentNewsDetails);


            }
        });

        holder.reactCount.setText(newsListDetailsList.get(position).getCntLikes().trim().toString());
        if(newsListDetailsList.get(position).getLikeStatus().equals("1")) {
            //holder.reactCountText.setTextColor(Color.GREEN);
            myViewHolder.reactCountText.setTextColor(mContext.getResources().getColor(R.color.colorDarkGreen));
            holder.reactCountText.setEnabled(false);
        }

        holder.reactCountText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                newsListDetailsList.get(position).setCntLikes(String.valueOf(Integer.parseInt(newsListDetailsList.get(position).getCntLikes())+1));
//                String cntLikes = newsListDetailsList.get(position).getCntLikes();
//                holder.reactCount.setText(cntLikes);
//                Log.i("React_News_count_1","\t"+cntLikes);
                boolean likeStatus = false;
                getNewsReactCount(holder, position);
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

    public void getNewsReactCount(final MyViewHolder myViewHolder, final int position)
    {
        String user_id = preferences.getString("UserId", "");
        String user_session = preferences.getString("UserSession", "");

        String news_likes_newsid = newsListDetailsList.get(position).getNewsId().toString();

        Log.i("newsAdpt_userid_session","\t"+user_id+"\t"+user_session+"\t"+news_likes_newsid);

        Call<StatusResultResponce> call = apiInterface.getNewsCountStatus(news_likes_newsid, user_id, user_session);
        call.enqueue(new Callback<StatusResultResponce>() {
            @Override
            public void onResponse(Call<StatusResultResponce> call, Response<StatusResultResponce> response) {
                //spotsDialog.dismiss();
                if(response.isSuccessful())
                {
                    if(response.body().getStatus().trim().toString().equals("1"))
                    {
                        newsListDetailsList.get(position).setCntLikes(String.valueOf(Integer.parseInt(newsListDetailsList.get(position).getCntLikes())+1));
                        myViewHolder.reactCount.setText(newsListDetailsList.get(position).getCntLikes().trim().toString());
                        if(newsListDetailsList.get(position).getLikeStatus().equals("1")) {
                            myViewHolder.reactCountText.setTextColor(mContext.getResources().getColor(R.color.colorDarkGreen));
                            myViewHolder.reactCountText.setEnabled(false);
                        }
                    }
                    else if(response.body().getStatus().trim().toString().equals("0"))
                    {
                        Toast.makeText(mContext, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                Log.i("Response_News_count_1","\t"+response.body().getStatus());

            }

            @Override
            public void onFailure(Call<StatusResultResponce> call, Throwable t) {
                //spotsDialog.dismiss();
                //Failed to get news data
                Toast.makeText(mContext, ""+new NewsListData().getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("Response_News_count_2","\t");

            }
        });

    }

}