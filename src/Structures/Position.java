package Structures;

public class Position {
    int ligne;
    int colonne;

    public Position(int ligne, int colonne){
        this.ligne = ligne;
        this.colonne = colonne;
    }

    public boolean test_position(int ligne, int colonne){
        return this.ligne == ligne && this.colonne == colonne;
    }

    public String toString(){
        return "("+ ligne + "," + colonne +")";
    }
}
