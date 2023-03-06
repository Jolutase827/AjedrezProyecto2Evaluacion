package es.ieslavereda.model;

import java.util.HashSet;
import java.util.Set;

public abstract class King extends Piece{
    private Set<Cordenada> listCoordinate;

    public King(Type type, Celda celda){
        super(type,celda);
    }


    @Override
    public Set<Cordenada> getNextMovements(){
        listCoordinate = new HashSet<>();
        Cordenada position = getCelda().getCordenada();
        Cordenada c;
        //Up
        c=position.up();
        check(c);

        //Up&&Left
        c=position.upLeft();
        check(c);

        //Up&&Right
        c=position.upRight();
        check(c);

        //Down
        c=position.down();
        check(c);

        //Down&&Right
        c=position.downRight();
        check(c);

        //Down&&Left
        c=position.downLeft();
        check(c);
        //Left
        c=position.left();
        check(c);
        //Right
        c = position.right();
        check(c);

        c = position.left().left();
        checkEnroqueLeft(c);

        c = position.right().right();
        checkEnroqueRight(c);

        return listCoordinate;
    }

    private void check(Cordenada c){
        Tablero t = getCelda().getTablero();
        if(t.getCelda(c)!=null){
            if (t.getCelda(c).isEmpty())
                listCoordinate.add(c);
            else if (t.getCelda(c).getPiece().getColor()!=getColor())
                listCoordinate.add(c);
        }
    }

    private void checkEnroqueLeft(Cordenada c) {
        Tablero t = getCelda().getTablero();
        if (!isMove())
            if (t.getCelda(c).isEmpty() && t.getCelda(c.right()).isEmpty() && t.getCelda(c.left()).isEmpty())
                if (!t.getCelda(c.left().left()).isEmpty())
                    if (t.getCelda(c.left().left()).getPiece() instanceof Rook)
                        if (!t.getCelda(c.left().left()).getPiece().isMove())
                            listCoordinate.add(c);

    }

    private void checkEnroqueRight(Cordenada c){
        Tablero t = getCelda().getTablero();
        if (!isMove())
            if (t.getCelda(c).isEmpty() && t.getCelda(c.left()).isEmpty())
                if (!t.getCelda(c.right()).isEmpty())
                    if (t.getCelda(c.right()).getPiece() instanceof Rook)
                        if (!t.getCelda(c.right()).getPiece().isMove())
                            listCoordinate.add(c);

    }
}
