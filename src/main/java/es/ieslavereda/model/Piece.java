package es.ieslavereda.model;

import javafx.scene.control.Cell;

import static com.diogonunes.jcolor.Ansi.colorize;


public abstract class Piece {
    private Type type;
    private Celda celda;

    public Piece(Type shape, Celda celda){
        this.type =shape;
        this.celda = celda;
        putInYourPlace();
    }

    public boolean moveTo(Cordenada cordenada){
        Tablero t = getCelda().getTablero();
        if (t.getCelda(cordenada)!=null){
            getCelda().setPiece(null);
            Celda celda = t.getCelda(cordenada);
            celda.setPiece(this);
            this.celda = celda;
            return true;
        }
        return false;
    }

    public Celda getCelda(){
        return celda;
    }

    public Color getColor(){
        return type.color;
    }

    public void putInYourPlace(){
        celda.setPiece(this);
    }


    @Override
    public String toString(){
        return colorize(colorize(String.valueOf((char)(type.getShape())), type.color.getAttribute()),getCelda().getColor().getAttribute());
    }

    public abstract Cordenada[] getNextMovements();

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


