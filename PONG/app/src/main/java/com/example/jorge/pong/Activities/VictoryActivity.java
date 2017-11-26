package com.example.jorge.pong.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.example.jorge.pong.R;

public class VictoryActivity extends Activity {

    Button btnPlayAgain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victory);

        btnPlayAgain = (Button)findViewById(R.id.btnPlayAgain);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.8));
    }

    public void playAgain(View v){
        Intent intent = new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }
}
