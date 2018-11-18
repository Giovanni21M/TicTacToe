package com.giovannimartinus.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    // array with an item of 2 (means unplayed) for each (x,y) on the grid
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    // will use to determine whether the game is active or not
    boolean gameIsActive = true;

    class Score {

        // an array of arrays
        int[][] winningPositions = {
                {0,1,2}, {3,4,5},
                {6,7,8}, {0,3,6},
                {1,4,7}, {2,5,8},
                {0,4,8}, {2,4,6}
        };

        public void determineScore() {

        }

    }


    class Buttons {

        // create variable used to determine which player is active
        int activePlayer = 0;

        //  onClick method for grid layout spaces
        public void markSpace(View view) {

            //create a variable of ImageView type
            ImageView marker = (ImageView) view;

            // assign a class to an instant
            Score findScore = new Score();

            // get the tag of marker and assign to variable of integer type
            int selectedMarker = Integer.parseInt(marker.getTag().toString());

            // if selectedMarker view is 2 (unplayed) and gameIsActive is true
            if (gameState[selectedMarker] == 2 && gameIsActive) {
                // assign active player to selectedMarker view
                gameState[selectedMarker] = activePlayer;
                marker.setTranslationY(-1000f);

                if (activePlayer == 0) {
                    marker.setImageResource(R.drawable.cross);
                    activePlayer = 1;
                } else if (activePlayer == 1) {
                    marker.setImageResource(R.drawable.nought);
                    activePlayer = 0;
                }

                marker.animate()
                        .translationYBy(1000f)
                        .rotation(360)
                        .setDuration(1000);

                findScore.determineScore();
            }

        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
