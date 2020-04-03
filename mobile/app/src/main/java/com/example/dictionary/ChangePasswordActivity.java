package com.example.dictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dictionary.dialog.ChangePasswordDialog;
import com.example.dictionary.model.BodyUserModel;
import com.example.dictionary.model.PasswordModel;
import com.example.dictionary.service.IHintService;
import com.example.dictionary.service.RetrofitClient;
import com.example.dictionary.service.SharePreferenceService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ChangePasswordActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private IHintService iHintService;
    private SharePreferenceService sharePreferenceService;

    TextView oldPassword, newPassword, retypePassword;
    Button changePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        retrofit = RetrofitClient.getClient();
        iHintService = retrofit.create(IHintService.class);
        sharePreferenceService = SharePreferenceService.getInstance(getBaseContext());

        oldPassword = findViewById(R.id.oldPasswordTxt);
        newPassword = findViewById(R.id.newPasswordTxt);
        retypePassword = findViewById(R.id.retypePasswordTxt);

        changePassword = findViewById(R.id.changePasswordBtn);
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (oldPassword.getText().length() != 0 && newPassword.getText().length() != 0 && retypePassword.getText().length() != 0) {
                    if (newPassword.getText().toString().equals(retypePassword.getText().toString())) {

                        PasswordModel passwordModel = new PasswordModel();
                        passwordModel.setNewPassword(newPassword.getText().toString());
                        passwordModel.setOldPassword(oldPassword.getText().toString());
                        iHintService.changePassword(sharePreferenceService.getToken(), passwordModel).enqueue(new Callback<BodyUserModel>() {
                            @Override
                            public void onResponse(Call<BodyUserModel> call, Response<BodyUserModel> response) {
                                if (response.code() == 202) {
                                    ChangePasswordDialog changePasswordDialog = new ChangePasswordDialog("success", "Password updated");
                                    changePasswordDialog.show(getSupportFragmentManager(), "SUCCESS");
                                } else {
                                    ChangePasswordDialog changePasswordDialog = new ChangePasswordDialog("error", "The new password must differ from your previous password");
                                    changePasswordDialog.show(getSupportFragmentManager(), "ERROR");
                                }
                            }

                            @Override
                            public void onFailure(Call<BodyUserModel> call, Throwable t) {

                            }
                        });
                    } else {
                        ChangePasswordDialog changePasswordDialog = new ChangePasswordDialog("error", "New password and Re-type password do not match");
                        changePasswordDialog.show(getSupportFragmentManager(), "ERROR");
                    }
                } else {
                    ChangePasswordDialog changePasswordDialog = new ChangePasswordDialog("error", "please fill all required fields");
                    changePasswordDialog.show(getSupportFragmentManager(), "ERROR");

                }
            }
        });


    }
}
