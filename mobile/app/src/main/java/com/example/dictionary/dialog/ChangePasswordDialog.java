package com.example.dictionary.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.dictionary.GoToSettingActivity;

public class ChangePasswordDialog extends AppCompatDialogFragment {
    private String status, text;

    public ChangePasswordDialog(String status, String text) {
        this.status = status;
        this.text = text;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(status.toUpperCase()).setMessage(text)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (status.equals("success")) {
                            Intent intent = new Intent(getActivity(), GoToSettingActivity.class);
                            startActivity(intent);
                        }

                    }
                });
        return builder.create();
    }
}
