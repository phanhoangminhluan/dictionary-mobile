package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.dictionary.adapter.TextAdapter;
import com.example.dictionary.fragment.FavoriteFragment;
import com.example.dictionary.fragment.HomeFragment;
import com.example.dictionary.model.BodyFavoriteWord;
import com.example.dictionary.model.DetailModel;
import com.example.dictionary.service.IHintService;
import com.example.dictionary.service.RetrofitClient;
import com.example.dictionary.service.SharePreferenceService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetailActivity extends AppCompatActivity {
    private IHintService iHintService = null;
    private Retrofit retrofit;
    private RecyclerView example;
    private RecyclerView synonym;
    private RecyclerView derivation;
    private TextView word;
    private SharePreferenceService sharePreferenceService;
    public static String KEY_TEXT_CREATE = "KEY_TEXT_CREATE";
    public static String KEY_TEXT_DELETE = "KEY_TEXT_DELETE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        retrofit = RetrofitClient.getClient();
        iHintService = retrofit.create(IHintService.class);
        sharePreferenceService = SharePreferenceService.getInstance(getBaseContext());
        word = (TextView) findViewById(R.id.word);

        final Intent intent = getIntent();
        String text = intent.getStringExtra(HomeFragment.KEY_TEXT_SEARCH);
        iHintService.getDetailText(text).enqueue(new Callback<DetailModel>() {
            @Override
            public void onResponse(Call<DetailModel> call, Response<DetailModel> response) {
                example = findViewById(R.id.example);
                synonym = findViewById(R.id.synonyms);
                derivation = findViewById(R.id.derivations);
                if (response.body() != null) {
                    if (response.body().getBody() != null) {
                        word.setText(response.body().getBody().getWord());
                        TextView pronunciation = (TextView) findViewById(R.id.pronunciation);
                        pronunciation.setText("/" + response.body().getBody().getPronunciation() + "/");
                        TextView partOfSpeech = (TextView) findViewById(R.id.partOfSpeech);
                        if (response.body().getBody().getDefinitionDetails() != null && response.body().getBody().getDefinitionDetails().size() > 0) {
                            partOfSpeech.setText(response.body().getBody().getDefinitionDetails().get(0).getPartOfSpeech());
                            TextView definition = (TextView) findViewById(R.id.definition);
                            definition.setText(response.body().getBody().getDefinitionDetails().get(0).getDefinition());

                            ArrayList<String> textExs = response.body().getBody().getDefinitionDetails().get(0).getExamples();
                            if (textExs != null) {
                                TextAdapter textAdapterEx = new TextAdapter(textExs);
                                LinearLayoutManager layoutManagerEx = new LinearLayoutManager(getBaseContext());
                                layoutManagerEx.setOrientation(LinearLayoutManager.VERTICAL);
                                example.setLayoutManager(layoutManagerEx);
                                example.setAdapter(textAdapterEx);
                            }

                            ArrayList<String> synonyms = response.body().getBody().getDefinitionDetails().get(0).getSynonyms();
                            if (synonyms != null) {
                                TextAdapter textAdapterSy = new TextAdapter(synonyms);
                                LinearLayoutManager layoutManagerSy = new LinearLayoutManager(getBaseContext());
                                layoutManagerSy.setOrientation(LinearLayoutManager.VERTICAL);
                                synonym.setLayoutManager(layoutManagerSy);
                                synonym.setAdapter(textAdapterSy);
                            }

                            ArrayList<String> derivations = response.body().getBody().getDefinitionDetails().get(0).getDerivations();
                            if (derivations != null) {
                                TextAdapter textAdapterDe = new TextAdapter(derivations);
                                LinearLayoutManager layoutManagerDe = new LinearLayoutManager(getBaseContext());
                                layoutManagerDe.setOrientation(LinearLayoutManager.VERTICAL);
                                derivation.setLayoutManager(layoutManagerDe);
                                derivation.setAdapter(textAdapterDe);
                            }

                            TextView partOfSpeech2 = (TextView) findViewById(R.id.partOfSpeech2);
                            if (partOfSpeech2 != null) {
                                partOfSpeech2.setText(response.body().getBody().getDefinitionDetails().get(0).getPartOfSpeech());

                            }
                        }

                    }
                }


            }

            @Override
            public void onFailure(Call<DetailModel> call, Throwable t) {
                System.out.println(t.getMessage());


            }
        });
        ToggleButton toggle = (ToggleButton) findViewById(R.id.toogleLove);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                final String word_f = word.getText().toString();
                if (isChecked) {
                    iHintService = retrofit.create(IHintService.class);
                    System.out.println(word_f);
                    iHintService.createFavoriteWord(sharePreferenceService.getToken(), word_f).enqueue(new Callback<BodyFavoriteWord>() {
                        @Override
                        public void onResponse(Call<BodyFavoriteWord> call, Response<BodyFavoriteWord> response) {
                            System.out.println(response.code());
                            if (response.code() == 201) {
                                System.out.println("Thanh Cong");
                                intent.putExtra(KEY_TEXT_CREATE, "");
                            }
                        }

                        @Override
                        public void onFailure(Call<BodyFavoriteWord> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });
                    System.out.println("loved");
                } else {
                    iHintService = retrofit.create(IHintService.class);
                    iHintService.deleteFavoriteWord(sharePreferenceService.getToken(), word_f).enqueue(new Callback<BodyFavoriteWord>() {
                        @Override
                        public void onResponse(Call<BodyFavoriteWord> call, Response<BodyFavoriteWord> response) {
                            System.out.println("Delete ne" + response.code());
                            if (response.code() == 202) {
                                System.out.println("Xoa thanh cong");
                            }
                        }

                        @Override
                        public void onFailure(Call<BodyFavoriteWord> call, Throwable t) {

                        }
                    });
                    System.out.println("dislove");
                }
            }
        });
    }

}
