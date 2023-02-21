package es.ieslavereda.model;

import java.util.HashSet;
import java.util.Set;

public abstract class Pawn extends Piece {
    private Set<Cordenada> list;


    public Pawn(Type type, Celda celda){
        super(type,celda);
    }

    @Override
    public void moveTo(Cordenada cordenada){
        super.moveTo(cordenada);
        if(this.getCelda().getCordenada().getFila()==8||this.getCelda().getCordenada().getFila()==1){
            trasnform();
        }
    }


    @Override
    public Set<Cordenada> getNextMovements() {
        list = new HashSet<>();
        getNextMovementsPawn();
        return list;
    }


    public abstract void getNextMovementsPawn();

    protected void check(Cordenada cordenada){
        Tablero tablero = getCelda().getTablero();
        if (tablero.getCelda(cordenada)!=null)
            if (tablero.getCelda(cordenada).isEmpty())
                list.add(cordenada);
    }

    protected void checkEnemy(Cordenada cordenada){
        Tablero tablero = getCelda().getTablero();
        if (tablero.getCelda(cordenada)!=null)
            if (!tablero.getCelda(cordenada).isEmpty())
                if (tablero.getCelda(cordenada).getPiece().getColor()!=getColor())
                    list.add(cordenada);
    }

    public abstract void trasnform();
}
