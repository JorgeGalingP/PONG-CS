package com.example.jorge.pong.Elements;

import android.widget.ImageView;

/**
 * Created by Jorge on 20/11/2017.
 */

public class Bullet extends Element {
    public Bullet(ImageView element) {
        super(element);
        this.setSpeed(30);
    }
}
