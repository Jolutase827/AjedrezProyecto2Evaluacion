package es.ieslavereda.model;

import java.io.Serializable;
import java.util.Set;

/**
 * @author José Luis Tárraga, Feat Joaquin
 */
public abstract class Queen extends Piece implements Serializable {
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
