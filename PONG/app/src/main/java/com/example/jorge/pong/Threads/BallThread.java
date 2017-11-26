package com.example.jorge.pong.Threads;

import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.jorge.pong.Activities.MainActivity;

/**
 * Created by Jorge on 26/11/2017.
 */

public class BallThread implements Runnable {

    private int movement, direction = 0;
    private boolean stop = false;
    private Handler control;
    private MainActivity main;

    public BallThread(MainActivity main) {
        this.main = main;
        control = new Handler();
        movement = 0;
    }

    @Override
    public void run() {
        if (main.getBall().getElement().getWidth() != 0 && !stop) {
            ballMovements();
        }
        control.postDelayed(this, (long) 10);
    }

    public void ballMovements() {
        switch (movement) {
            case 0://left to right
                leftToRightMovement(main.getBall().getElement(), main.getBall().getSpeed(), main.getBoard());
                break;
        }
    }

    public void ballCollisionWalls() {

    }

    public void stop() {
        stop = true;
        countDown.cancel();
    }

    public void leftToRightMovement(ImageView ball, int speed, RelativeLayout board) {
        switch (direction) {
            case 0:
                if (ball.getX() - speed <= board.getX() + 80) {
                    ball.setX(board.getX() + 50);
                    direction = 1;
                    break;
                }
                ball.setX(ball.getX() - speed);
                break;
            case 1:
                if (ball.getX() + ball.getWidth() + speed >= board.getX() + board.getWidth() - 50) {
                    ball.setX(board.getX() + board.getWidth() - 80);
                    direction = 0;
                    break;
                }
                ball.setX(ball.getX() + speed);
                break;
        }
    }

    CountDownTimer countDown = new CountDownTimer(10000, 10000) {
        @Override
        public void onTick(long l) {

        }

        @Override
        public void onFinish() {
            int move = (int) (Math.random() * (4));
            System.out.println(move);//pruebas
            countDown.start();
        }
    }.start();

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean strop) {
        this.stop = strop;
    }

    public Handler getControl() {
        return control;
    }

    public void setControl(Handler control) {
        this.control = control;
    }

    public MainActivity getMain() {
        return main;
    }

    public void setMain(MainActivity main) {
        this.main = main;
    }
}
