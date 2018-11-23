package com.example.josephfletcher.quizbet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.flashcard_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView) findViewById(R.id.flashcard_question)).setVisibility(View.INVISIBLE);

                ((TextView) findViewById(R.id.flashcard_answer)).setVisibility(View.VISIBLE);
                ((TextView) findViewById(R.id.flashcard_answer)).setBackgroundColor(getResources().getColor(R.color.offBlue));
            }
        });

        findViewById(R.id.flashcard_answer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView) findViewById(R.id.flashcard_question)).setVisibility(View.VISIBLE);
                ((TextView) findViewById(R.id.flashcard_question)).setBackgroundColor(getResources().getColor(R.color.turquoise));

                ((TextView) findViewById(R.id.flashcard_answer)).setVisibility(View.INVISIBLE);
            }
        });

        findViewById(R.id.myBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });

    }
    protected final void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) { // this 100 needs to match the 100 we used when we called startActivityForResult!
            if(data != null) {
                String string1 = data.getExtras().getString("string1"); // 'string1' needs to match the key we used when we put the string in the Intent
                ((TextView) findViewById(R.id.flashcard_question)).setText(string1);
                String string2 = data.getExtras().getString("string2");
                ((TextView) findViewById(R.id.flashcard_answer)).setText(string2);
            }
        }
    }
}