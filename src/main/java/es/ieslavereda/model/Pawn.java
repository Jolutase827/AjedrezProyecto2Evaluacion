package es.ieslavereda.model;

import es.ieslavereda.Tool;

public abstract class Pawn extends Piece {
    private Cordenada[] cordenadas;


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
    public Cordenada[] getNextMovements() {
        cordenadas = new Cordenada[0];
        getNextMovementsPawn();
        return cordenadas;
    }


    public abstract void getNextMovementsPawn();

    public void check(Cordenada cordenada){
        Tablero tablero = getCelda().getTablero();
        if (tablero.getCelda(cordenada)!=null)
            if (tablero.getCelda(cordenada).isEmpty())
                cordenadas = Tool.add(cordenadas,cordenada);
    }

    public void checkEnemy(Cordenada cordenada){
        Tablero tablero = getCelda().getTablero();
        if (tablero.getCelda(cordenada)!=null)
            if (!tablero.getCelda(cordenada).isEmpty())
                if (tablero.getCelda(cordenada).getPiece().getColor()!=getColor())
                    cordenadas = Tool.add(cordenadas,cordenada);
    }

    public abstract void trasnform();
}
