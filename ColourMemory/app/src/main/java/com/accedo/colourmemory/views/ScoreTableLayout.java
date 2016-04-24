package com.accedo.colourmemory.views;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.accedo.colourmemory.R;
import com.accedo.colourmemory.models.Score;

import java.util.List;

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

    public void init(List<Score> scores) {

        LayoutInflater inflater = LayoutInflater.from(getContext());

        String rankValue = getContext().getString(R.string.column_rank);
        String nameValue = getContext().getString(R.string.column_name);
        String scoreValue = getContext().getString(R.string.column_score);

        for (int i = 0; i < scores.size() + 1; i++) {

            TableRow tableRow = (TableRow) inflater.inflate(R.layout.layout_score_row, this, false);

            TextView rankText = (TextView) tableRow.findViewById(R.id.textRankRow);
            TextView nameText = (TextView) tableRow.findViewById(R.id.textNameRow);
            TextView scoreText = (TextView) tableRow.findViewById(R.id.textScoreRow);

            if (i > 0) {
                Score score = scores.get(i - 1);

                rankValue = String.valueOf(i);
                nameValue = score.getName();
                scoreValue = String.valueOf(score.getScore());

            }

            rankText.setText(rankValue);
            nameText.setText(nameValue);
            scoreText.setText(scoreValue);

            addView(tableRow);
        }

    }
}
