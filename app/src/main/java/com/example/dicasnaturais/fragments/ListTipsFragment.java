package com.example.dicasnaturais.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dicasnaturais.MainActivity;
import com.example.dicasnaturais.R;
import com.example.dicasnaturais.daos.TipDao;
import com.example.dicasnaturais.databinding.FragmentListTipsBinding;
import com.example.dicasnaturais.models.Tip;

import java.util.List;

public class ListTipsFragment extends Fragment {
    private FragmentListTipsBinding binding;
    private TipDao dao;

    public ListTipsFragment(TipDao dao) {
        this.dao = dao;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentListTipsBinding.inflate(inflater);
        View root = binding.getRoot();

        binding.addButton.setOnClickListener(this::renderCreateTipsFragment);

        Toast.makeText(getActivity(), "" + dao.list().size(), Toast.LENGTH_SHORT).show();

        return root;
    }

    public List<Tip> listAllTips() {
        return dao.list();
    }

    public void renderCreateTipsFragment(View view) {
        FragmentTransaction fragTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragTransaction.replace(R.id.frame, new CreateTipsFragment(dao));
        fragTransaction.commit();
    }
}