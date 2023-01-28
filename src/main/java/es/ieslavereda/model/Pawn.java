package es.ieslavereda.model;

import es.ieslavereda.TAD.ListCoordinate;

public abstract class Pawn extends Piece {
    private ListCoordinate list;


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
    public ListCoordinate getNextMovements() {
        list = new ListCoordinate();
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
