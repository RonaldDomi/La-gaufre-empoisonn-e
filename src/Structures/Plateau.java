package Structures;

public class Plateau {
    Coup[][] tableau;
    int nb_lignes;
    int nb_colonnes;

    Coup coup_vide;

    Plateau(Coup[][] plateau){
        this.tableau = plateau;
        //TODO
        //calcule nb_lignes, nb_colonnes
    }

    Plateau(){
    }

    Plateau(int nb_lignes, int nb_colonnes){
        this.nb_lignes = nb_lignes;
        this.nb_colonnes = nb_colonnes;
        this.tableau = new Coup[nb_lignes][nb_colonnes];
        init_tableau();
    }

    int nombre_lignes(){
        return nb_lignes;
    }

    int nombre_colonnes(){
        return nb_colonnes;
    }

    Coup[][] init_tableau(){
        coup_vide = new Coup();
        for (int i = 0; i < nb_lignes; i++)
            for (int j = 0; j < nb_colonnes; j++)
                tableau[i][j] = coup_vide;
        return tableau;
    }

    Coup get_coup_vide(){
        return coup_vide;
    }

    public Coup get_tableau(int ligne, int colonne){
        return tableau[ligne][colonne];
    }

    Coup placer_coup(int joueur, int ligne, int colonne, int tour){
        Coup nouveau_coup = new Coup(joueur, new Position(ligne, colonne), tour);
        for (int i = ligne; i < nombre_lignes(); i++) {
            for (int j = colonne; j < nombre_colonnes(); j++) {
                if (get_tableau(i, j).num_joueur() < 0){
                    tableau[i][j] = nouveau_coup;
                }
            }
        }
        return nouveau_coup;
    }

    Coup placer_coup(Coup coup, int ligne, int colonne){
        for (int i = ligne; i < nombre_lignes(); i++) {
            for (int j = colonne; j < nombre_colonnes(); j++) {
                if (get_tableau(i, j).num_joueur() < 0){
                    tableau[i][j] = coup;
                }
            }
        }
        return coup;
    }

    public int get_nb_lignes(){
        return nb_lignes;
    }
    public int get_nb_colonnes(){
        return nb_colonnes;
    }
}
