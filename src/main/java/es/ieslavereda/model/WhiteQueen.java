package es.ieslavereda.model;

import java.io.Serializable;

/**
 * @author José Luis Tárraga, Feat Joaquin
 */
public final class WhiteQueen extends Queen implements Serializable {
    /**
     * Constructor WhiteQueen
     * @param celda Celda en la que va a ir la pieza
     */
    public WhiteQueen(Celda celda){
        super(Type.WHITE_QUEEN,celda);
    }
}
