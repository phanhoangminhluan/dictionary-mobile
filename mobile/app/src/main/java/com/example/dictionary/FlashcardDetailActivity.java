package com.example.dictionary;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.dictionary.adapter.CardAdapter;
import com.example.dictionary.adapter.TermFlashcardAdapter;
import com.example.dictionary.dialog.DeleteDialog;
import com.example.dictionary.fragment.FlashcardFragment;
import com.example.dictionary.model.BodyCardModel;
import com.example.dictionary.model.BodyCardSetDetailModel;
import com.example.dictionary.model.Card;
import com.example.dictionary.service.IHintService;
import com.example.dictionary.service.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FlashcardDetailActivity extends AppCompatActivity {
    private String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZDIiLCJleHAiOjE1ODQ1ODc2OTF9.LuVVlNSj5dGiyy91HiC-dz2ypDkQ3pgJqsyfHy2ZJOmxFLZwTkscSH8WnmKCDzRX9j8Q6IuqHW7gboe_KvqXFg";
    private IHintService iHintService = null;
    private Retrofit retrofit;
    private RecyclerView listTerm_Definition;
    private TextView txtName;
    private TextView txtUsername;
    private  TextView txtCardCount;
    public static String KEY_FLASHCARD_ID = "KEY_FLASHCARD_ID";
    public static String KEY_FLASHCARD_TERM = "KEY_FLASHCARD_TERM";
    public static String KEY_FLASHCARD_DEFINITION = "KEY_FLASHCARD_DEFINITION";

    public static final int DELETE_DIALOG_FRAGMENT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard_detail);
        retrofit = RetrofitClient.getClient();
        iHintService = retrofit.create(IHintService.class);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String id = intent.getStringExtra(FlashcardFragment.KEY_ID);
        iHintService.getAllFlashcardDetail(token,id).enqueue(new Callback<BodyCardSetDetailModel>() {
            @Override
            public void onResponse(Call<BodyCardSetDetailModel> call, final Response<BodyCardSetDetailModel> response) {
                listTerm_Definition = findViewById(R.id.listTermAndDefinition);
                txtName = findViewById(R.id.txtName);
                txtUsername = findViewById(R.id.txtUsername);
                txtCardCount = findViewById(R.id.txtCardsCount);
                if(response.code() == 200){
                    if (response.body().getBody() != null) {
                        ArrayList<Card> term_definition = response.body().getBody().getCards();
                        if(term_definition != null){
                            CardAdapter cardAdapter = new CardAdapter(term_definition);
                            txtUsername.setText(response.body().getBody().getUsername());
                            txtCardCount.setText(response.body().getBody().getCards().size()+" terms");
                            txtName.setText(response.body().getBody().getName());
                            //Todo: truyền hàm setOnclickListener vô Adapter
                            cardAdapter.setOnItemClickListener(new CardAdapter.OnItemClickistener() {
                                @Override
                                public void onItemClickUpdate(String id, String term, String definition) {
                                    Intent intent = new Intent(getBaseContext(), UpdateFlashcardActivity.class);
                                    intent.putExtra(KEY_FLASHCARD_ID, id);
                                    intent.putExtra(KEY_FLASHCARD_TERM, term);
                                    intent.putExtra(KEY_FLASHCARD_DEFINITION, definition);

                                    startActivity(intent);
                                }

                                @Override
                                public void onItemClickDelete(String id, String term) {
                                    DeleteDialog deleteDialog = new DeleteDialog(term, id, new DeleteDialog.OnItemClickListener() {
                                        @Override
                                        public void onItemClickDone() {
                                            System.out.println("Call back");
                                            onResume();
                                        }
                                    });
                                    deleteDialog.show(getSupportFragmentManager().beginTransaction(), "DELETE");
                                }
                            });

                            LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            listTerm_Definition.setLayoutManager(layoutManager);
                            listTerm_Definition.setAdapter(cardAdapter);
                        }


                        ArrayList<Card> texts = response.body().getBody().getCards();
                        TermFlashcardAdapter termAdapter = new TermFlashcardAdapter(texts);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
                        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                        RecyclerView listTermCard = findViewById(R.id.listTermCard);
                        listTermCard.setLayoutManager(layoutManager);
                        listTermCard.setAdapter(termAdapter);

                        termAdapter.setOnItemClickListener(new TermFlashcardAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(String text) {
                                Intent intent = new Intent(getBaseContext(), FlashcardTermActivity.class);
                                startActivity(intent);

                            }
                        });

                    }
                }else {
                    System.out.println(response.code());
                }

            }

            @Override
            public void onFailure(Call<BodyCardSetDetailModel> call, Throwable t) {
                System.out.println(t.getMessage());

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
