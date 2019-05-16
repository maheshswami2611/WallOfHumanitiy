package com.prometteur.wallofhumanitiy.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.prometteur.wallofhumanitiy.Interface.APIInterface;
import com.prometteur.wallofhumanitiy.R;
import com.prometteur.wallofhumanitiy.Singleton.SingletonStore;
import com.prometteur.wallofhumanitiy.activity.SelectShareFriendsActivity;
import com.prometteur.wallofhumanitiy.adapters.StoreAdapter;
import com.prometteur.wallofhumanitiy.adapters.StoreQPurchaseAdapter;
import com.prometteur.wallofhumanitiy.helper.APIClient;
import com.prometteur.wallofhumanitiy.other.FriendListResp;
import com.prometteur.wallofhumanitiy.other.StoreResponce;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentBottomStore extends Fragment implements View.OnClickListener {
    private List<StoreResponce.TotalPlansGb> gbPlanList = new ArrayList<>();
    private List<StoreResponce.TotalPlansMonth> monthPlanList = new ArrayList<>();
    private RecyclerView recyclerView,rv_quick_purchase;
    private StoreAdapter mAdapter;
    private StoreQPurchaseAdapter storeQPurchaseAdapter;
    SpotsDialog spotsDialog;
    APIInterface apiInterface;
    Context mContext;
    String mainuser_id = "";
    String mainuser_session="";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_store, container, false);

        mContext =getActivity();

        apiInterface = APIClient.getClient().create(APIInterface.class);
        spotsDialog = new SpotsDialog(getActivity());
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        rv_quick_purchase = (RecyclerView) view.findViewById(R.id.rv_quick_purchase);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mainuser_id = preferences.getString("UserId", "");

        mainuser_session = preferences.getString("UserSession", "");

        getStoreDataList(mainuser_id,mainuser_session);
        return view;
    }

    @Override
    public void onClick(View v) {
        String tag = v.getTag().toString();
        Toast.makeText(getActivity(), "" + tag, Toast.LENGTH_SHORT).show();
    }




    private void getStoreDataList(final String store_userid, final String user_session) {
        spotsDialog.show();
        Call<StoreResponce> call = apiInterface.getStoreDataList(store_userid, user_session);
        call.enqueue(new Callback<StoreResponce>() {
            @Override
            public void onResponse(Call<StoreResponce> call, Response<StoreResponce> response) {

                try {

                    StoreResponce resource = response.body();


                    if (resource.getStatus().equals("1")) {
                        gbPlanList = resource.getTotalPlansGb();
                        monthPlanList = resource.getTotalPlansMonth();


                        mAdapter = new StoreAdapter(getActivity(), gbPlanList);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(mAdapter);

                        storeQPurchaseAdapter = new StoreQPurchaseAdapter(getActivity(), monthPlanList);
                        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity().getApplicationContext());
                        rv_quick_purchase.setLayoutManager(mLayoutManager1);
                        rv_quick_purchase.setItemAnimator(new DefaultItemAnimator());
                        rv_quick_purchase.setAdapter(storeQPurchaseAdapter);


                    } else if (resource.getStatus().toString().equals("2")) {
                        Toast.makeText(mContext, "" + resource.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    if (null != spotsDialog && spotsDialog.isShowing()) {
                        spotsDialog.dismiss();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("Error", e.toString());
                }
            }

            @Override
            public void onFailure(Call<StoreResponce> call, Throwable t) {
                call.cancel();
            }
        });
    }



}