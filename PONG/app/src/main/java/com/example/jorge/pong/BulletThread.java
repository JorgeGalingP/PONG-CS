package com.example.jorge.pong;

import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.jorge.pong.Elements.Bullet;

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
        if (main.getPaddle().getElement().getWidth()!=0) {
            bulletCollisionWalls(bullet.getElement(), main.getBoard());
            if (!stop) {
                bulletMovements(bullet.getElement(),bullet.getSpeed());
                control.postDelayed(this,(long)10);
            }
        }
    }

    public void bulletMovements(ImageView bullet, int speed) {
        bullet.setY(bullet.getY() - speed);
    }

    public void  bulletCollisionWalls(ImageView bullet, RelativeLayout board) {
        if (bullet.getY() + bullet.getHeight() <= main.getBoard().getY()) {
            System.out.println("Colision");
            stop = true;
            control.removeCallbacks(this);
            bullet.setVisibility(View.INVISIBLE);
            if(main.getPaddle().getBullets()>0) {
                main.enabledShot();
            }else{
                main.gameOver();
            }
        }
    }

}
