package es.ieslavereda.model;

import java.io.Serializable;

/**
 * @author José Luis Tárraga, Feat Joaquin
 */
public final class BlackQueen extends Queen implements Serializable {
    public BlackQueen(Celda celda){
        super(Type.BLACK_QUEEN,celda);
    }
}
