package com.giovannimartinus.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

        private void endMessage(String string) {

            TextView endMessage = (TextView) findViewById(R.id.endMessage);

            // make a LinerLayout visible
            LinearLayout playAgainLayout = (LinearLayout) findViewById(R.id.playAgainLayout);
            playAgainLayout.setVisibility(View.VISIBLE);

            // determine which string to assign to the TextView
            if (string == "cross" || string == "nought") {
                endMessage.setText(string + " has won!");
            } else if (string == "draw") {
                endMessage.setText("It was a " + string + "!");
            }

        }

        public void determineScore() {
            
            for (int[] winningPosition : winningPositions) {

                //  check to see if all three numbers in each array matches
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {

                    gameIsActive = false;
                    String winner;

                    // determine which player won
                    if (gameState[winningPosition[0]] == 0) {
                        winner = "cross";
                        endMessage(winner);
                    } else if (gameState[winningPosition[1]] == 1) {
                        winner = "nought";
                        endMessage(winner);
                    }
                  // possibilities if no current winner
                } else {

                    boolean gameIsOver = true;

                    // determine whether or not the game is still active
                    for (int markerState : gameState) {
                        if (markerState == 2) gameIsOver = false;
                    }

                    // determine if game is a draw
                    if (gameIsOver) {
                        endMessage("draw");
                    }

                }
            }
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
                // set player turn
                gameState[selectedMarker] = activePlayer;
                marker.setTranslationY(-1000f);

                // assign active player to selectedMarker view and switch player turn
                if (activePlayer == 0) {
                    marker.setImageResource(R.drawable.cross);
                    activePlayer = 1;
                } else if (activePlayer == 1) {
                    marker.setImageResource(R.drawable.nought);
                    activePlayer = 0;
                }

                // animate the view
                marker.animate()
                        .translationYBy(1000f)
                        .rotation(360)
                        .setDuration(1000);

                // call class' method
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
