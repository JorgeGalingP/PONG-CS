package com.example.jorge.pong;

import android.widget.ImageView;
import android.os.Handler;

/**
 * Created by Jorge on 20/11/2017.
 */

public class PaddleThread implements Runnable {

    private MainActivity main;
    private int direction = -1;
    private Handler control;

    public PaddleThread (MainActivity main){
        this.main = main;
        control = new Handler();
    }


    @Override
    public void run() {
        paddleMovements(main.getPaddle().getElement());
        control.postDelayed(this,(long)0.0005);

    }

    public void paddleMovements(ImageView paddle){
        switch(direction){
            case 0://UP
                paddle.setY(paddle.getY()-main.getPaddle().getSpeed());
                break;
            case 1://DOWN
                paddle.setY(paddle.getY()+main.getPaddle().getSpeed());
                break;
            case 2://RIGHT
                paddle.setX(paddle.getX()+main.getPaddle().getSpeed());
                break;
            case 3://LEFT
                paddle.setX(paddle.getX()-main.getPaddle().getSpeed());
                break;
        }
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
}
