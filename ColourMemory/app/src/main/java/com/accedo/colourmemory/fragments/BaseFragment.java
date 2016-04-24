package com.accedo.colourmemory.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;

import com.accedo.colourmemory.BaseActivity;
import com.accedo.colourmemory.interfaces.OnFragmentInteractionListener;

/**
 * Created by gabordudas on 24/04/16.
 * Copyright (c) 2015 ColourMemory. All rights reserved.
 */
public class BaseFragment extends Fragment {
    public static final String TAG = BaseFragment.class.getSimpleName();

    protected BaseActivity mActivity;
    protected Handler mHandler;
    protected OnFragmentInteractionListener mListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = (BaseActivity) getActivity();
        mHandler = mActivity.getHandler();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public static AlertDialog.Builder getAlertDialog(Context context, String title, String message,
                                                     boolean isCancelable, int positiveButtonTextRes,
                                                     DialogInterface.OnClickListener onPositiveButtonListener,
                                                     int negativeButtonTextRes,
                                                     DialogInterface.OnClickListener onNegativeButtonListener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (title != null) {
            builder.setTitle(title);
        }

        if (message != null) {
            builder.setMessage(message);
        }

        builder.setCancelable(isCancelable);

        if (positiveButtonTextRes != 0 && onPositiveButtonListener != null) {
            builder.setPositiveButton(positiveButtonTextRes, onPositiveButtonListener);
        }

        if (negativeButtonTextRes != 0 && onNegativeButtonListener != null) {
            builder.setNegativeButton(negativeButtonTextRes, onNegativeButtonListener);
        }
        return builder;
    }
}
