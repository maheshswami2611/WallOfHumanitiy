package com.prometteur.wallofhumanitiy.fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.prometteur.wallofhumanitiy.other.FlowLayout;
import com.prometteur.wallofhumanitiy.adapters.NewsAdapter;
import com.prometteur.wallofhumanitiy.R;
import com.prometteur.wallofhumanitiy.Singleton.SingletonNews;

import java.util.ArrayList;
import java.util.List;

public class FragmentBottomNews extends Fragment implements View.OnClickListener {
    private FlowLayout categoryLayout;
    ArrayList<String> categoryList = new ArrayList<>();


    private List<SingletonNews> newsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private NewsAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_news, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        mAdapter = new NewsAdapter(getActivity(),newsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareNewsData();


        categoryLayout = (FlowLayout) view.findViewById(R.id.flowBusiness);

        categoryList.add("TOP STORIES");
        categoryList.add("INDIA");
        categoryList.add("WORLD");
        categoryList.add("BUSINESS");
        categoryList.add("SPORTS");
        categoryList.add("LIFESTYLE");
        addLayouts();

        return view;
    }

    @Override
    public void onClick(View v) {
        String tag=v.getTag().toString();
        Toast.makeText(getActivity(), ""+tag, Toast.LENGTH_SHORT).show();
    }


    private void addLayouts() {
        if (categoryList == null) {
            categoryList = new ArrayList<>();
        }
        categoryLayout.removeAllViews();
        for (int i = 0; i < categoryList.size(); i++) {

            final boolean[] selected = {false};
            View view = getActivity().getLayoutInflater().inflate(R.layout.text_view, null);
            final TextView textView = (TextView) view.findViewById(R.id.tvText);
            final ImageView imageView = (ImageView) view.findViewById(R.id.selectedBubble);
            imageView.setVisibility(View.GONE);
            if (i % 5 == 0) {
                textView.setText(categoryList.get(i));
            } else {
                textView.setText(categoryList.get(i));
            }
            textView.setBackgroundResource(R.drawable.edittext_bg);
            textView.setTextColor(Color.parseColor("#278B2B"));
            if(i<=50){
                categoryLayout.addView(view);
            }

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (selected[0]) {
                        selected[0] = false;
                        textView.setBackgroundResource(R.drawable.edittext_bg);
                        textView.setTextColor(Color.parseColor("#278B2B"));
                        imageView.setVisibility(View.GONE);

                    } else {
                        selected[0] = true;
                        textView.setBackgroundResource(R.drawable.edittext_bg_green);
                        textView.setTextColor(Color.parseColor("#ffffff"));
                        imageView.setVisibility(View.VISIBLE);
                    }
                }
            });

        }
    }

    private void prepareNewsData() {
        int[] covers = new int[]{
                R.drawable.album1,
                R.drawable.album3,
                R.drawable.album5,
                R.drawable.album6};

        SingletonNews news = new SingletonNews(covers[0],"Action & Adventure", "2015");
        newsList.add(news);

        news = new SingletonNews(covers[1],"Animation, Kids & Family", "2015");
        newsList.add(news);

        news = new SingletonNews(covers[2],"Action", "2015");
        newsList.add(news);

        news = new SingletonNews(covers[3], "Animation", "2015");
        newsList.add(news);

        news = new SingletonNews(covers[1], "Science Fiction & Fantasy", "2015");
        newsList.add(news);

        news = new SingletonNews(covers[0],"Action", "2015");
        newsList.add(news);

        news = new SingletonNews( covers[3],"Animation", "2009");
        newsList.add(news);



        mAdapter.notifyDataSetChanged();
    }
}