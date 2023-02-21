package es.ieslavereda.model;

import java.util.HashSet;
import java.util.Set;

public abstract class Knight extends Piece{
    private Set<Cordenada> listCoordinate;

    public Knight(Type type, Celda celda){
        super(type,celda);
    }

    @Override
    public Set<Cordenada> getNextMovements(){
        listCoordinate = new HashSet<>();
        Cordenada position = getCelda().getCordenada();
        Cordenada c;

        //UP
        c= position.up().up().left();
        check(c);

        c=position.up().up().right();
        check(c);

        //Down
        c = position.down().down().right();
        check(c);

        c = position.down().down().left();
        check(c);

        //Right
        c= position.right().right().up();
        check(c);

        c= position.right().right().down();
        check(c);

        //Left
        c=position.left().left().up();
        check(c);

        c=position.left().left().down();
        check(c);

        return listCoordinate;
    }

    private void check(Cordenada c){
        Tablero tablero = getCelda().getTablero();
        if (tablero.getCelda(c)!=null){
            if (tablero.getCelda(c).isEmpty())
                listCoordinate.add(c);
            else if (tablero.getCelda(c).getPiece().getColor()!=getColor())
                listCoordinate.add(c);
        }
    }
}
