package com.accedo.colourmemory;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.accedo.colourmemory.fragments.BaseFragment;
import com.accedo.colourmemory.fragments.MainFragment;
import com.accedo.colourmemory.fragments.NameDialogFragment;
import com.accedo.colourmemory.utils.Constants;

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



    @Override
    public void onFragmentInteraction(String fragment, InteractionType type, Object... params) {
        switch (type) {
            case SCORE:

                if (MainFragment.TAG.equals(fragment)) {

                    if (params != null && params.length > 0) {
                        int scoreRound = (int) params[0];
                        boolean isEnd = (boolean) params[1];

                        ++mRounds;
                        mScore += scoreRound;
//                        Toast.makeText(this, "Won " + scoreRound + " sum " + mScore, Toast.LENGTH_SHORT).show();
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

    public int getScore() {
        return mScore;
    }

    public int getRounds() {
        return mRounds;
    }
}
