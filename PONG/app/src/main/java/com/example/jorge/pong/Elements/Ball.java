package com.example.jorge.pong.Elements;


import android.widget.ImageView;


/**
 * Created by Jorge on 29/10/2017.
 */

public class Ball extends Element {


    public Ball(ImageView element) {
        super(element);
        setSpeed(10);
    }
}
