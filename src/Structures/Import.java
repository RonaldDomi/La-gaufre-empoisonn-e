package Structures;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
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
	
	Jeu lire_fichier() {
		Jeu jeu = new Jeu();
		
		jeu.plateau = new Plateau(scanner.nextLine());
		
		String ans = "";
		for (int i = 0; i < 4; i++) {
			ans += scanner.nextLine();
		}
		jeu.historique = new Historique(ans);
		
		return jeu;
	}
}