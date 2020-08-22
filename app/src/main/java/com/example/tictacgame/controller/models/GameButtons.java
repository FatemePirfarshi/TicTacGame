package com.example.tictacgame.controller.models;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;

public class GameButtons implements Serializable {

    private Button mButton;
    private int mColor = Color.GRAY;
    private boolean isPlayed = false;

    public boolean isPlayed() {
        return isPlayed;
    }

    public void setPlayed(boolean played) {
        isPlayed = played;
    }

    public Button getButton() {
        return mButton;
    }

    public void setButton(Button button) {
        mButton = button;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mButton.setBackgroundColor(color);
        mColor = color;
        mButton.setEnabled(false);
    }
}
