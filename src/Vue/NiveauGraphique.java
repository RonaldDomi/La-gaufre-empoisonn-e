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
	JLabel nom1, nom2;

	static Font h1 = new Font("TimesRoman", Font.PLAIN, 20);
	static Font h1Bold = new Font("TimesRoman", Font.BOLD, 20);

	public NiveauGraphique(Jeu j, JLabel nom1, JLabel nom2) {

		this.nom1 = nom1;
		this.nom2 = nom2;
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
		for (int i=0; i<lignes; i++){
			for (int j=0; j<colonnes; j++){
				if (jeu.plateau().get_tableau(i, j).est_vide()){ //estVide()
					if (i == 0 && j == 0 )
						g.setColor(new Color(154,190,38));
					else
						g.setColor(new Color(238, 204, 147));
					g.fillRect(j * largeurCase, i * hauteurCase, largeurCase, hauteurCase);
				}
				else if (jeu.plateau().get_tableau(i, j).get_tour() == jeu.get_tour()-1 && jeu.get_joueur_courant() == 1){
					g.setColor(new Color(218, 97, 97, 64));
					g.fillRect(j*largeurCase, i*hauteurCase, largeurCase, hauteurCase);
				}
				else if (jeu.plateau().get_tableau(i, j).num_joueur()==-2 && jeu.get_joueur_courant() == 2){
					g.setColor(new Color(218, 97, 97/*, 64*/));
					g.fillRect(j*largeurCase, i*hauteurCase, largeurCase, hauteurCase);
				}
				else if (jeu.plateau().get_tableau(i, j).get_tour() == jeu.get_tour()-1){
					g.setColor(new Color(97, 126, 218, 64));
					g.fillRect(j*largeurCase, i*hauteurCase, largeurCase, hauteurCase);
				}
				else if (jeu.plateau().get_tableau(i, j).num_joueur()==-2){
					g.setColor(new Color(97, 126, 218/*, 64*/));
					g.fillRect(j*largeurCase, i*hauteurCase, largeurCase, hauteurCase);
				}
			}
		}


		//Joueur Courant
		if (jeu.get_joueur_courant() == 1){
//			nom1.setFont(h1Bold);
			nom1.setText("• Joueur 1 ");
//			nom2.setFont(h1);
			nom2.setText("  Joueur 2 ");
		}
		else{
//			nom2.setFont(h1Bold);
			nom1.setText("  Joueur 1 ");
//			nom1.setFont(h1);
			nom2.setText("• Joueur 2 ");
		}

		// Grille
		g.setColor(Color.BLACK);
		int k;
		for (int i=1; i<lignes;i++) {
			for (k = 0; (k<colonnes) && ((jeu.plateau().get_tableau(i, k).est_vide() || jeu.plateau().get_tableau(i, k).num_joueur()==-2)); k++);
			g.drawLine(0, i*hauteurCase, k*largeurCase, i*hauteurCase);
		}
		for (int i=1; i<colonnes;i++) {
			for (k = 0; k<lignes && (jeu.plateau().get_tableau(k, i).est_vide() || jeu.plateau().get_tableau(k, i).num_joueur()==-2); k++);
			g.drawLine(i*largeurCase, 0, i*largeurCase, k*hauteurCase);
		}

		g.setFont(new Font("TimesRoman", Font.PLAIN, largeur()/20));
		if (!jeu.en_cours())
			g.drawString("Le Joueur " + jeu.gagnant() + " a gagné !", largeur()/4, hauteur()/2);
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