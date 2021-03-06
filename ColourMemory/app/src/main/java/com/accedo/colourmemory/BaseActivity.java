package com.accedo.colourmemory;

/**
 * Created by gabordudas on 24/04/16.
 * Copyright (c) 2015 ColourMemory. All rights reserved.
 */

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.accedo.colourmemory.db.ScoreDataSource;
import com.accedo.colourmemory.interfaces.OnFragmentInteractionListener;
import com.accedo.colourmemory.utils.Converter;

/**
 * Base class for Activities to store the commonly used objects, methods
 */
public abstract class BaseActivity extends AppCompatActivity implements OnFragmentInteractionListener {
    public static final String TAG = BaseActivity.class.getSimpleName();

    public static int sHeight;
    public static int sWidth;
    protected Handler mHandler = new Handler();
    protected FragmentManager mFragmentManager;
    protected Toolbar mToolbar;
    protected ScoreDataSource mDataSource;

    public enum InteractionType {
        SCORE,
        BACK
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        sHeight = displaymetrics.heightPixels;
        sWidth = displaymetrics.widthPixels;

        mDataSource = new ScoreDataSource(this);
        mDataSource.open();

        mFragmentManager = getSupportFragmentManager();
    }

    @Override
    protected void onResume() {
        mDataSource.open();

        super.onResume();
    }

    @Override
    protected void onPause() {
        mDataSource.close();

        super.onPause();
    }

    /**
     * Use this method to set the toolbar
     * @param activity
     * @param title
     * @param logoResId
     * @param score
     */
    public void setToolbar(BaseActivity activity, String title, int logoResId, int score) {
        if (logoResId != 0) {
            mToolbar.setLogo(logoResId);
        }
        mToolbar.setTitle(title);

        for (int i = 0; i < mToolbar.getChildCount(); i++) {
            View child = mToolbar.getChildAt(i);
            if (child != null)
                if (child.getClass() == AppCompatTextView.class) {
                    TextView text = (TextView) child;

                    Toolbar.LayoutParams params = (Toolbar.LayoutParams) text.getLayoutParams();
                    params.setMargins(0, (int) Converter.convertDpToPixels(activity, 8), 0, (int)Converter.convertDpToPixels(activity, 8));
                    params.gravity = Gravity.CENTER;

                    text.setLayoutParams(params);

                    text.setText(String.format(activity.getString(R.string.score), score));
                }
        }

        activity.setSupportActionBar(mToolbar);
    }

    public Handler getHandler() {
        return mHandler;
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    public ScoreDataSource getDataSource() {
        return mDataSource;
    }
}
