package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dictionary.model.BodyCardsetLearnModel;
import com.example.dictionary.model.BodyCountModel;
import com.example.dictionary.service.IHintService;
import com.example.dictionary.service.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MessageActivity extends AppCompatActivity {
    private String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZDIiLCJleHAiOjE1ODQ1ODc2OTF9.LuVVlNSj5dGiyy91HiC-dz2ypDkQ3pgJqsyfHy2ZJOmxFLZwTkscSH8WnmKCDzRX9j8Q6IuqHW7gboe_KvqXFg";
    private Retrofit retrofit;
    private String cardSetId;
    private TextView txtText;
    private Button btnContinue;
    private Button btnRestart;
    IHintService iHintService = null;
    public static String result = "result";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Intent intent = getIntent();
        cardSetId = intent.getStringExtra(FlashcardTermActivity.KEY_CARD_SET_ID);
        txtText = findViewById(R.id.txtText);
        btnContinue = findViewById(R.id.btnContinue);
        btnRestart = findViewById(R.id.btnRestart);
        retrofit = RetrofitClient.getClient();
        iHintService = retrofit.create(IHintService.class);
        iHintService.getCountFlashcard(token, cardSetId).enqueue(new Callback<BodyCountModel>() {
            @Override
            public void onResponse(Call<BodyCountModel> call, Response<BodyCountModel> response) {
                if (response.code() == 200) {
                    txtText.setText("You just learned " + response.body().getBody().getRememberCount() + " terms! You've already forgotten " + response.body().getBody().getForgetCount() + " terms.");
                }
            }

            @Override
            public void onFailure(Call<BodyCountModel> call, Throwable t) {

            }
        });
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra(result, "return");
                setResult(Activity.RESULT_OK, returnIntent);
                finish();

            }
        });
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iHintService.resetProgress(token, cardSetId).enqueue(new Callback<BodyCardsetLearnModel>() {
                    @Override
                    public void onResponse(Call<BodyCardsetLearnModel> call, Response<BodyCardsetLearnModel> response) {
                        if (response.code() == 201) {
                            Intent resetIntent = new Intent();
                            resetIntent.putExtra(result, "reset");
                            resetIntent.putExtra("sessionId", response.body().getBody().getCardSetSessionId());
                            setResult(Activity.RESULT_OK, resetIntent);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<BodyCardsetLearnModel> call, Throwable t) {

                    }
                });
            }
        });
    }
}

