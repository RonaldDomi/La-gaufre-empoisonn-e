package Structures;

import java.util.LinkedList;

public class Historique {
    LinkedList<Coup> coups_joueur1;
    LinkedList<Coup> coups_joueur2;
    int coupIndexL1=0;
    int coupIndexL2=0;
    int dernierCoupJoue=0;
    
    Historique(){
        coups_joueur1 = new LinkedList<>();
        coups_joueur2 = new LinkedList<>();
    }
    
    Historique(String stringHist){
        coups_joueur1 = new LinkedList<>();
        coups_joueur2 = new LinkedList<>();
        
        String[] tabHist = stringHist.split("\n");
        coupIndexL1 = Integer.parseInt(tabHist[0]);
        coupIndexL2 = Integer.parseInt(tabHist[2]);
        String[] tabHistL1 = tabHist[1].split(", ");
        String[] tabHistL2 = tabHist[3].split(", ");
        
        for (int i = 0; i < tabHistL1.length; i++) {
            coups_joueur1.add(new Coup(tabHistL1[i]));
        }
        for (int j = 0; j < tabHistL2.length; j++) {
            coups_joueur2.add(new Coup(tabHistL2[j]));
        }
    }
    
    
    public boolean peut_annuler(){
        return (coupIndexL1 >= 1 || coupIndexL2 >= 1);
    }
    
    
   public boolean peut_refaire(){
        return (coupIndexL1 <= coups_joueur1.size() || coupIndexL2 <= coups_joueur2.size());
    }

    
    void ajouter_coup(Coup coup){
        if(coup.num_joueur() == 1){
            coups_joueur1.add(coupIndexL1, coup);
            coupIndexL1++;
            dernierCoupJoue = 1;
        }else{
            coups_joueur2.add(coupIndexL2, coup);
            coupIndexL2++;
            
            dernierCoupJoue = 2;
        }

        supprimer_suite_coup();
        System.out.println("Historique : ");
        System.out.println("1 : " + coups_joueur1);
        System.out.println("2 : " + coups_joueur2);
        System.out.println();
    }

    
    Coup annuler_coup(/* Prend un coup ? Ou on doit déterminer le dernier ? */){
        if (!peut_annuler()){
            System.err.println("Impossible d'annuler le coup. Aucun coup n'a été joué !");
            return null;
        }
        Coup dernierJoue;
        switch(dernierCoupJoue) {
            case (1):
                coupIndexL1--;
                dernierJoue = coups_joueur1.get(coupIndexL1);
                dernierCoupJoue = 2;
                break;
            case (2):
                coupIndexL2--;
                dernierJoue = coups_joueur2.get(coupIndexL2);
                dernierCoupJoue = 1;
                break;
            default:
                System.err.println("Dernier coup joué est en dehors des valeurs définies... " + dernierCoupJoue);
                return null;
        }
        return dernierJoue;
    }
    

    Coup refaire_coup() {
        Coup aRefaire;
        if (!peut_refaire()){
            System.err.println("Impossible de refaire le coup. Aucun coup n'a été annulé !");
            return null;
        }
        switch (dernierCoupJoue) {
            case (1):
                if (coupIndexL2 == coups_joueur2.size()) {
                    System.err.println("Impossible de refaire le coup. Aucun coup n'a été annulé sur le joueur 2 !");
                    return null;
                } else {
                    aRefaire = coups_joueur2.get(coupIndexL2);
                    coupIndexL2++;
                    dernierCoupJoue = 2;
                }
                break;
            case (2):
                if (coupIndexL1 == coups_joueur1.size()) {
                    System.err.println("Impossible de refaire le coup. Aucun coup n'a été annulé sur le joueur 1 !");
                    return null;
                } else {
                    aRefaire = coups_joueur1.get(coupIndexL1);
                    coupIndexL1++;
                    dernierCoupJoue = 1;
                }
                break;
            default:
                System.err.println("Dernier coup joué est en dehors des valeurs définies... " + dernierCoupJoue);
                return null;
        }
        return aRefaire;
    }

    
    void supprimer_suite_coup(){
        if (coups_joueur1.size() > coupIndexL1) {
            coups_joueur1.subList(coupIndexL1, coups_joueur1.size()).clear();
        }
        if (coups_joueur2.size() > coupIndexL2) {
            coups_joueur2.subList(coupIndexL2, coups_joueur2.size()).clear();
        }
    }
}
