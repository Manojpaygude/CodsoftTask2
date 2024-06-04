package com.example.dailyquotes;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private String[] quotes = {
            "* The only way to do great work is to love what you do. – Steve Jobs",
            "* In the middle of every difficulty lies opportunity. – Albert Einstein",
            "* Believe you can and you're halfway there. – Theodore Roosevelt",
            "* It does not matter how slowly you go as long as you do not stop. – Confucius",
            "* The greatest wealth is health. – Virgil",
            "* Take care of your body. It's the only place you have to live. – Jim Rohn",
            "* An apple a day keeps the doctor away. – Unknown",
            "* To keep the body in good health is a duty... otherwise we shall not be able to keep our mind strong and clear. – Buddha",
            "* Health is a state of complete harmony of the body, mind, and spirit. When one is free from physical disabilities and mental distractions, the gates of the soul open. – B.K.S. Iyengar",
            "* Success is not final, failure is not fatal: It is the courage to continue that counts. – Winston Churchill",
            "* You miss 100% of the shots you don't take. – Wayne Gretzky",
            "* The only limit to our realization of tomorrow will be our doubts of today. – Franklin D. Roosevelt",
            "* The best time to plant a tree was 20 years ago. The second best time is now. – Chinese Proverb",
            "* Your time is limited, don't waste it living someone else's life. – Steve Jobs",
            "* Don't cry because it's over, smile because it happened. – Dr. Seuss",
            "* You must be the change you wish to see in the world. – Mahatma Gandhi",
            "* Strive not to be a success, but rather to be of value. – Albert Einstein",
            "* The only person you are destined to become is the person you decide to be. – Ralph Waldo Emerson",
            "* The future belongs to those who believe in the beauty of their dreams. – Eleanor Roosevelt",
            "* I am not a product of my circumstances. I am a product of my decisions. – Stephen Covey",
            "* Life is what happens when you're busy making other plans. – John Lennon",
            "* Twenty years from now you will be more disappointed by the things that you didn't do than by the ones you did do. – Mark Twain",
            "* It is never too late to be what you might have been. – George Eliot",
            "* Do not go where the path may lead, go instead where there is no path and leave a trail. – Ralph Waldo Emerson",
            "* The only impossible journey is the one you never begin. – Tony Robbins"

    };


    TextView text;
    ImageView share, refresh, fav;
    List<String> savedQuotes;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.textView2);
        share = findViewById(R.id.imageView3);
        refresh = findViewById(R.id.imageView5);
        fav = findViewById(R.id.imageView6);

        savedQuotes = new ArrayList<>();

        Random random = new Random();
        int index = random.nextInt(quotes.length);
        final String currentQuote = quotes[index];
        text.setText(currentQuote);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Get the current quote text
                        String quoteText = text.getText().toString();

                        // Create a share intent
                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, quoteText);

                        // Start the activity with the share intent
                        startActivity(Intent.createChooser(shareIntent, "Share via"));
                    }
                });
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int index = random.nextInt(quotes.length);
                text.setText(quotes[index]);
            }
        });

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the current quote text
                String currentText = text.getText().toString();

                // Add the current quote to the list of saved quotes
                savedQuotes.add(currentText);

                // Pass all saved quotes to MainActivity2
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putStringArrayListExtra("saved_quotes", (ArrayList<String>) savedQuotes);
                startActivity(intent);
            }
        });


    }

    private void addToFavorites(String currentText) {
        MySQLiteHelper dbHelper = new MySQLiteHelper(this);

        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_TEXT, String.valueOf(text));

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(MySQLiteHelper.TABLE_FAVORITES, null, values);

        // Close the database connection
        db.close();
    }
}
