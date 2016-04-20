package com.accedo.colourmemory.fragments;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.accedo.colourmemory.MainActivity;
import com.accedo.colourmemory.R;
import com.accedo.colourmemory.adapters.GridAdapter;
import com.accedo.colourmemory.interfaces.OnCardFlipListener;
import com.accedo.colourmemory.models.Card;
import com.accedo.colourmemory.utils.CardUtils;
import com.accedo.colourmemory.utils.Constants;
import com.accedo.colourmemory.utils.Converter;
import com.nineoldandroids.animation.Animator;

/**
 * Created by gabordudas on 18/04/16.
 * Copyright (c) 2015 ColourMemory. All rights reserved.
 */
public class MainFragment extends Fragment implements OnCardFlipListener {
    public static final String TAG = MainFragment.class.getSimpleName();

    private MainActivity mActivity;
    private RecyclerView mGrid;
    private GridAdapter mAdapter;
    private GridLayoutManager mLayoutManager;
    private Handler mHandler;

    public MainFragment() {

    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = (MainActivity) getActivity();
        mHandler = mActivity.getHandler();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_framgent, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mGrid = (RecyclerView) view.findViewById(R.id.gridMain);
        mAdapter = new GridAdapter(mActivity, this);
        mLayoutManager = new GridLayoutManager(mActivity, Constants.COLUMN_COUNT);

        mGrid.setAdapter(mAdapter);
        mGrid.setLayoutManager(mLayoutManager);
        mGrid.setHasFixedSize(true);
        mGrid.addItemDecoration(
                new GridSpacingItemDecoration(
                        Constants.COLUMN_COUNT,
                        (int) Converter.convertDpToPixels(mActivity, 10),
                        true));

    }

    int faceUpCounter = 0;

    @Override
    public void onCardFlip(View view, final GridAdapter.ViewHolder holder, final int position) {
//        Toast.makeText(mActivity, "Card flipped on position " + holder.mPosition, Toast.LENGTH_SHORT).show();

        Log.d(TAG, "CARD FLIPPED position " + position);

        animateCardFlip(holder, position);

        if (mAdapter != null) {
            Card card = mAdapter.getCard(position);

            if (card.isFaceUp()) {
                ++faceUpCounter;
            } else {
                --faceUpCounter;
            }


            Log.d(TAG, "CARD FLIPPED faceUpCounter " + faceUpCounter);

            if (faceUpCounter == 2) {
                faceUpCounter = 0;
                for (Card c : mAdapter.getCards()) {

                    if (c.isFaceUp()) {
                        Log.d(TAG, "CARD FLIPPED BACK " + c.getColour() + " isFaceUp " + c.isFaceUp());
                        c.setFaceUp(false);

                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                CardUtils.flipOut(mActivity, holder.itemView, null);
                                mHandler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        holder.mFlipper.showPrevious();
                                    }
                                }, getResources().getInteger(R.integer.card_flip_time) / 2);
                            }
                        }, 1000);
                    }
                }
            }
        }
    }

    private void animateCardFlip(final GridAdapter.ViewHolder holder, final int position) {

        if (mAdapter != null) {
            Card card = mAdapter.getCard(position);

            if (!card.isFaceUp()) {
                card.setFaceUp(true);

                CardUtils.flipIn(mActivity, holder.itemView, null);
                holder.itemView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        holder.mFlipper.showNext();
                    }
                }, getResources().getInteger(R.integer.card_flip_time) / 2);

            } else {
                card.setFaceUp(false);

                CardUtils.flipOut(mActivity, holder.itemView, null);
                holder.itemView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        holder.mFlipper.showPrevious();
                    }
                }, getResources().getInteger(R.integer.card_flip_time) / 2);
            }
        }
    }


    public static class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

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
}
