package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.dictionary.fragment.SettingFragment;

public class GoToSettingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, new SettingFragment()).commit();
        }
    }
}

