package com.example.jorge.myapplication;


import android.widget.ImageView;

import android.os.Handler;
import android.widget.RelativeLayout;

import com.example.jorge.myapplication.Elements.Ball;
import com.example.jorge.myapplication.Elements.Element;
import com.example.jorge.myapplication.Elements.Paddle;

/**
 * Created by Jorge on 20/10/2017.
 */

public class PaddleThread implements Runnable {
    private boolean changeDirection;
    private Handler control;
    private int  direction;
    private Ball ball;
    private Paddle paddle;
    private RelativeLayout board;

    public PaddleThread(RelativeLayout board, ImageView paddle, Ball ball) {
        control = new Handler();
        changeDirection = false;
        this.board = board;
        this.ball = ball;
        this.paddle= new Paddle (paddle);
    }

    @Override
    public void run() {
        if (paddle.getElement().getY() != paddle.getElement().getY() + paddle.getElement().getHeight()) {//wait for initilization of main activity
            direction = changeDirections(direction);
            paddleMovements(paddle.getElement());
        }
        control.postDelayed(this, (long) 0.005);
    }

    public void paddleMovements(ImageView paddle) {
        switch (direction) {//rebotes
            case 0: //arriba izquierda
                paddle.setY(paddle.getY() - this.paddle.getSpeed());
                paddle.setX(paddle.getX() - this.paddle.getSpeed());
                break;
            case 1: //abajo izquierda
                paddle.setY(paddle.getY() + this.paddle.getSpeed());
                paddle.setX(paddle.getX() - this.paddle.getSpeed());
                break;
            case 2://arriba derecha
                paddle.setY(paddle.getY() - this.paddle.getSpeed());
                paddle.setX(paddle.getX() + this.paddle.getSpeed());
                break;
            case 3: //abajo derecha
                paddle.setY(paddle.getY() + this.paddle.getSpeed());
                paddle.setX(paddle.getX() + this.paddle.getSpeed());
                break;
        }
    }

    public int changeDirections(int direction) {

        if (!detectBallCollisionPaddle(ball.getElement())) {
            if (((paddle.getElement().getY() + paddle.getSpeed()) <= 0) && (direction == 0)) {
                return 1;
            } else if (((paddle.getElement().getY() + paddle.getSpeed()) <= 0) && (direction == 2)) {
                return 3;
            }

            if ((paddle.getElement().getX() - paddle.getSpeed() <= 0) && (direction == 0)) {
                return 2;
            } else if ((paddle.getElement().getX() - paddle.getSpeed() <= 0) && (direction == 1)) {
                return 3;
            }
            if ((paddle.getElement().getX() + paddle.getElement().getWidth() + paddle.getSpeed() >= board.getWidth() && (direction == 2))) {
                return 0;
            } else if ((paddle.getElement().getX() + paddle.getElement().getWidth() + paddle.getSpeed() >= board.getWidth() && (direction == 3))) {
                return 1;
            }

            if (((paddle.getElement().getY() + paddle.getElement().getHeight()) + paddle.getSpeed() >= board.getHeight()) && (direction == 1)) {
                return 0;
            } else if (((paddle.getElement().getY() + paddle.getElement().getHeight()) + paddle.getSpeed() >= board.getHeight()) && (direction == 3)) {
                return 2;
            }
        } else {
            System.out.println("CHOOOOQUEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
            ball.getMain().changeBallColor();
            switch (direction) {
                case 0:
                    return 2;
                case 1:
                    return 3;
                case 2:
                    return 0;
                case 3:
                    return 1;
            }
        }
        return direction;
    }

    public boolean detectBallCollisionPaddle(ImageView ball) {
        //Collision paddle

        if (((paddle.getElement().getY() >= ball.getY()) ||
                ((paddle.getElement().getY() + paddle.getElement().getHeight()) >= ball.getY())) &&
                ((paddle.getElement().getY() <= (ball.getY() + ball.getHeight())) ||
                        ((paddle.getElement().getY() + paddle.getElement().getHeight()) <= (ball.getY() + ball.getHeight())))) {
            if (((paddle.getElement().getX() - paddle.getSpeed()) <= 0)) {
                //ball.setX(paddle.getElement().getX() + paddel.getElement().getWidth());
                paddle.getElement().setX(ball.getX() + ball.getWidth());
                return true;
            } else if ((paddle.getElement().getX() <= (ball.getX() + ball.getWidth()))) {
                paddle.getElement().setX(ball.getX() + ball.getWidth());
                return true;
            }
        }

        return false;
    }


    public Paddle getPaddle() {
        return paddle;
    }

    public void setPaddle(Paddle paddle) {
        this.paddle = paddle;
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

}
