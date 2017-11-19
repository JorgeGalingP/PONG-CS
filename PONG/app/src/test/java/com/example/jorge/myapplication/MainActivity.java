package com.example.jorge.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private ImageView paddle, ball;

    private PaddleThread paddleThread;
    private BallThread ballThread;
    private RelativeLayout board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //paddle = (ImageView) findViewById(R.id.paddle);
        ball = (ImageView) findViewById(R.id.ball);
        board = (RelativeLayout) findViewById(R.id.board);

        paddleThread = new PaddleThread(this);
        ballThread = new BallThread(this);

        paddleThread.run();
        ballThread.run();
        //ballMovements.run();


        /*final Button btnUp = (Button) findViewById(R.id.btnUp);
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
        });*/

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

    /*Runnable ballMovements = new Runnable() {
        @Override
        public void run() {
            boolean collision = detectBallCollisionPaddle(ball);
            if (((ball.getX() < 0) || (ball.getX() > board.getWidth())) && (!collision)) {
                ball.setX(board.getWidth() / 2);
                ball.setY(board.getHeight() / 2);
                direction = 0;
            } else {
                if (collision) {
                    switch (direction) {
                        case 0:
                            direction = 2;
                            break;
                        case 1:
                            direction = 3;
                            break;
                        case 2:
                            direction = 0;
                            break;
                        case 3:
                            direction = 1;
                            break;
                    }
                }

                if (((ball.getY() + speedBall) <= 0) && (direction == 0)) {
                    direction = 1;
                } else if (((ball.getY() + speedBall) <= 0) && (direction == 2)) {
                    direction = 3;
                }

                if (((ball.getY() + ball.getHeight()) + speedMove >= board.getHeight()) && (direction == 1)) {
                    direction = 0;
                } else if (((ball.getY() + ball.getHeight()) + speedMove >= board.getHeight()) && (direction == 3)) {
                    direction = 2;
                }
                ballMovements(ball);
            }
            controlBall.postDelayed(ballMovements, (long) 0.005);
        }

        public void changeBallDirectionByPaddle(int dir) {
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
    };*/

   /* public boolean detectBallCollisionPaddle(ImageView ball) {
        //Collision paddle
        if (((ball.getY() >= paddle.getY()) ||
                ((ball.getY() + ball.getHeight()) >= paddle.getY())) &&
                ((ball.getY() <= (paddle.getY() + paddle.getHeight())) ||
                        ((ball.getY() + ball.getHeight()) <= (paddle.getY() + paddle.getHeight())))) {
            if (((ball.getX() - speedBall) <= 0)) {
                ball.setX(paddle.getX() + paddle.getWidth());
                return true;
            } else if ((ball.getX() <= (paddle.getX() + paddle.getWidth()))) {
                ball.setX(paddle.getX() + paddle.getWidth());
                return true;
            }
        }

        return false;
    }*/

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

    public ImageView getPaddle() {
        return paddle;
    }

    public void setPaddle(ImageView paddle) {
        this.paddle = paddle;
    }

    public ImageView getBall() {
        return ball;
    }

    public void setBall(ImageView ball) {
        this.ball = ball;
    }

    public RelativeLayout getBoard() {
        return board;
    }

    public void setBoard(RelativeLayout board) {
        this.board = board;
    }
}
