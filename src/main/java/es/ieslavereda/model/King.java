package es.ieslavereda.model;

import es.ieslavereda.TAD.ListCoordinate;

public abstract class King extends Piece{
    private ListCoordinate listCoordinate;

    public King(Type type, Celda celda){
        super(type,celda);
    }


    @Override
    public ListCoordinate getNextMovements(Piece p){
        listCoordinate = new ListCoordinate();
        Cordenada position = p.getCelda().getCordenada();
        Cordenada
        //Up



        //Up&&Left


        //Up&&Right


        //Down

        //Down&&Right


        //Down&&Left


        //Left


        //Right



        return listCoordinate;
    }
}
