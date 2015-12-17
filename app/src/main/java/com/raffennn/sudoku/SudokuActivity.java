package com.raffennn.sudoku;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class SudokuActivity extends AppCompatActivity {
    public static final String LVL = "niveau";
    private String niveau;
    private SudokuDifficulty difficulte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //choixNiveau dialog
        niveau = getIntent().getStringExtra(MainActivity.LVL);
        //
        /*switch (niveau) {
            case "1":
                this.difficulte = SudokuDifficulty.EASY;
                break;
            case "2":
                this.difficulte = SudokuDifficulty.MEDIUM;
                break;
            case "3":
                this.difficulte = SudokuDifficulty.HARD;
                break;
            default:
                break;
        }

        SudokuGrid9x9 sudoku =  new SudokuGrid9x9(difficulte);*/

    }

}
