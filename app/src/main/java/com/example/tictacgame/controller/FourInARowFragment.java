package com.example.tictacgame.controller;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tictacgame.R;

public class FourInARowFragment extends Fragment {

    private Button mButtonRow00, mButtonRow01, mButtonRow02, mButtonRow03, mButtonRow04,
            mButtonRow10, mButtonRow11, mButtonRow12, mButtonRow13, mButtonRow14,
            mButtonRow20, mButtonRow21, mButtonRow22, mButtonRow23, mButtonRow24,
            mButtonRow30, mButtonRow31, mButtonRow32, mButtonRow33, mButtonRow34,
            mButtonRow40, mButtonRow41, mButtonRow42, mButtonRow43, mButtonRow44;

    private int[] rowButtonsId =
            {R.id.row_btn_0_0, R.id.row_btn_0_1, R.id.row_btn_0_2, R.id.row_btn_0_3, R.id.row_btn_0_4,
                    R.id.row_btn_1_0, R.id.row_btn_1_1, R.id.row_btn_1_2, R.id.row_btn_1_3, R.id.row_btn_1_4,
                    R.id.row_btn_2_0, R.id.row_btn_2_1, R.id.row_btn_2_2, R.id.row_btn_2_3, R.id.row_btn_2_4,
                    R.id.row_btn_3_0, R.id.row_btn_3_1, R.id.row_btn_3_2, R.id.row_btn_3_3, R.id.row_btn_3_4,
                    R.id.row_btn_4_0, R.id.row_btn_4_1, R.id.row_btn_4_2, R.id.row_btn_4_3, R.id.row_btn_4_4};

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

        return view;
    }

    private void setListeners(View view) {
        for (int id : rowButtonsId) {
            view.findViewById(id).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }

    private void clickButton(View view) {
        switch (view.getId()) {
            case R.id.row_btn_0_0:
                setButtonsColor(view, mButtonRow00);
                break;
            case R.id.row_btn_0_1:
                setButtonsColor(view, mButtonRow01);
                break;
            case R.id.row_btn_0_2:
                setButtonsColor(view, mButtonRow02);
                break;
            case R.id.row_btn_0_3:
                setButtonsColor(view, mButtonRow03);
                break;
            case R.id.row_btn_0_4:
                setButtonsColor(view, mButtonRow04);
                break;
            case R.id.row_btn_1_0:
                setButtonsColor(view, mButtonRow10);
                break;
            case R.id.row_btn_1_1:
                setButtonsColor(view, mButtonRow11);
                break;
            case R.id.row_btn_1_2:
                setButtonsColor(view, mButtonRow12);
                break;
            case R.id.row_btn_1_3:
                setButtonsColor(view, mButtonRow13);
                break;
            case R.id.row_btn_1_4:
                setButtonsColor(view, mButtonRow14);
                break;
            case R.id.row_btn_2_0:
                setButtonsColor(view, mButtonRow20);
                break;
            case R.id.row_btn_2_1:
                setButtonsColor(view, mButtonRow21);
                break;
            case R.id.row_btn_2_2:
                setButtonsColor(view, mButtonRow22);
                break;
            case R.id.row_btn_2_3:
                setButtonsColor(view, mButtonRow23);
                break;
            case R.id.row_btn_2_4:
                setButtonsColor(view, mButtonRow24);
                break;
            case R.id.row_btn_3_0:
                setButtonsColor(view, mButtonRow30);
                break;
            case R.id.row_btn_3_1:
                setButtonsColor(view, mButtonRow31);
                break;
            case R.id.row_btn_3_2:
                setButtonsColor(view, mButtonRow32);
                break;
            case R.id.row_btn_3_3:
                setButtonsColor(view, mButtonRow33);
                break;
            case R.id.row_btn_3_4:
                setButtonsColor(view, mButtonRow34);
                break;
            case R.id.row_btn_4_0:
                setButtonsColor(view, mButtonRow40);
                break;
            case R.id.row_btn_4_1:
                setButtonsColor(view, mButtonRow41);
                break;
            case R.id.row_btn_4_2:
                setButtonsColor(view, mButtonRow42);
                break;
            case R.id.row_btn_4_3:
                setButtonsColor(view, mButtonRow43);
                break;
            case R.id.row_btn_4_4:
                setButtonsColor(view, mButtonRow44);
                break;
        }
    }

    private void setButtonsColor(View view, Button button) {

    }
}