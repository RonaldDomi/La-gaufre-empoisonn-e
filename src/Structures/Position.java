package Structures;

public class Position {
    public int ligne;
    public int colonne;

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
