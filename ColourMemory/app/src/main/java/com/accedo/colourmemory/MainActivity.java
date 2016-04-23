package com.accedo.colourmemory;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.accedo.colourmemory.fragments.MainFragment;
import com.accedo.colourmemory.interfaces.OnFragmentInteractionListener;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {
    public static final String TAG = MainActivity.class.getSimpleName();

    private Handler mHandler = new Handler();
    private int mScore = 0;
    private FragmentManager mFragmentManager;

    public enum InteractionType {
        SCORE
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentManager = getSupportFragmentManager();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, MainFragment.newInstance(), MainFragment.TAG).commit();
    }

    public Handler getHandler() {
        return mHandler;
    }


    @Override
    public void onFragmentInteraction(String fragment, InteractionType type, Object... params) {
        switch (type) {
            case SCORE:

                if (MainFragment.TAG.equals(fragment)) {

                    if (params != null && params.length > 0) {
                        int scoreRound = (int) params[0];

                        mScore += scoreRound;
                        Toast.makeText(this, "Won " + scoreRound + " sum " + mScore, Toast.LENGTH_SHORT).show();
                    }

                }
                break;
        }
    }
}
