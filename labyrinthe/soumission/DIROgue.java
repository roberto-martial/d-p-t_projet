package soumission;

import code_squelette.Aventure;
import code_squelette.Exterieur;
import code_squelette.Piece;
import code_squelette.RencontreType;
import rencontres.*;

import java.util.*;

public class DIROgue {

	public static void main(String[] args) {
		MonLabyrinthe b = new MonLabyrinthe(50);
		Aventure a = new MonAventure(b);

		String rapport = genererRapport(a);
		System.out.println(rapport);

		String scenario = genererScenario(a);
		System.out.println(scenario);
	}

	/***
	 * Génère un rapport détaillé de l'aventure, incluant les pièces, leurs connexions
	 * et les rencontres dans le labyrinthe.
	 *
	 * @param a L'instance de l'aventure contenant le labyrinthe.
	 * @return Un rapport en format texte avec les détails des pièces et des connexions.
	 ***/

	public static String genererRapport(Aventure a) {
		MonAventure b = (MonAventure) a;
		String rapport = "";
		int compteur = 0;
		Piece[] pieces_tab = new Piece[50];
		Piece c = null;
		Piece k = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez écrire votre rapport (tapez 'FIN' pour terminer) :");
		String mot = sc.nextLine();
		boolean modeCorridors = false;
		while (!mot.equals("FIN")) {
			if (!modeCorridors) {
				String[] parts = mot.split(" "); // Divise l'entrée en mots

				if (parts[0].equalsIgnoreCase("piece")) {
					compteur++;
					int f = Integer.parseInt(parts[1]);
					String rencontre = parts[2]; //

					RencontreType rencont;
					if (rencontre.equalsIgnoreCase("monstre")) {
						rencont = RencontreType.MONSTRE;
					} else if (rencontre.equalsIgnoreCase("boss")) {
						rencont = RencontreType.BOSS;
					} else if (rencontre.equalsIgnoreCase("tresor")) {
						rencont = RencontreType.TRESOR;
					} else {
						rencont = RencontreType.RIEN;
					}


					// Création de la pièce et ajout dans le tableau
					Piece piece = new Piece(f, rencont);
					pieces_tab[compteur - 1] = piece;
				}
			}else {
				String[] parts = mot.split(" ");
				int f = Integer.parseInt(parts[1]);
				int r = Integer.parseInt(parts[2]);

// Rechercher les pièces correspondant aux IDs
				for (int i = 0; i < compteur; i++) {
					if (pieces_tab[i].getID() == f) {
						c = pieces_tab[i];
						break;
					}
				}
				for (int i = 0; i < compteur; i++) {
					if (pieces_tab[i].getID() == r) {
						k = pieces_tab[i];
						break;
					}
				}

// Ajouter le corridor si les deux pièces existent
				if (f != 0 && r != 0 ) {
					b.monLabyrin.ajouteCorridor(c, k);
				} else if (f != 0 && r == 0) {
					// Gestion de l'entrée si f est différent de 0 et r est 0
					if (c != null) {
						Exterieur s = Exterieur.getExterieur();
						b.monLabyrin.ajouteEntree(s, c);
					} else {
						System.out.println("Erreur : la pièce avec ID " + f + " n'existe pas.");
					}
				} else if (f == 0 && r != 0) {
					// Gestion de l'entrée si f est 0 et r est différent de 0
					if (k != null) {
						Exterieur s = Exterieur.getExterieur();
						b.monLabyrin.ajouteEntree(s, k);
					} else {
						System.out.println("Erreur : la pièce avec ID " + r + " n'existe pas.");
					}
				} else {
					System.out.println("Erreur : l'une des pièces spécifiées n'existe pas.");
				}
			}

			mot = sc.nextLine();
			if(mot.equals("CORRIDORS")){
				modeCorridors = true;
				mot = sc.nextLine();
			}
		}
		// Met à jour le nombre de pièces
		b.monLabyrin.nombrePieces = compteur+1;
		b.monLabyrin.pieces[0] = Exterieur.getExterieur();
		for (int i = 0; i < compteur; i++) {
			b.monLabyrin.pieces[i+1] = pieces_tab[i];
		}
		int r =compteur+1;
		// Génération du rapport
		rapport = "Rapport :\n" + "Donjon avec " + r + " pièces\n";
		rapport += "<" + b.monLabyrin.pieces[0].getID() + "-" + b.monLabyrin.pieces[0].getRencontreType() + "> : [";
		boolean firstAdjs = true;
		for (int j = 0; j < 8; j++) {
			Piece adjPiece = b.monLabyrin.tableauListeAjacence[0][j];

			if (adjPiece == null) break;

			// Ajouter une virgule si ce n'est pas la première pièce adjacente
			if (!firstAdjs) {
				rapport += ", ";
			} else {
				firstAdjs = false;
			}
			// Ajouter la pièce adjacente au rapport
			rapport += "<" + adjPiece.getID() + "-" + adjPiece.getRencontreType() + ">";
		}
		rapport += "]\n";
// Parcourir toutes les pièces
		for (int i = 0; i < compteur; i++) {
			Piece piece = pieces_tab[i]; // Récupérer la pièce courante
			int pieceId = piece.getID();
			rapport += "<" + pieceId + "-" + piece.getRencontreType() + "> : [";

			boolean firstAdj = true; // Pour gérer la virgule entre les pièces adjacentes

			// Parcourir les adjacences de la pièce courante
			for (int j = 0; j < 8; j++) {
				Piece adjPiece = b.monLabyrin.tableauListeAjacence[pieceId][j];

				if (adjPiece == null) break;

				// Ajouter une virgule si ce n'est pas la première pièce adjacente
				if (!firstAdj) {
					rapport += ", ";
				} else {
					firstAdj = false;
				}

				// Ajouter la pièce adjacente au rapport
				rapport += "<" + adjPiece.getID() + "-" + adjPiece.getRencontreType() + ">";
			}

			rapport += "]\n";
		}
			// Vérifier les types de pièces spéciales et ajouter des informations au rapport
			boolean contientMonstre = false;
			boolean contientBoss = false;
			int compteurTresor = 0;

			for (int i = 0; i < compteur; i++) {
				Piece piece = pieces_tab[i];  // ici j'ai preferé utilser les switch case car avec les for ce serait trop long j'ai mis une version dans le fichier word
				switch (piece.getRencontreType()) {
					case MONSTRE:
						contientMonstre = true;
						break;
					case BOSS:
						contientBoss = true;
						break;
					case TRESOR:
						compteurTresor++;
						break;
				}
			}

// Ajoutons  les informations sur les pièces spéciales
			if (contientMonstre) {
				rapport += "//Non pacifique\n";
			}
			if (contientBoss) {
				rapport += "Contient un boss\n";
			}
			rapport += "Contient " + compteurTresor + " trésors\n";

//ici roberto on va mettre le chemin vers le boss
		Piece[] cheminVersBoss = b.cheminJusquAuBoss();
		rapport += "Chemin jusqu’au boss :\n";

		if (cheminVersBoss.length > 0) {
			for (Piece p : cheminVersBoss) {
				if (p != null) {
					rapport += "<" + p.getID() + "-" + p.getRencontreType() + "> \n";
				}
			}
		} else {
			rapport += "Aucun chemin trouvé vers le boss.\n";
		}

		return rapport;
	}

	/***
	 * Génère un scénario pour l'aventure en décrivant chaque rencontre sur le
	 * chemin jusqu'au boss.
	 *
	 * @param a L'instance de l'aventure contenant le labyrinthe.
	 * @return Une description des rencontres sur le chemin jusqu'au boss.
	 ***/
	public static String genererScenario(Aventure a) {
		MonAventure b = (MonAventure) a;
		Piece[] chemin = b.cheminJusquAuBoss();
		String scenario = "Scenario :\n";

		for (Piece piece : chemin) {
			if (piece == null) continue;

			RencontreType rencontreType = piece.getRencontreType();
			String rencontreDescription;

			if (rencontreType == RencontreType.RIEN) {
				rencontreDescription = "Un moment pacifique.";
			} else if (rencontreType == RencontreType.MONSTRE) {
				Monstres monstre;
				int randomInt = new Random().nextInt(3);

				if (randomInt == 0) {
					monstre = new Gobelin();
				} else if (randomInt == 1) {
					monstre = new Orque();
				} else if (randomInt == 2) {
					monstre = new Gargouille();
				} else {
					throw new IllegalStateException("Unexpected value");
				}

				rencontreDescription = monstre.rencontrer();
			} else if (rencontreType == RencontreType.TRESOR) {
				Trésor tresor;
				int randomInt = new Random().nextInt(3);

				if (randomInt == 0) {
					tresor = new SacDeButin();
				} else if (randomInt == 1) {
					tresor = new Potion();
				} else if (randomInt == 2) {
					tresor = new ArtefactMagique();
				} else {
					throw new IllegalStateException("Unexpected value");
				}

				rencontreDescription = tresor.rencontrer();
			} else if (rencontreType == RencontreType.BOSS) {
				Boss boss = new Boss();
				rencontreDescription = boss.rencontrer();
			} else {
				throw new IllegalArgumentException("Type de rencontre inconnu");
			}

			scenario += rencontreDescription + "\n";

		}
		return scenario;
	}
}

