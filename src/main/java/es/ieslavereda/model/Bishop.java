package es.ieslavereda.model;

import es.ieslavereda.Tool;

public abstract class Bishop extends Piece{
    private Cordenada[] cordenadas;

    public Bishop(Type type, Celda celda){
        super(type,celda);
    }

    public static Cordenada[] getNextMovementsAsBishop(Piece p){
        Cordenada[] cordenadas = new Cordenada[0];
        Cordenada position = p.getCelda().getCordenada();
        Tablero t = p.getCelda().getTablero();
        Cordenada c;
        Color color = p.getColor();

        //Up&Left
        c = position.up().left();
        while (t.getCelda(c)!=null&&t.getCelda(c).isEmpty()){
            cordenadas = Tool.add(cordenadas,c);
            c = c.up().left();
        }
        if(t.getCelda(c)!=null && t.getCelda(c).getPiece().getColor()!=color)
            cordenadas = Tool.add(cordenadas,c);

        //Up&Right
        c = position.up().right();
        while (t.getCelda(c)!=null&&t.getCelda(c).isEmpty()){
            cordenadas = Tool.add(cordenadas,c);
            c = c.up().right();
        }
        if(t.getCelda(c)!=null && t.getCelda(c).getPiece().getColor()!=color)
            cordenadas = Tool.add(cordenadas,c);

        //Down&Left
        c=position.down().left();
        while (t.getCelda(c)!=null&&t.getCelda(c).isEmpty()){
            cordenadas = Tool.add(cordenadas,c);
            c= c.down().left();
        }
        if(t.getCelda(c)!=null && t.getCelda(c).getPiece().getColor()!=color)
            cordenadas = Tool.add(cordenadas,c);

        //Down&Right
        c = position.down().right();
        while (t.getCelda(c)!=null&&t.getCelda(c).isEmpty()){
            cordenadas = Tool.add(cordenadas,c);
            c = c.down().right();
        }
        if(t.getCelda(c)!=null && t.getCelda(c).getPiece().getColor()!=color)
            cordenadas = Tool.add(cordenadas,c);

        return cordenadas;
    }


    @Override
    public Cordenada[] getNextMovements(){
        return getNextMovementsAsBishop(this);
    }



}
