package com.example.jorge.myapplication;

import android.graphics.Path;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private ImageView player1, playerCom, ball;
    private Boolean down, up, changeDirection;
    private Handler controlCom = new Handler();
    private Handler controlBall = new Handler();

    private int direction;
    private final int speedMove = 50;
    private final int speedBall = 10;
    private RelativeLayout board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1 = (ImageView) findViewById(R.id.player1);
        playerCom = (ImageView) findViewById(R.id.comPlayer);
        ball = (ImageView) findViewById(R.id.ball);
        board = (RelativeLayout) findViewById(R.id.board);
        down = false;
        up = false;
        changeDirection = false;


        playerComMovements.run();
        ballMovements.run();


        final Button btnUp = (Button) findViewById(R.id.btnUp);
        btnUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if ((event.getAction() == MotionEvent.ACTION_DOWN)) {
                    //player1.setY(player1.getY() - speedMove);
                    down = false;
                    up = true;
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    up = false;
                }
                return true;
            }
        });

        final Button btnDown = (Button) findViewById(R.id.btnDown);
        btnDown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if ((event.getAction() == MotionEvent.ACTION_DOWN)) {
                    //player1.setY(player1.getY() + speedMove);
                    up = false;
                    down = true;
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    down = false;
                }
                return true;
            }
        });
    }

    Runnable playerComMovements = new Runnable() {
        @Override
        public void run() {
            //Movements comPlayer
            if (!((playerCom.getY() - speedMove) <= 0) && (!changeDirection)) {//Up
                playerCom.setY(playerCom.getY() - speedMove);
            } else {
                changeDirection = true;
            }
            if (!((playerCom.getY() + playerCom.getHeight() + speedMove) >= board.getHeight()) && (changeDirection)) {//Down
                playerCom.setY(playerCom.getY() + speedMove);
            } else {
                changeDirection = false;
            }

            //Movements player1
            if ((up && !down) && (!((player1.getY() - speedMove) <= 0))) {
                player1.setY(player1.getY() - speedMove);
            } else if ((!up && down) && (!((player1.getY() + player1.getHeight() + speedMove) >= (board.getHeight())))) {
                player1.setY(player1.getY() + speedMove);
            }
            controlCom.postDelayed(playerComMovements, 100);
        }
    };

    Runnable ballMovements = new Runnable() {
        @Override
        public void run() {

            if ((ball.getX() < 0) || (ball.getX() > board.getWidth())) {
                ball.setX(board.getWidth() / 2);
                ball.setY(board.getHeight() / 2);
                direction = 0;
                /*try {
                    controlBall.wait(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            } else {
                if (((player1.getY() <= ball.getY()) && ((player1.getY() + player1.getHeight()) >= (ball.getY()))) && ((player1.getX() + player1.getWidth()) >= ball.getX())&&(direction == 0)) {
                    direction = 2;
                } else {
                    if (((ball.getY() + speedBall) <= 0) && (direction == 0)) {
                        direction = 1;
                    } else if (direction == 2) {
                        direction = 3;
                    }

                    if (((ball.getY() + ball.getHeight()) + speedMove >= board.getHeight()) && (direction == 1)) {
                        direction = 0;
                    } else if (direction == 3) {
                        direction = 2;
                    }
                }
                switch (direction) {//rebotes
                    case 0: //arriba izquierda
                        ball.setY(ball.getY() - speedBall);
                        ball.setX(ball.getX() - speedBall);
                        break;
                    case 1: //abajo izquierda
                        ball.setY(ball.getY() + speedBall);
                        ball.setX(ball.getX() - speedBall);
                        break;
                    case 2://arriba derecha
                        ball.setY(ball.getY() - speedBall);
                        ball.setX(ball.getX() + speedBall);
                        break;
                    case 3: //abajo derecha
                        ball.setY(ball.getY() + speedBall);
                        ball.setX(ball.getX() + speedBall);
                        break;
                }
            }
            controlBall.postDelayed(ballMovements, (long) 0.005);
        }
    };
}
