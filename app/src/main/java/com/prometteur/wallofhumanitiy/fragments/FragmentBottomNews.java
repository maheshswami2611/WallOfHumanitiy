package com.prometteur.wallofhumanitiy.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.prometteur.wallofhumanitiy.Interface.APIInterface;
import com.prometteur.wallofhumanitiy.R;
import com.prometteur.wallofhumanitiy.Singleton.NewsListData;
import com.prometteur.wallofhumanitiy.Singleton.NewsListDetails;
import com.prometteur.wallofhumanitiy.Singleton.SingletonNews;
import com.prometteur.wallofhumanitiy.activity.MainActivity;
import com.prometteur.wallofhumanitiy.adapters.NewsAdapter;
import com.prometteur.wallofhumanitiy.helper.APIClient;
import com.prometteur.wallofhumanitiy.other.FlowLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentBottomNews extends Fragment implements View.OnClickListener {

    APIInterface apiInterface;
    private FlowLayout categoryLayout;
    ArrayList<String> categoryList = new ArrayList<>();

    private List<SingletonNews> newsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private NewsAdapter mAdapter;

    Context context;
    Set<String> categoryIdList = new HashSet<>();
    String newsTypeIds = "";
    ArrayList<NewsListDetails> newsListDetailsList = new ArrayList<>();
    SpotsDialog spotsDialog;
    private String newsLikeId = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_news, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

//        mAdapter = new NewsAdapter(getActivity(),newsList);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(mAdapter);
//
//        prepareNewsData();

        categoryLayout = (FlowLayout) view.findViewById(R.id.flowBusiness);

        categoryList.add("TOP STORIES");
        categoryList.add("INDIA");
        categoryList.add("WORLD");
        categoryList.add("BUSINESS");
        categoryList.add("SPORTS");
        categoryList.add("LIFESTYLE");
        addLayouts();
        spotsDialog = new SpotsDialog(context);
        spotsDialog.show();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences((MainActivity)getActivity());
        String user_id = preferences.getString("UserId", "");
        String user_session = preferences.getString("UserSession", "");
        if(newsTypeIds.equals(""))
            getNewsListDetails(user_id, user_session, "0");

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
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

                    }
                    else
                    {
                        selected[0] = true;
                        textView.setBackgroundResource(R.drawable.edittext_bg_green);
                        textView.setTextColor(Color.parseColor("#ffffff"));
                        imageView.setVisibility(View.VISIBLE);
                    }

                    callNewsListDetails(selected[0], textView);
                }
            });
        }
    }

    private void callNewsListDetails(boolean selectedTabStatus,TextView textView)
    {
        //Log.i("Selected_Names","\t"+textView.getText());

        if(selectedTabStatus == true) {
            if (textView.getText().toString().trim().equalsIgnoreCase("TOP STORIES")) {
                categoryIdList.add("1");
            } else if (textView.getText().toString().trim().equalsIgnoreCase("INDIA")) {
                categoryIdList.add("2");
            } else if (textView.getText().toString().trim().equalsIgnoreCase("WORLD")) {
                categoryIdList.add("3");
            } else if (textView.getText().toString().trim().equalsIgnoreCase("BUSINESS")) {
                categoryIdList.add("4");
            } else if (textView.getText().toString().trim().equalsIgnoreCase("SPORTS")) {
                categoryIdList.add("5");
            } else if (textView.getText().toString().trim().equalsIgnoreCase("LIFESTYLE")) {
                categoryIdList.add("6");
            }

            newsTypeIds = ""+TextUtils.join(",", categoryIdList);
        }
        else if(selectedTabStatus == false) {
            if (textView.getText().toString().trim().equalsIgnoreCase("TOP STORIES")) {
                categoryIdList.remove("1");
            } else if (textView.getText().toString().trim().equalsIgnoreCase("INDIA")) {
                categoryIdList.remove("2");
            } else if (textView.getText().toString().trim().equalsIgnoreCase("WORLD")) {
                categoryIdList.remove("3");
            } else if (textView.getText().toString().trim().equalsIgnoreCase("BUSINESS")) {
                categoryIdList.remove("4");
            } else if (textView.getText().toString().trim().equalsIgnoreCase("SPORTS")) {
                categoryIdList.remove("5");
            } else if (textView.getText().toString().trim().equalsIgnoreCase("LIFESTYLE")) {
                categoryIdList.remove("6");
            }

            newsTypeIds = ""+TextUtils.join(",", categoryIdList);
        }

        //Log.i("Selected_Names_Ids","\t"+newsTypeIds);
        spotsDialog.show();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences((MainActivity)getActivity());
        String user_id = preferences.getString("UserId", "");
        String user_session = preferences.getString("UserSession", "");
        Log.i("Selected_Params","\t"+user_id+"\t"+user_session+"\t"+newsTypeIds);
        if(newsTypeIds.equals(""))
            newsTypeIds = "0";

        //api calling to get news list
        getNewsListDetails(user_id, user_session, newsTypeIds);

    }

    private void setNewsDetailsListOnAdapter(ArrayList<NewsListDetails> newsListDetailsList)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences((MainActivity)getActivity());
        mAdapter = new NewsAdapter(getActivity(),newsListDetailsList, preferences);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }


    private void getNewsListDetails(String user_id, String user_session, String newsTypeIds)
    {
        newsListDetailsList.clear();
        Call<NewsListData> call = apiInterface.getNewsList(user_id, user_session, newsTypeIds);
        call.enqueue(new Callback<NewsListData>() {
            @Override
            public void onResponse(Call<NewsListData> call, Response<NewsListData> response) {
                spotsDialog.dismiss();
                if(response.isSuccessful())
                {
                    newsListDetailsList = (ArrayList<NewsListDetails>) response.body().getMessage();

                    setNewsDetailsListOnAdapter(newsListDetailsList);
                }
                if(newsListDetailsList.isEmpty())
                {
                    Toast.makeText(context, ""+context.getResources().getString(R.string.data_not_available), Toast.LENGTH_SHORT).show();
                }
                Log.i("Response_News_list_1","\t"+newsListDetailsList.size());

            }

            @Override
            public void onFailure(Call<NewsListData> call, Throwable t) {
                spotsDialog.dismiss();
                //Failed to get news data
                Toast.makeText(context, ""+context.getResources().getString(R.string.failed_to_connect_to_server), Toast.LENGTH_SHORT).show();
                Log.i("Response_News_list_2","\t"+newsListDetailsList.size());

            }
        });
    }

}