package com.example.jorge.pong.Threads;

import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.jorge.pong.Elements.Bullet;
import com.example.jorge.pong.Activities.MainActivity;

/**
 * Created by Jorge on 20/11/2017.
 */

public class BulletThread implements Runnable {
    private MainActivity main;
    private Bullet bullet;
    private boolean stopBullet = false;
    private Handler control;

    public BulletThread(MainActivity main, Bullet bullet) {
        this.main = main;
        this.bullet = bullet;
        this.control = new Handler();
    }

    @Override
    public void run() {
        if (main.getPaddle().getElement().getWidth() != 0) {
            collisionBall(bullet.getElement(), bullet.getSpeed(), main.getBall().getElement());
            collisionWalls(bullet.getElement(), main.getBoard());
            if (!stopBullet) {
                movements(bullet.getElement(), bullet.getSpeed());
                control.postDelayed(this, (long) 10);
            }
        }
    }

    public void movements(ImageView bullet, int speed) {
        bullet.setY(bullet.getY() - speed);
    }

    public void collisionWalls(ImageView bullet, RelativeLayout board) {
        if (bullet.getY() + bullet.getHeight() <= main.getBoard().getY()) {
            System.out.println("Colision");
            stopBullet = true;
            control.removeCallbacks(this);
            bullet.setVisibility(View.INVISIBLE);
            if (main.getPaddle().getBullets() > 0) {
                main.enabledShot();
            } else {
                main.gameOver();
            }
        }
    }

    public void collisionBall(ImageView bullet, int speed, ImageView ball) {
        int initialPosition = (int) bullet.getY();
        int finalPosition = (int) bullet.getY() - speed;
        int i = initialPosition;
        while (i >= finalPosition && !stopBullet) {
            if (i == ball.getY() + ball.getHeight() &&
                    ((bullet.getX()>= ball.getX() || bullet.getX() + bullet.getWidth() >= ball.getX())
                            && bullet.getX() <= ball.getX() + ball.getWidth())) {
                System.out.println("CHOQUE CON LA BOLA");
                bullet.setY(i);
                stopBullet = true;
                control.removeCallbacks(this);
                bullet.setVisibility(View.INVISIBLE);
                ball.setVisibility(View.INVISIBLE);
                main.winGame();
            }
            i--;
        }
    }

}
