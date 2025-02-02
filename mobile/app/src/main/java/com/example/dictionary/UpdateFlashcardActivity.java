package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.dictionary.model.BodyCardModel;
import com.example.dictionary.model.Card;
import com.example.dictionary.service.IHintService;
import com.example.dictionary.service.RetrofitClient;
import com.example.dictionary.service.SharePreferenceService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UpdateFlashcardActivity extends AppCompatActivity {
    String id;
    EditText edTerm;
    EditText edDefinition;
    private IHintService iHintService = null;
    private Retrofit retrofit;
    private SharePreferenceService sharePreferenceService;
    private Card cardsDetailModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharePreferenceService = SharePreferenceService.getInstance(getBaseContext());
        setContentView(R.layout.activity_update_flashcard);
        Intent intent = getIntent();
        id = intent.getStringExtra(FlashcardDetailActivity.KEY_FLASHCARD_ID);
        String term = intent.getStringExtra(FlashcardDetailActivity.KEY_FLASHCARD_TERM);
        String definition = intent.getStringExtra(FlashcardDetailActivity.KEY_FLASHCARD_DEFINITION);
        edTerm = findViewById(R.id.txtTerm);
        edDefinition = findViewById(R.id.txtDefinition);
        if (term != null) {
            edTerm.setText(term);
        }
        if (definition != null) {
            edDefinition.setText(definition);
        }

    }

    public void onClickOkUpdate(View view) {

        retrofit = RetrofitClient.getClient();
        iHintService = retrofit.create(IHintService.class);
        cardsDetailModel = new Card();
        cardsDetailModel.setId(id);
        cardsDetailModel.setTerm(edTerm.getText().toString());
        cardsDetailModel.setDefinition(edDefinition.getText().toString());

        iHintService.updateFlashcard(sharePreferenceService.getToken(), cardsDetailModel).enqueue(new Callback<BodyCardModel>() {
            @Override
            public void onResponse(Call<BodyCardModel> call, Response<BodyCardModel> response) {
                if (response.code() == 202) {
                    if (response.body().getMessages() != null) {
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<BodyCardModel> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}
