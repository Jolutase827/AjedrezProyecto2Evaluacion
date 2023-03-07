package es.ieslavereda.model;
/**
 * @author José Luis Tárraga, Feat Joaquin
 */
public final class BlackPawn extends Pawn{


    public BlackPawn(Celda celda){
        super(Type.BLACK_PAWN,celda);
    }

    @Override
    public void trasnform() {
        new BlackQueen(getCelda());
    }

    @Override
    public void getNextMovementsPawn(){
        Cordenada position = getCelda().getCordenada();
        Cordenada cordenada;

        cordenada = position.down();
        check(cordenada);
        if (position.getFila()==2){
            cordenada = cordenada.down();
            check(cordenada);
        }

        cordenada = position.downLeft();
        checkEnemy(cordenada);

        cordenada = position.downRight();
        checkEnemy(cordenada);

    }
}
