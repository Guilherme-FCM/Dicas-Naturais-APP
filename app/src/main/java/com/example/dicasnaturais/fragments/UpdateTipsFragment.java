package com.example.dicasnaturais.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.dicasnaturais.R;
import com.example.dicasnaturais.daos.TipDao;
import com.example.dicasnaturais.databinding.FragmentCreateTipsBinding;
import com.example.dicasnaturais.models.Tip;

public class UpdateTipsFragment extends Fragment {
    private FragmentCreateTipsBinding binding;
    private TipDao dao;
    private Tip tip;

    public UpdateTipsFragment(TipDao dao, Tip tip) {
        this.dao = dao;
        this.tip = tip;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCreateTipsBinding.inflate(inflater);

        String[] categories = getResources().getStringArray(R.array.categories);
        categories[0] = tip.getCategory();

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter(
                getContext(),
                android.R.layout.simple_spinner_item,
                categories
        );
        binding.category.setAdapter(categoryAdapter);

        binding.title.setText(tip.getTitle());
        binding.description.setText(tip.getDescription());

        binding.createButton.setText("Salvar Dica");
        binding.createButton.setOnClickListener(this::updateTip);

        return binding.getRoot();
    }

    public void updateTip(View view) {
        String title = binding.title.getText().toString();
        String description = binding.description.getText().toString();
        String category = binding.category.getSelectedItem().toString();

        tip.setTitle(title);
        tip.setDescription(description);
        tip.setCategory(category);
        dao.update(tip);

        Toast.makeText(getActivity(), "Dica editada com sucesso", Toast.LENGTH_SHORT).show();
        renderMyTipsFragment();
    }

    public void renderMyTipsFragment() {
        FragmentTransaction fragTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragTransaction.replace(R.id.frame, new MyTipsFragment(dao));
        fragTransaction.commit();
    }
}