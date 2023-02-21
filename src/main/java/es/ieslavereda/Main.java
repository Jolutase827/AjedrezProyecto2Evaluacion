package es.ieslavereda;

import es.ieslavereda.model.Cordenada;
import es.ieslavereda.model.Tablero;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Tablero t = new Tablero();

        t.placePieces();
        System.out.println(t);
        Set<Cordenada> listCoordinate = t.getCelda(new Cordenada('E',3)).getPiece().getNextMovements();
        t.hightlight(listCoordinate);
        System.out.println(t);
//        t.getCelda(new Cordenada('E',2)).getPiece().moveTo(new Cordenada('E',3));
//        System.out.println(t);
    }
}