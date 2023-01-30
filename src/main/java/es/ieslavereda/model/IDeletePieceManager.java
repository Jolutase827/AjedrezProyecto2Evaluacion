package es.ieslavereda.model;

public interface IDeletePieceManager {

    void addPiece(Piece p);

    int count(Piece.Type pt);

    Piece removeLast();
}
