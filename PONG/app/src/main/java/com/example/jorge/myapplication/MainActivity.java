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

    private ImageView player1, playerCom;
    private Boolean down, up, changeDirection;
    private Handler controlCom = new Handler ();
    private int loop =0;

    private final int speedMove = 50;
    private  RelativeLayout board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1 = (ImageView)findViewById(R.id.player1);
        playerCom = (ImageView)findViewById(R.id.comPlayer);
        board = (RelativeLayout)findViewById(R.id.board);
        down= false;
        up= false;
        changeDirection = false;
        playerComMovements.run();


        final Button btnUp = (Button)findViewById(R.id.btnUp);
        btnUp.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent event){
                if ((event.getAction() == MotionEvent.ACTION_DOWN)) {
                    //player1.setY(player1.getY() - speedMove);
                    down= false;
                    up = true;
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    up = false;
                }
                return true;
            }
        });

        final Button btnDown = (Button)findViewById(R.id.btnDown);
        btnDown.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent event){
                if (( event.getAction() == MotionEvent.ACTION_DOWN )) {
                    //player1.setY(player1.getY() + speedMove);
                    up = false;
                    down = true;
                }
                if (event.getAction() == MotionEvent.ACTION_UP){
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
            if(!((playerCom.getY()-speedMove)<=0)&&(!changeDirection)){//Up
                playerCom.setY(playerCom.getY()-speedMove);
            }else {
                changeDirection= true;
            }
            if (!((playerCom.getY()+playerCom.getHeight()+speedMove)>=board.getHeight())&&(changeDirection)){//Down
                playerCom.setY(playerCom.getY()+speedMove);
            }else{
                changeDirection = false;
            }

            //Movements player1
            if((up && !down)&&(!((player1.getY()-speedMove)<=0))){
                player1.setY(player1.getY() - speedMove);
            }else if((!up && down)&&(!((player1.getY()+player1.getHeight()+speedMove)>=(board.getHeight())) )){
                player1.setY(player1.getY() + speedMove);
            }
            controlCom.postDelayed(playerComMovements, 100);
        }
    };
}
