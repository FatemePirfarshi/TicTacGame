package com.example.tictacgame.controller;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.tictacgame.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class TicTacToeFragment extends Fragment {

    public static final String KEY_PLAY_TURN = "play turn";
    public static final String KEY_PLAY_RESULT_ARRAY = "play result array";
    public static final String SHOW_WINNER = "show winner";
    public static final String GAME_FINISHED = "game finished";
    public static final String NO_ONE_IS_WINNER = "no one is winner";

    private int mTurn = 0;

    private LinearLayout mLinearLayoutRoot;

    private int[][] ticButtonsId = {{R.id.tic_btn_0_0, R.id.tic_btn_0_1, R.id.tic_btn_0_2},
            {R.id.tic_btn_1_0, R.id.tic_btn_1_1, R.id.tic_btn_1_2},
            {R.id.tic_btn_2_0, R.id.tic_btn_2_1, R.id.tic_btn_2_2}};

    private boolean[][] isPlayed = new boolean[3][3];
    private boolean[] playResult = new boolean[9];
    private boolean noOne = false;
    private Button[][] mButtons = new Button[3][3];


    private String mWinner = "";

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

        if (savedInstanceState != null) {
            mTurn = savedInstanceState.getInt(KEY_PLAY_TURN);

            playResult = savedInstanceState.getBooleanArray(KEY_PLAY_RESULT_ARRAY);

            boolean flag = true;
            int counter = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (playResult[counter++]) {
                        if (flag) {
                            mButtons[i][j].setText("O");
                            flag = false;
                        } else {
                            mButtons[i][j].setText("X");
                            flag = true;
                        }
                        mButtons[i][j].setEnabled(false);
                    }
                }
            }

            if(savedInstanceState.getBoolean(GAME_FINISHED)){
                mWinner = savedInstanceState.getString(SHOW_WINNER);
                showSnackBar(true);
            }

            if(savedInstanceState.getBoolean(NO_ONE_IS_WINNER)){
                final Snackbar snackbar = Snackbar.make(getActivity().findViewById(R.id.fragment_container),
                        "no one", BaseTransientBottomBar.LENGTH_INDEFINITE);
                snackbar.setDuration(5000000);
                snackbar.setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        snackbar.dismiss();
                        snackbar.show();
                    }
                });
            }
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(KEY_PLAY_TURN, mTurn);

        int counter = 0;

        for (int i = 0; i < mButtons.length; i++) {
            for (int j = 0; j < mButtons.length; j++) {

                if (isPlayed[i][j]) {
                    playResult[counter] = isPlayed[i][j];
                }
                counter++;
            }
        }

        outState.putBooleanArray(KEY_PLAY_RESULT_ARRAY, playResult);

        if(isGameFinished()) {
            outState.putString(SHOW_WINNER, mWinner);
            outState.putBoolean(GAME_FINISHED, true);
        }else
            outState.putBoolean(GAME_FINISHED , false);

        outState.putBoolean(NO_ONE_IS_WINNER, noOne);
    }

    private void findViwes(View view) {
        mLinearLayoutRoot = view.findViewById(R.id.tic_root_layout);
    }


    private void setListeners(View view) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int finalI = i;
                final int finalJ = j;
                mButtons[i][j] = view.findViewById(ticButtonsId[i][j]);
                mButtons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isPlayed[finalI][finalJ] = true;
                        setTurn(finalI, finalJ);
                        mButtons[finalI][finalJ].setEnabled(false);
                        showSnackBar(isGameFinished());
                    }
                });
            }
        }
    }

    private void setTurn(int i, int j) {
        mButtons[i][j].setText(mTurn % 2 == 0 ? "O" : "X");
        mTurn++;
    }

    private void showSnackBar(boolean isFinished) {
        String winner;
        if (mWinner.equals("O"))
            winner = "O";
        else
            winner = "X";

        final Snackbar snackbar = Snackbar.make(getActivity().findViewById(R.id.fragment_container),
                winner + " is Winner", BaseTransientBottomBar.LENGTH_INDEFINITE);
        snackbar.setDuration(5000000);
        snackbar.setAction("Dismiss", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.dismiss();
            }
        });

        if (isFinished) {
            snackbar.show();
            setEnableButtons();
        } else {
            if (isAllButtonsClicked()) {
                winner = "No one";
                snackbar.setText(winner);
                snackbar.show();
                setEnableButtons();
                noOne = true;
            }
        }

    }

    private void setEnableButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(mButtons[i][j].isEnabled())
                    mButtons[i][j].setEnabled(false);
            }
        }
    }

    private boolean isGameFinished() {

        for (int i = 0; i < 3; i++) {

            if (mButtons[i][0].getText().equals(mButtons[i][1].getText()) &&
                    mButtons[i][0].getText().equals(mButtons[i][2].getText()) &&
                    mButtons[i][0].getText() != "") {

                mWinner = mButtons[i][0].getText().toString();
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {

            if (mButtons[0][i].getText().equals(mButtons[1][i].getText()) &&
                    mButtons[0][i].getText().equals(mButtons[2][i].getText()) &&
                    mButtons[0][i].getText() != "") {

                mWinner = mButtons[i][0].getText().toString();
                return true;
            }
        }

        if (mButtons[0][0].getText().equals(mButtons[1][1].getText()) &&
                mButtons[1][1].getText().equals(mButtons[2][2].getText()) &&
                mButtons[0][0].getText() != "") {

            mWinner = mButtons[0][0].getText().toString();
            return true;
        }

        if (mButtons[0][2].getText().equals(mButtons[1][1].getText()) &&
                mButtons[1][1].getText().equals(mButtons[2][0].getText()) &&
                mButtons[0][2].getText() != "") {

            mWinner = mButtons[0][2].getText().toString();
            return true;
        }

        return false;
    }


    public boolean isAllButtonsClicked() {

        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!mButtons[i][j].isEnabled())
                    count++;
            }
        }

        return count == 9;
    }

}