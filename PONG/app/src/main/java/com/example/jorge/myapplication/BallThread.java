package com.example.jorge.myapplication;

import android.os.Handler;

import com.example.jorge.myapplication.Elements.Ball;


/**
 * Created by Jorge on 21/10/2017.


/**
 * Created by Jorge on 21/10/2017.
 */

public class BallThread implements Runnable {
    MainActivity main;
    Ball ball;
    int height;
    boolean down, up;
    Handler control;

    public BallThread() {
    }

    public BallThread(MainActivity main) {
        this.main = main;
        down = up = false;
        ball = main.getBall();
        control = new Handler();
    }

    @Override
    public void run() {
        if(ball.getElement().getY()!= ball.getElement().getY()+ball.getElement().getY()+ball.getElement().getHeight()) {//waiting for initilization of main activity
            height = main.getBoard().getHeight();
            if ((up && !down) && (!((ball.getElement().getY() - ball.getSpeed()) <= 0))) {
                ball.getElement().setY(ball.getElement().getY() - ball.getSpeed());
            } else if ((!up && down) && (!((ball.getElement().getY() + ball.getElement().getHeight() + ball.getSpeed()) >= (height)))) {
                ball.getElement().setY(ball.getElement().getY() + ball.getSpeed());
            }
            /*switch (direction) {//rebotes
                case 0: //arriba izquierda
                    paddle.setY(paddle.getY() - main.getPaddle().getSpeed());
                    paddle.setX(paddle.getX() - main.getPaddle().getSpeed());
                    break;
                case 1: //abajo izquierda
                    paddle.setY(paddle.getY() + main.getPaddle().getSpeed());
                    paddle.setX(paddle.getX() - main.getPaddle().getSpeed());
                    break;
                case 2://arriba derecha
                    paddle.setY(paddle.getY() - main.getPaddle().getSpeed());
                    paddle.setX(paddle.getX() + main.getPaddle().getSpeed());
                    break;
                case 3: //abajo derecha
                    paddle.setY(paddle.getY() + main.getPaddle().getSpeed());
                    paddle.setX(paddle.getX() + main.getPaddle().getSpeed());
                    break;
            }*/
        }
        control.postDelayed(this, (long) 0.0005);
    }

    public MainActivity getMain() {
        return main;
    }

    public void setMain(MainActivity main) {
        this.main = main;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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
