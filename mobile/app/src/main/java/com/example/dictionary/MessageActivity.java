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
import com.example.dictionary.service.SharePreferenceService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MessageActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private String cardSetId;
    private TextView txtText;
    private Button btnContinue;
    private Button btnRestart;
    private SharePreferenceService sharePreferenceService;

    IHintService iHintService = null;
    public static String result = "result";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharePreferenceService = SharePreferenceService.getInstance(getBaseContext());
        setContentView(R.layout.activity_message);
        Intent intent = getIntent();
        cardSetId = intent.getStringExtra(FlashcardTermActivity.KEY_CARD_SET_ID);
        txtText = findViewById(R.id.txtText);
        btnContinue = findViewById(R.id.btnContinue);
        btnRestart = findViewById(R.id.btnRestart);
        retrofit = RetrofitClient.getClient();
        iHintService = retrofit.create(IHintService.class);
        iHintService.getCountFlashcard(sharePreferenceService.getToken(), cardSetId).enqueue(new Callback<BodyCountModel>() {
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
                Intent resetIntent = new Intent();
                resetIntent.putExtra(result, "reset");
                setResult(Activity.RESULT_OK, resetIntent);
                finish();
            }
        });
    }
}

