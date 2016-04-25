package com.accedo.colourmemory.views;

/**
 * Created by gabordudas on 24/04/16.
 * Copyright (c) 2015 ColourMemory. All rights reserved.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.accedo.colourmemory.R;
import com.accedo.colourmemory.models.Score;
import com.accedo.colourmemory.utils.CardUtils;

import java.util.List;

/**
 * Extended TableLayout for displaying high scores
 */
public class ScoreTableLayout extends TableLayout {
    public static final String TAG = ScoreTableLayout.class.getSimpleName();

    public ScoreTableLayout(Context context) {
        super(context);
    }

    public ScoreTableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Initialize the layout after creation
     * @param currentId
     * @param scores
     */
    public void init(long currentId, List<Score> scores) {

        LayoutInflater inflater = LayoutInflater.from(getContext());

        for (int i = 0; i < scores.size(); i++) {
            Score score = scores.get(i);

            TableRow tableRow = (TableRow) inflater.inflate(R.layout.layout_score_row, this, false);

            TextView rankText = (TextView) tableRow.findViewById(R.id.textRankRow);
            TextView nameText = (TextView) tableRow.findViewById(R.id.textNameRow);
            TextView scoreText = (TextView) tableRow.findViewById(R.id.textScoreRow);

            rankText.setText(String.valueOf(i + 1));
            nameText.setText(score.getName());
            scoreText.setText(String.valueOf(score.getScore()));

            // Highlighting the last played rank
            if (currentId == score.getId()) {
                CardUtils.animateView(tableRow);
                rankText.append("*");
            }

            addView(tableRow);
        }

    }
}
