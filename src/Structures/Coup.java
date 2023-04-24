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
        this.joueur = -1;
        this.position = null;
        this.tour = -1;
    }
    
    public Coup(String stringCoup){
        stringCoup = stringCoup.substring(0, stringCoup.length()-1);
        String[] stringCoupSep = stringCoup.split("; ");
        this.joueur = Integer.parseInt(stringCoupSep[0]);
        this.position = new Position(stringCoupSep[1]);
        this.tour = Integer.parseInt(stringCoupSep[2]);
    }

    public Position position() {
        return position;
    }

    public int get_tour(){
        return tour;
    }

    public boolean est_vide(){
        return joueur == -1;
    }

    public void vider(){
        joueur = -1;
    }

    public void changer_joueur(int nouveau_joueur){
        joueur = nouveau_joueur;
    }

    public int num_joueur(){
        return joueur;
    }

    public String toString(){
        return "[" + joueur + "; " + position + "; " + tour + "]";
    }
}
