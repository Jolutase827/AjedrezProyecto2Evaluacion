package es.ieslavereda.model;

import es.ieslavereda.TAD.ListPiece;

import java.util.List;

public class DeletePieceManager implements IDeletePieceManager{
    private ListPiece listPiece;

    public DeletePieceManager(){
        listPiece = new ListPiece();
    }

    public void addPiece(Piece p){
        listPiece.addHead(p);
    }

    public int count(Piece.Type pt){
        return listPiece.countTypeOfPieces(pt);
    }

    public Piece removeLast(){
        return listPiece.remove(0);
    }
}
