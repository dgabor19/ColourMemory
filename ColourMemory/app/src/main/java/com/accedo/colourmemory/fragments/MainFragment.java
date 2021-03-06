package com.accedo.colourmemory.fragments;

/**
 * Created by gabordudas on 18/04/16.
 * Copyright (c) 2015 ColourMemory. All rights reserved.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.accedo.colourmemory.HighScoresActivity;
import com.accedo.colourmemory.MainActivity;
import com.accedo.colourmemory.R;
import com.accedo.colourmemory.interfaces.OnScoringListener;
import com.accedo.colourmemory.utils.Constants;
import com.accedo.colourmemory.views.CardGridLayout;

/**
 * Fragment that stores and handles card grid table
 */
public class MainFragment extends BaseFragment implements OnScoringListener {
    public static final String TAG = MainFragment.class.getSimpleName();

    private CardGridLayout mGrid;
    private boolean mIsEnd = false;

    public MainFragment() {

    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mGrid = (CardGridLayout) view.findViewById(R.id.gridMain);
        mGrid.init(Constants.COLUMN_COUNT, Constants.ROW_COUNT, this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_action_high_scores:

                Intent intent = new Intent(mActivity, HighScoresActivity.class);

                mActivity.startActivityForResult(intent, Constants.REQUEST_CODE);

                break;
            case R.id.menu_action_reset:

                ((MainActivity)mActivity).reset();

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Resets the table
     */
    public void reset() {
        mGrid.init(Constants.COLUMN_COUNT, Constants.ROW_COUNT, this);
    }

    @Override
    public void onScore(int score, boolean isEnd) {
        mIsEnd = isEnd;
        mListener.onFragmentInteraction(TAG, MainActivity.InteractionType.SCORE, score, isEnd);
    }
}
