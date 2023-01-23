package es.ieslavereda;

import es.ieslavereda.model.Cordenada;
import es.ieslavereda.model.Piece;
import es.ieslavereda.model.Tablero;

public class Main {
    public static void main(String[] args) {
        Tablero t = new Tablero();

        t.placePieces();
        System.out.println(t);
        Cordenada[] cordenadas = t.getCelda(new Cordenada('B',4)).getPiece().getNextMovements();
        t.hightlight(cordenadas);
        System.out.println(t);

    }
}