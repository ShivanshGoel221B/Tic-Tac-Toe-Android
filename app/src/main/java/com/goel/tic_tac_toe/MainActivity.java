
package com.goel.tic_tac_toe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {
    char[] gameState = {'0','0','0','0','0','0','0','0','0'};
    byte[][] winPositions = {
            {0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {6,4,2}
    };
    byte activePlayer = 1;
    boolean gameActive = true;
    //    Check if the match is drawn or not
    boolean draw()
    {
        for (char entry:
                gameState) {
            if (entry == '0') return false;
        }
        TextView statusBar = findViewById(R.id.statusBar);
        findViewById(R.id.statusBar).performHapticFeedback(1);
        statusBar.setText("Match Drawn - Tap to Replay");
        return true;
    }
    //    Reset the game
    public void gameReset(View view)
    {
        if (gameActive) return;
        gameActive = true;
        for (byte i=0; i<gameState.length; i++)
            gameState[i]='0';
        ((ImageView) findViewById(R.id.i0)).setImageResource(0);
        ((ImageView) findViewById(R.id.i1)).setImageResource(0);
        ((ImageView) findViewById(R.id.i2)).setImageResource(0);
        ((ImageView) findViewById(R.id.i3)).setImageResource(0);
        ((ImageView) findViewById(R.id.i4)).setImageResource(0);
        ((ImageView) findViewById(R.id.i5)).setImageResource(0);
        ((ImageView) findViewById(R.id.i6)).setImageResource(0);
        ((ImageView) findViewById(R.id.i7)).setImageResource(0);
        ((ImageView) findViewById(R.id.i8)).setImageResource(0);
        ((TextView) findViewById(R.id.statusBar)).setText("Player 1's Turn");
    }
    public void playerTap(View view)
    {
        if (gameActive)
        {
            ImageView img = (ImageView) view;
            byte cell = Byte.parseByte(img.getTag().toString());
            if (gameState[cell]=='0')
            {
                img.setTranslationY(-200f);
                if (activePlayer==1)
                {
                    img.setImageResource(R.drawable.o);
                    gameState[cell] = 'O';
                    activePlayer = 2;
                    ((TextView) findViewById(R.id.statusBar)).setText("Player 2's Turn");
                }
                else
                {
                    img.setImageResource(R.drawable.x);
                    gameState[cell] = 'X';
                    activePlayer = 1;
                    ((TextView) findViewById(R.id.statusBar)).setText("Player 1's Turn");
                }
                img.animate().translationYBy(200f).setDuration(400);
                if (draw()) gameActive = false;
                for (byte[] position : winPositions)
                {
                    if (gameState[position[0]]!='0' && gameState[position[0]] == gameState[position[1]] && gameState[position[0]] == gameState[position[2]])
                    {
                        view.performHapticFeedback(1);
                        gameActive = false;
                        TextView statusBar = findViewById(R.id.statusBar);
                        if (activePlayer == 1)
                            statusBar.setText("Player 2 Won - Tap to Replay");
                        else
                            statusBar.setText("Player 1 Won - Tap to Replay");
                        break;
                    }
                }
            }
            else Toast.makeText(this, "Invalid Move", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        View fullscreen = getWindow().getDecorView();
        fullscreen.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        View fullscreen = getWindow().getDecorView();
        fullscreen.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
    }
}