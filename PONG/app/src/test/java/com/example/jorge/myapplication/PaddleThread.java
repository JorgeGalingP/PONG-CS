package com.example.jorge.myapplication;


import android.provider.Settings;
import android.widget.ImageView;

import android.os.Handler;

/**
 * Created by Jorge on 20/10/2017.
 */

public class PaddleThread implements Runnable {
    private boolean changeDirection;
    private Handler control;
    private int height,width, speedMove, direction;
    private ImageView paddle;
    MainActivity main;

    public PaddleThread(MainActivity main) {
        control = new Handler();
        changeDirection = false;
        this.main = main;
        speedMove = 10;
    }

    @Override
    public void run() {
        height = main.getBoard().getHeight();
        width = main.getBoard().getWidth();
        direction = changeDirections(direction);
        paddleMovements(paddle);
        control.postDelayed(this,(long)0.005);
    }

    public void paddleMovements(ImageView paddle) {
        switch (direction) {//rebotes
            case 0: //arriba izquierda
                paddle.setY(paddle.getY() - speedMove);
                paddle.setX(paddle.getX() - speedMove);
                break;
            case 1: //abajo izquierda
                paddle.setY(paddle.getY() + speedMove);
                paddle.setX(paddle.getX() - speedMove);
                break;
            case 2://arriba derecha
                paddle.setY(paddle.getY() - speedMove);
                paddle.setX(paddle.getX() + speedMove);
                break;
            case 3: //abajo derecha
                paddle.setY(paddle.getY() + speedMove);
                paddle.setX(paddle.getX() + speedMove);
                break;
        }
    }

    public int changeDirections (int direction){
        if (((paddle.getY() + speedMove) <= 0) && (direction == 0)) {
            return 1;
        } else if (((paddle.getY() + speedMove) <= 0) && (direction == 2)) {
            return 3;
        }

        if((paddle.getX()-speedMove <= 0)&&(direction == 0)){
            System.out.println("ENTRO AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII 2");
            return 2;
        }else if((paddle.getX()-speedMove <= 0)&&(direction == 1)){
            return 3;
        }
        if ((paddle.getX()+paddle.getWidth()+speedMove >= width)&&(direction == 2)){
            return 0;
        }else if ((paddle.getX()+paddle.getWidth()+speedMove >= width)&&(direction == 3)){
            return 1;
        }

        if (((paddle.getY() + paddle.getHeight()) + speedMove >= height) && (direction == 1)) {
            return 0;
        } else if (((paddle.getY() + paddle.getHeight()) + speedMove >= height) && (direction == 3)) {
            return 2;
        }
        return direction;
    }
    public boolean isChangeDirection() {
        return changeDirection;
    }

    public void setChangeDirection(boolean changeDirection) {
        this.changeDirection = changeDirection;
    }

    public Handler getControl() {
        return control;
    }

    public void setControl(Handler control) {
        this.control = control;
    }

    public int getHeight() {
        return height;
    }

    public void setHeigth(int height) {
        this.height = height;
    }

    public int getSpeedMove() {
        return speedMove;
    }

    public void setSpeedMove(int speedMove) {
        this.speedMove = speedMove;
    }

    public ImageView getPaddle() {
        return paddle;
    }

    public void setPaddle(ImageView paddle) {
        this.paddle = paddle;
    }
}
