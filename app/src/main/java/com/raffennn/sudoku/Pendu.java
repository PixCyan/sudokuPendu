package com.raffennn.sudoku;

import android.support.v7.app.AppCompatActivity;

import java.util.HashSet;
import com.raffennn.sudoku.data.*;


/**
 * Created by raffennn on 04/11/2015.
 */
/*
public class Pendu {
    private Mot motAtrouver;
    private HashSet<Character> lettresEntrees;
    private char[] motActuel;
    private int pointPerdu;

    public Pendu() {
        this.pointPerdu = 0;
        this.nouveauMot();
    }

    public void nouveauMot() {
        DAOMot MotDAO = new DAOMot(this);
        MotDAO.open();
        this.motAtrouver = MotDAO.getMotRandom(1);
        //
        this.lettresEntrees = new HashSet<>();
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
        return this.motAtrouver.getLibelle().length();
    }

    public void comparerLettre(char lettreEntree) {
        char[] tabMot = getMotAtrouver().getLibelle().toCharArray();
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



    //----------- Getters et Setters -----------//

    public Mot getMotAtrouver() {
        return motAtrouver;
    }

    public void setMotAtrouver(Mot motAtrouver) {
        this.motAtrouver = motAtrouver;
    }

    public HashSet<Character> getLettresEntrees() {
        return lettresEntrees;
    }

    public void setLettresEntrees(HashSet<Character> lettresEntrees) {
        this.lettresEntrees = lettresEntrees;
    }

    public char[] getMotActuel() {
        return motActuel;
    }

    public void setMotActuel(char[] motActuel) {
        this.motActuel = motActuel;
    }

    public int getPointPerdu() {
        return pointPerdu;
    }

    public void setPointPerdu(int pointPerdu) {
        this.pointPerdu = pointPerdu;
    }
}*/
