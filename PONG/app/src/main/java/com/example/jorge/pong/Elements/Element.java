package com.example.jorge.pong.Elements;

import android.widget.ImageView;

/**
 * Created by Jorge on 29/10/2017.
 */

public abstract class Element {
    private int speed = 10;
    private ImageView element;

    protected Element(ImageView element) {
        this.element = element;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public ImageView getElement() {
        return element;
    }

    public void setElement(ImageView element) {
        this.element = element;
    }
}
