package com.prometteur.wallofhumanitiy.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.prometteur.wallofhumanitiy.R;
import com.prometteur.wallofhumanitiy.Singleton.SingletonStore;
import com.prometteur.wallofhumanitiy.adapters.StoreAdapter;
import com.prometteur.wallofhumanitiy.adapters.StoreQPurchaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentBottomStore extends Fragment implements View.OnClickListener {
    private List<SingletonStore> storeList = new ArrayList<>();
    private RecyclerView recyclerView,rv_quick_purchase;
    private StoreAdapter mAdapter;
    private StoreQPurchaseAdapter storeQPurchaseAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_store, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        rv_quick_purchase = (RecyclerView) view.findViewById(R.id.rv_quick_purchase);

        mAdapter = new StoreAdapter(getActivity(), storeList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        storeQPurchaseAdapter = new StoreQPurchaseAdapter(getActivity(), storeList);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity().getApplicationContext());
        rv_quick_purchase.setLayoutManager(mLayoutManager1);
        rv_quick_purchase.setItemAnimator(new DefaultItemAnimator());
        rv_quick_purchase.setAdapter(storeQPurchaseAdapter);

        prepareStoreData();
        return view;
    }

    @Override
    public void onClick(View v) {
        String tag = v.getTag().toString();
        Toast.makeText(getActivity(), "" + tag, Toast.LENGTH_SHORT).show();
    }

    private void prepareStoreData() {

        SingletonStore singletonStore = new SingletonStore( "1 GB = 0.25 $", "2015");
        storeList.add(singletonStore);

        singletonStore = new SingletonStore( "1 GB = 0.25 $", "2015");
        storeList.add(singletonStore);

        singletonStore = new SingletonStore( "4 GB = 0.99 $", "2015");
        storeList.add(singletonStore);

        singletonStore = new SingletonStore( "9 GB = 1.99 $", "2015");
        storeList.add(singletonStore);

        singletonStore = new SingletonStore( "13 GB = 2.99 $", "2015");
        storeList.add(singletonStore);

        mAdapter.notifyDataSetChanged();
    }
}