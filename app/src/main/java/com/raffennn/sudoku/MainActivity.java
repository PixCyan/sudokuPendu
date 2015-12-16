package com.raffennn.sudoku;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public final static int SUDOKU_REQUEST = 0;
    public final static int PENDU_REQUEST = 1;
    public static final String LVL = "niveau";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void lancerSudoku(View view) {
        final CharSequence[] items = {"1 : facile", "2 : intermédiaire", "3 : difficile"};
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Choisissez une niveau : ");
        dialogBuilder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                ListView lv = ((AlertDialog) dialog).getListView();
                lv.setTag(new Integer(which));
                choixLvlSudoku(lv.getTag().toString());
            }
        });
        dialogBuilder.create().show();

    }

    private void choixLvlSudoku(String valeur) {
        Intent intent = new Intent(this, Sudoku.class);
        startActivityForResult(intent, SUDOKU_REQUEST);
        int choix = Integer.parseInt(valeur)+1;
        intent.putExtra(LVL, Integer.toString(choix));
        //intent.putExtra(COMPTE, login);
        Toast.makeText(this, "FIN", Toast.LENGTH_LONG).show();
    }

    public void lancerPendu(View view) {
            final CharSequence[] items = {"1 : facile", "2 : intermédiaire", "3 : difficile"};
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            dialogBuilder.setTitle("Choisissez une niveau : ");
            dialogBuilder.setItems(items, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    ListView lv = ((AlertDialog) dialog).getListView();
                    lv.setTag(new Integer(which));
                    choixLvlPendu(lv.getTag().toString());
                }
            });
            dialogBuilder.create().show();
    }

    private void choixLvlPendu(String valeur) {
        Intent intent = new Intent(this, Pendu_Activity.class);
        int choix = Integer.parseInt(valeur)+1;
        intent.putExtra(LVL, Integer.toString(choix));
        //intent.putExtra(COMPTE, login);
        startActivityForResult(intent, PENDU_REQUEST);
    }

}
