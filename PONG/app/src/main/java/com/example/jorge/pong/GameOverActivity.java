package com.example.jorge.pong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

/**
 * Created by Jorge on 25/11/2017.
 */

public class GameOverActivity extends Activity {

    private Button btnRetry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        btnRetry = (Button) findViewById(R.id.btnRetry);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.8));
    }


    public void retry(View v) {
        System.out.println("REINICIO DE LA APLICACION");
        Intent intent = new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }
}
