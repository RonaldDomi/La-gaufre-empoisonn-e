package Structures;

public class Jeu {
    Plateau plateau;
    Historique historique;
    int jeuer_courant;
    int gagnant;


    public Jeu(Plateau plateau){
        this.plateau = plateau;
    }
    public Jeu(){
        this.plateau = new Plateau();
        this.historique = null;
        this.jeuer_courant = -1;
        this.gagnant = -1;
    }
}
