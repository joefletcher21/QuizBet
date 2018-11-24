package com.example.josephfletcher.quizbet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int currentCardDisplayedIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();

        if (allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(0).getAnswer());
        }

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

        findViewById(R.id.next_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // advance our pointer index so we can show the next card
                currentCardDisplayedIndex++;

                // make sure we don't get an IndexOutOfBoundsError if we are viewing the last indexed card in our list
                if (currentCardDisplayedIndex > allFlashcards.size() - 1) {
                    currentCardDisplayedIndex = 0;
                }

                // set the question and answer TextViews with data from the database
                ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
            }
        });

        findViewById(R.id.deleteBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allFlashcards = flashcardDatabase.getAllCards();
                flashcardDatabase.deleteCard(((TextView) findViewById(R.id.flashcard_question)).getText().toString());
                if(allFlashcards.size() == 0){
                    ((TextView) findViewById(R.id.flashcard_question)).setText("No Cards availible to view \nSelect the icon below to create a new card");
                }
                else{
                    ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                }

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
                flashcardDatabase.insertCard(new Flashcard(string1, string2));
                allFlashcards = flashcardDatabase.getAllCards();
            }
        }
    }
}