package Vue;/*
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

import javax.swing.*;
import java.awt.*;

public class InterfaceGraphique implements Runnable {
	Jeu j;
	CollecteurEvenements control;

	InterfaceGraphique(Jeu jeu, CollecteurEvenements c) {
		j = jeu;
		control = c;
	}

	public static void demarrer(Jeu j, CollecteurEvenements control) {
		SwingUtilities.invokeLater(new InterfaceGraphique(j, control));
	}

	@Override
	public void run() {
		JFrame frame = new JFrame("Ma fenetre a moi");
		NiveauGraphique niv = new NiveauGraphique(j);
		niv.addMouseListener(new AdaptateurSouris(niv, control));
		frame.add(niv);
		Box barre = Box.createVerticalBox();
		Box barre2;

		barre.add(new JLabel("Au tour de"));
		barre.add(new JLabel("Joueur 1"));

		barre.add(Box.createGlue());

		barre2 = Box.createHorizontalBox();
		JButton butUndo = new JButton("Undo");
//		butUndo.addActionListener(); TODO
		JButton butRedo = new JButton("Redo");
//		butRedo.addActionListener(); TODO
		barre2.add(butUndo);
		barre2.add(butRedo);
		barre.add(barre2);

		barre.add(Box.createGlue());

		barre.add(new JLabel("Taille :"));

		JTextField userSizeRows = new JTextField("6");
		JTextField userSizeCols = new JTextField("8");
		userSizeRows.setMaximumSize(new Dimension(
				userSizeRows.getMaximumSize().width, userSizeRows.getMinimumSize().height));
		userSizeRows.addActionListener(new AdaptateurTaille(control, userSizeRows, userSizeCols));
		userSizeCols.setMaximumSize(new Dimension(
				userSizeCols.getMaximumSize().width, userSizeCols.getMinimumSize().height));
		userSizeCols.addActionListener(new AdaptateurTaille(control, userSizeRows, userSizeCols));

		barre2 = Box.createHorizontalBox();
		barre2.add(new JLabel("Lignes "));
		barre2.add(userSizeRows);
		barre.add(barre2);

		barre2 = Box.createHorizontalBox();
		barre2.add(new JLabel("Colonnes "));
		barre2.add(userSizeCols);
		barre.add(barre2);

		barre.add(Box.createGlue());

		for (int i=0; i<2; i++) {
			barre.add(new JLabel("Joueur " + (i+1)));
			JToggleButton but = new JToggleButton("IA");
			but.addActionListener(new AdaptateurJoueur(control, but, i));
			barre.add(but);
		}

		barre.add(Box.createGlue());
		frame.add(barre, BorderLayout.LINE_END);

		Timer chrono = new Timer( 16, new AdaptateurTemps(control));
		chrono.start();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setVisible(true);
	}
}
