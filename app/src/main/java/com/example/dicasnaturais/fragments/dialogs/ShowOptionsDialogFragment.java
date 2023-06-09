package com.example.dicasnaturais.fragments.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.dicasnaturais.R;
import com.example.dicasnaturais.daos.TipDao;
import com.example.dicasnaturais.fragments.CreateTipsFragment;
import com.example.dicasnaturais.fragments.MyTipsFragment;
import com.example.dicasnaturais.fragments.UpdateTipsFragment;
import com.example.dicasnaturais.models.Tip;

public class ShowOptionsDialogFragment extends DialogFragment {
    private TipDao dao;
    private Tip tip;

    public ShowOptionsDialogFragment(TipDao dao, Tip tip) {
        this.dao = dao;
        this.tip = tip;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Deseja excluir ou editar sua dica?")
                .setIcon(R.drawable.java_spring_web)
                .setNeutralButton("Excluir", (dialogInterface, i) -> {
                    dao.delete(tip);
                    Toast.makeText(getActivity(), "Dica removida com sucesso", Toast.LENGTH_SHORT).show();
                    render(new MyTipsFragment(dao));
                })
                .setPositiveButton("Editar", (dialogInterface, i) -> {
                    render(new UpdateTipsFragment(dao, tip));
                });

        return builder.create();
    }

    private void render(Fragment fragment) {
        FragmentTransaction fragTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragTransaction.replace(R.id.frame, fragment);
        fragTransaction.commit();
    }
}
