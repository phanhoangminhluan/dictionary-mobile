package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.dictionary.dialog.ErrorDialog;
import com.example.dictionary.dialog.LoginDialog;
import com.example.dictionary.model.BodyLoginModel;
import com.example.dictionary.model.LoginViewModel;
import com.example.dictionary.service.IHintService;
import com.example.dictionary.service.RetrofitClient;
import com.example.dictionary.service.SharePreferenceService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private IHintService iHintService = null;
    private EditText edtUsername;
    private EditText editPassword;
    private SharePreferenceService sharePreferenceService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharePreferenceService = SharePreferenceService.getInstance(getBaseContext());
        setContentView(R.layout.activity_main);
        edtUsername = findViewById(R.id.txtUsername);
        editPassword = findViewById(R.id.txtPaswordLogin);
        retrofit = RetrofitClient.getClient();
        iHintService = retrofit.create(IHintService.class);
        edtUsername.setText("ad");
        editPassword.setText("123");

    }

    public void clickToHomePage(View view) {
        LoginViewModel loginViewModel = new LoginViewModel();
        loginViewModel.setUsername(edtUsername.getText().toString());
        loginViewModel.setPassword(editPassword.getText().toString());
        iHintService.loginSuccess(loginViewModel).enqueue(new Callback<BodyLoginModel>() {
            @Override
            public void onResponse(Call<BodyLoginModel> call, Response<BodyLoginModel> response) {
                if(response.code() == 200){
                    sharePreferenceService.setToken(response.body().getBody().getToken());

                    Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                    startActivity(intent);

                }
                else{
                    LoginDialog loginDialog = new LoginDialog();
                    loginDialog.show(getSupportFragmentManager(), "ERROR");
                }

            }

            @Override
            public void onFailure(Call<BodyLoginModel> call, Throwable t) {
                t.printStackTrace();

            }
        });



    }


}
