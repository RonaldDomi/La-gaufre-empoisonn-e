package Structures;

import java.util.ArrayList;
import java.util.List;

public class Arbre {

    Jeu j;
    List<Arbre> fils;
    Position p;
    Boolean isOK;

    //constructeur arbre
    public Arbre(Position p, Jeu j){
        fils = new ArrayList<>();
        this.p = new Position(p.ligne, p.colonne);
        this.j = j;
    }

    //initialisation 1er arbre
    public Arbre (Jeu j){
        fils = new ArrayList<>();
        p = new Position(-1, -1);
        this.j = j;
    }

    public void create(){
        if(p.ligne == 0 && p.colonne == 0)
            return;
        Jeu temp;
        Arbre temp2;
        for (int i = 0; i < j.plateau.nb_lignes; i++) {
            for (int k = 0; k < j.plateau.nb_colonnes; k++) {
                if (j.plateau.tableau[i][k].est_vide()){
                    //on cree le clone du jeu
                    temp = j.Clone();
                    //on joue le coup
                    temp.jouer_coup(i, k);
                    //on cree l'arbre associer
                    temp2 = new Arbre(new Position(i, k), temp);
                    //on ajoute dans le pool d arbre fils
                    fils.add(temp2);
                    //on apelle recursivement
                    temp2.create();
                }
            }
        }
    }

    public Boolean CalculeJoueurA(){
        //si feuille
        if (fils.isEmpty()){
            isOK = false;
            return false;
        }
        Boolean v = false;
        Boolean v2;
        for (Arbre elem:fils){
            v2 = elem.CalculeJoueurB();
            v = v || v2;
            elem.isOK = v2;
        }
        return  v;
    }

    public Boolean CalculeJoueurB(){
        if (fils.isEmpty()){
            isOK = true;
            return true;
        }
        Boolean v = true;
        Boolean v2;
        for (Arbre elem:fils){
            v2 = elem.CalculeJoueurA();
            v = v && v2;
            elem.isOK = v2;
        }
        return  v;
    }

}


