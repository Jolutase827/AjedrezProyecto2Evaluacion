package es.ieslavereda.model;

import es.ieslavereda.TAD.ListCoordinate;

public abstract class Queen extends Piece{
    private Cordenada[] cordenadas;

    public Queen(Type type, Celda celda){
        super(type,celda);
    }

    @Override
    public ListCoordinate getNextMovements(){
        ListCoordinate rookMovements = Rook.getNextMovementsAsRook(this);
        ListCoordinate bishopMovements = Bishop.getNextMovementsAsBishop(this);
        rookMovements.addAll(bishopMovements);
        return rookMovements;
    }
}
