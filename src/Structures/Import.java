package Structures;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Import {
	Scanner scanner;
	
	public Import(String pathname) {
		File importFile = new File(pathname);
		try {
			scanner = new Scanner(importFile);
		} catch (FileNotFoundException ignored) {
			System.err.println("Le fichier n'existe pas!");
		}
		
	}
	
	public Jeu lire_fichier() {
		Jeu jeu = new Jeu();

		jeu.plateau = new Plateau(scanner.nextLine());
		jeu.joueur_courant = 1;

		String ans = "";
		for (int i = 0; i < 4; i++) {
			ans += scanner.nextLine();
			ans += '\n';
		}
		jeu.historique = new Historique(ans);

		int i = 0;
		int j = 0;
		boolean tourJ1 = true;
		jeu.historique.dernierCoupJoue = 1;

		while(i < jeu.get_historique().coups_joueur1.size() || j < jeu.get_historique().coups_joueur2.size()){
			if (tourJ1){ // J1 joue
				if (i < jeu.get_historique().coupIndexL1) {
					jeu.hist_jouer_coup(jeu.get_historique().coups_joueur1.get(i));
					jeu.historique.dernierCoupJoue = 1;
				}
				else
					jeu.hist_jouer_coup_annule(jeu.get_historique().coups_joueur1.get(i));
				i++;
			}
			else { // J2 joue
				if (j < jeu.get_historique().coupIndexL2) {
					jeu.hist_jouer_coup(jeu.get_historique().coups_joueur2.get(j));
					jeu.historique.dernierCoupJoue = 2;
				}
				else
					jeu.hist_jouer_coup_annule(jeu.get_historique().coups_joueur2.get(j));
				j++;
			}
			tourJ1 = !tourJ1;
		}
		return jeu;
	}
}
