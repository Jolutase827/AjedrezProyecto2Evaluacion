package es.ieslavereda.model;

import es.ieslavereda.Tool;

public abstract class Queen extends Piece{
    private Cordenada[] cordenadas;

    public Queen(Type type, Celda celda){
        super(type,celda);
    }

    @Override
    public Cordenada[] getNextMovements(){
        Cordenada[] rookMovements = Rook.getNextMovementsAsRook(this);
        Cordenada[] bishopMovements = Bishop.getNextMovementsAsBishop(this);

        return Tool.merge(rookMovements,bishopMovements);
    }
}
