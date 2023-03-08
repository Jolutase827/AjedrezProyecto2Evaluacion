package es.ieslavereda.model;

import java.io.Serializable;

/**
 * @author José Luis Tárraga, Feat Joaquin
 */
public final class BlackBishop extends Bishop implements Serializable {

    public BlackBishop(Celda celda){
        super(Type.BLACK_BISHOP,celda);
    }
}
