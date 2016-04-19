package com.accedo.colourmemory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.accedo.colourmemory.fragments.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, MainFragment.newInstance(), MainFragment.TAG).commit();
    }
}
