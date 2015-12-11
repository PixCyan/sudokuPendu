package com.raffennn.sudoku;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.raffennn.sudoku.data.DAOMot;
import com.raffennn.sudoku.data.Mot;

import java.util.HashSet;

public class Pendu_Activity extends AppCompatActivity {
    public final static int PERDU_REQUEST = 0;
    public final static int GAGNE_REQUEST = 1;
   // private Pendu pendu = new Pendu();

    private String motAtrouver;
    private HashSet<Character> lettresEntrees;
    private char[] motActuel;
    private int pointPerdu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendu);

        this.pointPerdu = 0;
        this.nouveauMot();
        System.out.println("Je suis là ?");
        int tailleMot = this.tailleMot();
        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
        for(int i = 0; i < tailleMot; i++) {
            //text view
            TextView textV = new TextView(this);
            textV.setText("_");
            textV.setWidth(50);
            textV.setHeight(150);
            textV.setId(i);
            //textV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            layout.addView(textV);
        }

        EditText editT = (EditText) findViewById(R.id.editText);
        editT.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
        editT.setFilters(new InputFilter[]{
               new InputFilter.AllCaps(),
                new InputFilter.LengthFilter(1)
        });
    }

    public void valider(View view) {
        EditText editT = (EditText) findViewById(R.id.editText);
        this.comparerLettre(editT.getText().charAt(0));
        char[] mot = this.motActuel;
        for(int i = 0; i < this.tailleMot(); i++) {
            TextView textV = (TextView) findViewById(i);
            textV.setText(Character.toString(mot[i]));
        }
        ImageView imgPendu = (ImageView) findViewById(R.id.imgPendu);
        int[] id = {R.drawable.pendu1, R.drawable.pendu2, R.drawable.pendu3,
                R.drawable.pendu4, R.drawable.pendu5,
                R.drawable.pendu6, R.drawable.pendu7,
                R.drawable.pendu8, R.drawable.pendu9,
                R.drawable.pendu10};
        System.out.println("pendu"+this.pointPerdu);
        imgPendu.setBackgroundResource(id[this.pointPerdu]);
        if(this.perduPendu()) {
            Toast.makeText(this, "PERDU", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Perdu_Pendu_Activity.class);
            startActivityForResult(intent, PERDU_REQUEST);
            //TODO passer le mot à trouver en paramètre

        } else if(this.gagnerPendu()) {
            Intent intent = new Intent(this, Gagne_Pendu_Activity.class);
            startActivityForResult(intent, GAGNE_REQUEST);
            Toast.makeText(this, "GAGNE", Toast.LENGTH_LONG).show();
            //TODO passer le mot à trouver en paramètre
        }
        //TODO renseigner les lettres entrées

        /*Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.newImage);
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.GameLayout);
        linearLayout.setBackgroundDrawable(drawable);*/

    }

    //TODO ajout de l'indice

    //ajout des méthodes de la class Pendu
    public void nouveauMot() {
        System.out.println("TEST NOUVEAU MOT");
        this.lettresEntrees = new HashSet<>();
        DAOMot MotDAO = new DAOMot(this);
        MotDAO.open();
        this.motAtrouver =  MotDAO.getMotRandom(Integer.toString(1)).getLibelle();
        System.out.println("Test");
        //
        this.motActuel = new char[this.tailleMot()];
        for(int i = 0; i < this.tailleMot(); i++) {
            this.motActuel[i] = '_';
        }
        //
        MotDAO.close();
    }

    public boolean perduPendu() {
        boolean perdu = false;
        if(pointPerdu == 9) {
            perdu = true;
        } else {
            perdu = false;
        }
        return perdu;
    }

    public boolean gagnerPendu() {
        boolean gagner = false;
        if(new String(this.motActuel).equals(this.motAtrouver)) {
            gagner = true;
        } else {
            gagner = false;
        }
        return gagner;
    }

    public int tailleMot() {
        return this.motAtrouver.length();
    }

    public void comparerLettre(char lettreEntree) {
        char[] tabMot = this.motAtrouver.toCharArray();
        boolean lettrePrésente = false;
        for(int i = 0; i < tabMot.length; i++) {
            if(tabMot[i] == lettreEntree) {
                this.motActuel[i] = lettreEntree;
                this.lettresEntrees.add(lettreEntree);
                lettrePrésente = true;
            }
        }
        if(!lettrePrésente){
            this.lettresEntrees.add(lettreEntree);
            this.pointPerdu++;
        }
    }
}
