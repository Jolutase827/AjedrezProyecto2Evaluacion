package es.ieslavereda.model;

import javafx.scene.control.Cell;

import static com.diogonunes.jcolor.Ansi.colorize;


public class Piece {
    private Type type;
    private Celda celda;

    public Piece(Type shape, Celda celda){
        this.type =shape;
        this.celda = celda;
    }

    public Celda getCelda(){
        return celda;
    }

    public Color getColor(){
        return type.color;
    }

    public void putInYourPlace()


    @Override
    public String toString(){
        return colorize(""+ type.getShape(), type.color.getAttribute());
    }


    public enum Type {
        BLACK_PAWN('♟',Color.BLACK),
        BLACK_ROOK('♜',Color.BLACK),
        BLACK_BISHOP('♝',Color.BLACK),
        BLACK_KING('♚',Color.BLACK),
        BLACK_QUEEN('♛',Color.BLACK),
        BLACK_KNIGHT('♞',Color.BLACK),

        WHITE_PAWN('♟',Color.WHITE),
        WHITE_ROOK('♜',Color.WHITE),
        WHITE_BISHOP('♝',Color.WHITE),
        WHITE_KING('♚',Color.WHITE),
        WHITE_QUEEN('♛',Color.WHITE),
        WHITE_KNIGHT('♞',Color.WHITE);

        char shape;
        Color color;
        Type(char shape, Color color){
            this.shape = shape;
            this.color = color;
        }

        public char getShape(){
            return shape;
        }
    }



}


