package com.example.dicasnaturais.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dicasnaturais.R;
import com.example.dicasnaturais.daos.TipDao;
import com.example.dicasnaturais.databinding.FragmentListTipsBinding;
import com.example.dicasnaturais.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;
    private TipDao tipDao;
    private final int max = 3;

    public ProfileFragment(TipDao tipDao) {
        this.tipDao = tipDao;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater);
        View root = binding.getRoot();

        int countTips = tipDao.count();
        int level = (int) Math.floor(countTips / max) + 1;
        int progress = countTips % max;

        binding.progress.setMax(max);
        binding.level.setText(String.valueOf(level));
        binding.progress.setProgress(progress);

        return root;
    }
}