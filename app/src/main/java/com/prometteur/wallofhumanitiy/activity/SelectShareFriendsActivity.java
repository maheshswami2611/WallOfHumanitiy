package com.prometteur.wallofhumanitiy.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.prometteur.wallofhumanitiy.R;
import com.prometteur.wallofhumanitiy.Singleton.SingletonFriend;
import com.prometteur.wallofhumanitiy.fragments.FriendsAdapter;

import java.util.ArrayList;
import java.util.List;

public class SelectShareFriendsActivity extends AppCompatActivity {
    ImageView imgBack;
    EditText edtFriendSearch;

    private List<SingletonFriend> friendList = new ArrayList<>();
    private RecyclerView recyclerView;
    private FriendsAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_select_share_friends);
        imgBack = findViewById(R.id.imgBack);
        edtFriendSearch = findViewById(R.id.edtFriendSearch);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new FriendsAdapter(SelectShareFriendsActivity.this,friendList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareFriendData();

    }

    private void prepareFriendData() {
        int[] covers = new int[]{
                R.drawable.album1,
                R.drawable.album3,
                R.drawable.album5,
                R.drawable.album6};
        SingletonFriend singletonFriend = new SingletonFriend( covers[0],"Fury Road", "Action & Adventure");
        friendList.add(singletonFriend);

        singletonFriend = new SingletonFriend(covers[1],"Inside Out", "Kids & Family");
        friendList.add(singletonFriend);

        singletonFriend = new SingletonFriend(covers[2],"Star Wars", "Action");
        friendList.add(singletonFriend);

        singletonFriend = new SingletonFriend(covers[3],"Shaun the Sheep", "Animation");
        friendList.add(singletonFriend);

        singletonFriend = new SingletonFriend(covers[1],"The Martian", "Science Fiction");
        friendList.add(singletonFriend);

        singletonFriend = new SingletonFriend(covers[2],"Mission", "Action");
        friendList.add(singletonFriend);

        singletonFriend = new SingletonFriend(covers[0],"Up", "Animation");
        friendList.add(singletonFriend);

        mAdapter.notifyDataSetChanged();
}
}
