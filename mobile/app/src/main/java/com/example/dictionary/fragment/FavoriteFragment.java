package com.example.dictionary.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dictionary.DetailActivity;
import com.example.dictionary.R;
import com.example.dictionary.adapter.FavoriteAdapter;
import com.example.dictionary.adapter.FlashcardAdapter;
import com.example.dictionary.adapter.TextAdapter;
import com.example.dictionary.model.BodyFavorite;
import com.example.dictionary.model.DetailModel;
import com.example.dictionary.service.IHintService;
import com.example.dictionary.service.RetrofitClient;
import com.example.dictionary.service.SharePreferenceService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FavoriteFragment extends Fragment {
    private String text;
    private TextView text_favorite;
    private Retrofit retrofit;
    private IHintService iHintService = null;
    private SharePreferenceService sharePreferenceService;
    private RecyclerView listWordF;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_favorite, container, false);
        // Inflate the layout for this fragment
        sharePreferenceService = SharePreferenceService.getInstance(getContext());
        retrofit = RetrofitClient.getClient();
        iHintService = retrofit.create(IHintService.class);

        listWordF = view.findViewById(R.id.listWordFavorite);
        //cách mới = Linnear Layout in Adapter but Dong can't research anymore :v
//        listWordF.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//            @Override
//            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
//                return false;
//            }
//
//            @Override
//            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
//                System.out.println("Day la vo Dong ne");
//
//            }
//
//            @Override
//            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//            }
//        });

        iHintService.getListWords(sharePreferenceService.getToken()).enqueue(new Callback<BodyFavorite>() {
            @Override
            public void onResponse(Call<BodyFavorite> call, Response<BodyFavorite> response) {

                if(response.code() == 200){
                    FavoriteAdapter favoriteAdapter = new FavoriteAdapter(response.body().getBody());
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    listWordF.setLayoutManager(layoutManager);
                    listWordF.setAdapter(favoriteAdapter);
                    favoriteAdapter.setOnItemClickListener(new FavoriteAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(String word) {
                            Intent intent = new Intent(getContext(), DetailActivity.class);
                            intent.putExtra(HomeFragment.KEY_TEXT_SEARCH, word);
                            startActivity(intent);

                        }
                    });



                }
            }

            @Override
            public void onFailure(Call<BodyFavorite> call, Throwable t) {
                t.printStackTrace();

            }
        });
        return view;




    }

}



