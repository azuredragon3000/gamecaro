package com.myapp.lovetest_azuredragon3001.minigame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.myapp.lovetest_azuredragon3001.R;

import java.util.ArrayList;
import java.util.Random;

public class Minigame2 extends AppCompatActivity implements View.OnClickListener {


    private Button[][] buttons = new Button[3][3];

    private boolean player1Turn = true;
    private int roundCount;
    private int player1Points;
    private int player2Points;
    private boolean win = false;
    private TextView textViewPlayer1;
    // private TextView textViewPlayer2;
    private boolean hoa;
    private int ihoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minigame2);

        textViewPlayer1 = findViewById(R.id.win);
        hoa = false;
        ihoa = 0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id",getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        //Button pl2 = player2turn();
       // pl2.setText("0");

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(!((Button)v).getText().toString().equals("")){
            return;
        }

        if(player1Turn){
            ((Button)v).setText("X");
        }else{
            ((Button)v).setText("0");
        }
        roundCount++;

        if(checkForWin()){
            if(player1Turn){
                player1Wins();
            }else{
                player2Wins();
            }
        }else if(roundCount ==9){
            playerdraw();
        }else{
            player1Turn = !player1Turn;
        }
    }



    /*private Button player2turn() {
        String[][] field = new String[3][3];
        ArrayList<Button> list = new ArrayList<>();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                field[i][j] = buttons[i][j].getText().toString();
                if(field[i][j].equals("")){
                    list.add(buttons[i][j]);
                }
            }
        }

        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }*/

    private boolean checkForWin(){
        String[][] field = new String[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        /* check result according to row */
        for(int i=0;i<3;i++){
            if((field[i][0].equals(field[i][1])) &&
                    (field[i][0].equals(field[i][2]))&&
                    (!field[i][0].equals(""))){
                buttons[i][0].setTextColor(Color.RED);
                buttons[i][1].setTextColor(Color.RED);
                buttons[i][2].setTextColor(Color.RED);
                return true;
            }
        }
        /* check result according to column */
        for(int i=0;i<3;i++){
            if((field[0][i].equals(field[1][i])) &&
                    (field[0][i].equals(field[2][i])) &&
                    (!field[0][i].equals(""))){
                buttons[0][i].setTextColor(Color.RED);
                buttons[1][i].setTextColor(Color.RED);
                buttons[2][i].setTextColor(Color.RED);
                return true;
            }
        }


        if(     (field[0][0].equals(field[1][1])) &&
                (field[0][0].equals(field[2][2])) &&
                (!field[0][0].equals(""))
        ){
            buttons[0][0].setTextColor(Color.RED);
            buttons[1][1].setTextColor(Color.RED);
            buttons[2][2].setTextColor(Color.RED);
            return true;
        }

        if(     (field[0][2].equals(field[1][1])) &&
                (field[0][2].equals(field[2][0])) &&
                (!field[0][2].equals(""))
        ){
            buttons[0][2].setTextColor(Color.RED);
            buttons[1][1].setTextColor(Color.RED);
            buttons[2][0].setTextColor(Color.RED);
            return true;
        }



        return false;
    }

    private void player1Wins(){
        player1Points++;
        //Toast.makeText(this,"player 1 wins!",Toast.LENGTH_SHORT);
        updatePointsText("YOU WIN");
        win = true;
        //resetBoard();
    }
    private void playerdraw() {
        player1Points++;
        //Toast.makeText(this,"player 1 wins!",Toast.LENGTH_SHORT);
        updatePointsText("DRAW");
        win = true;
        hoa = false;
        ihoa = 0;
    }
    private void player2Wins(){
        player2Points++;
        //Toast.makeText(this,"player 2 wins!",Toast.LENGTH_SHORT);
        updatePointsText("YOU LOSE");
        win = true;
        //resetBoard();
    }

    private void draw(){
        Toast.makeText(this,"Draw!",Toast.LENGTH_SHORT);
        //resetBoard();
    }
    private void updatePointsText(String display){
        textViewPlayer1.setText(display);
        //textViewPlayer2.setText("player 2: "+ player2Points);
    }

    private void resetBoard(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                buttons[i][j].setText("");
                buttons[i][j].setTextColor(Color.BLACK);
            }
        }
        roundCount = 0;
        player1Turn = true;
    }

    private void resetGame(){
        player1Points = 0;
        player2Points = 0;
//         updatePointsText();
        win =false;
        resetBoard();
        //Button pl2 = player2turn();
       // pl2.setText("0");
        updatePointsText("HAPPY TIME");
    }

    private void nextGame(){
        resetBoard();
        win =false;
        //Button pl2 = player2turn();
       // pl2.setText("0");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt("roundCount",roundCount);
        outState.putInt("player1Points",player1Points);
        outState.putInt("player2Points",player2Points);
        outState.putBoolean("player1Turn",player1Turn);
        outState.putBoolean("win",win);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        roundCount = savedInstanceState.getInt("roundCount");
        player1Points = savedInstanceState.getInt("player1Points");
        player2Points = savedInstanceState.getInt("player2Points");
        player1Turn = savedInstanceState.getBoolean("player1Turn");
        win = savedInstanceState.getBoolean("win");
    }
}