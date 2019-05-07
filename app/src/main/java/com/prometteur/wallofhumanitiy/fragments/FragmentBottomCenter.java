package com.prometteur.wallofhumanitiy.fragments;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prometteur.wallofhumanitiy.adapters.ChatAdapter;
import com.prometteur.wallofhumanitiy.R;
import com.prometteur.wallofhumanitiy.Singleton.SingletonChat;

import java.util.ArrayList;
import java.util.List;

public class FragmentBottomCenter extends Fragment {
ChatAdapter mAdapter;
    private List<SingletonChat> messageList = new ArrayList<>();
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_center, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        mAdapter = new ChatAdapter(getActivity().getApplicationContext(),messageList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        prepareMessageData();

        return view;

    }
    private void prepareMessageData() {
        int[] covers = new int[]{
                R.drawable.profile,
                R.drawable.profile,
                R.drawable.profile,
                R.drawable.profile};
        SingletonChat singletonChat = new SingletonChat( covers[0],"25 Aug 2017", "Lorem Ipsum", "Lorem Ipsum is simply dummy text of printing industry");
        messageList.add(singletonChat);

        singletonChat = new SingletonChat(covers[1],"25 Aug 2017", "Lorem Ipsum", "Lorem Ipsum is simply dummy text of printing industry");
        messageList.add(singletonChat);

        singletonChat = new SingletonChat(covers[2],"25 Aug 2017", "Lorem Ipsum", "Lorem Ipsum is simply dummy text of printing industry");
        messageList.add(singletonChat);

        singletonChat = new SingletonChat(covers[3],"25 Aug 2017", "Lorem Ipsum", "Lorem Ipsum is simply dummy text of printing industry");
        messageList.add(singletonChat);

        singletonChat = new SingletonChat(covers[1],"25 Aug 2017", "Lorem Ipsum", "Lorem Ipsum is simply dummy text of printing industry");
        messageList.add(singletonChat);

        singletonChat = new SingletonChat(covers[2],"25 Aug 2017", "Lorem Ipsum", "Lorem Ipsum is simply dummy text of printing industry");
        messageList.add(singletonChat);

        singletonChat = new SingletonChat(covers[0],"25 Aug 2017", "Lorem Ipsum", "Lorem Ipsum is simply dummy text of printing industry");
        messageList.add(singletonChat);

        mAdapter.notifyDataSetChanged();
    }
}