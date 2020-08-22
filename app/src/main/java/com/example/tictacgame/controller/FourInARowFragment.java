package com.example.tictacgame.controller;

import android.graphics.Color;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tictacgame.R;
import com.example.tictacgame.controller.models.GameButtons;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class FourInARowFragment extends Fragment {
    private static final String TAG = "FourInARowFragment";
    public static final String KEY_TURN_GAME = "turn game";
    public static final String KEY_BUTTONS_ARRAY = "key buttons array";
    public static final String KEY_GAME_FINISHED = "Game finished";
    public static final String KEY_SHOW_WINNER = "show winner";

    private int mTurn = 0;
    private int[][] rowButtonsId =
            {{R.id.row_btn_0_0, R.id.row_btn_0_1, R.id.row_btn_0_2, R.id.row_btn_0_3, R.id.row_btn_0_4},
                    {R.id.row_btn_1_0, R.id.row_btn_1_1, R.id.row_btn_1_2, R.id.row_btn_1_3, R.id.row_btn_1_4},
                    {R.id.row_btn_2_0, R.id.row_btn_2_1, R.id.row_btn_2_2, R.id.row_btn_2_3, R.id.row_btn_2_4},
                    {R.id.row_btn_3_0, R.id.row_btn_3_1, R.id.row_btn_3_2, R.id.row_btn_3_3, R.id.row_btn_3_4},
                    {R.id.row_btn_4_0, R.id.row_btn_4_1, R.id.row_btn_4_2, R.id.row_btn_4_3, R.id.row_btn_4_4}};

    boolean[] played = new boolean[25];
    private GameButtons[][] mButtons = new GameButtons[5][5];

    private int mWinner;


    public FourInARowFragment() {
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
        View view = inflater.inflate(R.layout.fragment_four_in_a_row, container, false);

        setListeners(view);
        setDisable();

        if (savedInstanceState != null) {

            mTurn = savedInstanceState.getInt(KEY_TURN_GAME);

            played = savedInstanceState.getBooleanArray(KEY_BUTTONS_ARRAY);
            boolean flag = true;
            int counter = 0;
            for (int i = 0; i < mButtons.length; i++) {
                for (int j = 0; j < mButtons.length; j++) {
                    if (played[counter++]) {
                        if (flag) {
                            mButtons[i][j].setColor(Color.BLUE);
                            flag = false;
                        } else {
                            mButtons[i][j].setColor(Color.RED);
                            flag = true;
                        }
                        if (i < 4)
                            mButtons[i + 1][j].getButton().setEnabled(true);
                    }
                }
            }

            if(savedInstanceState.getBoolean(KEY_GAME_FINISHED)){
                mWinner = savedInstanceState.getInt(KEY_SHOW_WINNER);
                showSnackBar(true);
            }
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(KEY_TURN_GAME, mTurn);

        int counter = 0;

        for (int i = 0; i < mButtons.length; i++) {
            for (int j = 0; j < mButtons.length; j++) {

                if (mButtons[i][j].isPlayed()) {
                    played[counter++] = mButtons[i][j].isPlayed();
                }
            }
        }
        outState.putBooleanArray(KEY_BUTTONS_ARRAY, played);

        if(isGameFinished()) {
            outState.putInt(KEY_SHOW_WINNER, mWinner);
            outState.putBoolean(KEY_GAME_FINISHED, true);
        }else
            outState.putBoolean(KEY_GAME_FINISHED , false);

    }

    private void setListeners(View view) {
        for (int i = 0; i < mButtons.length; i++) {
            for (int j = 0; j < mButtons[i].length; j++) {
                final int finalI = i;
                final int finalJ = j;
                final Button button = view.findViewById(rowButtonsId[i][j]);
                mButtons[i][j] = new GameButtons();
                mButtons[i][j].setButton(button);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mButtons[finalI][finalJ].setPlayed(true);
                        setTurn(finalI, finalJ);
                        if (finalI < mButtons.length - 1)
                            mButtons[finalI + 1][finalJ].getButton().setEnabled(true);
                        showSnackBar(isGameFinished());
                    }
                });
            }
        }
    }

    public void showSnackBar(boolean isFinished) {

        String winner = mWinner == Color.RED ? "Red" : "Blue";
        final Snackbar snackbar = Snackbar.make(getActivity().findViewById(R.id.fragment_container),
                winner, BaseTransientBottomBar.LENGTH_INDEFINITE);

        snackbar.setDuration(5000000);
        snackbar.setAction("Dismiss", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.dismiss();
            }
        });

        if (isFinished) {
            setDisable();
            snackbar.show();
        } else {
            if (isAllButtonsClicked()) {
                setDisable();
                winner = "No one";
                snackbar.setText(winner);
                snackbar.show();
            }
        }
    }

    public void setDisable() {
        for (int i = 1; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                mButtons[i][j].getButton().setEnabled(false);
            }
        }
    }

    public boolean isAllButtonsClicked() {

        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (!mButtons[i][j].getButton().isEnabled())
                    count++;
            }
        }

        return count == 20;
    }

    private void setTurn(int i, int j) {
        mButtons[i][j].setColor(mTurn % 2 == 0 ? Color.BLUE :
                Color.RED);
        int btnColor = mButtons[i][j].getColor();

        Log.d(TAG, "setTurn: " + btnColor);

        mTurn++;
    }

    public boolean isGameFinished() {

        for (int i = 0; i < rowButtonsId.length; i++) {
            for (int j = 0; j < rowButtonsId.length - 3; j++) {

                if (mButtons[i][j].getColor() != Color.GRAY &&
                        mButtons[i][j].getColor() == (mButtons[i][j + 1].getColor()) &&
                        mButtons[i][j + 1].getColor() == (mButtons[i][j + 2].getColor()) &&
                        mButtons[i][j + 2].getColor() == (mButtons[i][j + 3].getColor())) {

                    mWinner = mButtons[i][j].getColor();
                    return true;
                }
            }
        }

        for (int i = 0; i < rowButtonsId.length - 3; i++) {
            for (int j = 0; j < rowButtonsId.length; j++) {

                if (mButtons[i][j].getColor() != Color.GRAY &&
                        mButtons[i][j].getColor() == (mButtons[i + 1][j].getColor()) &&
                        mButtons[i + 1][j].getColor() == (mButtons[i + 2][j].getColor()) &&
                        mButtons[i + 2][j].getColor() == (mButtons[i + 3][j].getColor())) {

                    mWinner = mButtons[i][j].getColor();
                    return true;
                }
            }
        }

        for (int i = 0; i < rowButtonsId.length - 3; i++) {
            for (int j = 3; j < rowButtonsId.length; j++) {

                if (mButtons[i][j].getColor() != Color.GRAY &&
                        mButtons[i][j].getColor() == (mButtons[i + 1][j - 1].getColor()) &&
                        mButtons[i + 1][j - 1].getColor() == (mButtons[i + 2][j - 2].getColor()) &&
                        mButtons[i + 2][j - 2].getColor() == (mButtons[i + 3][j - 3].getColor())) {

                    mWinner = mButtons[i][j].getColor();
                    return true;
                }
            }
        }

        for (int i = 0; i < rowButtonsId.length - 3; i++) {
            for (int j = 0; j < rowButtonsId.length - 3; j++) {

                if (mButtons[i][j].getColor() != Color.GRAY &&
                        mButtons[i][j].getColor() == mButtons[i + 1][j + 1].getColor() &&
                        mButtons[i + 1][j + 1].getColor() == mButtons[i + 2][j + 2].getColor() &&
                        mButtons[i + 2][j + 2].getColor() == mButtons[i + 3][j + 3].getColor()) {
                    mWinner = mButtons[i][j].getColor();
                    return true;
                }
            }
        }
        //if row and column items were more than 8
        if (rowButtonsId.length > 8)
            for (int i = 4; i < rowButtonsId.length - 3; i--) {
                for (int j = 3; j < rowButtonsId.length; j--) {
                    if (mButtons[i][j].getColor() != Color.GRAY && mButtons[i][j].getColor() == (mButtons[i + 1][j + 1].getColor()) &&
                            mButtons[i + 1][j + 1].getColor() == (mButtons[i + 2][j + 2].getColor()) && mButtons[i + 2][j + 2].getColor() == (mButtons[i + 3][j + 3].getColor())) {
                        mWinner = mButtons[i][j].getColor();
                        return true;
                    }
                }
            }

        return false;
    }
}
