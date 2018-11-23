package com.example.josephfletcher.quizbet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        findViewById(R.id.saveBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newQuestion = ((EditText) findViewById(R.id.questionTextField)).getText().toString();
                Intent data = new Intent(); // create a new Intent, this is where we will put our data
                data.putExtra("string1", newQuestion); // puts one string into the Intent, with the key as 'string1'
                String newAnswer = ((EditText) findViewById(R.id.answerTextField)).getText().toString();
                data.putExtra("string2", newAnswer); // puts another string into the Intent, with the key as 'string2
                setResult(RESULT_OK, data); // set result code and bundle data for response
                finish(); // closes this activity and pass data to the original activity that launched this activity
            }
        });

        findViewById(R.id.myBtn).setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                finish();

            }
        });


    }
}
