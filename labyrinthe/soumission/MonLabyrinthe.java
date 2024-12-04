package soumission;


import java.util.*;
import code_squelette.Exterieur;
import code_squelette.Labyrinthe;
import code_squelette.Piece;
import code_squelette.RencontreType;


public class MonLabyrinthe implements Labyrinthe { // Utilise 'implements' si c'est une interface
    protected int nombrePieces; // Nombre actuel de pièces
    int max_pieces = 50; // Limite maximale de pièces
    Random random = new Random();
    RencontreType[] types = RencontreType.values(); // Type de rencontres
    protected Piece[][] tableauListeAjacence ;
    // Tableau pour stocker les pièces
    Piece[] pieces;

    // Constructeur
    public MonLabyrinthe(int nombrePieces) {
        tableauListeAjacence = new Piece[max_pieces][8];
        this.nombrePieces = nombrePieces;
        if (nombrePieces > max_pieces) {
            this.nombrePieces = max_pieces; // Limite le nombre de pièces à max_pieces
        }
        pieces = new Piece[this.nombrePieces]; // Initialise le tableau de pièces
    }

    /**
     * on deternime d'abord le nombre de piece de notre labyrinthe de facon aleatoire
     * ce nombre ne peut depasser 50 on cree un tabeau de taille 50
     * @return
     */
    public Piece[] getPieces() {

        return pieces;
    }

    public int nombreDePieces() {
        return nombrePieces;
    }



    public void ajouteEntree(Exterieur out, Piece e) {
        // Piece[] listAjacenceOut = new Piece[9];
        //listAjacenceOut[0] = pieces[0];// ici on fait de tel sorte que pour chaque listes d'ajacente son tableau commence par la piece qui caraterise la liste

        if (pieces[0]==null) {
            pieces[0] = out.getExterieur();
        }
       /// int compteur = 0;

        if (e == null) {
            e = new Piece(1 + random.nextInt(max_pieces), types[random.nextInt(types.length)]);
            pieces[e.getID()] = e;
        }
        for(int i=0; i<8; i++) {
            if(tableauListeAjacence[0][i]==null) {
                tableauListeAjacence[0][i] = e;
                break;
            }
        }
        for(int i=0; i<8; i++) {
            if (tableauListeAjacence[e.getID()][i] == null) {
                tableauListeAjacence[e.getID()][i] = out.getExterieur();
                break;
            }
        }
        //System.out.println("Ajout corridor entre : O  et " + e.getID()); ici quand je compile ca m'aide
        }



    public void ajouteCorridor(Piece e1, Piece e2) {
        // Vérifiez que les pièces ne sont pas nulles
        if (e1 == null || e2 == null) {
            System.out.println("Erreur : Les pièces doivent être non nulles.");
            return;
        }

        // Ajoutez les pièces au tableau des pièces si elles ne sont pas déjà présentes
        if (pieces[e1.getID()] == null) pieces[e1.getID()] = e1;
        if (pieces[e2.getID()] == null) pieces[e2.getID()] = e2;

        int id1 = e1.getID();
        int id2 = e2.getID();

        // Ajout de e2 dans la liste d'adjacence de e1
        for (int i = 0; i < 8; i++) {
            if (tableauListeAjacence[id1][i] == null) {
                tableauListeAjacence[id1][i] = e2;
                break;
            }
        }

        // Ajout de e1 dans la liste d'adjacence de e2
        for (int i = 0; i < 8; i++) {
            if (tableauListeAjacence[id2][i] == null) {
                tableauListeAjacence[id2][i] = e1;
                break;
            }
        }
        //System.out.println("Ajout corridor entre : " + e1.getID() + " et " + e2.getID()); ici quand je compile ca m'aide
    }


    public boolean existeCorridorEntre(Piece e1, Piece e2) {
        int i = e1.getID();
        for (int j = 0; j < 8; j++) {
            if (tableauListeAjacence[i][j] != null && tableauListeAjacence[i][j].equals(e2)) {
                return true;
            }
        }
        return false;
    }


    public Piece[] getPiecesConnectees(Piece e) {
        int u = e.getID();
        int connectionCount = 0;

        // Compter les pièces connectées
        for (int j = 0; j < 8; j++) {
            if (tableauListeAjacence[u][j] != null) {
                connectionCount++;
            }
        }

        // Créer un tableau pour stocker les pièces connectées
        Piece[] connectedPieces = new Piece[connectionCount];
        int index = 0;

        // Remplir le tableau avec les pièces connectées
        for (int j = 0; j < 8; j++) {
            if (tableauListeAjacence[u][j] != null) {
                connectedPieces[index] = tableauListeAjacence[u][j];
                index++;
            }
        }
        return connectedPieces;
    }

}
