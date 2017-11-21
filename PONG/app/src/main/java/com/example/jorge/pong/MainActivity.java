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
import com.example.jorge.pong.Elements.Bullet;
import com.example.jorge.pong.Elements.Paddle;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    private ImageView paddleImage, ballImage,shot1, shot2,shot3, shot4,shot5;
    private Paddle paddle;
    private ImageView bullets[];
    private Bullet bullet;
    private Ball ball;
    private PaddleThread paddleThread;
    private RelativeLayout board;
    private Button btnShot;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnShot = (Button)findViewById(R.id.btnShot);

        board = (RelativeLayout)findViewById(R.id.board);
        ballImage = (ImageView) findViewById(R.id.ball);
        paddleImage = (ImageView) findViewById(R.id.paddle);
        shot1 = (ImageView)findViewById(R.id.shot1);
        shot2 = (ImageView)findViewById(R.id.shot2);
        shot3 = (ImageView)findViewById(R.id.shot3);
        shot4 = (ImageView)findViewById(R.id.shot4);
        shot5 = (ImageView)findViewById(R.id.shot5);
        bullets = new ImageView[]{shot1, shot2, shot3, shot4, shot5};
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
    }

    public void shot(View v){
        if(paddle.getBullets()>0) {
            System.out.println("DISPARO");
            ImageView bulletImage = new ImageView(getBaseContext());
            bulletImage.setImageDrawable(getResources().getDrawable(R.drawable.bullet));
            bulletImage.setY(paddle.getElement().getY() + bulletImage.getHeight());
            bulletImage.setX(paddle.getElement().getX() + (paddle.getElement().getWidth() / 2) - 10);
            board.addView(bulletImage);

            bullet = new Bullet(bulletImage);
            BulletThread bulletThread = new BulletThread(this, bullet);
            bulletThread.run();
            bullets[paddle.getBullets()-1].setVisibility(View.INVISIBLE);
            paddle.shotBullets();
            btnShot.setEnabled(false);
        }
    }

    public void enabledShot(){
        System.out.println("Habilitar disparo");
        bullet = null;
        btnShot.setEnabled(true);
    }

    public Button getBtnShot() {
        return btnShot;
    }

    public void setBtnShot(Button btnShot) {
        this.btnShot = btnShot;
    }

    public Bullet getBullet() {
        return bullet;
    }

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
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
