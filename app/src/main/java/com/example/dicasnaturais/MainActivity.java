package com.example.dicasnaturais;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.dicasnaturais.databinding.ActivityMainBinding;
import com.example.dicasnaturais.fragments.ListTipsFragment;
import com.example.dicasnaturais.fragments.ProfileFragment;

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

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                closeKeyboard();
                return this.onQueryTextChange(query);
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.equals(""))
                    render(new ListTipsFragment(db.tipDao()));
                else
                    render(new ListTipsFragment(db.tipDao(), db.tipDao().filterByTitle(newText)));
                return true;
            }
        });
        searchView.setOnCloseListener(() -> {
            render(new ListTipsFragment(db.tipDao()));
            closeKeyboard();
            return true;
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.profile)
            render( new ProfileFragment(db.tipDao()) );

        else if (id == R.id.tips)
            Toast.makeText(this, "Minhas dicas", Toast.LENGTH_SHORT).show();

        else if (id == R.id.settings)
            Toast.makeText(this, "Configurações", Toast.LENGTH_SHORT).show();

        else if (id == R.id.logout)
            finish();

        else if (id == android.R.id.home)
            render( new ListTipsFragment(db.tipDao()) );

        return true;
    }

    private void render(Fragment fragment) {
        fragTransaction = fragManager.beginTransaction();
        fragTransaction.replace(R.id.frame, fragment);
        fragTransaction.commit();
    }

    private void closeKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }
}