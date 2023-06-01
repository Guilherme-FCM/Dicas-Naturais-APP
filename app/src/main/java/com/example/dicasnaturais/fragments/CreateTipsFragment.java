package com.example.dicasnaturais.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dicasnaturais.R;
import com.example.dicasnaturais.daos.TipDao;
import com.example.dicasnaturais.databinding.FragmentCreateTipsBinding;
import com.example.dicasnaturais.models.Tip;

public class CreateTipsFragment extends Fragment {
    private FragmentCreateTipsBinding binding;
    private TipDao dao;

    public CreateTipsFragment(TipDao dao) {
        this.dao = dao;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCreateTipsBinding.inflate(inflater);
        View root = binding.getRoot();

        binding.createButton.setOnClickListener(this::createTip);

        return root;
    }

    public void createTip(View view) {
        String title = binding.title.getText().toString();
        String description = binding.description.getText().toString();

        Tip tip = new Tip(title, description);
        dao.insert(tip);

        renderListTipsFragment();
    }

    public void renderListTipsFragment() {
        FragmentTransaction fragTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragTransaction.replace(R.id.frame, new ListTipsFragment(dao));
        fragTransaction.commit();
    }
}