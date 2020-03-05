package com.example.dictionary.fragment;

import android.content.Intent;
import android.os.Bundle;
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
    private IHintService iHintService = null;
    private ImageView searchView;
    private EditText searchText;
    private Retrofit retrofit;
    private RecyclerView textList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        searchView = view.findViewById(R.id.searchView);
        searchText = view.findViewById(R.id.txtTextSearch);
        textList = view.findViewById(R.id.textList);
        retrofit = RetrofitClient.getClient();
        iHintService = retrofit.create(IHintService.class);

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String textSearch = searchText.getText().toString();


//                System.out.println(textSearch);
//                List<String> texts = hintService.searchText(textSearch);
//
//                if(texts == null) {
//                    textresult = "Not Found";
//                }else {
//                    for (int i = 0; i < texts.size(); i++){
//                        textresult = textresult + " " + texts.get(i);
//                    }
//                }
                iHintService.searchText(textSearch).enqueue(new Callback<TextModel>() {

                    @Override
                    public void onResponse(Call<TextModel> call, Response<TextModel> response) {
                        int code = response.code();
                        System.out.println(response.code());
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
                                    intent.putExtra("hi", text);
                                    startActivity(intent);
                                    System.out.println(texts);

                                }
                            });
                        }else{
                            ErrorDialog errorDialog = new ErrorDialog(textSearch);
                            errorDialog.show(getFragmentManager(), "Example");
                            System.out.println(errorDialog);
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
