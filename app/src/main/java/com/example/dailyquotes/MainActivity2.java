package com.example.dailyquotes;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    TextView savedQuotesTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        savedQuotesTextView = findViewById(R.id.text_title);

        // Retrieve the saved quotes
        ArrayList<String> savedQuotes = getIntent().getStringArrayListExtra("saved_quotes");

        // Display the saved quotes
        displaySavedQuotes(savedQuotes);
    }

    private void displaySavedQuotes(ArrayList<String> savedQuotes) {
        // Initialize a StringBuilder to store all quotes
        StringBuilder allQuotesBuilder = new StringBuilder();

        // Append all saved quotes
        if (savedQuotes != null && !savedQuotes.isEmpty()) {
            for (String quote : savedQuotes) {
                allQuotesBuilder.append(quote).append("\n");
            }
        }

        // Set the text of the TextView to display all quotes
        savedQuotesTextView.setText(allQuotesBuilder.toString());
    }
}
