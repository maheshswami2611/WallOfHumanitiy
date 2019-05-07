package com.prometteur.wallofhumanitiy.fragments;
import android.app.Fragment;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.prometteur.wallofhumanitiy.other.FlowLayout;
import com.prometteur.wallofhumanitiy.adapters.TransmissionMemoriesAdapter;
import com.prometteur.wallofhumanitiy.activity.MainActivity;
import com.prometteur.wallofhumanitiy.R;
import com.prometteur.wallofhumanitiy.Singleton.SingletonTransmissionMemories;

import java.util.ArrayList;
import java.util.List;

public class FragmentTransmission extends Fragment {
    ImageView imgBack,imgAddVisitedPlace;
    private FlowLayout categoryLayout;
    ArrayList<String> categoryList = new ArrayList<>();

    private RecyclerView recyclerView;
    private TransmissionMemoriesAdapter adapter;
    private List<SingletonTransmissionMemories> singletonTransmissionMemoriesList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transmission, container, false);

        imgAddVisitedPlace = view.findViewById(R.id.imgAddVisitedPlace);
        imgAddVisitedPlace.setVisibility(View.GONE);
        imgBack = view.findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).pushFragment(new FragmentBottomMemories());
            }
        });


        categoryLayout = (FlowLayout) view.findViewById(R.id.flowBusiness);

        categoryList.add("Memories");
        categoryList.add("Opinions");
        categoryList.add("Moods");
        categoryList.add("Desires");
        addLayouts();


        recyclerView = (RecyclerView)view. findViewById(R.id.recycler_view);

        singletonTransmissionMemoriesList = new ArrayList<>();
        adapter = new TransmissionMemoriesAdapter(getActivity(), singletonTransmissionMemoriesList,imgAddVisitedPlace);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();
        return view;
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
                        //imageView.setVisibility(View.GONE);

                    } else {
                        selected[0] = true;
                        textView.setBackgroundResource(R.drawable.edittext_bg_green);
                        textView.setTextColor(Color.parseColor("#ffffff"));
                        //imageView.setVisibility(View.VISIBLE);
                    }
                }
            });

        }
    }

    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.album1,
                R.drawable.album3,
                R.drawable.album3,
                R.drawable.album5,
                R.drawable.album6};

        SingletonTransmissionMemories a = new SingletonTransmissionMemories("True Romance",  covers[0]);
        singletonTransmissionMemoriesList.add(a);

        a = new SingletonTransmissionMemories("Xscpae",  covers[1]);
        singletonTransmissionMemoriesList.add(a);

        a = new SingletonTransmissionMemories("Maroon 5", covers[2]);
        singletonTransmissionMemoriesList.add(a);


        a = new SingletonTransmissionMemories("Spring",  covers[2]);
        singletonTransmissionMemoriesList.add(a);


        a = new SingletonTransmissionMemories("Water Whale",  covers[3]);
        singletonTransmissionMemoriesList.add(a);


        adapter.notifyDataSetChanged();
    }

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