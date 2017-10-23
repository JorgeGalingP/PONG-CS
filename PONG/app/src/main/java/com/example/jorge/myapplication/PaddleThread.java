package com.example.jorge.myapplication;


import android.provider.Settings;
import android.widget.ImageView;

import android.os.Handler;
import android.widget.RelativeLayout;

/**
 * Created by Jorge on 20/10/2017.
 */

public class PaddleThread implements Runnable {
    private boolean changeDirection;
    private Handler control;
    private int speedMove, direction;
    private ImageView paddle;
    private RelativeLayout board;

    public PaddleThread(RelativeLayout board, ImageView paddle) {
        control = new Handler();
        changeDirection = false;
        this.board = board;
        this.paddle = paddle;
        speedMove = 5;
    }

    @Override
    public void run() {
        direction = changeDirections(direction);
        paddleMovements(paddle);
        control.postDelayed(this, (long) 0.005);
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

    public int changeDirections(int direction) {
        if (((paddle.getY() + speedMove) <= 0) && (direction == 0)) {
            return 1;
        } else if (((paddle.getY() + speedMove) <= 0) && (direction == 2)) {
            return 3;
        }

        if ((paddle.getX() - speedMove <= 0) && (direction == 0)) {
            return 2;
        } else if ((paddle.getX() - speedMove <= 0) && (direction == 1)) {
            return 3;
        }
        if ((paddle.getX() + paddle.getWidth() + speedMove >= board.getWidth() && (direction == 2))) {
            return 0;
        } else if ((paddle.getX() + paddle.getWidth() + speedMove >= board.getWidth() && (direction == 3))) {
            return 1;
        }

        if (((paddle.getY() + paddle.getHeight()) + speedMove >= board.getHeight()) && (direction == 1)) {
            return 0;
        } else if (((paddle.getY() + paddle.getHeight()) + speedMove >= board.getHeight()) && (direction == 3)) {
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

    public RelativeLayout getBoard() {
        return board;
    }

    public void setBoard(RelativeLayout board) {
        this.board = board;
    }

    public Handler getControl() {
        return control;
    }

    public void setControl(Handler control) {
        this.control = control;
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
