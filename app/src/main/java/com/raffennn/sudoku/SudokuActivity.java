package com.raffennn.sudoku;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class SudokuActivity extends AppCompatActivity {
    public static final String LVL = "niveau";

    public static int gridToViewCoord(int i, int j) {
        int k = i * 9 + j;
        int xGrid = k % 9 / 3;
        int yGrid = k / 27;
        int numGrid = yGrid * 3 + xGrid;
        int numCell = (k - (yGrid * 27 + xGrid * 3)) / 3 + k % 3;
        return numGrid * 9 + numCell; // n
    }

    public static int[] viewToGridCoord(int n) {
        int numGrid = n / 9;
        int numCell = n % 9;
        int i = numGrid / 3 + numCell / 3;
        int j = numGrid % 3 + numCell % 3;
        return new int[]{i, j};
    }

    private String niveau;
    private SudokuDifficulty difficulte;
    private GridView gv;

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
        SudokuGrid9x9 sudoku = new SudokuGrid9x9(difficulte);


        //Récupération cellule par cellule
        //Chaque petite grille = un tableau
        //Ranger toute les cellules d'une ligne dans un tableau "tab"
        //Placer ce tab dans la grille "i" du GridLayout

        int[] tab = new int[81];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tab[gridToViewCoord(i, j)] = Integer.parseInt(sudoku.getValueAt(i, j));
            }
        }

        int[][] BASE_GRID = {
                // A B C D E F G H I
                {3, 2, 9, 6, 5, 7, 8, 4, 1}, // A
                {7, 4, 5, 8, 3, 1, 2, 9, 6}, // B
                {6, 1, 8, 2, 4, 9, 3, 7, 5}, // C
                {1, 9, 3, 4, 6, 8, 5, 2, 7}, // D
                {2, 7, 6, 1, 9, 5, 4, 8, 3}, // E
                {8, 5, 4, 3, 7, 2, 6, 1, 9}, // F
                {4, 3, 2, 7, 1, 6, 9, 5, 8}, // G
                {5, 8, 7, 9, 2, 3, 1, 6, 4}, // H
                {9, 6, 1, 5, 8, 4, 7, 3, 2}};// I

        gridview.setAdapter(new GrilleAdapter(tab));

            /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, tab);
            gridview.setAdapter(adapter);

            //ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(10, 10);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            //lp.setMargins(-40, -40, -40,-40);

            gridview.setLayoutParams(lp);*/

    }

    private class SousGrilleAdapter extends BaseAdapter {
        private final int[] sudoku;
        private final int positionDepart;

        public SousGrilleAdapter(int[] sudoku, int positionDepart) {
            this.sudoku = sudoku;
            this.positionDepart = positionDepart;
        }

        @Override
        public int getCount() {
            return 9;
        }

        @Override
        public Object getItem(int position) {
            return this.sudoku[positionDepart + position];
        }

        @Override
        public long getItemId(int position) {
            return this.positionDepart + position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            //création d'une classe anonyme
            final TextView res = new TextView(SudokuActivity.this) {

                @Override
                protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                    //forcer la vue à être carrée
                    super.onMeasure(widthMeasureSpec, widthMeasureSpec);
                }

            };

            res.setClickable(true);
            res.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(res.getContext(), getItem(position).toString(), Toast.LENGTH_SHORT).show();
                }
            });

            res.setBackgroundColor(Color.WHITE);
            res.setText(this.getItem(position).toString());
            res.setGravity(Gravity.CENTER);
            //ignore le padding du texte :
            res.setIncludeFontPadding(false);
            res.setSingleLine(true);
            res.setPadding(0, 0, 0, 0);
            return res;
        }

    }

    //Adapter pour GridView principale
    private class GrilleAdapter extends BaseAdapter {
        private final int[] sudoku;

        public GrilleAdapter(int[] sudoku) {
            this.sudoku = sudoku;
        }

        @Override
        public int getCount() {
            return 9;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            GridView sousGrille = new GridView(SudokuActivity.this) {

                @Override
                protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                    //forcer la vue à être carrée
                    super.onMeasure(widthMeasureSpec, widthMeasureSpec);
                }

            };

            float densite = getResources().getDisplayMetrics().density;
            int dpToPixel = (int) (1 * densite);
            sousGrille.setAdapter(new SousGrilleAdapter(this.sudoku, position * 9));
            sousGrille.setNumColumns(3);
            sousGrille.setHorizontalSpacing(dpToPixel);
            sousGrille.setVerticalSpacing(dpToPixel);

            return sousGrille;
        }
    }
}
