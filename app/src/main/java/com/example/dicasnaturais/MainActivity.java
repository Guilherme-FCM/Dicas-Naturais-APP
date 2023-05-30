package com.example.dicasnaturais;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.Toast;

import com.example.dicasnaturais.databinding.ActivityMainBinding;
import com.example.dicasnaturais.fragments.ListTipsFragment;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private Database db;
    private FragmentManager fragManager;
    private FragmentTransaction fragTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = Database.getConnection(getApplicationContext());
        fragManager = getSupportFragmentManager();

        render(new ListTipsFragment());
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    void render(Fragment fragment) {
        fragTransaction = fragManager.beginTransaction();
        fragTransaction.replace(R.id.frame, fragment);
        fragTransaction.commit();
    }
}