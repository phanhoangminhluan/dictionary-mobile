package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dictionary.dialog.ChangeEmailDialog;
import com.example.dictionary.fragment.SettingFragment;
import com.example.dictionary.model.BodyUserModel;
import com.example.dictionary.model.EmailModel;
import com.example.dictionary.service.IHintService;
import com.example.dictionary.service.RetrofitClient;
import com.example.dictionary.service.SharePreferenceService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ChangeEmailActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private IHintService iHintService;
    private SharePreferenceService sharePreferenceService;

    EditText email;
    Button changeEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);

        retrofit = RetrofitClient.getClient();
        iHintService = retrofit.create(IHintService.class);
        sharePreferenceService = SharePreferenceService.getInstance(getBaseContext());

        email = findViewById(R.id.emailTxt);
        email.setText(getIntent().getStringExtra("email"));

        changeEmail = findViewById(R.id.changeEmailBtn);
        changeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmailModel emailModel = new EmailModel();
                emailModel.setEmail(email.getText().toString());
                iHintService.changeEmail(sharePreferenceService.getToken(), emailModel).enqueue(new Callback<BodyUserModel>() {
                    @Override
                    public void onResponse(Call<BodyUserModel> call, Response<BodyUserModel> response) {
                        if (response.code() == 202) {
                            ChangeEmailDialog changeEmailDialog = new ChangeEmailDialog("success", "Email updated");
                            changeEmailDialog.show(getSupportFragmentManager(), "SUCCESS");

                        } else {
                            ChangeEmailDialog changeEmailDialog = new ChangeEmailDialog("error", "New email cannot be same as old email");
                            changeEmailDialog.show(getSupportFragmentManager(), "ERROR");
                        }
                    }

                    @Override
                    public void onFailure(Call<BodyUserModel> call, Throwable t) {

                    }
                });

            }
        });
    }
}
