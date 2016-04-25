package com.accedo.colourmemory;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import com.accedo.colourmemory.fragments.HighScoresFragment;
import com.accedo.colourmemory.models.Score;

import java.util.List;

/**
 * Created by gabordudas on 24/04/16.
 * Copyright (c) 2015 ColourMemory. All rights reserved.
 */
public class HighScoresActivity extends BaseActivity {
    public static final String TAG = HighScoresActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        List<Score> scores = mDataSource.getAllScores();

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setToolbar(this, getString(R.string.high_scores), 0, 0);
        }

        getSupportActionBar().setHomeButtonEnabled(true); // disable the button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // remove the left caret
        getSupportActionBar().setDisplayShowHomeEnabled(true); // remove the icon

        HighScoresFragment highScoresFragment = (HighScoresFragment) mFragmentManager.findFragmentById(R.id.highScoresFragment);
        highScoresFragment.setHighScores(scores);
    }


    @Override
    public void onFragmentInteraction(String fragment, InteractionType type, Object... params) {

    }
}
