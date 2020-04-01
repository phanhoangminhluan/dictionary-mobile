package com.example.dictionary.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dictionary.ChoiceActivity;
import com.example.dictionary.MainActivity;
import com.example.dictionary.R;
import com.example.dictionary.dialog.DeleteDialog;
import com.example.dictionary.dialog.LogoutDialog;
import com.example.dictionary.model.BodyLogoutModel;
import com.example.dictionary.service.IHintService;
import com.example.dictionary.service.RetrofitClient;
import com.example.dictionary.service.SharePreferenceService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class SettingFragment extends Fragment {

    private Retrofit retrofit;
    private IHintService iHintService;

    private TextView logout;

    private SharePreferenceService sharePreferenceService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        retrofit = RetrofitClient.getClient();
        iHintService = retrofit.create(IHintService.class);

        sharePreferenceService = SharePreferenceService.getInstance(getContext());


        logout = view.findViewById(R.id.logoutTxt);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogoutDialog logoutDialog = new LogoutDialog();
                logoutDialog.show(getFragmentManager(), "CONFIRM");
            }
        });

        return view;
    }


}
