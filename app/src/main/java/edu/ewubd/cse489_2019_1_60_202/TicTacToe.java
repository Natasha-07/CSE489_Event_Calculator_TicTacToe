package edu.ewubd.cse489_2019_1_60_202;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

public class TicTacToe extends AppCompatActivity {

    private static final String PLAYER_X = "X";
    private static final String PLAYER_O = "O";
    private static final String EMPTY = "";

    private String currentPlayer = PLAYER_X;
    private String[][] board = {
            {"", "", ""},
            {"", "", ""},
            {"", "", ""},
    };
    private Button[][] buttons = new Button[3][3];

    private TextView tvTurn;

    private Button btn00, btn01, btn02, btn10, btn11, btn12, btn20, btn21, btn22, btn_reset;

    private static final String TAG = "TicTacToe";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        tvTurn = (TextView) findViewById(R.id.tv_turn);


        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                String buttonId = "btn" + row + col;
                int resId = getResources().getIdentifier(buttonId, "id", getPackageName());
                buttons[row][col] = (Button) findViewById(resId);
                if (buttons[row][col] == null) {
                    Log.e(TAG, "Button " + buttonId + " not found.");
                } else {
                    buttons[row][col].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onButtonClicked((Button) v);
                        }
                    });
                }
            }
        }


        Button btnReset = (Button) findViewById(R.id.btn_reset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

    }

    private void resetGame() {
        currentPlayer = PLAYER_X;
        tvTurn.setText(currentPlayer + "'s turn");

        // Reset the board and button text
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = EMPTY;
                buttons[row][col].setText(EMPTY);
                buttons[row][col].setEnabled(true);
            }
        }
    }


    private void onButtonClicked(Button button) {
        int row = -1, col = -1;

        // Find the row and column of the clicked button
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (button == buttons[i][j]) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        // Update the board and the button text
        if (board[row][col].equals(EMPTY)) {
            board[row][col] = currentPlayer;
            button.setText(currentPlayer);
            togglePlayer();
            checkForWinner();
        }
    }

    private void togglePlayer() {
        if (currentPlayer.equals(PLAYER_X)) {
            currentPlayer = PLAYER_O;
        } else {
            currentPlayer = PLAYER_X;
        }
        tvTurn.setText(currentPlayer + "'s turn");
    }

    private void disableButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setEnabled(false);
            }
        }
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col].equals(EMPTY)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void checkForWinner() {
        String winner = getWinner();
        if (!winner.equals(EMPTY)) {
            tvTurn.setText(winner + " wins!");
            disableButtons();
        } else if (isBoardFull()) {
            tvTurn.setText("It's a tie!");
        }
    }

    private String getWinner() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2])) {
                return board[i][0];
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i].equals(board[1][i]) && board[0][i].equals(board[2][i])) {
                return board[0][i];
            }
        }

        // Check diagonals
        if (board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2])) {
            return board[0][0];
        }
        if (board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0])) {
            return board[0][2];
        }

        return EMPTY;
    }
}
