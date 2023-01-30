package es.ieslavereda.model;

import es.ieslavereda.TAD.ListPiece;

public class DeletePieceManagerTAD implements IDeletePieceManager{
    private ListPiece listPiece;

    public DeletePieceManagerTAD(){
        listPiece = new ListPiece();
    }



    @Override
    public void addPiece(Piece p){
        listPiece.addHead(p);
    }

    @Override
    public int count(Piece.Type pt){
        return listPiece.countTypeOfPieces(pt);
    }

    @Override
    public Piece removeLast(){
        return listPiece.remove(0);
    }
}
