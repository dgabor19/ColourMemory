package com.accedo.colourmemory.fragments;

/**
 * Created by gabordudas on 24/04/16.
 * Copyright (c) 2015 ColourMemory. All rights reserved.
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.accedo.colourmemory.BaseActivity;
import com.accedo.colourmemory.HighScoresActivity;
import com.accedo.colourmemory.MainActivity;
import com.accedo.colourmemory.R;
import com.accedo.colourmemory.db.ScoreDataSource;
import com.accedo.colourmemory.models.Score;
import com.accedo.colourmemory.utils.Constants;

/**
 * DialogFragment for requesting and validating the user name
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

        mEditName.requestFocus();

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        return view;
    }


    public void setScore(int score) {
        mScore = score;
    }

    @Override
    public void onClick(View v) {
        final MainActivity activity = (MainActivity) getActivity();

        switch (v.getId()) {
            case R.id.buttonPositiveDialog:
                String name = mEditName.getText().toString();

                // Check if the name field is empty
                if (name.length() < 1) {
                    mInputLayout.setError(getString(R.string.name_error));
                } else {

                    dismiss();

                    ScoreDataSource dataSource = ((BaseActivity) getActivity()).getDataSource();
                    long id = dataSource.createScore(new Score(0, name, mScore));

                    Intent intent = new Intent(getActivity(), HighScoresActivity.class);
                    intent.putExtra(Constants.PARAM_ID, id);
                    getActivity().startActivityForResult(intent, Constants.REQUEST_CODE);
                }

                break;
            case R.id.buttonNegativeDialog:

                dismiss();

                // Showing a dialog to offer table reset
                BaseFragment.getAlertDialog(activity, null,
                        getString(R.string.new_game),
                        false, R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                activity.reset();
                            }
                        }, R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                activity.finish();
                            }
                        }).show();

                break;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }
}
