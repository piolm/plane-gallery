package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fM = getSupportFragmentManager();
        FragmentTransaction fT = fM.beginTransaction();
        MainFragment mainFragment = new MainFragment();
        fT.add(R.id.main_fragment, mainFragment);
        fT.commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getSupportFragmentManager().popBackStack();
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}