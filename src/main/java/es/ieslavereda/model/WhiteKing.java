package es.ieslavereda.model;

import java.io.Serializable;

/**
 * @author José Luis Tárraga, Feat Joaquin
 */
public final class WhiteKing extends King implements Serializable {

    /**
     * Constructor WhiteKing
     * @param celda Celda en la que va a ir la pieza
     */
    public WhiteKing(Celda celda){
        super(Type.WHITE_KING,celda);
    }
}
