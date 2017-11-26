package com.example.jorge.pong.Threads;

import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.jorge.pong.Elements.Bullet;
import com.example.jorge.pong.MainActivity;

/**
 * Created by Jorge on 20/11/2017.
 */

public class BulletThread implements Runnable {
    private MainActivity main;
    private Bullet bullet;
    private boolean stop = false;
    private Handler control;

    public BulletThread(MainActivity main, Bullet bullet) {
        this.main = main;
        this.bullet = bullet;
        this.control = new Handler();
    }

    @Override
    public void run() {
        if (main.getPaddle().getElement().getWidth() != 0) {
            bulletCollisionBall(bullet.getElement(), bullet.getSpeed(), main.getBall().getElement());
            bulletCollisionWalls(bullet.getElement(), main.getBoard());
            if (!stop) {
                bulletMovements(bullet.getElement(), bullet.getSpeed());
                control.postDelayed(this, (long) 10);
            }
        }
    }

    public void bulletMovements(ImageView bullet, int speed) {
        bullet.setY(bullet.getY() - speed);
    }

    public void bulletCollisionWalls(ImageView bullet, RelativeLayout board) {
        if (bullet.getY() + bullet.getHeight() <= main.getBoard().getY()) {
            System.out.println("Colision");
            stop = true;
            control.removeCallbacks(this);
            bullet.setVisibility(View.INVISIBLE);
            if (main.getPaddle().getBullets() > 0) {
                main.enabledShot();
            } else {
                main.gameOver();
            }
        }
    }

    public void bulletCollisionBall(ImageView bullet, int speed, ImageView ball) {
        int initialPosition = (int) bullet.getY();
        int finalPosition = (int) bullet.getY() - speed;
        int i = initialPosition;
        while (i >= finalPosition && !stop) {
            if (i == ball.getY() + ball.getHeight() &&
                    ((bullet.getX()>= ball.getX() || bullet.getX() + bullet.getWidth() >= ball.getX()) && bullet.getX() <= ball.getX() + ball.getWidth())) {
                System.out.println("CHOQUE CON LA BOLA");
                bullet.setY(i);
                stop = true;
                control.removeCallbacks(this);
                bullet.setVisibility(View.INVISIBLE);
                ball.setVisibility(View.INVISIBLE);
                main.winGame();
            }
            i--;
        }
    }

}
