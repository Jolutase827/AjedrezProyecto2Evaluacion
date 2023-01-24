package es.ieslavereda.model;

import com.diogonunes.jcolor.Attribute;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Celda {
    private Piece piece;

    private Color color;
    private Color original;
    private Tablero tablero;
    private Cordenada cordenada;

    public Celda(Tablero tablero, Cordenada cordenada) {
        this.cordenada = cordenada;
        this.tablero = tablero;
        this.piece = null;
        if((cordenada.getFila()-1 + cordenada.getCol() - 'A')%2==0){
            original = Color.WHITE_CELL;
        }else {
            original = Color.BLACK_CELL;
        }
        this.color = original;
    }

    public Tablero getTablero(){
        return tablero;
    }
    public Cordenada getCordenada(){
        return cordenada;
    }

    public Piece getPiece(){
        return piece;
    }

    public Color getColor(){
        return color;
    }

    public void setPiece(Piece piece){
        this.piece = piece;
    }

    public boolean isEmpty(){
        return piece==null;
    }
    public void highLight(){
        if (isEmpty()){
            if (original == Color.WHITE_CELL)
                color = Color.HIGHLIGHT_WHITE;
            else
                color = Color.HIGHLIGHT_BLACK;
        }else {
            color = (original == Color.BLACK_CELL)? Color.HIGHLIGHT_KILL_BLACK :Color.HIGHLIGHT_KILL_WHITE;
        }
    }

    @Override
    public String toString(){
        if (isEmpty())
            return colorize("   ", color.getAttribute());
        return colorize(" ", color.getAttribute())+ piece.toString() + colorize(" ",color.getAttribute());
    }


    public void resetColor(){
        color = original;
    }
    enum Color{
        WHITE_CELL(Attribute.BACK_COLOR(180, 180, 180)),
        BLACK_CELL(Attribute.BACK_COLOR(100, 100, 100)),
        HIGHLIGHT_KILL_WHITE(Attribute.BACK_COLOR(180, 0, 0)),
        HIGHLIGHT_KILL_BLACK(Attribute.BACK_COLOR(130, 0, 0)),
        HIGHLIGHT_WHITE(Attribute.BACK_COLOR(180, 180, 0)),
        HIGHLIGHT_BLACK(Attribute.BACK_COLOR(130, 130, 0));

        private Attribute attribute;
        Color(Attribute attribute){
            this.attribute = attribute;
        }

        public Attribute getAttribute() {
            return attribute;
        }
    }
}
