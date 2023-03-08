package es.ieslavereda.model;

import java.io.Serializable;

/**
 * @author José Luis Tárraga, Feat Joaquin
 */
public final class WhiteRook extends Rook implements Serializable {
    public WhiteRook(Celda celda){
        super(Piece.Type.WHITE_ROOK,celda);
    }
}
