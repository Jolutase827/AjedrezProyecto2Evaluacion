package es.ieslavereda.entrada;

import es.ieslavereda.model.Color;
import es.ieslavereda.model.Cordenada;
import es.ieslavereda.model.Tablero;

import javax.print.DocFlavor;
import java.util.Scanner;

public class Tool {

    /**
     * Method that clean all the display through a for that print 40 enter
     */
    public static void borrar(){
        for (int i =0; i<40;i++)
            System.out.println();
    }

    /**
     * Method to obtain for the user a good data of coordinate in format String
     * @param i This variable, I use for know what format I need because if the user wants to drop a piece that the program admits the letter 'C' to drop it
     * @return A valid coordinate or a C to drop the piece
     */
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

    /**
     *  Check if the move to the coordinate that the user give to us is valid
     *
     * @param t Board that we are playing
     * @param cExit Coordinate to move the piece
     * @param c Color of the turn
     * @return Return <ul>
     *                      <li>True: When the coordinate can move to this place</li>
     *                      <li>False: When the coordinate can't move</li>
     *                </ul>
     */
    public static boolean comprobarCordenadaAptaMover(Tablero t, Cordenada cExit,Color c) {
        if (t.getCelda(cExit)!=null)
            if (t.getCelda(cExit).isHighLight())
                if (t.movementsValid(c).contains(cExit))
                    return true;
                else
                    System.out.println("Error 1.6: SI MUEVES A ESA CORDENADA LA PIEZA TE PUEDEN MATAR AL REY");
            else
                System.out.println("ERROR 1.5:LA PIEZA NO PUEDE REALIZAR ESE MOVIMIENTO");
        else
            System.out.println("ERROR 1.2: LA CORDENADA NO ESTA EN FORMATO (a1) O LOS VALORES NO SON DE LA 'A' A LA 'H' O DEL '1' AL '8'");

        return false;
    }

    /**
     * Check if the move to the coordinate that the user give to us is valid
     *
     * @param t Board that we are playing
     * @param turno Color of the turn
     * @param cExit Color of the turn
     * @return Return <ul>
     *                          <li>True: When the coordinate can be select</li>
     *                          <li>False: When the coordinate can't be select</li>
     *                    </ul>
     */
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

    /**
     * Reset the colors of all cells and hightlight the king if there are in check
     * @param color The colour of the player that is playing
     * @param t The board
     * @return Board clean
     */
    public static Tablero inicioDeTurno(Color color,Tablero t){
        t.resetColors();
        if (t.oneColorJake(color)){
            t.getCelda(t.getKingPosition(color)).hightLightHake();
        }
        return t;
    }
}
