package Structures;

public class Coup {
    int jouer;
    Position position;
    int tour;

    public Coup(int jouer, Position position, int tour){
        this.jouer = jouer;
        this.tour = tour;
        this.position = position;
    }

    public Coup(){
        jouer = -1;
        position = null;
        tour = -1;
    }
}
