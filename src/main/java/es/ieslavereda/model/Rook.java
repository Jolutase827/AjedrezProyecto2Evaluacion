package es.ieslavereda.model;

import java.util.HashSet;
import java.util.Set;

public abstract class Rook extends Piece {

    public Rook(Type type,Celda celda){
        super(type,celda);
    }

    public static Set<Cordenada> getNextMovementsAsRook(Piece p) {
        Set<Cordenada> listCordinates = new HashSet<>();
        Cordenada posicion = p.getCelda().getCordenada();
        Tablero t = p.getCelda().getTablero();
        Cordenada c;
        Color color = p.getColor();

        //Up
        c = posicion.up();
        while (t.getCelda(c)!=null&&t.getCelda(c).isEmpty()) {
            listCordinates.add(c);
            c = c.up();
        }
        if(t.getCelda(c)!=null && t.getCelda(c).getPiece().getColor()!=color)
            listCordinates.add(c);

        //Down

        c = posicion.down();
        while (t.getCelda(c)!=null&&t.getCelda(c).isEmpty()){
            listCordinates.add(c);
            c = c.down();
        }
        if(t.getCelda(c)!=null && t.getCelda(c).getPiece().getColor()!=color)
            listCordinates.add(c);

        //Left

        c = posicion.left();
        while (t.getCelda(c)!=null&&t.getCelda(c).isEmpty()){
            listCordinates.add(c);
            c = c.left();
        }
        if(t.getCelda(c)!=null && t.getCelda(c).getPiece().getColor()!=color)
            listCordinates.add(c);


        //Right

        c = posicion.right();
        while (t.getCelda(c)!=null&&t.getCelda(c).isEmpty()) {
            listCordinates.add(c);
            c = c.right();
        }
        if(t.getCelda(c)!=null && t.getCelda(c).getPiece().getColor()!=color)
            listCordinates.add(c);

        return listCordinates;
    }

    @Override
    public Set<Cordenada> getNextMovements() {
        return getNextMovementsAsRook(this);
    }



}
