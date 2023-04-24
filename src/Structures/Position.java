package Structures;

public class Position {
    public int ligne;
    public int colonne;

    public Position(int ligne, int colonne){
        this.ligne = ligne;
        this.colonne = colonne;
    }
    
    public Position(String stringPos){
        String[] stringPosSplit = stringPos.substring(1, stringPos.length()-1).split(";");
        this.ligne = Integer.parseInt(stringPosSplit[0]);
        this.colonne = Integer.parseInt(stringPosSplit[1]);
    }

    public boolean test_position(int ligne, int colonne){
        return this.ligne == ligne && this.colonne == colonne;
    }

    public String toString(){
        return "("+ ligne + ";" + colonne +")";
    }
}
