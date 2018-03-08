package com.example.jorge.pong.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.jorge.pong.Elements.Ball;
import com.example.jorge.pong.Elements.Bullet;
import com.example.jorge.pong.Elements.Paddle;
import com.example.jorge.pong.R;
import com.example.jorge.pong.Threads.BallThread;
import com.example.jorge.pong.Threads.BulletThread;
import com.example.jorge.pong.Threads.PaddleThread;

public class MainActivity extends AppCompatActivity {

    private ImageView paddleImage;
    private ImageView ballImage;
    private Paddle paddle;
    private ImageView [] bullets;
    private Bullet bullet;
    private Ball ball;
    private PaddleThread paddleThread;
    private BallThread ballThread;
    private RelativeLayout board;
    private Button btnShot;

    private String debugName = MainActivity.class.getName();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShot = findViewById(R.id.btnShot);

        board = findViewById(R.id.board);

        ballImage = findViewById(R.id.ball);
        paddleImage = findViewById(R.id.paddle);
        ImageView shot1 = findViewById(R.id.shot1);
        ImageView shot2 = findViewById(R.id.shot2);
        ImageView shot3 = findViewById(R.id.shot3);
        ImageView shot4 = findViewById(R.id.shot4);
        ImageView shot5 = findViewById(R.id.shot5);


        bullets = new ImageView[]{shot1, shot2, shot3, shot4, shot5};
        ball = new Ball(ballImage);
        paddle = new Paddle(paddleImage);

        paddleThread = new PaddleThread(this);
        paddleThread.run();

        ballThread = new BallThread(this);
        ballThread.run();


        final Button btnUp1 = findViewById(R.id.btnUp1);
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
        final Button btnUp2 = findViewById(R.id.btnUp2);
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
        final Button btnDown = findViewById(R.id.btnDown);
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
        final Button btnRight = findViewById(R.id.btnRight);
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
        final Button btnLeft = findViewById(R.id.btnLeft);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(debugName, requestCode + "" + resultCode);
        if (requestCode == 999 && resultCode == RESULT_OK) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
        Log.d(debugName, requestCode + "" + resultCode);
        if(requestCode == 888 && resultCode == RESULT_OK){
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }
    }

    public void shot(View v) {
        if (paddle.getBullets() > 0) {
            Log.d(debugName, "DISPARO");

            ImageView bulletImage = new ImageView(getBaseContext());
            bulletImage.setImageDrawable(getResources().getDrawable(R.drawable.bullet));
            bulletImage.setY(paddle.getElement().getY() + bulletImage.getHeight());
            bulletImage.setX(paddle.getElement().getX() + (paddle.getElement().getWidth() / 2.0f) - 10);
            board.addView(bulletImage);

            bullets[paddle.getBullets() - 1].setVisibility(View.INVISIBLE);
            paddle.shotBullets();
            btnShot.setEnabled(false);

            bullet = new Bullet(bulletImage);
            BulletThread bulletThread = new BulletThread(this, bullet);
            bulletThread.run();
        }
    }

    public void enabledShot() {
        Log.d(debugName, "Habilitar disparo");
        bullet = null;
        btnShot.setEnabled(true);
    }

    public void gameOver() {
        Log.d(debugName, "Entro en game over");
        finishGame();
        Intent intent = new Intent(this, GameOverActivity.class);
        startActivityForResult(intent, 999);
    }

    public void winGame() {
        Log.d(debugName, "Entro en win game");
        finishGame();
        startActivityForResult(new Intent(this,VictoryActivity.class),888);
    }

    public void finishGame(){
        ballThread.stop();
        ballThread.getControl().removeCallbacks(ballThread);

        paddleThread.getControl().removeCallbacks(paddleThread);
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

    public void setPaddleImage(ImageView paddleImage) {
        this.paddleImage = paddleImage;
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
