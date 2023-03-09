package es.ieslavereda.salida;

import es.ieslavereda.entrada.Options;
import es.ieslavereda.entrada.Tool;
import es.ieslavereda.model.Color;
import es.ieslavereda.model.Cordenada;
import es.ieslavereda.model.Jugador;
import es.ieslavereda.model.Tablero;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

/**
 * @author José Luis Tárraga
 */
public class Game {
    /**
     * Start the game and initialize the players and the board;
     */
    public static void start(){
        Scanner sc = new Scanner(System.in);
        String opcionDePartida;
        System.out.println("------------------------AJEDREZ-----------------------------------");
        System.out.println("----------------------Pulsa enter---------------------------------");
        sc.nextLine();
        Tool.borrar();
        do {


            opcionDePartida = Options.menuTablero();
            Tool.borrar();
            if (opcionDePartida.equals("1"))
                cargarPartida();

            if (opcionDePartida.equals("2"))
                partidaNueva();
        }while (!opcionDePartida.equals("3"));

    }

    private static void cargarPartida() {
        Scanner sc = new Scanner(System.in);
        if (!Tool.partidasVacias()) {
            System.out.println("------------------------Partidas disponibles-----------------------");
            String nombreDeLaPartida = Options.selccionarPartidaParaCargar();
            if (!nombreDeLaPartida.equals("4"))
                try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreDeLaPartida))){

                    Tablero t = (Tablero) ois.readObject();
                    Jugador j1 = (Jugador) ois.readObject();
                    Jugador j2 = (Jugador) ois.readObject();
                    Color turno = (Color) ois.readObject();
                    Tool.borrar();
                    startMatch(t,j1,j2,turno);

                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("Ha avido un error vuelve a intentarlo");
                    sc.nextLine();
                }
        }else {
            System.out.println("No hay partidas guardadas inicia una nueva");
            sc.nextLine();
        }
        Tool.borrar();

    }

    private static void partidaNueva() {
        Tablero t = new Tablero();
        Jugador j2;
        Jugador j1;
        System.out.println("Vamos a crear jugadores:");
        j1 = Options.menuJugador(null, t);
        Tool.borrar();
        j2 = Options.menuJugador(j1, t);
        Tool.borrar();
        startMatch(t,j1,j2,Color.WHITE);
    }

    /**
     * Initialize one match
     * @param t Board game
     * @param j1 Player one
     * @param j2 Player two
     */
    public static void startMatch(Tablero t, Jugador j1, Jugador j2,Color turno1){
        Color turno = turno1;
        Cordenada cordenadaPiece;
        Cordenada cordenadaMove;
        String valorDeOpciones = "3";
        System.out.println("Empieza el juego");
        while (!finishGame(t,turno,valorDeOpciones)) {
            t = Tool.inicioDeTurno(turno,t);
            System.out.println(t);
            if (turno.equals(j1.getColor())) {
                System.out.println("Turno de " + j1.getNombre() + " que utiliza -> "+j1.getColor());
            } else {
                System.out.println("Turno de " + j2.getNombre() + " que utiliza ->" + j2.getColor());
            }
            cordenadaPiece = cordenadaSeleccionarPieza(t,turno);
            if (cordenadaPiece!=null) {
                t.hightlight(t.getCelda(cordenadaPiece).getPiece().getNextMovements());
                Tool.borrar();
                System.out.println(t);
                cordenadaMove = cordenadaMoverPieza(t, turno);
                if (cordenadaMove != null) {
                    t.movePiece(cordenadaPiece, cordenadaMove);
                    turno = turno.next();
                }
            }else
                valorDeOpciones = Options.opcionesDePausa(t,j1,j2,turno);
        }
    }

    /**
     * Check if the game is finish
     *
     * @param t Board game
     * @param turno Turn color
     * @return <ul>
     *     <li>True: When the king is in check-mate</li>
     *     <li>False: When the king isn't in check-mate</li>
     * </ul>
     */
    private static boolean finishGame(Tablero t,Color turno,String valorDeOpciones) {
        return t.oneColorJakeMate(turno)||!valorDeOpciones.equals("3");
    }


    /**
     * Answer to user what piece want to move
     *
     * @param t Board game
     * @param turno Turn color
     * @return Coordinate of the piece that the user select
     */
    public static Cordenada cordenadaSeleccionarPieza(Tablero t,Color turno){
        System.out.println("Dime que ficha quieres mover con este formato (a1) o pulsa C para pausar el Juego");
        String aux = Tool.devuelveStringFormatoCelda(1);
        if (aux.equalsIgnoreCase("c"))
            return null;
        Cordenada cExit = new Cordenada(aux.charAt(0),Integer.parseInt(aux.substring(1)));
        while (!Tool.comprobarPiezaMia(t,turno,cExit)){
            aux = Tool.devuelveStringFormatoCelda(1);
            if (aux.equalsIgnoreCase("c"))
                return null;
            cExit = new Cordenada(aux.charAt(0),Integer.parseInt(aux.substring(1)));
        }
        return cExit;
    }

    /**
     * Answer to user where move the piece
     * @param t Board game
     * @param color Turn color
     * @return Coordinate where the user want to move the token
     */
    public static Cordenada cordenadaMoverPieza(Tablero t,Color color){
        System.out.println("Donde quieres mover la pieza inserte la cordenada con formato (a1) o si quieres soltar la pieza pulsa c");
        Cordenada cExit = null;
        String aux = Tool.devuelveStringFormatoCelda(2);
        if (aux.length()==2)
            cExit = new Cordenada(aux.charAt(0),Integer.parseInt(aux.substring(1)));
        while (!Tool.comprobarCordenadaAptaMover(t,cExit,color)) {
            aux = Tool.devuelveStringFormatoCelda(2);
            if (aux.length()==2)
                cExit = new Cordenada(aux.charAt(0),Integer.parseInt(aux.substring(1)));
            if (aux.compareToIgnoreCase("c") == 0) {
                return null;
            }
        }

        return cExit;
    }
}
