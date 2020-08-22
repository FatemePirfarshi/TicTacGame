package com.example.tictacgame.controller;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.tictacgame.R;
import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;

public class TicTacToeFragment extends Fragment {

   // public static final String BUTTON_STRING = "button String";
    public static final String KEY_PLAYED_BUTTON = "played button";

    private int mTurn  = 0;
    
    private Button mButtonTic00, mButtonTic01, mButtonTic02,
            mButtonTic10, mButtonTic11, mButtonTic12,
            mButtonTic20, mButtonTic21, mButtonTic22;

    private FrameLayout mFrameLayoutRoot;

    private int[] ticButtonsId = {R.id.tic_btn_0_0, R.id.tic_btn_0_1, R.id.tic_btn_0_2,
            R.id.tic_btn_1_0, R.id.tic_btn_1_1, R.id.tic_btn_1_2,
            R.id.tic_btn_2_0, R.id.tic_btn_2_1, R.id.tic_btn_2_2};

    private boolean[] isPlayed = new boolean[9];
    private Button[] mButtons = new Button[9];

    public TicTacToeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tic_tac_toe, container, false);

        findViwes(view);
        setListeners(view);

        if(savedInstanceState != null){
//            String buttonText = savedInstanceState.getString(BUTTON_STRING);
//            mButtonTic00.setText(buttonText);
//            isPlayed = savedInstanceState.getBooleanArray(KEY_PLAYED_BUTTON);
//            flag = true;
//            for (int i = 0; i < 9; i++) {
//                if(isPlayed[i]){
//                    if(flag) {
//                        mButtons[i].setText("O");
//                        flag = false;
//                    }else{
//                        mButtons[i].setText("X");
//                        flag = true;
//                    }
//                }
//
//            }
        }

        return view;
    }

    private void findViwes(View view) {
        mFrameLayoutRoot = view.findViewById(R.id.tic_root_layout);
    }


    private void setListeners(View view) {
        for (int id : ticButtonsId) {
            view.findViewById(id).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickButton(view);
                }
            });
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
     //   outState.putBooleanArray(KEY_PLAYED_BUTTON, isPlayed);
      //  outState.putString(BUTTON_STRING, (String) mButtonTic00.getText());
    }

    private void clickButton(View view) {

        if (checkWinner())
            return;

        switch (view.getId()) {

            case R.id.tic_btn_0_0:
                mButtonTic00 = view.findViewById(R.id.tic_btn_0_0);
                setButtonsText(mButtonTic00);
                isPlayed[0] = true;
                mButtons[0] = mButtonTic00;
                break;
            case R.id.tic_btn_0_1:
                mButtonTic01 = view.findViewById(R.id.tic_btn_0_1);
                setButtonsText(mButtonTic01);
                isPlayed[1] = true;
                mButtons[1] = mButtonTic01;
                break;
            case R.id.tic_btn_0_2:
                mButtonTic02 = view.findViewById(R.id.tic_btn_0_2);
                setButtonsText(mButtonTic02);
                isPlayed[2] = true;
                mButtons[2] = mButtonTic02;
                break;
            case R.id.tic_btn_1_0:
                mButtonTic10 = view.findViewById(R.id.tic_btn_1_0);
                setButtonsText(mButtonTic10);
                isPlayed[3] = true;
                mButtons[3] = mButtonTic10;
                break;
            case R.id.tic_btn_1_1:
                mButtonTic11 = view.findViewById(R.id.tic_btn_1_1);
                setButtonsText(mButtonTic11);
                isPlayed[4] = true;
                mButtons[4] = mButtonTic11;
                break;
            case R.id.tic_btn_1_2:
                mButtonTic12 = view.findViewById(R.id.tic_btn_1_2);
                setButtonsText(mButtonTic12);
                isPlayed[5] = true;
                mButtons[5] = mButtonTic12;
                break;
            case R.id.tic_btn_2_0:
                mButtonTic20 = view.findViewById(R.id.tic_btn_2_0);
                setButtonsText(mButtonTic20);
                isPlayed[6] = true;
                mButtons[6] = mButtonTic20;
                break;
            case R.id.tic_btn_2_1:
                mButtonTic21 = view.findViewById(R.id.tic_btn_2_1);
                setButtonsText(mButtonTic21);
                isPlayed[7] = true;
                mButtons[7] = mButtonTic21;
                break;
            case R.id.tic_btn_2_2:
                mButtonTic22 = view.findViewById(R.id.tic_btn_2_2);
                setButtonsText(mButtonTic22);
                isPlayed[8] = true;
                mButtons[8] = mButtonTic22;
                break;
        }
        checkWinner();
    }

    boolean flag = true;

    private void setButtonsText(Button button) {
        if (flag) {
            button.setText("O");
            button.setEnabled(false);
            flag = false;
        } else {
            button.setText("X");
            button.setEnabled(false);
            flag = true;
        }
    }

    private boolean checkWinner() {

        boolean line1 = winState(mButtonTic00, mButtonTic01, mButtonTic02);
        boolean line2 = winState(mButtonTic10, mButtonTic11, mButtonTic12);
        boolean line3 = winState(mButtonTic20, mButtonTic21, mButtonTic22);
        boolean column1 = winState(mButtonTic00, mButtonTic10, mButtonTic20);
        boolean column2 = winState(mButtonTic01, mButtonTic11, mButtonTic21);
        boolean column3 = winState(mButtonTic02, mButtonTic12, mButtonTic22);
        boolean diagonal1 = winState(mButtonTic20, mButtonTic11, mButtonTic02);
        boolean diagonal2 = winState(mButtonTic00, mButtonTic11, mButtonTic22);

        return (line1 || line2 || line3 ||
                column1 || column2 || column3 ||
                diagonal1 || diagonal2);
    }

    private boolean winState(Button first, Button second, Button last) {

        if (first != null && second != null && last != null)

            if (first.getText().equals(second.getText())
                    && first.getText().equals(last.getText())) {

                if (first.getText().toString().equals("O"))

                    Snackbar.make(getActivity().findViewById(android.R.id.content),
                            "O is Winner", Snackbar.LENGTH_SHORT).show();
                else
                    Snackbar.make(getActivity().findViewById(android.R.id.content),
                            "X is Winner", Snackbar.LENGTH_SHORT).show();
                return true;
            }
        return false;
    }
}