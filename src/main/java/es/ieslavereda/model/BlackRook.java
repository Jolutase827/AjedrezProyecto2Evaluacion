package es.ieslavereda.model;

import java.io.Serializable;

/**
 * @author José Luis Tárraga, Feat Joaquin
 */
public final class BlackRook extends Rook implements Serializable {
    public BlackRook(Celda celda){
        super(Type.BLACK_ROOK,celda);
    }
}
