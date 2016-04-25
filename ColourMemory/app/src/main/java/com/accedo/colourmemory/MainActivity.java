package com.accedo.colourmemory;

/**
 * Created by gabordudas on 24/04/16.
 * Copyright (c) 2015 ColourMemory. All rights reserved.
 */

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.accedo.colourmemory.fragments.MainFragment;
import com.accedo.colourmemory.fragments.NameDialogFragment;

/**
 * Launcher Activity which represents the card table
 */
public class MainActivity extends BaseActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private int mScore = 0;
    private int mRounds = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setToolbar(this, "", R.drawable.logo, mScore);
        }
    }

    /**
     * Callback from the fragment
     * @param fragment
     * @param type
     * @param params
     */
    @Override
    public void onFragmentInteraction(String fragment, InteractionType type, Object... params) {
        switch (type) {
            case SCORE: // Round has finished, calculate the score

                if (MainFragment.TAG.equals(fragment)) {

                    if (params != null && params.length > 0) {
                        int scoreRound = (int) params[0];
                        boolean isEnd = (boolean) params[1];

                        ++mRounds;
                        mScore += scoreRound;

                        ((TextView) mToolbar.findViewById(R.id.textScore))
                                .setText(String.format(getString(R.string.score), mScore));

                        if (isEnd) {
                            NameDialogFragment dialog = NameDialogFragment.newInstance(mScore);
                            dialog.show(mFragmentManager, NameDialogFragment.TAG);
                        }
                    }

                }
                break;
        }
    }

    /**
     * Reset the table (new game)
     */
    public void reset() {
        mScore = 0;
        mRounds = 0;
        ((TextView) mToolbar.findViewById(R.id.textScore))
                .setText(String.format(getString(R.string.score), mScore));

        ((MainFragment) mFragmentManager.findFragmentById(R.id.mainFragment)).reset();
    }

    public int getScore() {
        return mScore;
    }

}
