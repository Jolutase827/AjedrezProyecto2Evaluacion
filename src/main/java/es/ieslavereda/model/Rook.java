package es.ieslavereda.model;

import es.ieslavereda.Tool;

public abstract class Rook extends Piece {

    public Rook(Type type,Celda celda){
        super(type,celda);
    }

    public static Cordenada[] getNextMovementsAsRook(Piece p) {
        Cordenada[] cordenadas = new Cordenada[0];
        Cordenada posicion = p.getCelda().getCordenada();
        Tablero t = p.getCelda().getTablero();
        Cordenada c;
        Color color = p.getColor();

        //Up
        c = posicion.up();
        while (t.getCelda(c)!=null&&t.getCelda(c).isEmpty()) {
            cordenadas = Tool.add(cordenadas, c);
            c = c.up();
        }
        if(t.getCelda(c)!=null && t.getCelda(c).getPiece().getColor()!=color)
            cordenadas = Tool.add(cordenadas,c);

        //Down

        c = posicion.down();
        while (t.getCelda(c)!=null&&t.getCelda(c).isEmpty()){
            cordenadas = Tool.add(cordenadas, c);
            c = c.down();
        }
        if(t.getCelda(c)!=null && t.getCelda(c).getPiece().getColor()!=color)
            cordenadas = Tool.add(cordenadas,c);

        //Left

        c = posicion.left();
        while (t.getCelda(c)!=null&&t.getCelda(c).isEmpty()){
            cordenadas = Tool.add(cordenadas, c);
            c = c.left();
        }
        if(t.getCelda(c)!=null && t.getCelda(c).getPiece().getColor()!=color)
            cordenadas = Tool.add(cordenadas,c);


        //Right

        c = posicion.right();
        while (t.getCelda(c)!=null&&t.getCelda(c).isEmpty()) {
            cordenadas = Tool.add(cordenadas, c);
            c = c.right();
        }
        if(t.getCelda(c)!=null && t.getCelda(c).getPiece().getColor()!=color)
            cordenadas = Tool.add(cordenadas,c);

        return cordenadas;
    }

    @Override
    public Cordenada[] getNextMovements() {
        return getNextMovementsAsRook(this);
    }



}
