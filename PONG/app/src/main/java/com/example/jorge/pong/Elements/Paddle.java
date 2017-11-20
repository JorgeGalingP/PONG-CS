package com.example.jorge.pong.Elements;

import android.widget.ImageView;

/**
 * Created by Jorge on 29/10/2017.
 */

public class Paddle extends Element {
    private int bullets = 5;

    public Paddle(ImageView element) {
        super(element);
        this.setSpeed(10);
    }

    public void shotBullets() {
        bullets -= 1;
    }

    public int getBullets() {
        return bullets;
    }

    public void setBullets(int bullets) {
        this.bullets = bullets;
    }
}
