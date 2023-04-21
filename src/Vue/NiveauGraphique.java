package Vue;
/*
 * Morpion pédagogique

 * Copyright (C) 2016 Guillaume Huard

 * Ce programme est libre, vous pouvez le redistribuer et/ou le
 * modifier selon les termes de la Licence Publique Générale GNU publiée par la
 * Free Software Foundation (version 2 ou bien toute autre version ultérieure
 * choisie par vous).

 * Ce programme est distribué car potentiellement utile, mais SANS
 * AUCUNE GARANTIE, ni explicite ni implicite, y compris les garanties de
 * commercialisation ou d'adaptation dans un but spécifique. Reportez-vous à la
 * Licence Publique Générale GNU pour plus de détails.

 * Vous devez avoir reçu une copie de la Licence Publique Générale
 * GNU en même temps que ce programme ; si ce n'est pas le cas, écrivez à la Free
 * Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307,
 * États-Unis.

 * Contact: Guillaume.Huard@imag.fr
 *          Laboratoire LIG
 *          700 avenue centrale
 *          Domaine universitaire
 *          38401 Saint Martin d'Hères
 */

import Structures.Jeu;
import Patterns.Observateur;

import javax.swing.*;
import java.awt.*;

public class NiveauGraphique extends JComponent implements Observateur {
	Jeu jeu;
	int largeurCase, hauteurCase;

	public NiveauGraphique(Jeu j) {
		jeu = j;
		jeu.ajouteObservateur(this);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D drawable = (Graphics2D) g;
        int lignes = jeu.plateau().get_nb_lignes();
        int colonnes = jeu.plateau().get_nb_colonnes();
        largeurCase = largeur() / colonnes;
        hauteurCase = hauteur() / lignes;

        g.clearRect(0, 0, largeur(), hauteur());
		g.setFont(new Font("TimesRoman", Font.PLAIN, largeur()/20));
        if (!jeu.en_cours())
			g.drawString("Le Joueur " + jeu.gagnant() + " a gagné !", largeur()/4, hauteur()/2);
		for (int i=0; i<lignes; i++){
			for (int j=0; j<colonnes; j++){
				if (jeu.plateau().get_tableau(i, j).est_vide()){ //estVide()
					g.setColor(new Color(238, 204, 147));
					g.fillRect(j*largeurCase, i*hauteurCase, largeurCase, hauteurCase);
				}
//				else if (jeu.valeur(i, j) != -1){ //J1
////					g.setColor(new Color(238, 204, 147, (jeu.tour/jeu.valeur(i, j))));
////					g.fillRect(j*largeurCase, i*hauteurCase, largeurCase, hauteurCase);
//				}
			}
		}

		g.setColor(new Color(154,190,38));
		g.fillRect(0, 0, largeurCase, hauteurCase);

		// Grille
		g.setColor(Color.BLACK);
		int k;
		for (int i=1; i<lignes;i++) {
			for (k = 0; (k<colonnes) && (jeu.plateau().get_tableau(i, k).est_vide()); k++);
			g.drawLine(0, i*hauteurCase, k*largeurCase, i*hauteurCase);
		}
		for (int i=1; i<colonnes;i++) {
			for (k = 0; k<lignes && jeu.plateau().get_tableau(k, i).est_vide(); k++);
			g.drawLine(i*largeurCase, 0, i*largeurCase, k*hauteurCase);
		}
	}

	int largeur() {
		return getWidth();
	}

	int hauteur() {
		return getHeight();
	}

	public int largeurCase() {
		return largeurCase;
	}

	public int hauteurCase() {
		return hauteurCase;
	}

	@Override
	public void miseAJour() {
		repaint();
	}
}