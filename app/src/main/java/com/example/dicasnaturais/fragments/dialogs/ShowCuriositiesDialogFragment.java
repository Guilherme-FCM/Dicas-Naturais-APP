package com.example.dicasnaturais.fragments.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.dicasnaturais.R;

import java.util.Random;

public class ShowCuriositiesDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Você Sabia?")
                .setMessage(getCuriosity())
                .setIcon(R.drawable.java_spring_web);

        return builder.create();
    }

    public String getCuriosity() {
        String[] curiosities = getResources().getStringArray(R.array.curiosities);
        int randomPosition = new Random().nextInt(curiosities.length);
        return curiosities[randomPosition];
    }
}
