package es.ieslavereda.model;

import es.ieslavereda.TAD.ListCoordinate;

public abstract class Bishop extends Piece{
    private Cordenada[] cordenadas;

    public Bishop(Type type, Celda celda){
        super(type,celda);
    }


    @Override
    public ListCoordinate getNextMovements(){
        return getNextMovementsAsBishop(this);
    }

    public static ListCoordinate getNextMovementsAsBishop(Piece p){
        ListCoordinate listCoordinate = new ListCoordinate();
        Cordenada position = p.getCelda().getCordenada();
        Tablero t = p.getCelda().getTablero();
        Cordenada c;
        Color color = p.getColor();

        //Up&Left
        c = position.up().left();
        while (t.getCelda(c)!=null&&t.getCelda(c).isEmpty()){
            listCoordinate.add(c);
            c = c.up().left();
        }
        if(t.getCelda(c)!=null && t.getCelda(c).getPiece().getColor()!=color)
            listCoordinate.add(c);

        //Up&Right
        c = position.up().right();
        while (t.getCelda(c)!=null&&t.getCelda(c).isEmpty()){
            listCoordinate.add(c);
            c = c.up().right();
        }
        if(t.getCelda(c)!=null && t.getCelda(c).getPiece().getColor()!=color)
            listCoordinate.add(c);

        //Down&Left
        c=position.down().left();
        while (t.getCelda(c)!=null&&t.getCelda(c).isEmpty()){
            listCoordinate.add(c);
            c= c.down().left();
        }
        if(t.getCelda(c)!=null && t.getCelda(c).getPiece().getColor()!=color)
            listCoordinate.add(c);

        //Down&Right
        c = position.down().right();
        while (t.getCelda(c)!=null&&t.getCelda(c).isEmpty()){
            listCoordinate.add(c);
            c = c.down().right();
        }
        if(t.getCelda(c)!=null && t.getCelda(c).getPiece().getColor()!=color)
            listCoordinate.add(c);

        return listCoordinate;
    }






}
