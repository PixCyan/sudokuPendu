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
import android.widget.Toast;

import java.util.List;

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
        switch (niveau) {
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
        //Toast.makeText(this, this.difficulte.getLabel(), Toast.LENGTH_LONG).show();

        //Récupération du GridLayout
        GridView gridview = (GridView) findViewById(R.id.grid);
        SudokuGrid9x9 sudoku =  new SudokuGrid9x9(difficulte);

        //Récupération cellule par cellule
        //Chaque petite grille = un tableau
        //Ranger toute les cellules d'une ligne dans un tableau "tab"
        //Placer ce tab dans la grille "i" du GridLayout

        int k = 0;
        String[] tab = new String[81];
        for(int i = 0; i < 9; i++) {
            for(int j = 0 ; j < 9  ; j++) {
                //System.out.println("i = "+ i +" ; j = "+ j);
                System.out.println("Case = " + sudoku.getValueAt(i,j));
                tab[k] = sudoku.getValueAt(i,j);
                k++;
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, tab);
            gridview.setAdapter(adapter);
            gridview.setBackgroundColor(1);
        }




        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, sudoku.toString());

        GridView gridview = (GridView) findViewById(R.id.grid);
        gridview.setAdapter(adapter);
        gridview.setBackgroundColor(Color.WHITE);

        textView.setLayoutParams(
              new GridLayout.LayoutParams(
                       GridLayout.spec(row, GridLayout.FILL),
                       GridLayout.spec(column, GridLayout.FILL));
       myGridLayout.addView(createTextView("H", 0, 0, R.color.grey_dark, false, false));

        */

    }

}
