package Structures;

import Patterns.Observable;

public class Jeu extends Observable {
    Plateau plateau;
    Historique historique;
    int joueur_courant;
    int gagnant;
    int tour;
    Coup coup_previsualise;

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

    public void set_gagnant(int joueur){
        gagnant = joueur;
    }

    public void nouvelle_partie(int nb_lignes, int nb_colonnes){
        this.plateau = new Plateau(nb_lignes, nb_colonnes);
        this.historique = new Historique();
        this.joueur_courant = 1;
        this.gagnant = -1;
        this.tour = 0;
        metAJour();
    }

    public boolean jouer_coup(int ligne, int colonne){
        if (coup_previsualise != null){
            coup_previsualise.vider();
            coup_previsualise = null;
        }
        if (plateau.get_tableau(ligne, colonne).est_vide()){
            if (ligne == 0 && colonne == 0){
                gagnant = joueur_courant;
            }
            Coup nouveau_coup =  plateau.placer_coup(joueur_courant, ligne, colonne, tour);
            historique.ajouter_coup(nouveau_coup);
            tour++;
            joueur_courant = (1 - (joueur_courant - 1)) + 1;
            metAJour();
            return true;
        }
        return false;
    }

    public boolean jouer_coup(int ligne, int colonne, int couleur) {
        if (coup_previsualise != null){
            if (!coup_previsualise.position().test_position(ligne, colonne))
                plateau.placer_coup(plateau.get_coup_vide(), coup_previsualise.position().ligne, coup_previsualise.position().colonne); // Coup vide
            else{
                if (ligne == 0 && colonne == 0){
                    gagnant = joueur_courant;
                }
                coup_previsualise.changer_joueur(joueur_courant);
                historique.ajouter_coup(coup_previsualise);
                coup_previsualise = null;
                tour++;
                joueur_courant = (1 - (joueur_courant - 1)) + 1;
                metAJour();
                return true;
            }
        }
        if (plateau.get_tableau(ligne, colonne).est_vide()){
            coup_previsualise = plateau.placer_coup(couleur, ligne, colonne, tour);
            metAJour();
        }
        return false;
    }

    public boolean en_cours(){
        return gagnant == -1;
    }

    public int gagnant(){
        return gagnant;
    }

    public int get_tour(){
        return tour;
    }

    public int get_joueur_courant(){
        return joueur_courant;
    }

    public void annuler_coup(){
        Coup coup_annule = historique.annuler_coup();
        if (coup_annule != null) {
            coup_annule.vider();
            gagnant = -1;
            joueur_courant = (1 - (joueur_courant - 1)) + 1;
            tour--;
            metAJour();
        }
    }

    public Historique get_historique(){
        return historique;
    }

    public void refaire_coup(){
        Coup coup_refait = historique.refaire_coup();
        if (coup_refait != null) {
            if (coup_refait.position.test_position(0, 0)){
                gagnant = joueur_courant;
            }
            joueur_courant = (1 - (joueur_courant - 1)) + 1;
            coup_refait.changer_joueur(joueur_courant);
            tour++;
            metAJour();
        }
    }
}
