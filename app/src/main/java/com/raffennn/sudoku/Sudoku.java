package com.raffennn.sudoku;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class Sudoku extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        String[] numbers = new String[] {
                "1", "2", "2", "3", "1",
                "4", "2", "5", "4", "3",
                "1", "2", "3", "4", "0",
                "7", "7", "3", "0", "3",
                "4", "5", "7", "8", "3",
                "9", "4",
                "1", "2", "2", "3", "1",
                "4", "2", "5", "4", "3",
                "1", "2", "3", "4", "0",
                "7", "7", "3", "0", "3",
                "4", "5", "7", "8", "3",
                "9", "4",
                "1", "2", "2", "3", "1",
                "4", "2", "5", "4", "3",
                "1", "2", "3", "4", "0",
                "7", "7", "3", "0", "3",
                "4", "5", "7", "8", "3",
                "9", "4"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, numbers);

        GridView gridview = (GridView) findViewById(R.id.grid);
        gridview.setAdapter(adapter);
        gridview.setBackgroundColor(Color.WHITE);
    }
}
