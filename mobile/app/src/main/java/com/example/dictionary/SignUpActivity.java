package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.dictionary.dialog.LoginDialog;
import com.example.dictionary.dialog.RegistorDialog;
import com.example.dictionary.model.BodyRegisterModel;
import com.example.dictionary.model.RegisterModel;
import com.example.dictionary.service.IHintService;
import com.example.dictionary.service.RetrofitClient;
import com.example.dictionary.service.SharePreferenceService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignUpActivity extends AppCompatActivity {
    private IHintService iHintService = null;
    private Retrofit retrofit;
    private EditText edtUsername;
    private EditText edtPassword;
    private EditText edtPassword2;
    private EditText edtEmail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        edtUsername = findViewById(R.id.txtUsernameSignUp);
        edtPassword = findViewById(R.id.txtPw);
        edtPassword2 = findViewById(R.id.txtPw2);
        edtEmail = findViewById(R.id.txtEmail);
        retrofit = RetrofitClient.getClient();
        iHintService = retrofit.create(IHintService.class);


    }
    public void onClickLoginFromSignUp(View view){
        final RegisterModel registerModel = new RegisterModel();
        registerModel.setUsername(edtUsername.getText().toString());
        registerModel.setPassword(edtPassword.getText().toString());
        registerModel.setPassword(edtPassword2.getText().toString());
        registerModel.setEmail(edtEmail.getText().toString());

        iHintService.registerAcccount(registerModel).enqueue(new Callback<BodyRegisterModel>() {
            @Override
            public void onResponse(Call<BodyRegisterModel> call, Response<BodyRegisterModel> response) {
                if(response.code() == 200){
                    Intent intent = new Intent(getBaseContext(),MainActivity.class );
                    startActivity(intent);
                }
                else {
                    RegistorDialog registorDialog = new RegistorDialog();
                    registorDialog.show(getSupportFragmentManager(), "ERROR");
                    System.out.println(response.code());
                    System.out.println("Day la 1"+registerModel.getUsername());
                    System.out.println("Day la 2"+ response.body().getBody().getEmail());

                }
            }

            @Override
            public void onFailure(Call<BodyRegisterModel> call, Throwable t) {
                    t.printStackTrace();
            }
        });

    }
}
