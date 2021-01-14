package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    int turn = 0;
    int[][] board = {{0,0,0},{0,0,0},{0,0,0}};
    int player = 1;

    public boolean checkWin(){
        int pos1 = board[0][0];
        int pos2 = board[0][1];
        int pos3 = board[0][2];

        int pos4 = board[1][0];
        int pos5 = board[1][1];
        int pos6 = board[1][2];

        int pos7 = board[2][0];
        int pos8 = board[2][1];
        int pos9 = board[2][2];

        boolean hasWon =
                (pos1 > 0 && pos1 == pos2 && pos1 == pos3) ||
                (pos1 > 0 && pos1 == pos4 && pos1 == pos7) ||
                (pos1 > 0 && pos1 == pos5 && pos1 == pos9) ||
                (pos2 > 0 && pos2 == pos5 && pos2 == pos8) ||
                (pos3 > 0 && pos3 == pos6 && pos3 == pos9) ||
                (pos3 > 0 && pos3 == pos5 && pos3 == pos7) ||
                (pos4 > 0 && pos4 == pos5 && pos4 == pos6) ||
                        (pos7 > 0 && pos7 == pos8 && pos7 == pos9);

        return hasWon;
    }

    public void reset(View view){
        for (int row = 0; row <3; row++){
            for (int col = 0; col <3; col++){
                board[row][col]=0;
            }
        }
        for (int pos = 1; pos <10; pos++){
            String posName = "pos"+pos;
            int viewId = getResources().getIdentifier(posName,"id", getBaseContext().getPackageName());
            findViewById(viewId).setBackgroundResource(R.drawable.empty);
        }
        turn=0;
        player=1;
        ((TextView) findViewById(R.id.info)).setText("Player 1 to move");
    }

public void place(View view){
    if(turn == 9){
        return;
    }
    if (checkWin()){
        return;
    }
    int viewId = view.getId();
    String tokenPositionString = getResources().getResourceEntryName(viewId);
    int tokenPosition = Integer.parseInt(tokenPositionString.substring(tokenPositionString.length()-1));
    int rowPosition = (int) Math.floor((double)(tokenPosition-1) / 3.0);
    int columnPosition = (int) ((double) (tokenPosition-1) % 3);
    if (board[rowPosition][columnPosition] != 0) {
        return;
    }
    board[rowPosition][columnPosition] = player;
    int tokenId = turn % 2 == 0 ? R.drawable.twitter : R.drawable.whatsappicon;
    TextView choice = (TextView) findViewById(view.getId());
    choice.setBackgroundResource(tokenId);
    choice.setTranslationY(-1000f);
    choice.animate().translationYBy(1000f).setDuration(200);
    TextView info = (TextView) findViewById(R.id.info);
    if (checkWin()){
       info.setText("Player "+player+" wins!");
        return;
    }
    turn++;
    if (turn == 9){
        info.setText("It's a Draw! ...Typical");
        return;
    }
    player = 3 - player;
    info.setText("Player "+player+" to move");
}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}