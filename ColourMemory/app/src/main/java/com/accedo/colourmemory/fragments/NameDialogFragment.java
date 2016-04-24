package com.accedo.colourmemory.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.accedo.colourmemory.HighScoresActivity;
import com.accedo.colourmemory.R;
import com.accedo.colourmemory.utils.Constants;

/**
 * Created by gabordudas on 24/04/16.
 * Copyright (c) 2015 ColourMemory. All rights reserved.
 */
public class NameDialogFragment extends DialogFragment implements View.OnClickListener {
    public static final String TAG = NameDialogFragment.class.getSimpleName();

    private static final String PARAM_SCORE = "score";

    private TextInputLayout mInputLayout;
    private EditText mEditName;
    private Button mPositiveButton;
    private Button mNegativeButton;

    private int mScore;

    public NameDialogFragment() {

    }

    public static NameDialogFragment newInstance(int score) {
        NameDialogFragment fragment = new NameDialogFragment();

        Bundle args = new Bundle();
        args.putInt(PARAM_SCORE, score);

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();

        if (args != null) {
            mScore = args.getInt(PARAM_SCORE, 0);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_dialog, container);

        setCancelable(false);

        mInputLayout = (TextInputLayout) view.findViewById(R.id.layoutNameDialog);
        mEditName = (EditText) view.findViewById(R.id.editNameDialog);
        mPositiveButton = (Button) view.findViewById(R.id.buttonPositiveDialog);
        mNegativeButton = (Button) view.findViewById(R.id.buttonNegativeDialog);

        mPositiveButton.setOnClickListener(this);
        mNegativeButton.setOnClickListener(this);

        return view;
    }


    public void setScore(int score) {
        mScore = score;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttonPositiveDialog:
                // Check if the name field is empty
                if (mEditName.getText().toString().length() < 1) {
                    mInputLayout.setError(getString(R.string.name_error));
                } else {

                    dismiss();

                    Intent intent = new Intent(getActivity(), HighScoresActivity.class);
                    intent.putExtra(Constants.PARAMS_SCORE, mScore);
                    intent.putExtra(Constants.PARAMS_NAME, mEditName.getText().toString());

                    startActivity(intent);
                }

                break;
            case R.id.buttonNegativeDialog:

                dismiss();

                break;
        }


    }
}
