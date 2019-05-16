package com.prometteur.wallofhumanitiy.fragments;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
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
import com.prometteur.wallofhumanitiy.Singleton.NewsListData;
import com.prometteur.wallofhumanitiy.Singleton.NewsListDetails;

import java.util.ArrayList;

public class FragmentNewsDetails extends Fragment {

    Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_news_details, container, false);

        ArrayList<NewsListDetails> newsListDetailsArrayList = new ArrayList<>();
        int newsDataPosition = -1;
        if(getArguments()!=null) {
            newsListDetailsArrayList = (ArrayList<NewsListDetails>) getArguments().getSerializable("newsDataList");
            newsDataPosition = getArguments().getInt("newsDataPosition");

            Log.i("Serializable_NewsList", "\t" + newsListDetailsArrayList);

            TextView titleText = (TextView) view.findViewById(R.id.newsDetailsTitle);
            TextView newsTimeText = (TextView) view.findViewById(R.id.newsDetailsTime);
            TextView newsDescrText = (TextView) view.findViewById(R.id.newsDetailsDescription);
            TextView newsdetailsReactcount = (TextView) view.findViewById(R.id.newsdetails_react_count);//newsdetails_react_count
            ImageView newsImage = (ImageView) view.findViewById(R.id.news_imageview);

            titleText.setText(newsListDetailsArrayList.get(newsDataPosition).getNewsTitle().toString().trim());
            newsTimeText.setText(newsListDetailsArrayList.get(newsDataPosition).getNewsUpdateDate().trim());
            newsDescrText.setText(newsListDetailsArrayList.get(newsDataPosition).getNewsDetail().trim());
            newsdetailsReactcount.setText(newsListDetailsArrayList.get(newsDataPosition).getCntLikes().trim());

            RequestOptions requestOptions = new RequestOptions();
            requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(16));

            Glide.with(context).load(newsListDetailsArrayList.get(newsDataPosition).getNewsImage())
                    .apply(requestOptions)
                    .into(newsImage);
        }
        else
        {
            Toast.makeText(context, ""+new NewsListData().getMessage(), Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
}