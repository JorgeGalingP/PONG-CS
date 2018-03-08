package com.example.jorge.pong.Threads;

import android.os.Handler;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.jorge.pong.Activities.MainActivity;

/**
 * Created by Jorge on 20/11/2017.
 */

public class PaddleThread implements Runnable {

    private MainActivity main;
    private int direction = -1;
    private Handler control;

    public PaddleThread(MainActivity main) {
        this.main = main;
        control = new Handler();
    }


    @Override
    public void run() {
        if (main.getPaddle().getElement().getWidth() != 0) {
            collisionWalls(main.getPaddle().getElement(), main.getBoard());
            movements(main.getPaddle().getElement());

        }
        control.postDelayed(this, (long) 0.0005);

    }

    public void movements(ImageView paddle) {
        switch (direction) {
            case 0://UP
                paddle.setY(paddle.getY() - main.getPaddle().getSpeed());
                break;
            case 1://DOWN
                paddle.setY(paddle.getY() + main.getPaddle().getSpeed());
                break;
            case 2://RIGHT
                paddle.setX(paddle.getX() + main.getPaddle().getSpeed());
                break;
            case 3://LEFT
                paddle.setX(paddle.getX() - main.getPaddle().getSpeed());
                break;
        }
    }

    public void collisionWalls(ImageView paddle, RelativeLayout board) {
        if (paddle.getX() + paddle.getWidth() <= board.getX()) {
            paddle.setX(board.getWidth());
        } else if (paddle.getX() >= board.getWidth()) {
            paddle.setX(board.getX() - paddle.getWidth());
        }
        if (paddle.getY() + paddle.getHeight() <= board.getY()) {
            paddle.setY(board.getHeight());
        } else if (paddle.getY() >= board.getHeight()) {
            paddle.setY(board.getY() - paddle.getHeight());
        }
    }

    public MainActivity getMain() {
        return main;
    }

    public void setMain(MainActivity main) {
        this.main = main;
    }

    public Handler getControl() {
        return control;
    }

    public void setControl(Handler control) {
        this.control = control;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
