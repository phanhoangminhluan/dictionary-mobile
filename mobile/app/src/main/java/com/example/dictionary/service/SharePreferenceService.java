package com.example.dictionary.service;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferenceService {
    private SharedPreferences pre;
    private static SharePreferenceService sharePreferenceService;
    private Context context;

    public SharePreferenceService(Context context) {
        this.pre = context.getSharedPreferences("MyData", Context.MODE_PRIVATE);
    }

    public static SharePreferenceService getInstance(Context context) {
        if (sharePreferenceService == null) {
            sharePreferenceService = new SharePreferenceService(context);
        }
        return sharePreferenceService;
    }

    public void setToken(String token) {
        SharedPreferences.Editor editor = pre.edit();
        editor.putString("token", token);
        editor.commit();
    }

    public String getToken() {
        String result = pre.getString("token", "");
        return result;
    }

}
