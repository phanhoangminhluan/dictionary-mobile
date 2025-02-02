package com.example.dictionary.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionary.DetailActivity;
import com.example.dictionary.R;
import com.example.dictionary.adapter.TextAdapter;
import com.example.dictionary.dialog.ErrorDialog;
import com.example.dictionary.model.TextModel;
import com.example.dictionary.service.IHintService;
import com.example.dictionary.service.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment {
    public static String KEY_TEXT_SEARCH = "KEY_TEXT_SEARCH";
    private IHintService iHintService = null;
    private ImageView btnSearch;
    private EditText searchText;
    private Retrofit retrofit;
    private RecyclerView textList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        btnSearch = view.findViewById(R.id.searchView);
        searchText = (EditText) view.findViewById(R.id.txtTextSearch);
        textList = view.findViewById(R.id.textList);
        retrofit = RetrofitClient.getClient();
        iHintService = retrofit.create(IHintService.class);
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                final String textSearch = searchText.getText().toString();
                iHintService.searchText(textSearch).enqueue(new Callback<TextModel>() {

                    @Override
                    public void onResponse(Call<TextModel> call, Response<TextModel> response) {
                        int code = response.code();
                        if (code == 200) {
                            final ArrayList<String> texts = response.body().getBody();
                            TextAdapter textAdapter = new TextAdapter(texts);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            textList.setLayoutManager(layoutManager);
                            textList.setAdapter(textAdapter);
                            textAdapter.setOnItemClickListener(new TextAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(String text) {
                                    Intent intent = new Intent(getContext(), DetailActivity.class);
                                    intent.putExtra(KEY_TEXT_SEARCH, text);
                                    startActivity(intent);

                                }
                            });
                        }

                    }

                    @Override
                    public void onFailure(Call<TextModel> call, Throwable t) {

                        System.out.println(t.getMessage());

                    }

                });

            }
        });


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String textSearch = searchText.getText().toString();
                iHintService.searchText(textSearch).enqueue(new Callback<TextModel>() {

                    @Override
                    public void onResponse(Call<TextModel> call, Response<TextModel> response) {
                        int code = response.code();
                        if (code == 200) {
                            final ArrayList<String> texts = response.body().getBody();
                            TextAdapter textAdapter = new TextAdapter(texts);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            textList.setLayoutManager(layoutManager);
                            textList.setAdapter(textAdapter);
                            textAdapter.setOnItemClickListener(new TextAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(String text) {
                                    Intent intent = new Intent(getContext(), DetailActivity.class);
                                    intent.putExtra(KEY_TEXT_SEARCH, text);
                                    startActivity(intent);

                                }
                            });
                        } else {
                            ErrorDialog errorDialog = new ErrorDialog(textSearch);
                            errorDialog.show(getFragmentManager(), "Example");
                        }
                    }

                    @Override
                    public void onFailure(Call<TextModel> call, Throwable t) {
                        System.out.println(t.getMessage());

                    }

                });

            }
        });

        return view;
    }


}
