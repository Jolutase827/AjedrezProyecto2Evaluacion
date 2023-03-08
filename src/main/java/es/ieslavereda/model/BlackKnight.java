package es.ieslavereda.model;

import java.io.Serializable;

/**
 * @author José Luis Tárraga, Feat Joaquin
 */
public final class BlackKnight extends Knight implements Serializable {
    public BlackKnight(Celda celda){
        super(Type.BLACK_KNIGHT,celda);
    }
}
