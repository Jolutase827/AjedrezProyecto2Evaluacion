package es.ieslavereda.model;
/**
 * @author José Luis Tárraga, Feat Joaquin
 */
public class Cordenada {
    private int fila;
    private char col;

    public Cordenada(char col, int fila){
        this.fila = fila;
        this.col = Character.toUpperCase(col);
    }

    public int getFila() {
        return fila;
    }

    public char getCol() {
        return col;
    }

    public Cordenada up(){
        return new Cordenada(col,fila-1);
    }
    public Cordenada down(){
        return new Cordenada(col,fila+1);
    }
    public Cordenada left(){
        return new Cordenada((char)(col-1),fila);
    }
    public Cordenada right(){
        return new Cordenada((char)(col+1),fila);
    }
    public Cordenada upRight(){
        return right().up();
    }
    public Cordenada upLeft(){
        return left().up();
    }
    public Cordenada downRight(){
        return right().down();
    }
    public Cordenada downLeft(){
        return left().down();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Cordenada){
            Cordenada c = (Cordenada) obj;
            return col==c.col && fila==c.fila;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return fila+col;
    }

    @Override
    public String toString(){
        return "("+col+","+fila+")";
    }

}
