package com.example.dictionary.fragment;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.CreateFlashCardActivity;
import com.example.dictionary.FlashcardDetailActivity;
import com.example.dictionary.R;
import com.example.dictionary.adapter.FlashcardAdapter;
import com.example.dictionary.dialog.ErrorDialog;
import com.example.dictionary.model.CardSetModel;
import com.example.dictionary.model.BodyCardSetModel;
import com.example.dictionary.service.IHintService;
import com.example.dictionary.service.RetrofitClient;
import com.example.dictionary.service.SharePreferenceService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FlashcardFragment extends Fragment {
    private IHintService iHintService = null;
    private Retrofit retrofit;
    private RecyclerView listFlashcard;
    public static String KEY_ID = "KEY_ID";
    private ArrayList<CardSetModel> flashcards;
    private SharePreferenceService sharePreferenceService;

    EditText edTextSearch;
    ImageView imgSearch;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flashcard, container, false);
        Button btnCreate = view.findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateFlashcard(v);
            }
        });
        listFlashcard = view.findViewById(R.id.listItemFlashcard);
        retrofit = RetrofitClient.getClient();
        iHintService = retrofit.create(IHintService.class);
        edTextSearch = view.findViewById(R.id.txtTextSearch);
        imgSearch = view.findViewById(R.id.searchView);
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtSearch = edTextSearch.getText().toString();
                if (flashcards != null) {
                    ArrayList<CardSetModel> flashcardsSearch = new ArrayList<>();
                    for (CardSetModel item : flashcards) {
                        if (item.getName().toLowerCase().contains(txtSearch.toLowerCase())) {
                            flashcardsSearch.add(item);
                        }
                    }
                    FlashcardAdapter termFlashcardAdapter = new FlashcardAdapter(flashcardsSearch);
                    termFlashcardAdapter.setOnItemClickListener(new FlashcardAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(String id) {
                            Intent intent = new Intent(getContext(), FlashcardDetailActivity.class);
                            intent.putExtra(KEY_ID, id);
                            startActivity(intent);
                        }
                    });
                    listFlashcard.setAdapter(termFlashcardAdapter);
                }
            }
        });


        return view;
    }

    public void onCreateFlashcard(View view) {
        Intent intent = new Intent(getContext(), CreateFlashCardActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        sharePreferenceService = SharePreferenceService.getInstance(getContext());
        iHintService.getAllFlashcard(sharePreferenceService.getToken()).enqueue(new Callback<BodyCardSetModel>() {
            @Override
            public void onResponse(Call<BodyCardSetModel> call, Response<BodyCardSetModel> response) {
                int code = response.code();
                if (code == 200) {
                    flashcards = response.body().getBody();
                    FlashcardAdapter termFlashcardAdapter = new FlashcardAdapter(flashcards);
                    //Set onClickListener in Adapter:
                    //From HomeFlashcard To Flashcard Detail
                    termFlashcardAdapter.setOnItemClickListener(new FlashcardAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(String id) {
                            Intent intent = new Intent(getContext(), FlashcardDetailActivity.class);
                            intent.putExtra(KEY_ID, id);
                            startActivity(intent);
                        }
                    });

                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    listFlashcard.setLayoutManager(layoutManager);
                    listFlashcard.setAdapter(termFlashcardAdapter);
                } else {
                    ErrorDialog errorDialog = new ErrorDialog("API can not work");
                    errorDialog.show(getFragmentManager(), "Example");
                }
            }

            @Override
            public void onFailure(Call<BodyCardSetModel> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}
