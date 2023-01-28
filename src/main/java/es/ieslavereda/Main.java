package es.ieslavereda;

import es.ieslavereda.TAD.ListCoordinate;
import es.ieslavereda.model.Cordenada;
import es.ieslavereda.model.Piece;
import es.ieslavereda.model.Tablero;

public class Main {
    public static void main(String[] args) {
        Tablero t = new Tablero();

        t.placePieces();
        System.out.println(t);
        ListCoordinate listCoordinate = t.getCelda(new Cordenada('E',3)).getPiece().getNextMovements();
        t.hightlight(listCoordinate);
        System.out.println(t);
//        t.getCelda(new Cordenada('E',2)).getPiece().moveTo(new Cordenada('E',3));
//        System.out.println(t);
    }
}