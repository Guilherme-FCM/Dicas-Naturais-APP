package com.example.dicasnaturais;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
        setSupportActionBar(binding.toolbar);

        render(new ListTipsFragment(db.tipDao()));
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

        }
        return super.onOptionsItemSelected(item);
    }

    void render(Fragment fragment) {
        fragTransaction = fragManager.beginTransaction();
        fragTransaction.replace(R.id.frame, fragment);
        fragTransaction.commit();
    }
}