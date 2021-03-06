package com.accedo.colourmemory.fragments;

/**
 * Created by gabordudas on 24/04/16.
 * Copyright (c) 2015 ColourMemory. All rights reserved.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.accedo.colourmemory.BaseActivity;
import com.accedo.colourmemory.R;
import com.accedo.colourmemory.models.Score;
import com.accedo.colourmemory.views.ScoreTableLayout;

import java.util.List;

/**
 * Fragment that stores and handles high scores
 */
public class HighScoresFragment extends BaseFragment {
    public static final String TAG = HighScoresFragment.class.getSimpleName();

    private ScoreTableLayout mScoreLayout;

    public HighScoresFragment() {

    }

    public static HighScoresFragment newInstance() {
        return new HighScoresFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_high_scores, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mScoreLayout = (ScoreTableLayout) view.findViewById(R.id.scoreTable);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mListener.onFragmentInteraction(TAG, BaseActivity.InteractionType.BACK);

        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Sets high scores
     * @param scores
     */
    public void setHighScores(long currentId, List<Score> scores) {
        mScoreLayout.init(currentId, scores);
    }

}
