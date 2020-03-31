package com.example.dictionary.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.dictionary.model.BodyCardDetailModel;
import com.example.dictionary.model.BodyCardModel;
import com.example.dictionary.model.Card;
import com.example.dictionary.service.IHintService;
import com.example.dictionary.service.RetrofitClient;
import com.example.dictionary.service.SharePreferenceService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DeleteDialog extends AppCompatDialogFragment {
    String text, id;
    private IHintService iHintService = null;
    private Retrofit retrofit;
    private SharePreferenceService sharePreferenceService;
    private DeleteDialog.OnItemClickListener onItemClickListener;
    public DeleteDialog(String text, String id,DeleteDialog.OnItemClickListener onItemClickListener) {
        this.text = text;
        this.id = id;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        sharePreferenceService = SharePreferenceService.getInstance(getContext());

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("DELETE").setMessage("Do you want to delete " +
                "" +text)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        retrofit = RetrofitClient.getClient();
                        iHintService = retrofit.create(IHintService.class);
                        System.out.println("InhintService");
                        iHintService.deleteFlashcard(sharePreferenceService.getToken(), id).enqueue(new Callback<BodyCardDetailModel>() {
                            @Override
                            public void onResponse(Call<BodyCardDetailModel> call, Response<BodyCardDetailModel> response) {
                                int code = response.code();
                                System.out.println(response.code());
                                if(code == 202){
                                    onItemClickListener.onItemClickDone();
                                }
                            }
                            @Override
                            public void onFailure(Call<BodyCardDetailModel> call, Throwable t) {
                                t.printStackTrace();
                            }
                        });
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder.create();
    }

    public  interface OnItemClickListener {
        public void onItemClickDone();
    }
}
