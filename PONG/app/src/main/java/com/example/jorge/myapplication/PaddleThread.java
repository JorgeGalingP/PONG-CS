package com.example.jorge.myapplication;


import android.widget.ImageView;

import android.os.Handler;
import android.widget.RelativeLayout;

import com.example.jorge.myapplication.Elements.Ball;
import com.example.jorge.myapplication.Elements.Paddle;

/**
 * Created by Jorge on 20/10/2017.
 */

public class PaddleThread implements Runnable {
    private boolean changeDirection,stop = false;
    private Handler control;
    private MainActivity main;
    private int direction = -1;

    public PaddleThread(MainActivity main) {
        this.main = main;
    }

    @Override
    public void run() {
        if (main.getPaddle().getElement().getY() != main.getPaddle().getElement().getY() + main.getPaddle().getElement().getHeight()) {//wait for initilization of main activity
            if(!stop) {
                //direction = changeDirections(direction);
                paddleMovements(main.getPaddle().getElement());
            }
        }
        control.postDelayed(this, (long) 0.005);

    }

    public void paddleMovements(ImageView paddle) {
        switch (direction) {
            case 0: //arriba
                main.getPaddle().getElement().setY(main.getPaddle().getElement().getY()+ main.getPaddle().getSpeed());
                break;
            case 1: //abajo
                main.getPaddle().getElement().setY(main.getPaddle().getElement().getY() - main.getPaddle().getSpeed());
                break;
            case 2://derecha
                main.getPaddle().getElement().setX(main.getPaddle().getElement().getX() + main.getPaddle().getSpeed());
                break;
            case 3: //izquierda
                main.getPaddle().getElement().setX(main.getPaddle().getElement().getX() - main.getPaddle().getSpeed());
                break;
        }
    }

    /*public int changeDirections(int direction) {

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

    public void stop(){
        stop = true;
        Thread t = new Thread(this);
        try {
            t.join();
        }catch (InterruptedException e){}

    }

    public boolean detectBallCollisionPaddle(ImageView ball) {
        //Collision paddle

        if (((paddle.getElement().getY() >= ball.getY()) ||
                ((paddle.getElement().getY() + paddle.getElement().getHeight()) >= ball.getY())) &&
                ((paddle.getElement().getY() <= (ball.getY() + ball.getHeight())) ||
                        ((paddle.getElement().getY() + paddle.getElement().getHeight()) <= (ball.getY() + ball.getHeight())))) {
            if (((paddle.getElement().getX() - paddle.getSpeed()) <= 0)) {
                paddle.getElement().setX(ball.getX() + ball.getWidth());
                return true;
            } else if ((paddle.getElement().getX() <= (ball.getX() + ball.getWidth()))) {
                paddle.getElement().setX(ball.getX() + ball.getWidth());
                return true;
            }
        }

        return false;
    }*/




    public boolean isChangeDirection() {
        return changeDirection;
    }

    public void setChangeDirection(boolean changeDirection) {
        this.changeDirection = changeDirection;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public MainActivity getMain() {
        return main;
    }

    public void setMain(MainActivity main) {
        this.main = main;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Handler getControl() {
        return control;
    }

    public void setControl(Handler control) {
        this.control = control;
    }

}
