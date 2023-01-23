package es.ieslavereda.model;

public class Tablero {
    private Celda[][] celdas;

    public Tablero(){
        celdas = new Celda[8][8];
        for (int fila =0 ; fila<celdas.length; fila++)
            for ( int columna =0 ; columna< celdas[fila].length;columna++)
                celdas[fila][columna]= new Celda(this, new Cordenada((char)('A'+columna),1+fila));
    }

    public void placePieces(){
        new BlackKnight(getCelda(new Cordenada('C',8)));
        new BlackKnight(getCelda(new Cordenada('B',4)));
    }

    public Celda getCelda(Cordenada cordenada){
        if (cordenada.getFila()<1||cordenada.getFila()>8)
            return null;
        if (cordenada.getCol()<'A'||cordenada.getCol()>'H')
            return null;
        return celdas[cordenada.getFila()-1][cordenada.getCol()-'A'];
    }

    public void hightlight(Cordenada[] cordenadas){
        for (Cordenada c : cordenadas){
            getCelda(c).highLight();
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
