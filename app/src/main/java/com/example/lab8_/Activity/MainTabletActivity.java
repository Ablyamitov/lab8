package com.example.lab8_.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.lab8_.Fragments.ListFragment;
import com.example.lab8_.Fragments.TaskInfoFragment;
import com.example.lab8_.R;

public class MainTabletActivity extends AppCompatActivity {

    FrameLayout tableListFrameLayout;
    FrameLayout tableTaskInfoFrameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tablet);

        tableListFrameLayout = findViewById(R.id.tabletListFrameLayout);
        tableTaskInfoFrameLayout = findViewById(R.id.tabletTaskInfoFrameLayout);


        Fragment listFragment = new ListFragment();
        Fragment taskInfoFragment = new TaskInfoFragment();



        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.tabletListFrameLayout,listFragment)
                .replace(R.id.tabletTaskInfoFrameLayout,taskInfoFragment)
                .commit();
    }
}