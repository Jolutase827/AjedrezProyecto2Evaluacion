package es.ieslavereda.model;

import java.util.*;

public class Tablero {
    private Map<Cordenada,Celda> celdas;

    private IDeletePieceManager deletePieceManager;


    public Tablero(){
        this.deletePieceManager = new DeletePieceManagerList();
        celdas = new LinkedHashMap<>();
        for (int fila =0 ; fila<8; fila++)
            for ( int columna =0 ; columna< 8;columna++)
                celdas.put(new Cordenada((char)('A'+columna),1+fila),new Celda(this, new Cordenada((char)('A'+columna),1+fila)));
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
        new WhiteKing(getCelda(new Cordenada('E',3)));
        new BlackKing(getCelda(new Cordenada('E',1)));
        for (int i = 0; i<8; i++) {
            new BlackPawn(getCelda(new Cordenada((char)('A'+i), 2)));
            new WhitePawn(getCelda(new Cordenada((char)('A'+i), 7)));
        }
    }

    public Celda getCelda(Cordenada cordenada){
        return celdas.get(cordenada);
    }

    public void hightlight(Set<Cordenada> setcordenada){
        List<Cordenada> listCordenada = new LinkedList<>(setcordenada);
        for (Cordenada c : listCordenada){
            getCelda(c).highLight();
        }
    }

    public void resetColors(){
        for (Celda c : celdas.values())
                c.resetColor();
    }

    @Override
    public String toString(){
        String salida = "    A  B  C  D  E  F  G  H\n";

        for (int i = 0; i<8;i++) {
            salida += " " + (i + 1) + " ";
            for (int j = 0; j <8; j++)
                salida += celdas.get(new Cordenada((char)('A'+j),1+i));
            salida += " " + (i+1)+ "\n";
        }

        salida += "    A  B  C  D  E  F  G  H\n\n" + deletePieceManager;


        return salida;
    }
}
