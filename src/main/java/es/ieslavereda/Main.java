package es.ieslavereda;

import es.ieslavereda.model.Cordenada;
import es.ieslavereda.model.Piece;

public class Main {
    public static void main(String[] args) {
        Cordenada c = new Cordenada('c',2);
        Piece piece = new Piece(Piece.Type.WHITE_KNIGHT);
        System.out.println(piece);
    }
}