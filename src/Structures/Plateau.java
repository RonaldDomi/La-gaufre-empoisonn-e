package Structures;

public class Plateau {
    Coup[][] tableau;
    int nb_ligne;
    int nb_colonne;

    void Plateau(Coup[][] plateau){
        this.tableau = plateau;
        //TODO
        //calcule nb_ligne, nb_colonne
    }

    void Plateau(int nb_ligne, int nb_colonne){
        this.tableau = init_tableau();
    }

    Coup[][] init_tableau(){
        return null;
    }
}
