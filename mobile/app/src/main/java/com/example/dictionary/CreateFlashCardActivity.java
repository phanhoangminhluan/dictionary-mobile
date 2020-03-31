package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.dictionary.adapter.CreateCardsAdapter;
import com.example.dictionary.fragment.FlashcardFragment;
import com.example.dictionary.model.BodyCreateCard;
import com.example.dictionary.model.Card;
import com.example.dictionary.model.CreateCardSetModel;
import com.example.dictionary.service.IHintService;
import com.example.dictionary.service.RetrofitClient;
import com.example.dictionary.service.SharePreferenceService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CreateFlashCardActivity extends AppCompatActivity {

    private RecyclerView listCreateCards;
    ArrayList<Card> cards = new ArrayList<Card>();
    private IHintService iHintService = null;
    private Retrofit retrofit;
    private CreateCardSetModel createCardSetModel;
    private EditText edTitle;
    private SharePreferenceService sharePreferenceService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharePreferenceService = SharePreferenceService.getInstance(getBaseContext());
        setContentView(R.layout.activity_create_flash_card);
        listCreateCards = findViewById(R.id.listCreateCard);
        edTitle = findViewById(R.id.edTitle);
        cards.add(new Card());
        CreateCardsAdapter createCardsAdapter = new CreateCardsAdapter(cards);
        createCardsAdapter.setOnItemClickListener(new CreateCardsAdapter.OnItemClickListener() {
            @Override
            public void onItemDeleteClick(int position) {
                deletePosition(position);
            }

            @Override
            public void onItemSaveClick(String term, String definition, int position) {
                saveCardData(term, definition, position);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        listCreateCards.setLayoutManager(layoutManager);
        listCreateCards.setAdapter(createCardsAdapter);


    }

    public void backToFlashcardHome(View view) {
        finish();
    }

    public void onAddCardClick(View view) {
        cards.add(new Card());
        CreateCardsAdapter createCardsAdapter = new CreateCardsAdapter(cards);
        createCardsAdapter.setOnItemClickListener(new CreateCardsAdapter.OnItemClickListener() {
            @Override
            public void onItemDeleteClick(int position) {
                deletePosition(position);
            }

            @Override
            public void onItemSaveClick(String term, String definition, int position) {
                saveCardData(term, definition, position);
            }
        });
        listCreateCards.setAdapter(createCardsAdapter);


    }

    private void deletePosition(int position) {
        cards.remove(position);
        CreateCardsAdapter createCardsAdapter = new CreateCardsAdapter(cards);
        createCardsAdapter.setOnItemClickListener(new CreateCardsAdapter.OnItemClickListener() {
            @Override
            public void onItemDeleteClick(int position) {
                deletePosition(position);
            }

            @Override
            public void onItemSaveClick(String term, String definition, int position) {
                saveCardData(term, definition, position);
            }
        });
        listCreateCards.setAdapter(createCardsAdapter);

    }

    private void saveCardData(String term, String definition, int position) {
        cards.get(position).setTerm(term);
        cards.get(position).setDefinition(definition);

        CreateCardsAdapter createCardsAdapter = new CreateCardsAdapter(cards);
        createCardsAdapter.setOnItemClickListener(new CreateCardsAdapter.OnItemClickListener() {
            @Override
            public void onItemDeleteClick(int position) {
                deletePosition(position);
            }

            @Override
            public void onItemSaveClick(String term, String definition, int position) {
                saveCardData(term, definition, position);
            }
        });
        listCreateCards.setAdapter(createCardsAdapter);


    }

    public void onCreateCardSet(View view) {
        retrofit = RetrofitClient.getClient();
        iHintService = retrofit.create(IHintService.class);
        createCardSetModel = new CreateCardSetModel();
        createCardSetModel.setCards(cards);
        createCardSetModel.setName(edTitle.getText().toString());

        iHintService.createCardSet(sharePreferenceService.getToken(), createCardSetModel).enqueue(new Callback<BodyCreateCard>() {
            @Override
            public void onResponse(Call<BodyCreateCard> call, Response<BodyCreateCard> response) {
                if (response.code() == 201) {
                    if (response.body().getMessages() != null) {
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<BodyCreateCard> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
