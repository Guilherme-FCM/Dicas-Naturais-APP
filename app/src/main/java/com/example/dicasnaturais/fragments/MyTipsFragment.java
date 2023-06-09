package com.example.dicasnaturais.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.dicasnaturais.daos.TipDao;
import com.example.dicasnaturais.databinding.FragmentMyTipsBinding;
import com.example.dicasnaturais.fragments.dialogs.ShowOptionsDialogFragment;
import com.example.dicasnaturais.models.Tip;

import java.util.List;
import java.util.stream.Collectors;

public class MyTipsFragment extends Fragment {
    private FragmentMyTipsBinding binding;
    private TipDao tipDao;
    private List<Tip> tips;

    public MyTipsFragment(TipDao tipDao) {
        this.tipDao = tipDao;
        this.tips = tipDao.list();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMyTipsBinding.inflate(inflater);

        ArrayAdapter tipsAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, getTipsTitle());
        binding.tips.setAdapter(tipsAdapter);
        binding.tips.setOnItemClickListener((adapterView, view, i, l) -> {
            ShowOptionsDialogFragment dialog = new ShowOptionsDialogFragment(tipDao, tips.get(i));
            dialog.show(getActivity().getSupportFragmentManager(), "ShowOptionsDialogFragment");
        });

        return binding.getRoot();
    }

    private List<String> getTipsTitle() {
        return tips.stream().map((tip -> tip.getTitle())).collect(Collectors.toList());
    }
}