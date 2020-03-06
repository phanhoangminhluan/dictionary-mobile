package com.example.dictionary.service;

import com.example.dictionary.model.DetailModel;
import com.example.dictionary.model.FlashcardModel;
import com.example.dictionary.model.TextModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface IHintService {
    @GET("dictionary-flashcard/hint/{textSearch}")
    Call<TextModel> searchText(@Path("textSearch") String textSearch);
    @GET("dictionary-flashcard/word/{textDetail}")
    Call<DetailModel> getDetailText(@Path("textDetail") String textDetail);//ToDo 1 DetailModel o1 gọi

    @GET("dictionary-flashcard/card-set")
    Call<FlashcardModel> getAllFlashcard(@Header("Authorization") String token);//ToDo 1 DetailModel o1 gọi
}
