package es.ieslavereda.model;

import java.io.Serializable;

/**
 * @author José Luis Tárraga, Feat Joaquin
 */
public final class BlackKing extends King implements Serializable {
    public BlackKing(Celda celda){
        super(Type.BLACK_KING,celda);
    }
}
