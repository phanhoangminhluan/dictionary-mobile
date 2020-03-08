package com.example.dictionary.service;

import com.example.dictionary.model.BodyCardDetailModel;
import com.example.dictionary.model.BodyCardModel;
import com.example.dictionary.model.BodyCardSetDetailModel;
import com.example.dictionary.model.Card;
import com.example.dictionary.model.DetailModel;
import com.example.dictionary.model.BodyCardSetModel;
import com.example.dictionary.model.TextModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IHintService {
    @GET("dictionary-flashcard/hint/{textSearch}")
    Call<TextModel> searchText(@Path("textSearch") String textSearch);
    @GET("dictionary-flashcard/word/{textDetail}")
    Call<DetailModel> getDetailText(@Path("textDetail") String textDetail);//ToDo 1 DetailModel o1 gọi



    @GET("dictionary-flashcard/card-set")
    Call<BodyCardSetModel> getAllFlashcard(@Header("Authorization") String token);//ToDo 1 DetailModel o1 gọi

    @GET("dictionary-flashcard/card-set/{id}")
    Call<BodyCardSetDetailModel> getAllFlashcardDetail(@Header("Authorization") String token , @Path("id") String id);//






    @GET("dictionary-flashcard/card")
    Call<BodyCardModel> getAllCards(@Header("Authorization") String token);

    @PUT("dictionary-flashcard/card")
    Call<BodyCardModel> updateFlashcard(@Header("Authorization") String token, @Body Card cardsDetailModel);

    @DELETE("dictionary-flashcard/card/{id}")
    Call<BodyCardDetailModel> deleteFlashcard(@Header("Authorization") String token, @Path("id") String id);

}
