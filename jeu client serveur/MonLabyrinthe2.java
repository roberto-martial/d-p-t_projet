package dirogue.example;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import dirogue.example.code_squelette.*;

public class MonLabyrinthe2 implements Labyrinthe, Serializable {
    private List<Piece> pieces;
    private Map<Integer, List<Integer>> adjList;

    public MonLabyrinthe2() {
        pieces = new ArrayList<>();
        adjList = new HashMap<>();
    }

    public Piece[] getPieces() {
        return pieces.toArray(new Piece[0]);
    }

    public int nombreDePieces() {
        return pieces.size();
    }

    public void ajouteEntree(Exterieur out, Piece e) {
        pieces.add(out);
        pieces.add(e);
        addEdge(out, e);
    }

    public void ajouteCorridor(Piece e1, Piece e2) {
        if (getPieceByID(e1.getID()) == null)
            pieces.add(e1);

        if (getPieceByID(e2.getID()) == null)
            pieces.add(e2);

        addEdge(e1, e2);
    }

    /**
     * Ajoute un corridor entre deux pièces spécifiées par leurs identifiants.
     *
     * Cette méthode recherche les pièces correspondant aux identifiants fournis, et
     * si elles existent, elle crée un corridor entre elles. Si l'une des pièces
     * n'existe pas, une exception {@link PieceNotFoundException} est lancée.
     *
     * @param e1ID L'identifiant de la première pièce.
     * @param e2ID L'identifiant de la deuxième pièce.
     * @throws PieceNotFoundException Si l'une des pièces spécifiées n'existe pas.
     */

    public void ajouteCorridor(int e1ID, int e2ID) throws PieceNotFoundException {
        Piece p1 = getPieceByID(e1ID);
        Piece p2 = getPieceByID(e2ID);

        if (p1 == null || p2 == null) {
            throw new PieceNotFoundException("L'une des pièces spécifiées n'existe pas.");
        }
        addEdge(p1, p2);
    }

    public int ajoutePiece(Piece e) {
        if (!pieces.contains(e)) pieces.add(e);
        return pieces.indexOf(e);
    }

    public boolean existeCorridorEntre(Piece e1, Piece e2) {
        return adjList.containsKey(e1.getID()) && adjList.get(e1.getID()).contains(e2.getID());
    }

    /**
     * Récupère les pièces connectées à une pièce spécifiée.
     *
     * Cette méthode utilise l'identifiant de la pièce spécifiée pour récupérer
     * les pièces qui sont directement connectées à celle-ci via des corridors.
     * Elle renvoie un tableau des pièces connectées. Si la pièce spécifiée
     * n'a pas de voisins, un tableau vide est retourné.
     *
     * @param e La pièce pour laquelle on cherche les pièces connectées.
     * @return Un tableau de {@link Piece} représentant les pièces connectées à la pièce spécifiée.
     *         Si aucune pièce n'est connectée, un tableau vide est retourné.
     */

    public Piece[] getPiecesConnectees(Piece e) {
        List<Integer> voisins = adjList.get(e.getID());
        if (voisins == null) return new Piece[0];

        List<Piece> piecesConnectees = new ArrayList<>();
        for (Integer voisinID : voisins) {
            piecesConnectees.add(getPieceByID(voisinID));
        }

        return piecesConnectees.toArray(new Piece[0]);
    }

    private void addEdge(Piece e1, Piece e2) {
        adjList.computeIfAbsent(e1.getID(), k -> new ArrayList<>()).add(e2.getID());
        adjList.computeIfAbsent(e2.getID(), k -> new ArrayList<>()).add(e1.getID());
    }

    private Piece getPieceByID(int ID) {
        for (Piece piece : pieces) {
            if (piece.getID() == ID) return piece;
        }
        return null;
    }
}
