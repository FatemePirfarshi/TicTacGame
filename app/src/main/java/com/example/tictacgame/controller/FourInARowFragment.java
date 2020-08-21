package com.example.tictacgame.controller;

import android.annotation.SuppressLint;
import android.graphics.Color;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tictacgame.R;
import com.google.android.material.snackbar.Snackbar;

public class FourInARowFragment extends Fragment {

    private Button mButtonRow00, mButtonRow01, mButtonRow02, mButtonRow03, mButtonRow04,
            mButtonRow10, mButtonRow11, mButtonRow12, mButtonRow13, mButtonRow14,
            mButtonRow20, mButtonRow21, mButtonRow22, mButtonRow23, mButtonRow24,
            mButtonRow30, mButtonRow31, mButtonRow32, mButtonRow33, mButtonRow34,
            mButtonRow40, mButtonRow41, mButtonRow42, mButtonRow43, mButtonRow44;

    private int[][] rowButtonsId =
            {{R.id.row_btn_0_0, R.id.row_btn_0_1, R.id.row_btn_0_2, R.id.row_btn_0_3, R.id.row_btn_0_4},
                    {R.id.row_btn_1_0, R.id.row_btn_1_1, R.id.row_btn_1_2, R.id.row_btn_1_3, R.id.row_btn_1_4},
                    {R.id.row_btn_2_0, R.id.row_btn_2_1, R.id.row_btn_2_2, R.id.row_btn_2_3, R.id.row_btn_2_4},
                    {R.id.row_btn_3_0, R.id.row_btn_3_1, R.id.row_btn_3_2, R.id.row_btn_3_3, R.id.row_btn_3_4},
                    {R.id.row_btn_4_0, R.id.row_btn_4_1, R.id.row_btn_4_2, R.id.row_btn_4_3, R.id.row_btn_4_4}};

//    private void findViews(View view) {
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 4; j++) {
//                view.findViewById(rowButtonsId[i][j]);
//            }
//        }
//        enableButtons();
//    }

    private void findViews(View view){
        mButtonRow00 = view.findViewById(R.id.row_btn_0_0);
        mButtonRow01 = view.findViewById(R.id.row_btn_0_1);
        mButtonRow02 = view.findViewById(R.id.row_btn_0_2);
        mButtonRow03 = view.findViewById(R.id.row_btn_0_3);
        mButtonRow04 = view.findViewById(R.id.row_btn_0_4);
        mButtonRow10 = view.findViewById(R.id.row_btn_1_0);
        mButtonRow11 = view.findViewById(R.id.row_btn_1_1);
        mButtonRow12 = view.findViewById(R.id.row_btn_1_2);
        mButtonRow13 = view.findViewById(R.id.row_btn_1_3);
        mButtonRow14 = view.findViewById(R.id.row_btn_1_4);
        mButtonRow20 = view.findViewById(R.id.row_btn_2_0);
        mButtonRow21 = view.findViewById(R.id.row_btn_2_1);
        mButtonRow22 = view.findViewById(R.id.row_btn_2_2);
        mButtonRow23 = view.findViewById(R.id.row_btn_2_3);
        mButtonRow24 = view.findViewById(R.id.row_btn_2_4);
        mButtonRow30 = view.findViewById(R.id.row_btn_3_0);
        mButtonRow31 = view.findViewById(R.id.row_btn_3_1);
        mButtonRow32 = view.findViewById(R.id.row_btn_3_2);
        mButtonRow33 = view.findViewById(R.id.row_btn_3_3);
        mButtonRow34 = view.findViewById(R.id.row_btn_3_4);
        mButtonRow40 = view.findViewById(R.id.row_btn_4_0);
        mButtonRow41 = view.findViewById(R.id.row_btn_4_1);
        mButtonRow42 = view.findViewById(R.id.row_btn_4_2);
        mButtonRow43 = view.findViewById(R.id.row_btn_4_3);
        mButtonRow44 = view.findViewById(R.id.row_btn_4_4);

    }

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
        findViews(view);
        setListeners(view);

        return view;
    }

    private void setListeners(View view) {
        for (int[] id : rowButtonsId) {
            for (int rowId : id)
                view.findViewById(rowId).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        clickButton(view);
                    }});
        }
    }

    private void clickButton(View view) {

        if (checkWinner())
            return;

        switch (view.getId()) {

            case R.id.row_btn_0_0:
                setButtonsColor(mButtonRow00);
                mButtonRow10.setEnabled(true);
                break;
            case R.id.row_btn_0_1:
                setButtonsColor(mButtonRow01);
                mButtonRow11.setEnabled(true);
                break;
            case R.id.row_btn_0_2:
                setButtonsColor(mButtonRow02);
                mButtonRow12.setEnabled(true);
                break;
            case R.id.row_btn_0_3:
                setButtonsColor(mButtonRow03);
                mButtonRow13.setEnabled(true);
                break;
            case R.id.row_btn_0_4:
                setButtonsColor(mButtonRow04);
                mButtonRow14.setEnabled(true);
                break;
            case R.id.row_btn_1_0:
                setButtonsColor(mButtonRow10);
                mButtonRow20.setEnabled(true);
                break;
            case R.id.row_btn_1_1:
                setButtonsColor(mButtonRow11);
                mButtonRow21.setEnabled(true);
                break;
            case R.id.row_btn_1_2:
                setButtonsColor(mButtonRow12);
                mButtonRow22.setEnabled(true);
                break;
            case R.id.row_btn_1_3:
                setButtonsColor(mButtonRow13);
                mButtonRow23.setEnabled(true);
                break;
            case R.id.row_btn_1_4:
                setButtonsColor(mButtonRow14);
                mButtonRow24.setEnabled(true);
                break;
            case R.id.row_btn_2_0:
                setButtonsColor(mButtonRow20);
                mButtonRow30.setEnabled(true);
                break;
            case R.id.row_btn_2_1:
                setButtonsColor(mButtonRow21);
                mButtonRow31.setEnabled(true);
                break;
            case R.id.row_btn_2_2:
                setButtonsColor(mButtonRow22);
                mButtonRow32.setEnabled(true);
                break;
            case R.id.row_btn_2_3:
                setButtonsColor(mButtonRow23);
                mButtonRow33.setEnabled(true);
                break;
            case R.id.row_btn_2_4:
                setButtonsColor(mButtonRow24);
                mButtonRow34.setEnabled(true);
                break;
            case R.id.row_btn_3_0:
                setButtonsColor(mButtonRow30);
                mButtonRow40.setEnabled(true);
                break;
            case R.id.row_btn_3_1:
                setButtonsColor(mButtonRow31);
                mButtonRow41.setEnabled(true);
                break;
            case R.id.row_btn_3_2:
                setButtonsColor(mButtonRow32);
                mButtonRow42.setEnabled(true);
                break;
            case R.id.row_btn_3_3:
                setButtonsColor(mButtonRow33);
                mButtonRow43.setEnabled(true);
                break;
            case R.id.row_btn_3_4:
                setButtonsColor(mButtonRow34);
                mButtonRow44.setEnabled(true);
                break;
            case R.id.row_btn_4_0:
                setButtonsColor(mButtonRow40);
                break;
            case R.id.row_btn_4_1:
                setButtonsColor(mButtonRow41);
                break;
            case R.id.row_btn_4_2:
                setButtonsColor(mButtonRow42);
                break;
            case R.id.row_btn_4_3:
                setButtonsColor(mButtonRow43);
                break;
            case R.id.row_btn_4_4:
                setButtonsColor(mButtonRow44);
                break;
        }
        checkWinner();
    }

    boolean flag = true;

    @SuppressLint("ResourceAsColor")
    private void setButtonsColor(Button button) {

        if (button.isEnabled())
            if (flag) {
                button.setBackgroundColor(Color.RED);
                button.setEnabled(false);
                flag = false;
            } else {
                button.setBackgroundColor(Color.BLUE);
                button.setEnabled(false);
                flag = true;
            }
    }

    private void enableButtons() {

        setEnableButton(true, mButtonRow00, mButtonRow01,
                mButtonRow02, mButtonRow03, mButtonRow04);

        setEnableButton(false, mButtonRow10, mButtonRow11,
                mButtonRow12, mButtonRow13, mButtonRow14);

        setEnableButton(false, mButtonRow20, mButtonRow21,
                mButtonRow22, mButtonRow23, mButtonRow24);

        setEnableButton(false, mButtonRow30, mButtonRow31,
                mButtonRow32, mButtonRow33, mButtonRow34);

        setEnableButton(false, mButtonRow40, mButtonRow41,
                mButtonRow42, mButtonRow43, mButtonRow44);
    }

    private void setEnableButton(boolean active, Button buttonRow00, Button buttonRow01,
                                 Button buttonRow02, Button buttonRow03, Button buttonRow04) {

        buttonRow00.setEnabled(active);
        buttonRow01.setEnabled(active);
        buttonRow02.setEnabled(active);
        buttonRow03.setEnabled(active);
        buttonRow04.setEnabled(active);
    }

    private boolean checkWinner() {

        boolean line1 = winState(mButtonRow00, mButtonRow01, mButtonRow02, mButtonRow03);
        boolean line2 = winState(mButtonRow01, mButtonRow02, mButtonRow03, mButtonRow04);
        boolean line3 = winState(mButtonRow10, mButtonRow11, mButtonRow12, mButtonRow13);
        boolean line4 = winState(mButtonRow11, mButtonRow12, mButtonRow13, mButtonRow14);
        boolean line5 = winState(mButtonRow20, mButtonRow21, mButtonRow22, mButtonRow23);
        boolean line6 = winState(mButtonRow21, mButtonRow22, mButtonRow23, mButtonRow24);
        boolean line7 = winState(mButtonRow30, mButtonRow31, mButtonRow32, mButtonRow33);
        boolean line8 = winState(mButtonRow31, mButtonRow32, mButtonRow33, mButtonRow34);
        boolean line9 = winState(mButtonRow40, mButtonRow41, mButtonRow42, mButtonRow43);
        boolean line10 = winState(mButtonRow41, mButtonRow42, mButtonRow43, mButtonRow44);

        if (line1 || line2 || line3 || line4 || line5 || line6 || line7 || line8 || line9 || line10)
            return true;

        boolean column1 = winState(mButtonRow00, mButtonRow10, mButtonRow20, mButtonRow30);
        boolean column2 = winState(mButtonRow10, mButtonRow20, mButtonRow30, mButtonRow40);
        boolean column3 = winState(mButtonRow01, mButtonRow11, mButtonRow21, mButtonRow31);
        boolean column4 = winState(mButtonRow11, mButtonRow21, mButtonRow31, mButtonRow41);
        boolean column5 = winState(mButtonRow02, mButtonRow12, mButtonRow22, mButtonRow32);
        boolean column6 = winState(mButtonRow12, mButtonRow22, mButtonRow32, mButtonRow42);
        boolean column7 = winState(mButtonRow03, mButtonRow13, mButtonRow23, mButtonRow33);
        boolean column8 = winState(mButtonRow13, mButtonRow23, mButtonRow33, mButtonRow43);
        boolean column9 = winState(mButtonRow04, mButtonRow14, mButtonRow24, mButtonRow34);
        boolean column10 = winState(mButtonRow14, mButtonRow24, mButtonRow34, mButtonRow44);

        if (column1 || column2 || column3 || column4 || column5 ||
                column6 || column7 || column8 || column9 || column10)
            return true;

        boolean diagonal1 = winState(mButtonRow01, mButtonRow12, mButtonRow23, mButtonRow34);
        boolean diagonal2 = winState(mButtonRow00, mButtonRow11, mButtonRow22, mButtonRow33);
        boolean diagonal3 = winState(mButtonRow11, mButtonRow22, mButtonRow33, mButtonRow44);
        boolean diagonal4 = winState(mButtonRow10, mButtonRow21, mButtonRow32, mButtonRow43);

        if (diagonal1 || diagonal2 || diagonal3 || diagonal4)
            return true;

        return false;
    }

    private boolean winState(Button first, Button second, Button third, Button last) {
//
//        int firstColor = getButtonColor(first);
//        int secondColor = getButtonColor(second);
//        int thirdColor = getButtonColor(third);
//        int lastColor = getButtonColor(last);

        int colorRed = Color.RED;
        int colorBlue = Color.BLUE;

        Drawable drawableRed = first.getBackground();

        if (second.getBackground().equals(drawableRed) && third.getBackground().equals(drawableRed)
                && last.getBackground().equals(drawableRed)) {

            Snackbar.make(getActivity().findViewById(android.R.id.content),
                    "Red is Winner", Snackbar.LENGTH_SHORT).show();
            return true;
        }

//            Snackbar.make(getActivity().findViewById(android.R.id.content),
//                    "Blue is Winner", Snackbar.LENGTH_SHORT).show();
//            return true;

        return false;
    }

}