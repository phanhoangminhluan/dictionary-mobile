package com.example.dictionary.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.CreateFlashCardActivity;
import com.example.dictionary.DetailActivity;
import com.example.dictionary.FlashcardDetailActivity;
import com.example.dictionary.R;
import com.example.dictionary.adapter.FlashcardAdapter;
import com.example.dictionary.adapter.TermFlashcardAdapter;
import com.example.dictionary.adapter.TextAdapter;
import com.example.dictionary.dialog.ErrorDialog;
import com.example.dictionary.model.BodyGetDetailFlashcardModel;
import com.example.dictionary.model.FlashcardModel;
import com.example.dictionary.model.TextModel;
import com.example.dictionary.service.IHintService;
import com.example.dictionary.service.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FlashcardFragment extends Fragment {
    private IHintService iHintService = null;
    private String token="Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZDIiLCJleHAiOjE1ODQzMjAzMDB9.zyuUNXdeOg9zA5yIUH-d9AXSpx0m7DT7A1Rv1D2IBGVhaqTE8S4RtzW66olrS4ObxXnEA5C7btLLLkYyrW9dqg";
    private Retrofit retrofit;
    private RecyclerView listFlashcard;


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
        iHintService.getAllFlashcard(token).enqueue(new Callback<FlashcardModel>() {
            @Override
            public void onResponse(Call<FlashcardModel> call, Response<FlashcardModel> response) {
                int code = response.code();
                if(code == 200){
                    final ArrayList<BodyGetDetailFlashcardModel> flashcards = response.body().getBody();
                    System.out.println(flashcards.size());
                    FlashcardAdapter termFlashcardAdapter = new FlashcardAdapter(flashcards);
                    //Set onClickListener in Adapter:
                    //From HomeFlashcard To Flashcard Detail
                    termFlashcardAdapter.setOnItemClickListener(new FlashcardAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(String id) {
                            Intent intent = new Intent(getContext(), FlashcardDetailActivity.class);
                            System.out.println(id);
                            startActivity(intent);
                        }
                    });

                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    listFlashcard.setLayoutManager(layoutManager);
                    listFlashcard.setAdapter(termFlashcardAdapter);
                }else {
                    ErrorDialog errorDialog = new ErrorDialog("API can not work");
                    errorDialog.show(getFragmentManager(), "Example");
                }
            }

            @Override
            public void onFailure(Call<FlashcardModel> call, Throwable t) {
                t.printStackTrace();
            }
        });




        return view;
    }
    public void onCreateFlashcard(View view){
        Intent intent = new Intent(getContext(), CreateFlashCardActivity.class);
        startActivity(intent);
    }
}
