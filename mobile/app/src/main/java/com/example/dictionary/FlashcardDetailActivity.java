package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.dictionary.adapter.TermFlashcardAdapter;
import com.example.dictionary.adapter.TextAdapter;

import java.util.ArrayList;

public class FlashcardDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_detail);

        ArrayList<String> texts = new ArrayList<String>();
        texts.add("abc");
        texts.add("xuz");
        TermFlashcardAdapter termAdapter = new TermFlashcardAdapter(texts);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        RecyclerView listTermCard = findViewById(R.id.listTermCard);
        listTermCard.setLayoutManager(layoutManager);
        listTermCard.setAdapter(termAdapter);

        termAdapter.setOnItemClickListener(new TermFlashcardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String text) {
                Intent intent = new Intent(getBaseContext(), LearnTermActivity.class);
                startActivity(intent);

            }
        });

    }
}
