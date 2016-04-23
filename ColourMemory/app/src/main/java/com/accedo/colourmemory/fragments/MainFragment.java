package com.accedo.colourmemory.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.accedo.colourmemory.MainActivity;
import com.accedo.colourmemory.R;
import com.accedo.colourmemory.utils.Constants;
import com.accedo.colourmemory.views.CardGridLayout;

/**
 * Created by gabordudas on 18/04/16.
 * Copyright (c) 2015 ColourMemory. All rights reserved.
 */
public class MainFragment extends Fragment {
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
        mGrid.init(Constants.COLUMN_COUNT, Constants.ROW_COUNT);

    }
}
