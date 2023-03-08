package es.ieslavereda.model;

import java.io.Serializable;

/**
 * @author José Luis Tárraga, Feat Joaquin
 */
public final class WhiteKnight extends Knight implements Serializable {
    public WhiteKnight(Celda celda){
        super(Type.WHITE_KNIGHT,celda);
    }
}
