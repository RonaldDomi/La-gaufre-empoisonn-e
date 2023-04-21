package Structures;

import Patterns.Observable;

public class Jeu extends Observable {
    Plateau plateau;
    Historique historique;
    int joueur_courant;
    int gagnant;
    int tour;


    public Jeu(Plateau plateau){
        this.plateau = plateau;
    }

    public Jeu(){
        this.plateau = new Plateau();
        this.historique = null;
        this.joueur_courant = -1;
        this.gagnant = -1;
    }

    public Jeu(int nb_lignes, int nb_colonnes){
        nouvelle_partie(nb_lignes, nb_colonnes);
    }

    public Plateau plateau(){
        return plateau;
    }

    public void nouvelle_partie(int nb_lignes, int nb_colonnes){
        this.plateau = new Plateau(nb_lignes, nb_colonnes);
        this.historique = null; // TODO
        this.joueur_courant = 1; //TODO
        this.gagnant = -1;
        this.tour = 0;
        metAJour();
    }
    
    public boolean jouer_coup(int ligne, int colonne){
        if (plateau.get_tableau(ligne, colonne).est_vide()){
            plateau.placer_coup(joueur_courant, ligne, colonne, tour);
            tour++;
            joueur_courant = (1 - joueur_courant - 1) + 1;
            metAJour();
            return true;
        }
        return false;
    }

    public boolean en_cours(){
        return gagnant == -1;
    }
}
