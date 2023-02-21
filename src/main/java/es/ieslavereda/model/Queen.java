package es.ieslavereda.model;

import java.util.Set;

public abstract class Queen extends Piece{
    private Cordenada[] cordenadas;

    public Queen(Type type, Celda celda){
        super(type,celda);
    }

    @Override
    public Set<Cordenada> getNextMovements(){
        Set<Cordenada> rookMovements = Rook.getNextMovementsAsRook(this);
        Set<Cordenada> bishopMovements = Bishop.getNextMovementsAsBishop(this);
        rookMovements.addAll(bishopMovements);
        return rookMovements;
    }
}
