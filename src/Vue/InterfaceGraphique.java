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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceGraphique implements Runnable {
	Jeu j;
	CollecteurEvenements control;

	static Font h1 = new Font("TimesRoman", Font.PLAIN, 20);
	static Font h2 = new Font("TimesRoman", Font.PLAIN, 15);

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
		JLabel label;

		label = new JLabel("Au tour de");
		label.setFont(h1);
		label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		barre.add(label);
		label = new JLabel("Joueur 1");
		label.setFont(h1);
		label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		barre.add(label);

		barre.add(Box.createGlue());

		barre2 = Box.createHorizontalBox();
		JButton butUndo = new JButton("Undo");
		ActionListener actionUndo = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				j.annuler_coup();
			}
		};
		butUndo.addActionListener(actionUndo);

		JButton butRedo = new JButton("Redo");
		ActionListener actionRedo = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				j.refaire_coup();
			}
		};
		butRedo.addActionListener(actionRedo);

		barre2.add(butUndo);
		barre2.add(butRedo);
		barre.add(barre2);

		barre.add(Box.createGlue());

		label = new JLabel("Taille :");
		label.setFont(h1);
		label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		barre.add(label);

		JTextField userSizeRows = new JTextField("6");
		JTextField userSizeCols = new JTextField("8");
		userSizeRows.setMaximumSize(new Dimension(
				userSizeRows.getMaximumSize().width, userSizeRows.getMinimumSize().height));
		userSizeRows.addActionListener(new AdaptateurTaille(control, userSizeRows, userSizeCols));
		userSizeCols.setMaximumSize(new Dimension(
				userSizeCols.getMaximumSize().width, userSizeCols.getMinimumSize().height));
		userSizeCols.addActionListener(new AdaptateurTaille(control, userSizeRows, userSizeCols));

		barre2 = Box.createHorizontalBox();
		label = new JLabel("Lignes");
		label.setFont(h2);
		label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		barre2.add(label);
		barre2.add(userSizeRows);
		barre.add(barre2);

		barre2 = Box.createHorizontalBox();
		label = new JLabel("Colonnes");
		label.setFont(h2);
		label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		barre2.add(label);
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
