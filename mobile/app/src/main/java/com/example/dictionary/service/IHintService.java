package com.example.dictionary.service;

import com.example.dictionary.model.BodyCardDetailModel;
import com.example.dictionary.model.BodyCardModel;
import com.example.dictionary.model.BodyCardSetDetailModel;
import com.example.dictionary.model.BodyCardsetLearnModel;
import com.example.dictionary.model.BodyCountModel;
import com.example.dictionary.model.BodyCreateCard;
import com.example.dictionary.model.BodyFavorite;
import com.example.dictionary.model.BodyFavoriteWord;
import com.example.dictionary.model.BodyLoginModel;
import com.example.dictionary.model.BodyLogoutModel;
import com.example.dictionary.model.BodyRegisterModel;
import com.example.dictionary.model.BodyRememberForgetFlashcardModel;
import com.example.dictionary.model.BodyUserModel;
import com.example.dictionary.model.BodyWordModel;
import com.example.dictionary.model.Card;
import com.example.dictionary.model.CreateCardSetModel;
import com.example.dictionary.model.DetailModel;
import com.example.dictionary.model.BodyCardSetModel;
import com.example.dictionary.model.EmailModel;
import com.example.dictionary.model.LoginViewModel;
import com.example.dictionary.model.PasswordModel;
import com.example.dictionary.model.RegisterModel;
import com.example.dictionary.model.TextModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IHintService {
    @GET("dictionary-flashcard/hint/{textSearch}")
    Call<TextModel> searchText(@Path("textSearch") String textSearch);

    @POST("dictionary-flashcard/word")
    Call<BodyWordModel> createWord(@Header("Authorization") String token, @Body BodyWordModel bodyWordModel);

    @GET("dictionary-flashcard/word/{textDetail}")
    Call<DetailModel> getDetailText(@Path("textDetail") String textDetail);//ToDo 1 DetailModel o1 gọi

    @GET("dictionary-flashcard/card-set")
    Call<BodyCardSetModel> getAllFlashcard(@Header("Authorization") String token);//ToDo 1 DetailModel o1 gọi

    @GET("dictionary-flashcard/card-set/{id}")
    Call<BodyCardSetDetailModel> getAllFlashcardDetail(@Header("Authorization") String token, @Path("id") String id);//


//Todo: @Body: đẩy lên, BodyCreateCard: nhận về
    @POST("dictionary-flashcard/card-set")
    Call<BodyCreateCard> createCardSet(@Header("Authorization") String token, @Body CreateCardSetModel createCardSetModel);

    @GET("dictionary-flashcard/card")
    Call<BodyCardModel> getAllCards(@Header("Authorization") String token);

    @PUT("dictionary-flashcard/card") //
    Call<BodyCardModel> updateFlashcard(@Header("Authorization") String token, @Body Card cardsDetailModel);

    @DELETE("dictionary-flashcard/card/{id}") //
    Call<BodyCardDetailModel> deleteFlashcard(@Header("Authorization") String token, @Path("id") String id);

    @POST("dictionary-flashcard/flashcard/learn/{cardSetId}")
    Call<BodyCardsetLearnModel> createToLearn(@Header("Authorization") String token, @Path("cardSetId") String cardSetId);

    @GET("dictionary-flashcard/flashcard/reset-progress/{id}")
    Call<BodyCardsetLearnModel> resetProgress(@Header("Authorization") String token, @Path("id") String id);

    @PUT("dictionary-flashcard/flashcard/forget/{cardSetSessionId}/{cardId}")
    Call<BodyRememberForgetFlashcardModel> forgetFlashcard(@Header("Authorization") String token, @Path("cardSetSessionId") String cardSetSessionId, @Path("cardId") String cardId);

    @PUT("dictionary-flashcard/flashcard/remember/{cardSetSessionId}/{cardId}")
    Call<BodyRememberForgetFlashcardModel> rememberFlashcard(@Header("Authorization") String token, @Path("cardSetSessionId") String cardSetSessionId, @Path("cardId") String cardId);

    @GET("dictionary-flashcard/flashcard/count/{id}")
    Call<BodyCountModel> getCountFlashcard(@Header("Authorization") String token, @Path("id") String id);

    @POST("dictionary-flashcard/login")
    Call<BodyLoginModel> loginSuccess(@Body LoginViewModel loginViewModel);

    @POST("dictionary-flashcard/user/register")
    Call<BodyRegisterModel> registerAcccount(@Body RegisterModel registerModel); //

    @GET("/dictionary-flashcard/logout")
    Call<BodyLogoutModel> logout(@Header("Authorization") String token);

    @POST("dictionary-flashcard/favorite-word/{word}")
    Call<BodyFavoriteWord> createFavoriteWord(@Header("Authorization") String token, @Path("word") String word);

    @DELETE("dictionary-flashcard/favorite-word/{word}")
    Call<BodyFavoriteWord> deleteFavoriteWord(@Header("Authorization") String token, @Path("word") String word);

    @GET("dictionary-flashcard/favorite-word")
    Call<BodyFavoriteWord> getFavoriteWords(@Header("Authorization") String token);

    @GET("dictionary-flashcard/favorite-word")
    Call<BodyFavorite> getListWords(@Header("Authorization") String token);

    @GET("dictionary-flashcard/user")
    Call<BodyUserModel> getUserInfo(@Header("Authorization") String token);

    @PUT("dictionary-flashcard/user/email")
    Call<BodyUserModel> changeEmail(@Header("Authorization") String token, @Body EmailModel emailModel);

    @PUT("dictionary-flashcard/user/password")
    Call<BodyUserModel> changePassword(@Header("Authorization") String token, @Body PasswordModel passwordModel);

}
