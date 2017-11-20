package com.example.jorge.pong;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.jorge.pong.Elements.Ball;
import com.example.jorge.pong.Elements.Paddle;

public class MainActivity extends AppCompatActivity {

    private ImageView paddleImage, ballImage;
    private Paddle paddle;
    private Ball ball;
    private PaddleThread paddleThread;
    private RelativeLayout board;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        board = (RelativeLayout)findViewById(R.id.board);
        ballImage = (ImageView) findViewById(R.id.ball);
        paddleImage = (ImageView) findViewById(R.id.paddle);

        ball = new Ball(ballImage);
        paddle = new Paddle(paddleImage);

        paddleThread = new PaddleThread(this);
        paddleThread.run();


        final Button btnUp1 = (Button) findViewById(R.id.btnUp1);
        btnUp1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if ((event.getAction() == MotionEvent.ACTION_DOWN)) {
                    paddleThread.setDirection(0);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    paddleThread.setDirection(-1);
                }
                return true;
            }
        });
        final Button btnUp2 = (Button) findViewById(R.id.btnUp2);
        btnUp2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if ((event.getAction() == MotionEvent.ACTION_DOWN)) {
                    paddleThread.setDirection(0);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    paddleThread.setDirection(-1);
                }
                return true;
            }
        });
        final Button btnDown = (Button) findViewById(R.id.btnDown);
        btnDown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if ((event.getAction() == MotionEvent.ACTION_DOWN)) {
                    paddleThread.setDirection(1);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    paddleThread.setDirection(-1);
                }
                return true;
            }
        });
        final Button btnRight = (Button) findViewById(R.id.btnRight);
        btnRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if ((event.getAction() == MotionEvent.ACTION_DOWN)) {
                    paddleThread.setDirection(2);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    paddleThread.setDirection(-1);
                }
                return true;
            }
        });
        final Button btnLeft = (Button) findViewById(R.id.btnLeft);
        btnLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if ((event.getAction() == MotionEvent.ACTION_DOWN)) {
                    paddleThread.setDirection(3);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    paddleThread.setDirection(-1);
                }
                return true;
            }
        });
        final Button btnShot = (Button) findViewById(R.id.btnShot);
        btnShot.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if ((event.getAction() == MotionEvent.ACTION_DOWN)) {
                    System.out.println("DISPARO");
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {

                }
                return true;
            }
        });
    }

    public ImageView getPaddleImage() {
        return paddleImage;
    }

    public PaddleThread getPaddleThread() {
        return paddleThread;
    }

    public void setPaddleThread(PaddleThread paddleThread) {
        this.paddleThread = paddleThread;
    }

    public RelativeLayout getBoard() {
        return board;
    }

    public void setBoard(RelativeLayout board) {
        this.board = board;
    }

    public void setPaddleImage(ImageView paddleImage) {
        this.paddleImage = paddleImage;
    }

    public ImageView getBallImage() {
        return ballImage;
    }

    public void setBallImage(ImageView ballImage) {
        this.ballImage = ballImage;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }
}
