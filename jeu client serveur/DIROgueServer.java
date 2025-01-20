package dirogue.example;

import java.io.IOException;

import dirogue.example.code_squelette.*;

/**
 * Classe représentant le serveur pour l'application DIROgue.
 * Ce serveur écoute les commandes provenant des clients pour gérer la création et la modification du labyrinthe
 * ainsi que l'aventure associée. Les commandes incluent la création de pièces, l'ajout de corridors,
 * la fin de la création du labyrinthe, et la sauvegarde du rapport d'aventure.
 *
 * Le serveur attend les commandes sur un port spécifique et agit en fonction des entrées reçues.
 * Il gère les éléments du labyrinthe à travers des objets tels que `MonLabyrinthe2`, `MonAventure`, et `Piece`.
 */
public class DIROgueServer {
	static boolean exterieurAjoute = false;
	static MonLabyrinthe2 l = new MonLabyrinthe2();
	static MonAventure m ;

	/**
	 * Méthode principale qui initialise le serveur, écoute les commandes et agit en conséquence.
	 * Chaque commande reçue par le serveur est traitée par un gestionnaire d'événements.
	 *
	 * Les commandes gérées incluent :
	 * - "piece" : Ajoute une nouvelle pièce au labyrinthe.
	 * - "CORRIDORS" : Traite les corridors entre les pièces (le traitement est encore à implémenter).
	 * - "corridor" : Ajoute un corridor entre deux pièces ou une entrée extérieure.
	 * - "FIN" : Marque la fin de la création du labyrinthe (fonctionnalité en développement).
	 * - "save" : Sauvegarde le rapport d'aventure dans un fichier.
	 *
	 * @param args Les arguments de ligne de commande (non utilisés dans cette version).
	 * @throws IOException Si une erreur se produit lors de la gestion des connexions ou des entrées/sorties.
	 */
	public static void main(String[] args) throws IOException {
		Server s = new Server(1370);
		try {
			m = new MonAventure(l);
			s.addEventHandler((cmd, cmdArgs) -> {
				if (cmd.equals("piece")) {
					if (cmdArgs.length == 2) {
						int id = Integer.parseInt(cmdArgs[0]);
						RencontreType type = RencontreType.valueOf(cmdArgs[1].toUpperCase());
						if (!exterieurAjoute) {
							l.ajouteEntree(Exterieur.getExterieur(), new Piece(id, type));
							exterieurAjoute = true;
						} else {
							l.ajoutePiece(new Piece(id, type));
						}
					}
				}
			});


			s.addEventHandler((cmd, cmdArgs) -> {
				if (cmd.equals("CORRIDORS")) {
					System.out.println("Commande CORRIDORS reçue...");
				}
			});

			s.addEventHandler((cmd, cmdArgs) -> {
				if (cmd.equals("corridor")) {
						int id = Integer.parseInt(cmdArgs[0]);
						int id2 = Integer.parseInt(cmdArgs[1]);
						if(id!=0 || id2!=0) {
							l.ajouteCorridor(l.getPieces()[id], l.getPieces()[id2]);
						}else if(id==0) {
							l.ajouteEntree((Exterieur) l.getPieces()[0], l.getPieces()[id2]);
						}else{
							l.ajouteEntree((Exterieur) l.getPieces()[0], l.getPieces()[id]);
						}
				}
			});

			s.addEventHandler((cmd, cmdArgs) -> {
				if (cmd.equals("FIN")) {
					System.out.println("Commande FIN reçue...");
				}
			});
			s.addEventHandler((cmd, cmdArgs) -> {
				if (cmd.equals("save")) {
						m.sauvegarderRapport(cmdArgs[0]);
				}
			});
			s.listen();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			s.finalize();
		}
	}
}
