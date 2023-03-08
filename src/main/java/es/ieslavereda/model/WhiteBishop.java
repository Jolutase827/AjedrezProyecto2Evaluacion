package es.ieslavereda.model;

import java.io.Serializable;

/**
 * @author José Luis Tárraga, Feat Joaquin
 */
public final class WhiteBishop extends Bishop implements Serializable {
    public WhiteBishop(Celda celda){
        super(Type.WHITE_BISHOP,celda);
    }

}
