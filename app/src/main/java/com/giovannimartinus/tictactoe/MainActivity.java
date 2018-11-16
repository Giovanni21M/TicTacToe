package com.giovannimartinus.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    public void markSpace(View view) {

        ImageView marker = (ImageView) view;

        marker.setTranslationY(-1000f);

         marker.setImageResource(R.drawable.cross);
        // marker.setImageResource(R.drawable.nought);

        marker.animate()
                .translationYBy(1000f)
                .rotation(360)
                .setDuration(1000);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
