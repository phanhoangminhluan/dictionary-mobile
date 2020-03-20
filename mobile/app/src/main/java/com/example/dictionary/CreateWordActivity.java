package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dictionary.fragment.HomeFragment;

public class CreateWordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_word);
        Intent intent = getIntent();
        String text = intent.getStringExtra("text");
        TextView textView = findViewById(R.id.text);
        if (text != null) {
            textView.setText(text);

        }


    }
    public void onClickHomeFragment(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
//public class Login2Model(String username, String password){
//
//        }
//

