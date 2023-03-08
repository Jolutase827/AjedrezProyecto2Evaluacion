package es.ieslavereda.model;

import java.io.Serializable;

/**
 * @author José Luis Tárraga, Feat Joaquin
 */
public final class WhiteBishop extends Bishop implements Serializable {
    /**
     * Constructor WhiteBishop
     * @param celda Celda en la que va a ir la pieza
     */
    public WhiteBishop(Celda celda){
        super(Type.WHITE_BISHOP,celda);
    }

}
