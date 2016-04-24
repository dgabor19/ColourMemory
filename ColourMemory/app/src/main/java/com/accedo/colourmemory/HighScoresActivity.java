package com.accedo.colourmemory;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.accedo.colourmemory.utils.Constants;

/**
 * Created by gabordudas on 24/04/16.
 * Copyright (c) 2015 ColourMemory. All rights reserved.
 */
public class HighScoresActivity extends BaseActivity {
    public static final String TAG = HighScoresActivity.class.getSimpleName();

    private int mScore = 0;
    private String mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        mScore = getIntent().getIntExtra(Constants.PARAMS_SCORE, 0);
        mName = getIntent().getStringExtra(Constants.PARAMS_NAME);

        Toast.makeText(this, "score: " + mScore + " name: " + mName, Toast.LENGTH_LONG).show();

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setToolbar(this, getString(R.string.high_scores), 0, 0);
        }


    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onFragmentInteraction(String fragment, InteractionType type, Object... params) {

    }
}
