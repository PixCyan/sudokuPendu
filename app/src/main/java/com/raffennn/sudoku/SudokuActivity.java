package com.raffennn.sudoku;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

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
    SudokuGrid9x9 sudokuGame;
    private SudokuDifficulty difficulte;
    private int[] sudokuTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);

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
        //Récupération du GridLayout
        GridView gridview = (GridView) findViewById(R.id.grid);
        this.sudokuGame = new SudokuGrid9x9(difficulte);

        int[] tab = new int[81];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tab[gridToViewCoord(i, j)] = Integer.parseInt(this.sudokuGame.getValueAt(i, j));
            }
        }
        this.sudokuTab = tab;
        gridview.setAdapter(new GrilleAdapter());
    }

    public void valider(View view) {
        List<SudokuError> errors = this.sudokuGame.checkGrid();
        if(errors.isEmpty()) {
            Toast.makeText(SudokuActivity.this, "Sudoku VALIDE", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(SudokuActivity.this, "Sudoku FAUX", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean emptyCellInTab() {
        boolean res = false;
        for(int i = 0; i < sudokuTab.length; i++) {
            if(sudokuTab[i] == 0) {
                res = true;
                break;
            }
        }
        return res;
    }

    private class SousGrilleAdapter extends BaseAdapter {
        private final int positionDepart;

        public SousGrilleAdapter(int positionDepart) {
            this.positionDepart = positionDepart;
        }

        @Override
        public int getCount() {
            return 9;
        }

        @Override
        public Object getItem(int position) {
            return sudokuTab[positionDepart + position];
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
            if(getItem(position).toString().equals("0")) {
                res.setText("");
                res.setBackgroundColor(Color.WHITE);
                res.setClickable(true);
                res.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(res.getContext(), getItem(position).toString(), Toast.LENGTH_SHORT).show();
                        final Dialog dialogue = new Dialog(SudokuActivity.this);
                        dialogue.setTitle("Choisissez un nombre : ");
                        dialogue.setContentView(R.layout.number_picker);
                        Button bouton = (Button) dialogue.findViewById(R.id.bouton);

                        final NumberPicker np = (NumberPicker) dialogue.findViewById(R.id.np);
                        np.setMaxValue(9);
                        np.setMinValue(1);
                        bouton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //Toast.makeText(SudokuActivity.this, Integer.toString(np.getValue()), Toast.LENGTH_SHORT).show();
                                res.setText(Integer.toString(np.getValue()));
                                dialogue.dismiss();
                                int[] vtg = viewToGridCoord(position);
                                System.out.println("vtg == " + vtg[0] + "  " + vtg[1]);
                                System.out.println("Solution = " +sudokuGame.getSolutionAt(vtg[0], vtg[1]) );
                                if(!(sudokuGame.getSolutionAt(vtg[0], vtg[1]).equals(res.getText().toString()))) {
                                    Toast.makeText(SudokuActivity.this, "FAUX", Toast.LENGTH_SHORT).show();
                                    sudokuGame.setValueAt(vtg[0], vtg[1], Integer.parseInt(res.getText().toString()));
                                }

                            }
                        });
                        dialogue.show();
                    }
                });
            } else {
                res.setBackgroundColor(0xFFC0C0C0);
                res.setText(this.getItem(position).toString());
            }
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
            sousGrille.setAdapter(new SousGrilleAdapter(position * 9));
            sousGrille.setNumColumns(3);
            sousGrille.setHorizontalSpacing(dpToPixel);
            sousGrille.setVerticalSpacing(dpToPixel);

            return sousGrille;
        }
    }
}
