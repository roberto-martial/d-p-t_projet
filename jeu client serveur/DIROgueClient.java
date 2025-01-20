package dirogue.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Classe représentant un client pour l'application DIROgue.
 * Ce client se connecte à un serveur spécifique et peut envoyer des commandes
 * pour charger, sauvegarder des fichiers ou quitter l'application.
 *
 * Le client se connecte au serveur via un socket et interagit avec l'utilisateur
 * en attendant des commandes. Il gère les commandes suivantes :
 * - "load" : Charge un fichier et l'envoie au serveur.
 * - "save" : Sauvegarde un rapport sur le serveur à l'emplacement spécifié.
 * - "exit" : Ferme la connexion avec le serveur et quitte l'application.
 *
 * Les commandes envoyées sont accompagnées des informations nécessaires
 * telles que les chemins des fichiers pour les commandes "load" et "save".
 *
 * L'application attend et traite les commandes jusqu'à ce que l'utilisateur choisisse de quitter.
 */


public class DIROgueClient {

	/**
	 * Méthode principale qui initialise le client, se connecte au serveur et gère les commandes de l'utilisateur.
	 *
	 * Le client se connecte au serveur à l'adresse spécifiée et envoie des commandes
	 * telles que "load", "save" et "exit". Il attend les réponses du serveur et interagit
	 * avec l'utilisateur pour obtenir les informations nécessaires, telles que les chemins de fichiers.
	 *
	 * Les étapes suivantes sont exécutées :
	 * - Connexion au serveur via un socket.
	 * - Envoi de la commande "load" suivie du contenu du fichier spécifié.
	 * - Envoi de la commande "save" suivie du chemin de sauvegarde pour le rapport.
	 * - Fermeture de la connexion avec le serveur lorsque la commande "exit" est reçue.
	 *
	 * @param args Les arguments de ligne de commande (non utilisés dans cette version).
	 */
	public static void main(String[] args) {
		String serverAddress = "127.0.0.1";
		int serverPort = 1370;

		Socket socket = null;
		PrintWriter out = null;
		Scanner scanner = new Scanner(System.in);

		try {


			socket = new Socket(serverAddress, serverPort);
			out = new PrintWriter(socket.getOutputStream(), true);
			System.out.println("Connecté au serveur.");

			String input;
			while (true) {
				System.out.println("Entrer une commande (load, save, exit):");
				input = scanner.nextLine().trim();

				if (input.equals("load")) {
					System.out.println("Entrez le chemin du fichier que vous souhaitez charger :");
					while (true) {
						String chemin = scanner.nextLine().trim();
						try {
							FileReader fr = new FileReader(chemin);
							BufferedReader reader = new BufferedReader(fr);
							String s;

							out.println("load " + chemin);

							// Envoi du contenu du fichier
							while ((s = reader.readLine()) != null) {
								out.println(s);
							}
							out.println("END");
							reader.close();

							break;
						} catch (IOException ex) {
							System.out.println("Erreur à l'ouverture du fichier. Réessayez.");
						}
					}
				} else if (input.equals("save")) {
					System.out.println("Entrez le chemin où vous voulez sauvegarder le rapport :");
					var reportPath = scanner.nextLine().trim();
					out.println(input + " " + reportPath);
				} else if (input.equals("exit")) {
					out.println(input);
					break;
				} else {
					System.out.println("Commande non valide. Veuillez entrer 'load', 'save' ou 'exit'.");
				}
			}

		} catch (IOException e) {
			System.out.println("Erreur lors de la connexion au serveur : " + e.getMessage());
			e.printStackTrace();
		} finally {
			scanner.close();
			if (out != null) {
				out.close();
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
