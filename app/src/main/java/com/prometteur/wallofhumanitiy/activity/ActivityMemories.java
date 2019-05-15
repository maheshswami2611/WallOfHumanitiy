package com.prometteur.wallofhumanitiy.activity;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

//import com.prometteur.wallofhumanitiy.Singleton.MemoryListResp;
import com.prometteur.wallofhumanitiy.adapters.MemoriesAdapter;
import com.prometteur.wallofhumanitiy.R;
import com.prometteur.wallofhumanitiy.other.MemoryListResp;

import java.util.ArrayList;
import java.util.List;

public class ActivityMemories extends AppCompatActivity {
    ImageView imgBack;
    private RecyclerView recyclerView;
    private MemoriesAdapter adapter;
    private List<MemoryListResp.Result> memoryListRespList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_memories);
        imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        memoryListRespList = new ArrayList<>();
        adapter = new MemoriesAdapter(this, memoryListRespList, new MemoriesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MemoryListResp.Result item) {

            }
        });

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        //prepareAlbums();
/*
        try {
            Glide.with(this).load(R.drawable.cover).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

/*

    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.album1,
                R.drawable.album3,
                R.drawable.album5,
                R.drawable.album6};

        MemoryListResp a = new MemoryListResp("True Romance", "15", "1", covers[0]);
        memoryListRespList.add(a);

        a = new MemoryListResp("Xscpae", "55", "30", covers[1]);
        memoryListRespList.add(a);

        a = new MemoryListResp("Maroon 5", "23", "100", covers[2]);
        memoryListRespList.add(a);


        a = new MemoryListResp("", "", "", covers[2]);
        memoryListRespList.add(a);


        adapter.notifyDataSetChanged();
    }
*/

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
