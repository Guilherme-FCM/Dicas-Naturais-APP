package com.example.dicasnaturais.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.dicasnaturais.R;
import com.example.dicasnaturais.models.Tip;

public class ShowTipsDialogFragment extends DialogFragment {
    private Tip tip;

    public ShowTipsDialogFragment(Tip tip) {
        this.tip = tip;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(tip.getTitle())
                .setMessage(tip.getDescription())
                .setIcon(R.drawable.java_spring_web);

        return builder.create();
    }
}
