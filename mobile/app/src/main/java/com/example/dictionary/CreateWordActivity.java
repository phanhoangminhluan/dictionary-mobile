package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dictionary.fragment.HomeFragment;
import com.example.dictionary.model.BodyWordModel;
import com.example.dictionary.model.WordDefinitionDetailModel;
import com.example.dictionary.service.IHintService;
import com.example.dictionary.service.RetrofitClient;
import com.example.dictionary.service.SharePreferenceService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CreateWordActivity extends AppCompatActivity {
    private IHintService iHintService = null;
    private Retrofit retrofit;
    private SharePreferenceService sharePreferenceService;
    private EditText example;
    private EditText synonym;
    private EditText derivation;
    private EditText definition;
    private EditText partOfSpeech;
    private EditText ukPhonetic;
    private EditText usPhonetic;
    private String text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_word);
        sharePreferenceService = SharePreferenceService.getInstance(getBaseContext());
        Intent intent = getIntent();
         text = intent.getStringExtra("text");
        TextView textView = findViewById(R.id.text);
        if (text != null) {
            textView.setText(text);

        }
        example = findViewById(R.id.example);
        synonym = findViewById(R.id.synonyms);
        derivation = findViewById(R.id.derivations);
        definition = findViewById(R.id.definition);
        partOfSpeech = findViewById(R.id.partOfSpeech);
        ukPhonetic = findViewById(R.id.uk);
        usPhonetic = findViewById(R.id.us);
        retrofit = RetrofitClient.getClient();
        iHintService = retrofit.create(IHintService.class);


    }

    public void onClickHomeFragment(View view) {
        final BodyWordModel bodyWordModel = new BodyWordModel();
        String ex = example.getText().toString();
        String sy = synonym.getText().toString();
        String deri = derivation.getText().toString();
        String defi = definition.getText().toString();
        String part = partOfSpeech.getText().toString();
        WordDefinitionDetailModel wordDefinitionDetailModel = new WordDefinitionDetailModel();
        wordDefinitionDetailModel.setDefinition(defi);
        wordDefinitionDetailModel.setPartOfSpeech(part);

        ArrayList<String> examples = new ArrayList<>();
        examples.add(ex);
        wordDefinitionDetailModel.setExamples(examples);

        ArrayList<String> synonyms = new ArrayList<>();
        synonyms.add(sy);
        wordDefinitionDetailModel.setSynonyms(synonyms);

        ArrayList<String> derivations = new ArrayList<>();
        derivations.add(deri);
        wordDefinitionDetailModel.setDerivations(derivations);

        bodyWordModel.setWord(text);
        bodyWordModel.setUkPhonetic(ukPhonetic.getText().toString());
        bodyWordModel.setUsPhonetic(usPhonetic.getText().toString());

        ArrayList<WordDefinitionDetailModel> wordDefinitionDetailModels = new ArrayList<>();
        wordDefinitionDetailModels.add(wordDefinitionDetailModel);
        bodyWordModel.setDefinitionDetails(wordDefinitionDetailModels);
        iHintService.createWord(sharePreferenceService.getToken(),bodyWordModel).enqueue(new Callback<BodyWordModel>() {
            @Override
            public void onResponse(Call<BodyWordModel> call, Response<BodyWordModel> response) {
                if (response.code() == 200) {
                    Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                    startActivity(intent);

                } else {
                    System.out.println("register word:" + response.code());
                }
            }

            @Override
            public void onFailure(Call<BodyWordModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}


