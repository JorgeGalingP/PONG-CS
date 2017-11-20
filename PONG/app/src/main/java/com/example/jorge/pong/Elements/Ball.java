package com.example.jorge.pong.Elements;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.widget.ImageView;

import com.example.jorge.pong.MainActivity;

import static java.security.AccessController.getContext;

/**
 * Created by Jorge on 29/10/2017.
 */

public class Ball extends Element {

    private int lives = 3;

    public Ball(ImageView element) {
        super(element);
    }

    public int getLives() {
        return lives;
    }

    public void damage() {
        this.lives -= 1;
    }
}
