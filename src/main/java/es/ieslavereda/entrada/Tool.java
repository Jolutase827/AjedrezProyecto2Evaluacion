package es.ieslavereda.entrada;

import es.ieslavereda.model.Color;
import es.ieslavereda.model.Cordenada;
import es.ieslavereda.model.Tablero;

import javax.print.DocFlavor;
import java.util.Scanner;

public class Tool {
    public static void borrar(){
        for (int i =0; i<40;i++)
            System.out.println();
    }

    public static String devuelveStringFormatoCelda(int i){
        Scanner sc = new Scanner(System.in);
        String exit = sc.nextLine();
        boolean valido=false;

        while (!valido) {
            if (i==1)
                valido = exit.length()==2;
            else
                valido = (exit.length()==2||exit.compareToIgnoreCase("c")==0);

            if (!valido) {
                System.out.println((i == 1) ? "ERROR 1.1: LA LONGITUD DEL VALOR NO ES VALIDO POR FAVOR SELECCIONE UNA FICHA CON FORMATO a1" : "ERROR 1.1: LA LONGITUD DEL VALOR NO ES VALIDO POR FAVOR SELECCIONE UNA CELDA CON FORMATO (a1) O ESCRIBE (c) PARA SOLTAR LA PIEZA");
                exit = sc.nextLine();
            }
        }
        return exit;
    }

    public static boolean comprobarCordenadaAptaMover(Tablero t, Cordenada cExit,Color c) {
        if (t.getCelda(cExit)!=null)
            if (t.getCelda(cExit).isHighLight())
                if (t.movementsValid(c).contains(cExit))
                    return true;
                else
                    System.out.println("Error 1.6: SI MUEVES ESTA PIEZA TE PUEDEN MATAR AL REY");
            else
                System.out.println("ERROR 1.5:LA PIEZA NO PUEDE REALIZAR ESE MOVIMIENTO");
        else
            System.out.println("ERROR 1.2: LA CORDENADA NO ESTA EN FORMATO (a1) O LOS VALORES NO SON DE LA 'A' A LA 'H' O DEL '1' AL '8'");

        return false;
    }

    public static boolean comprobarPiezaMia(Tablero t, Color turno, Cordenada cExit) {
        if (t.getCelda(cExit)!=null)
            if (!t.getCelda(cExit).isEmpty())
                if (t.getCelda(cExit).getPiece().getColor()==turno)
                    if (t.getCelda(cExit).getPiece().getNextMovements().size()>0)
                        if (t.piecesSalveKing(cExit,turno).contains(cExit))
                            return true;
                        else
                            System.out.println("Error 1.6: NO PUEDES MOVER ESTA PIEZA PORQUE SI NO MATAN A TU REY");
                    else
                        System.out.println("Error 1.5: LA PIEZA NO TIENE MOVIMIENTOS");
                else
                    System.out.println("ERROR 1.4: LA PIEZA NO ES DE TU COLOR");
            else
                System.out.println("ERROR 1.3: LA CELDA ESTA VACIA");
        else
            System.out.println("ERROR 1.2: LA CORDENADA NO ESTA EN FORMATO (a1) O LOS VALORES NO SON DE LA 'A' A LA 'H' O DEL '1' AL '8'");

        return false;
    }

    public static Tablero inicioDeTurno(Color color,Tablero t){
        t.resetColors();
        if (t.oneColorJake(color)){
            t.getCelda(t.getKingPosition(color)).hightLightHake();
        }
        return t;
    }
}
