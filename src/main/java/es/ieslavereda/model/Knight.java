package es.ieslavereda.model;

import javax.tools.Tool;

public abstract class Knight extends Piece{
    private Cordenada[] cordenadas;

    public Knight(Type type, Celda celda){
        super(type,celda);
    }

    @Override
    public Cordenada[] getNextMovements(){
        cordenadas = new Cordenada[0];
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

        return cordenadas;
    }

    private void check(Cordenada c){
        Tablero tablero = getCelda().getTablero();
        if (tablero.getCelda(c)!=null){
            if (tablero.getCelda(c).isEmpty())
                cordenadas = es.ieslavereda.Tool.add(cordenadas,c);
            else if (tablero.getCelda(c).getPiece().getColor()!=getColor())
                cordenadas = es.ieslavereda.Tool.add(cordenadas,c);
        }
    }
}
