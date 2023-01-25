package es.ieslavereda.model;

import es.ieslavereda.TAD.ListCoordinate;
import es.ieslavereda.TAD.Node;

public class Tablero {
    private Celda[][] celdas;

    public Tablero(){
        celdas = new Celda[8][8];
        for (int fila =0 ; fila<celdas.length; fila++)
            for ( int columna =0 ; columna< celdas[fila].length;columna++)
                celdas[fila][columna]= new Celda(this, new Cordenada((char)('A'+columna),1+fila));
    }

    public void placePieces(){
        new BlackKnight(getCelda(new Cordenada('G',1)));
        new BlackKnight(getCelda(new Cordenada('B',1)));
        new WhiteKnight(getCelda(new Cordenada('B',8)));
        new WhiteKnight(getCelda(new Cordenada('G',8)));
        new WhiteRook(getCelda(new Cordenada('H',8)));
        new WhiteRook(getCelda(new Cordenada('A',8)));
        new BlackRook(getCelda(new Cordenada('A',1)));
        new BlackRook(getCelda(new Cordenada('H',1)));
        new WhiteBishop(getCelda(new Cordenada('C',8)));
        new WhiteBishop(getCelda(new Cordenada('F',8)));
        new BlackBishop(getCelda(new Cordenada('C',1)));
        new BlackBishop(getCelda(new Cordenada('F',1)));
        new WhiteQueen(getCelda(new Cordenada('D',8)));
        new BlackQueen(getCelda(new Cordenada('D',1)));
        for (int i = 0; i<8; i++) {
            new BlackPawn(getCelda(new Cordenada((char)('A'+i), 2)));
            new WhitePawn(getCelda(new Cordenada((char)('A'+i), 7)));
        }
    }

    public Celda getCelda(Cordenada cordenada){
        if (cordenada.getFila()<1||cordenada.getFila()>8)
            return null;
        if (cordenada.getCol()<'A'||cordenada.getCol()>'H')
            return null;
        return celdas[cordenada.getFila()-1][cordenada.getCol()-'A'];
    }

    public void hightlight(ListCoordinate listCoordinate){

        while(!listCoordinate.isEmpty()) {
            getCelda(listCoordinate.remove(0)).highLight();
        }

    }

    public void resetColors(){
        for (Celda[] celdas1: celdas)
            for (Celda c: celdas1)
                c.resetColor();
    }

    @Override
    public String toString(){
        String salida = "    A  B  C  D  E  F  G  H\n";

        for (int i = 0; i<celdas.length;i++) {
            salida += " " + (i + 1) + " ";
            for (int j = 0; j <celdas[i].length; j++)
                salida += celdas[i][j];
            salida += " " + (i+1)+ "\n";
        }

        salida += "    A  B  C  D  E  F  G  H\n";
        return salida;
    }
}
