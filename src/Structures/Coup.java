package Structures;

public class Coup {
    int joueur;
    Position position;
    int tour;

    public Coup(int joueur, Position position, int tour){
        this.joueur = joueur;
        this.tour = tour;
        this.position = position;
    }

    public Coup(){
        joueur = -1;
        position = null;
        tour = -1;
    }

    public boolean est_vide(){
        return joueur == -1;
    }

    public int num_joueur(){
        return joueur;
    }
}
