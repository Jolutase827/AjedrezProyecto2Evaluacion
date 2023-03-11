package es.ieslavereda.entrada;

import es.ieslavereda.model.Color;
import es.ieslavereda.model.Cordenada;
import es.ieslavereda.model.Jugador;
import es.ieslavereda.model.Tablero;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

/**
 * @author José Luis Tárraga
 */
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
            valido = (exit.length()==2||exit.compareToIgnoreCase("c")==0);
            if (exit.length()==2){
                valido = (exit.charAt(1)>='1'&&exit.charAt(1)<='9');
            }
            if (!valido) {
                System.out.println((i == 1) ? "ERROR 1.1: EL FORMATO NO ES VALIDO POR FAVOR SELECCIONE UNA FICHA CON FORMATO a1 o escriba C para ir a opciones" : "ERROR 1.1: EL FORMATO DEL VALOR NO ES VALIDO POR FAVOR SELECCIONE UNA CELDA CON FORMATO (a1) O ESCRIBE (c) PARA SOLTAR LA PIEZA");
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

    /**
     * Comprueba que ficheros estan vacios
     * @return Un fichero para sobreescribir;
     */
    public static String ficheroVacio(){
        if (partidasVacias()){
            return partida1();
        }else if (todasLasPartidasOcupadas()){
            System.out.println("-------------------Selecciona la partida que quieres remplazar-------------------");
            return Options.selccionarPartida();
        }else
            return buscadorDePartidasVacias();
    }

    /**
     * Da la partida 1
     * @return Da el path de la partida 1 en formato String
     */
    public static String partida1(){
        return "src/main/java/Partida1.txt";
    }
    /**
     * Da la partida 3
     * @return Da el path de la partida 3 en formato String
     */
    public static String partida2(){
        return "src/main/java/Partida2.txt";
    }
    /**
     * Da la partida 3
     * @return Da el path de la partida 3 en formato String
     */
    public static String partida3(){
        return "src/main/java/Partida3.txt";
    }

    /**
     * Busca las partidas sin objetos
     * @return La partida sin objetos
     */
    public static String buscadorDePartidasVacias(){
        if (comprobarPartida1()==null)
            return partida1();
        else if (comprobarPartida2()==null)
            return partida2();
        else
            return partida3();
    }

    /**
     * Comprueba si todas las partidas tienen objetos
     * @return True: Si hay informacion en todas<br/> False: Si existe alguna vacia
     *
     *
     */
    public static boolean todasLasPartidasOcupadas(){
        return (comprobarPartida1()!=null&& comprobarPartida2()!=null&& comprobarPartida3()!=null);
    }

    /**
     * Comprueba si la partida esta vacia
     * @param partida la partida que queremos comprobar
     * @return Si esta vacia o no
     */
    public static boolean partidaNoVacia(String partida){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(partida))){

            Tablero t = (Tablero) ois.readObject();
            Jugador j1 = (Jugador) ois.readObject();
            Jugador j2 = (Jugador) ois.readObject();
            Color turno = (Color) ois.readObject();
            return true;

        } catch (IOException | ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Compruba si estan vacias todas las partidas
     * @return Si estan vacias o no en formato boolean
     */
    public static boolean partidasVacias(){
        return (comprobarPartida1()==null&& comprobarPartida2()==null&& comprobarPartida3()==null);
    }

    /**
     * Comprueba si la partida1 esta vacia
     * @return Si esta vacia o no en formato boolean
     */
    public static String comprobarPartida1(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/java/Partida1.txt"))){

            Tablero t = (Tablero) ois.readObject();
            Jugador j1 = (Jugador) ois.readObject();
            Jugador j2 = (Jugador) ois.readObject();
            Color turno = (Color) ois.readObject();
            return "src/main/java/Partida1.txt";

        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    /**
     * Comprueba si la partida2 esta vacia
     * @return Si esta vacia o no en formato boolean
     */
    public static String comprobarPartida2(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/java/Partida2.txt"))){

            Tablero t = (Tablero) ois.readObject();
            Jugador j1 = (Jugador) ois.readObject();
            Jugador j2 = (Jugador) ois.readObject();
            Color turno = (Color) ois.readObject();
            return "src/main/java/Partida2.txt";

        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    /**
     * Comprueba si la partida3 esta vacia
     * @return Si esta vacia o no en formato boolean
     */
    public static String comprobarPartida3(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/java/Partida3.txt"))){

            Tablero t = (Tablero) ois.readObject();
            Jugador j1 = (Jugador) ois.readObject();
            Jugador j2 = (Jugador) ois.readObject();
            Color turno = (Color) ois.readObject();
            return "src/main/java/Partida3.txt";

        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}
