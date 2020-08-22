package com.example.tictacgame.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.tictacgame.R;

import java.io.Serializable;
import java.sql.Timestamp;

public class GameActivity extends AppCompatActivity {

    public static final String CURRENTLY_FRAGMENT = "currently fragment";
    private Button mButtonTicTac;
    private Button mButtonRow;
    private FrameLayout mFrameLayoutContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        findViews();
        setListeners();
    }

    private void findViews() {
        mButtonTicTac = findViewById(R.id.btn_tic_tac_toe);
        mButtonRow = findViewById(R.id.btn_Row);
        mFrameLayoutContainer = findViewById(R.id.fragment_container);
    }

    private void setListeners() {
        final FragmentManager fragmentManager = getSupportFragmentManager();

        mButtonTicTac.setOnClickListener(new View.OnClickListener() {

            TicTacToeFragment mFragmentTicTac = new TicTacToeFragment();

            @Override
            public void onClick(View view) {
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, mFragmentTicTac)
                        .commit();
            }
        });

        mButtonRow.setOnClickListener(new View.OnClickListener() {

            FourInARowFragment mFourInARowFragment = new FourInARowFragment();

            @Override
            public void onClick(View view) {
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, mFourInARowFragment)
                        .commit();
            }
        });
    }
}