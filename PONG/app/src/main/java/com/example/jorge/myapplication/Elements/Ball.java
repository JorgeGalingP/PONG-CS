package com.example.jorge.myapplication.Elements;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.widget.ImageView;

import com.example.jorge.myapplication.MainActivity;

import static java.security.AccessController.getContext;

/**
 * Created by Jorge on 29/10/2017.
 */

public class Ball extends Element {

    private int lives = 3;
    private MainActivity main;

    public Ball(ImageView element, MainActivity main) {
        super(element);
        this.main = main;
    }

    public int getLives() {
        return lives;
    }

    public void damage() {
        this.lives -= 1;
    }

    public MainActivity getMain() {
        return main;
    }

    public void setMain(MainActivity main) {
        this.main = main;
    }
/*public void changeColor() {
        this.lives -= 1;
        System.out.println("CAMBIO DE COLOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR " + lives);
        switch (lives) {
            case 2:
                LightingColorFilter yellow = new LightingColorFilter(Color.rgb(255, 255, 0), Color.rgb(255, 255, 0));
                l = yellow;
                System.out.println("Amarillo");
                break;
            case 1:
                LightingColorFilter orange = new LightingColorFilter(Color.rgb(255, 255, 0), Color.rgb(255, 102, 0));
                l = orange;
                System.out.println("NARANJA");
                break;
            case 0:
                LightingColorFilter red = new LightingColorFilter(Color.rgb(255, 255, 0), Color.rgb(255, 0, 0));
                l = red;
                break;
        }
        this.getElement().setColorFilter(l);
    } */
}
