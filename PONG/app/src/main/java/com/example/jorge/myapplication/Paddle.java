package com.example.jorge.myapplication;

import android.app.Activity;
import android.widget.ImageView;

/**
 * Created by Jorge on 30/09/2017.
 */

public class Paddle{

    int width, higth;
    ImageView paddle;

    public Paddle() {
        width = paddle.getWidth();
        higth = paddle.getHeight();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHigth() {
        return higth;
    }

    public void setHigth(int higth) {
        this.higth = higth;
    }

    public ImageView getPaddleImage() {
        return paddle;
    }

    public void setPaddleImage(ImageView paddle) {
        this.paddle = paddle;
    }
}
