package com.example.dictionary;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dictionary.model.BodyCardSetDetailModel;
import com.example.dictionary.model.BodyCardsetLearnModel;
import com.example.dictionary.model.BodyRememberForgetFlashcardModel;
import com.example.dictionary.model.Card;
import com.example.dictionary.service.IHintService;
import com.example.dictionary.service.RetrofitClient;
import com.example.dictionary.service.SharePreferenceService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FlashcardTermActivity extends AppCompatActivity {
    private ImageView imgCloseLearn;
    private Button btnStudyAgain;
    private Button btnGotIt;
    private String cardSetId, sessionId;
    private TextView txtTerm, txtDefinition;
    private Integer currentIndex = 0;
    private ArrayList<Card> cards;
    private ArrayList<Card> card_datas;

    private IHintService iHintService = null;
    private Retrofit retrofit;
    private SharePreferenceService sharePreferenceService;
    public static String KEY_CARD_SET_ID = "KEY_CARD_SET_ID";
    public static Integer RESULT_MESSAGE = 1;


    private void setTextData() {
        if (currentIndex >= 0 && currentIndex < 6 && currentIndex < cards.size()) {
            txtTerm.setText(cards.get(currentIndex).getTerm());
            txtDefinition.setText(cards.get(currentIndex).getDefinition());
        }
        if (currentIndex == 6 || currentIndex == cards.size()) {
            Intent intent = new Intent(getBaseContext(), MessageActivity.class);
            intent.putExtra(KEY_CARD_SET_ID, cardSetId);
            startActivityForResult(intent, RESULT_MESSAGE);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharePreferenceService = SharePreferenceService.getInstance(getBaseContext());
        setContentView(R.layout.activity_learn_flashcards);
        btnStudyAgain = findViewById(R.id.btnStudyAgain);
        btnGotIt = findViewById(R.id.btnGotIt);
        txtTerm = findViewById(R.id.txtTerm);
        txtDefinition = findViewById(R.id.txtDefinition);

        Intent intent = getIntent();
        cardSetId = intent.getStringExtra(FlashcardDetailActivity.KEY_CARD_SET);
        sessionId = intent.getStringExtra(FlashcardDetailActivity.KEY_SESSION);
        retrofit = RetrofitClient.getClient();
        iHintService = retrofit.create(IHintService.class);

        iHintService.getAllFlashcardDetail(sharePreferenceService.getToken(), cardSetId).enqueue(new Callback<BodyCardSetDetailModel>() {
            @Override
            public void onResponse(Call<BodyCardSetDetailModel> call, Response<BodyCardSetDetailModel> response) {
                if (response.code() == 200) {
                    cards = response.body().getBody().getCards();
                    card_datas = new ArrayList<>();
                    for(Card card: cards){
                        Card item = new Card();
                        item.setDefinition(card.getDefinition());
                        item.setTerm(card.getTerm());
                        item.setCardSetId(card.getCardSetId());
                        item.setId(card.getId());
                        card_datas.add(item);
                    }
                    setTextData();

                }
            }
            file huwosng daxn, slide,

            @Override
            public void onFailure(Call<BodyCardSetDetailModel> call, Throwable t) {
                t.printStackTrace();
            }
        });

        btnStudyAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex >= 0 && currentIndex <= 6 ) {
                    iHintService.forgetFlashcard(sharePreferenceService.getToken(), sessionId, cards.get(currentIndex).getId()).enqueue(new Callback<BodyRememberForgetFlashcardModel>() {
                        @Override
                        public void onResponse(Call<BodyRememberForgetFlashcardModel> call, Response<BodyRememberForgetFlashcardModel> response) {
                            if (response.code() == 201) {
                                currentIndex++;
                                setTextData();
                            }
                        }

                        @Override
                        public void onFailure(Call<BodyRememberForgetFlashcardModel> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });
                }

            }
        });
        btnGotIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex >= 0 && currentIndex <= 6) {
                    iHintService.rememberFlashcard(sharePreferenceService.getToken(), sessionId, cards.get(currentIndex).getId()).enqueue(new Callback<BodyRememberForgetFlashcardModel>() {
                        @Override
                        public void onResponse(Call<BodyRememberForgetFlashcardModel> call, Response<BodyRememberForgetFlashcardModel> response) {
                            if (response.code() == 201) {
                                cards.remove(cards.get(currentIndex));
                                System.out.println(cards.size());
                                System.out.println(card_datas.size());
                                setTextData();
                            }
                        }

                        @Override
                        public void onFailure(Call<BodyRememberForgetFlashcardModel> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });
                }
            }
        });
        imgCloseLearn = findViewById(R.id.imgCloseLearnFlashcard);
        imgCloseLearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_MESSAGE) {
            if (resultCode == Activity.RESULT_OK) {
                String _return = data.getStringExtra(MessageActivity.result);
                if (_return.equals("return")) {
                    currentIndex = 0;
                    setTextData();
                }
                else {

                    iHintService.resetProgress(sharePreferenceService.getToken(), cardSetId).enqueue(new Callback<BodyCardsetLearnModel>() {
                        @Override
                        public void onResponse(Call<BodyCardsetLearnModel> call, Response<BodyCardsetLearnModel> response) {
                            if (response.code() == 201) {
                                sessionId = response.body().getBody().getCardSetSessionId();
                                cards = card_datas;
                                currentIndex = 0;
                                setTextData();
                            }
                        }
                        @Override
                        public void onFailure(Call<BodyCardsetLearnModel> call, Throwable t) {

                        }
                    });
                }

            }
        }


    }
}
