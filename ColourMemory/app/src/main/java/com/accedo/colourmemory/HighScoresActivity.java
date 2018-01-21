package com.accedo.colourmemory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import com.accedo.colourmemory.fragments.HighScoresFragment;
import com.accedo.colourmemory.models.Score;
import com.accedo.colourmemory.utils.Constants;

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

        long currentId = getIntent().getLongExtra(Constants.PARAM_ID, 0l);

        List<Score> scores = mDataSource.getAllScores();

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setToolbar(this, getString(R.string.high_scores), 0, 0);
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true); // disable the button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // remove the left caret
            getSupportActionBar().setDisplayShowHomeEnabled(true); // remove the icon
        }

        HighScoresFragment highScoresFragment = (HighScoresFragment) mFragmentManager.findFragmentById(R.id.highScoresFragment);
        highScoresFragment.setHighScores(currentId, scores);
    }

    @Override
    public void onBackPressed() {
        navigateBack();
    }

    @Override
    public void onFragmentInteraction(String fragment, InteractionType type, Object... params) {
        if (InteractionType.BACK.equals(type)) {
            navigateBack();
        }
    }

    private void navigateBack() {
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
