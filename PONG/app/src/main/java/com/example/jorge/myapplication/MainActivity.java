package com.example.jorge.myapplication;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private ImageView player1, playerCom, ball, net;
    private Boolean down, up, changeDirection;
    private Handler controlCom = new Handler();
    private Handler controlBall = new Handler();

    private int direction;
    private final int speedMove = 10;
    private final int speedBall = 10;
    private RelativeLayout board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1 = (ImageView) findViewById(R.id.player1);
        playerCom = (ImageView) findViewById(R.id.comPlayer);
        ball = (ImageView) findViewById(R.id.ball);
        net = (ImageView) findViewById(R.id.net);
        board = (RelativeLayout) findViewById(R.id.board);
        down = false;
        up = false;
        changeDirection = false;

        net.setMaxHeight(board.getHeight());

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
            controlCom.postDelayed(playerComMovements, (long) 0.005);
        }
    };

    Runnable ballMovements = new Runnable() {
        @Override
        public void run() {
            boolean collision = detectBallCollisionPaddle(ball);
            System.out.println(collision);
            if (((ball.getX() < 0) || (ball.getX() > board.getWidth())) && (!collision)) {
                System.out.println("REINICIO");
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
                System.out.println("LA DIRECCION SEGUNDO INTENTO ES " + direction);
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
    };

    public boolean detectBallCollisionPaddle(ImageView ball) {
        //Collision player1
        if (((ball.getY() >= player1.getY()) ||
                ((ball.getY() + ball.getHeight()) >= player1.getY())) &&
                ((ball.getY() <= (player1.getY() + player1.getHeight())) ||
                        ((ball.getY() + ball.getHeight()) <= (player1.getY() + player1.getHeight())))) {
            if (((ball.getX() - speedBall) <= 0)) {
                ball.setX(player1.getX()+player1.getWidth());
                return true;
            } else if ((ball.getX() <= (player1.getX() + player1.getWidth()))) {
                ball.setX(player1.getX()+player1.getWidth());
                return true;
            }
        }
        //Collision comPlayer 
        if (((ball.getY() >= playerCom.getY()) ||
                ((ball.getY() + ball.getHeight()) >= playerCom.getY())) &&
                ((ball.getY() <= (playerCom.getY() + playerCom.getHeight())) ||
                        ((ball.getY() + ball.getHeight()) <= (playerCom.getY() + playerCom.getHeight())))) {
            if (((ball.getX() + speedBall) >= board.getWidth())) {
                ball.setX(playerCom.getX()-ball.getWidth());
                return true;
            } else if (((ball.getX()+ball.getWidth()) >= playerCom.getX())) {
                ball.setX(playerCom.getX()-ball.getWidth());
                return true;
            }
        }
        return false;
    }

    public void ballMovements(ImageView ball) {
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
}
