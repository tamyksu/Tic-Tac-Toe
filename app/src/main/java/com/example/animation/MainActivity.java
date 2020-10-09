package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //0 = yellow
    //arr val 2 not touched
    //1- red
    //0-yellow
    int activePlayer =0;
    int[] arr= {2,2,2,2,2,2,2,2,2};
    boolean overGame= false;
    int [][] winningPositions ={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropin(View view){
        ImageView counter = (ImageView) view;
        if(arr[Integer.parseInt(counter.getTag().toString())]==2 && overGame == false ) {
            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                arr[Integer.parseInt(counter.getTag().toString())]=0;

                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
                arr[Integer.parseInt(counter.getTag().toString())]=1;

            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
            for (int[] index: winningPositions)
            {
                if (arr[index[0]]==arr[index[1]] && arr[index[1]]==arr[index[2]] && arr[index[0]] != 2)
                {
                    overGame=true;
                    TextView WinnerMessage =(TextView)findViewById(R.id.WinnerMessage);
                    if(activePlayer == 0)
                        WinnerMessage.setText("Red has Won!");
                    else{
                        WinnerMessage.setText("Yellow has Won!");
                    }
                    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);

                }

            }
        }

    }
    public void playAgain(View view){
        //make the message and button invisible

        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        overGame=false;
         activePlayer = 0;

        for (int i=0;i<arr.length;i++)
        {
            arr[i] = 2;
        }

        androidx.gridlayout.widget.GridLayout gridLayout = (androidx.gridlayout.widget.GridLayout) findViewById(R.id.gridLayout);

        for (int i=0;i<gridLayout.getChildCount();i++)
        {
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
   /* public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }*/
}
