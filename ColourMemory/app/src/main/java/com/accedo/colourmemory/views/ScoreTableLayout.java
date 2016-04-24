package com.accedo.colourmemory.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.accedo.colourmemory.R;

/**
 * Created by gabordudas on 24/04/16.
 * Copyright (c) 2015 ColourMemory. All rights reserved.
 */
public class ScoreTableLayout extends TableLayout {
    public static final String TAG = ScoreTableLayout.class.getSimpleName();

    public ScoreTableLayout(Context context) {
        super(context);
    }

    public ScoreTableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void init(int rowCount) {

        LayoutInflater inflater = LayoutInflater.from(getContext());

        for (int i = 0; i < rowCount; i++) {
            TableRow tableRow = (TableRow) inflater.inflate(R.layout.layout_score_row, this, false);

            TextView rankText = (TextView) tableRow.findViewById(R.id.textRankRow);
            TextView nameText = (TextView) tableRow.findViewById(R.id.textNameRow);
            TextView scoreText = (TextView) tableRow.findViewById(R.id.textScoreRow);

            rankText.setText("Rank " + i);
            nameText.setText("Name " + i);
            scoreText.setText("Score " + i);

            addView(tableRow);
        }

    }
}
