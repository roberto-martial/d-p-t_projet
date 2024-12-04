package soumission;

import code_squelette.Aventure;
import code_squelette.Labyrinthe;
import code_squelette.Piece;
import code_squelette.RencontreType;


public class MonAventure extends Aventure { //TODO: extends ? implements?
    protected MonLabyrinthe monLabyrin;

    public MonAventure(MonLabyrinthe monLabyrin) {
        super(monLabyrin);
        this.monLabyrin = monLabyrin;
        }
    public boolean estPacifique() {
        Boolean t = true;
        for (int i = 0; i < monLabyrin.pieces.length; i++) {
            if (monLabyrin.pieces[i] != null) {
                if (monLabyrin.pieces[i].getRencontreType() == RencontreType.BOSS || monLabyrin.pieces[i].getRencontreType() == RencontreType.MONSTRE) {
                    t = false;
                    break;
                } else {
                    t = true;
                }
            } else {
                continue;
            }
        }
        return t;
    }



    public  boolean contientDuTresor(){
        Boolean t = true;
        for(int i =0; i<monLabyrin.pieces.length; i++){
            if(monLabyrin.pieces[i]!= null){
                if(monLabyrin.pieces[i].getRencontreType() == RencontreType.TRESOR){
                    t=true;
                    break;
                }else{
                    t=false;
                }
            }else{
                continue;
            }
        }
        return t;
    }


    public  int getTresorTotal() {
        int t = 0;
        for (int i = 0; i < monLabyrin.pieces.length; i++) {
            if (monLabyrin.pieces[i] != null) {
                if (monLabyrin.pieces[i].getRencontreType() == RencontreType.TRESOR) {
                    t += 1;
                }
            } else {
                continue;
            }
        }
        return t;
    }



    public  boolean contientBoss() {
        Boolean t = true;
        for (int i = 0; i < monLabyrin.pieces.length; i++) {
            if (monLabyrin.pieces[i] != null) {
                if (monLabyrin.pieces[i].getRencontreType() == RencontreType.BOSS) {
                    t = true;
                    break;
                } else {
                    t = false;
                }
            } else {
                continue;
            }
        }
        return t;
    }



    public Piece[] cheminJusquAuBoss() {
        Piece[] cheminVersBoss = new Piece[monLabyrin.pieces.length];
        int cheminIndex = 0;

        // Parcourir les pièces en ordre croissant des IDs
        for (int i = 0; i < monLabyrin.pieces.length; i++) {
            Piece piece = monLabyrin.pieces[i];
            //  ID non valide ou pièce manquante
            if (piece == null || piece.getID() != i) {
                System.out.println("ID manquant ou la piece n'existe pas ");
                return new Piece[0];
            }
            if(monLabyrin.existeCorridorEntre(piece,monLabyrin.pieces[i+1])){
                System.out.println("il n'existe pas  de chemin ");
                return new Piece[0];
            }
            // Ajouter la pièce actueL au chemin
            cheminVersBoss[cheminIndex++] = piece;

            // Si on trouve un Boss, le tableau final et le retour
            if (piece.getRencontreType().equals(RencontreType.BOSS)) {
                Piece[] cheminFinal = new Piece[cheminIndex];
                for (int j = 0; j < cheminIndex; j++) {
                    cheminFinal[j] = cheminVersBoss[j];
                }
                return cheminFinal;
            }
        }

        // Aucun Boss trouvé  donc retournons un tableau vide
        return new Piece[0];
    }

}
