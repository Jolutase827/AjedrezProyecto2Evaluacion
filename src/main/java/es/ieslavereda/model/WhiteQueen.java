package es.ieslavereda.model;

import java.io.Serializable;

/**
 * @author José Luis Tárraga, Feat Joaquin
 */
public final class WhiteQueen extends Queen implements Serializable {
    public WhiteQueen(Celda celda){
        super(Type.WHITE_QUEEN,celda);
    }
}
