package com.accedo.colourmemory.fragments;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.accedo.colourmemory.MainActivity;
import com.accedo.colourmemory.R;
import com.accedo.colourmemory.interfaces.OnCardFlipListener;
import com.accedo.colourmemory.utils.CardUtils;
import com.accedo.colourmemory.views.CardGridLayout;

/**
 * Created by gabordudas on 18/04/16.
 * Copyright (c) 2015 ColourMemory. All rights reserved.
 */
public class MainFragment extends Fragment implements OnCardFlipListener {
    public static final String TAG = MainFragment.class.getSimpleName();

    private MainActivity mActivity;
    private CardGridLayout mGrid;
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

        mGrid = (CardGridLayout) view.findViewById(R.id.gridMain);
        mGrid.init(this);

    }


    @Override
    public void onCardFlip(View view, int position) {
//        Toast.makeText(mActivity, "Card flipped on position " + holder.mPosition, Toast.LENGTH_SHORT).show();

//        Log.d(TAG, "CARD FLIPPED position " + position);


//
//        if (mAdapter != null) {

//            mAdapter.setCardFlipperEnabler(true);
//            mAdapter.notifyItemChanged(position);
//            mAdapter.getCard(position).setFaceUp(!mAdapter.getCard(position).isFaceUp());
//            mAdapter.notifyItemChanged(position);

//            List<Card> cards = mAdapter.getCards();

//            int faceUpCounter = 0;

//            for (int i = 0; i < cards.size(); i++) {
//                Card card = mAdapter.getCard(i);
//
//                if (card.isFaceUp()) {
//                    ++faceUpCounter;
//                }
//            }

//            final List<Integer> faceUpCards = mAdapter.getFaceUpCards();
//
//            if (faceUpCards.size() < 2) {
//                if (!faceUpCards.contains(position)) {
//                    faceUpCards.add(position);
//                    Log.d(TAG, "CARD ADDED " + faceUpCards);
//                }
//            }
//            if (faceUpCards.size() > 1) {
//                for (int i = 0; i < faceUpCards.size(); i++) {
//                    final int index = faceUpCards.get(i);
//
//                    mHandler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            Log.d(TAG, "CARD REMOVED " + faceUpCards + " index: " + index);
//                            mAdapter.setFlipEnabler(true);
//                            mAdapter.notifyItemChanged(index);
//                        }
//                    }, 1000);
//                }
//                faceUpCards.clear();
//            }


//            Log.d(TAG, "CARD FLIPPED faceUpCounter " + faceUpCounter);

//            if (faceUpCounter == 2) {
//                faceUpCounter = 0;
//                for (int i = 0; i < mAdapter.getCards().size(); i++) {
//                    Card c = mAdapter.getCard(i);
//
//                    if (c.isFaceUp()) {

//                        c.setFaceUp(false);
//
//                        final int finalI = i;
//
////                        mHandler.postDelayed(new Runnable() {
////                            @Override
////                            public void run() {
////                                CardUtils.flipOut(mActivity, holder.itemView, null);
//
////                                mHandler.postDelayed(new Runnable() {
////                                    @Override
////                                    public void run() {
//                                        Log.d(TAG, "CARD FLIPPED Notified item " + finalI);
//                                        mAdapter.notifyItemChanged(finalI);
////                                        holder.mFlipper.showPrevious();
////                                    }
////                                }, getResources().getInteger(R.integer.card_flip_time) / 2);
////                            }
////                        }, 1000);
//                    }
//                }
//            }
//        }
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
