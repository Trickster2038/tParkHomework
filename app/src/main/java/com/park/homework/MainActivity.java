package com.park.homework;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            MainFragment firstFr = new MainFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frameFragLayout, firstFr)
                    .commit();
        }
    }
}