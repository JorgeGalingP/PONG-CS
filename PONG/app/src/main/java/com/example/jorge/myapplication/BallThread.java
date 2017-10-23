package com.example.jorge.myapplication;

import android.os.Handler;
import android.widget.ImageView;


/**
 * Created by Jorge on 21/10/2017.
 */

public class BallThread implements Runnable {
    MainActivity main;
    ImageView ball;
    int height, speedMove = 10;
    boolean down, up;
    Handler control;

    public BallThread() {
    }

    public BallThread(MainActivity main) {
        this.main = main;
        down = up = false;
        control = new Handler();
    }

    @Override
    public void run() {
        ball = main.getBall();
        height = main.getBoard().getHeight();
        if ((up && !down) && (!((ball.getY() - speedMove) <= 0))) {
            ball.setY(ball.getY() - speedMove);
        } else if ((!up && down) && (!((ball.getY() + ball.getHeight() + speedMove) >= (height)))) {
            ball.setY(ball.getY() + speedMove);
        }
        control.postDelayed(this, (long) 0.0005);
    }

    public MainActivity getMain() {
        return main;
    }

    public void setMain(MainActivity main) {
        this.main = main;
    }

    public ImageView getBall() {
        return ball;
    }

    public void setBall(ImageView ball) {
        this.ball = ball;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getSpeedMove() {
        return speedMove;
    }

    public void setSpeedMove(int speedMove) {
        this.speedMove = speedMove;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public Handler getControl() {
        return control;
    }

    public void setControl(Handler control) {
        this.control = control;
    }
}
