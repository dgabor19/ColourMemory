package com.accedo.colourmemory.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.accedo.colourmemory.HighScoresActivity;
import com.accedo.colourmemory.MainActivity;
import com.accedo.colourmemory.R;
import com.accedo.colourmemory.interfaces.OnScoringListener;
import com.accedo.colourmemory.utils.Constants;
import com.accedo.colourmemory.views.CardGridLayout;

/**
 * Created by gabordudas on 18/04/16.
 * Copyright (c) 2015 ColourMemory. All rights reserved.
 */
public class MainFragment extends BaseFragment implements OnScoringListener {
    public static final String TAG = MainFragment.class.getSimpleName();

    private CardGridLayout mGrid;

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

                startActivity(intent);

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void reset() {
        mGrid.init(Constants.COLUMN_COUNT, Constants.ROW_COUNT, this);
    }

    @Override
    public void onScore(int point, boolean isEnd) {
        mListener.onFragmentInteraction(TAG, MainActivity.InteractionType.SCORE, point, isEnd);
    }
}
