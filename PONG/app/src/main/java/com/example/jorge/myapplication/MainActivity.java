package com.example.jorge.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.jorge.myapplication.Elements.Ball;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView ballImage;
    private Ball ball;
    private Handler controlPaddleCreator = new Handler();
    private BallThread ballThread;
    private RelativeLayout board;
    private List<PaddleThread> paddles = new ArrayList<>();
    private int num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ballImage = (ImageView) findViewById(R.id.ball);
        board = (RelativeLayout) findViewById(R.id.board);

        ball = new Ball(ballImage, this);
        paddleCreator.run();
        ballThread = new BallThread(this);
        ballThread.run();

        final Button btnUp = (Button) findViewById(R.id.btnUp);
        btnUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if ((event.getAction() == MotionEvent.ACTION_DOWN)) {
                    ballThread.setDown(false);
                    ballThread.setUp(true);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    ballThread.setUp(false);
                }
                return true;
            }
        });

        final Button btnDown = (Button) findViewById(R.id.btnDown);
        btnDown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if ((event.getAction() == MotionEvent.ACTION_DOWN)) {
                    ballThread.setUp(false);
                    ballThread.setDown(true);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    ballThread.setDown(false);
                }
                return true;
            }
        });
    }


    Runnable paddleCreator = new Runnable() {
        @Override
        public void run() {
            num++;
            PaddleThread paddleThread = new PaddleThread(board, createPaddle(),ball,num);
            paddles.add(paddleThread);
            paddleThread.run();
            controlPaddleCreator.postDelayed(paddleCreator, (long) 10000);

        }
    };

    public void changeBallDirection(int dir) {
        switch (dir) {
            case 0:
                dir = 2;
                break;
            case 1:
                dir = 3;
                break;
            case 2:
                dir = 0;
                break;
            case 3:
                dir = 1;
                break;
        }
    }

    public ImageView createPaddle() {
        ImageView paddle = new ImageView(getBaseContext());
        paddle.setImageDrawable(getResources().getDrawable(R.drawable.paddle));
        paddle.setX(board.getWidth() / 2);
        paddle.setY(board.getHeight() / 2);
        board.addView(paddle);
        return paddle;
    }

    public void changeBallColor (){
        ball.damage();
        if(ball.getLives()>=0) {
            switch (ball.getLives()) {
                case 2:
                    ball.getElement().setColorFilter(Color.rgb(255, 255, 0));
                    break;
                case 1:
                    ball.getElement().setColorFilter(Color.rgb(255, 102, 0));
                    break;
                case 0:
                    ball.getElement().setColorFilter(Color.rgb(255, 0, 0));
                    break;
            }
        }else{
            finish();
            Intent intent = new Intent(this,GameOverActivity.class);
            startActivity(intent);
            controlPaddleCreator.removeCallbacks(paddleCreator); //stop the calls for paddleCreator runnable
            for (PaddleThread p: paddles){
                p.stop();
            }
        }
    }
    public RelativeLayout getBoard() {
        return board;
    }

    public void setBoard(RelativeLayout board) {
        this.board = board;
    }

    public ImageView getBallImage() {
        return ballImage;
    }

    public void setBallImage(ImageView ballImage) {
        this.ballImage = ballImage;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }
}
