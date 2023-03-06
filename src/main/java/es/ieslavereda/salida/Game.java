package es.ieslavereda.salida;

import es.ieslavereda.entrada.Options;
import es.ieslavereda.entrada.Tool;
import es.ieslavereda.model.Color;
import es.ieslavereda.model.Cordenada;
import es.ieslavereda.model.Jugador;
import es.ieslavereda.model.Tablero;

import java.util.Scanner;

public class Game {
    public static void start(){
        Scanner sc = new Scanner(System.in);
        Tablero t;
        Jugador j1;
        Jugador j2;
        System.out.println("------------------------AJEDREZ-----------------------------------");
        System.out.println("----------------------Pulsa enter---------------------------------");
        sc.nextLine();
        Tool.borrar();
        t = Options.menuTablero();
        Tool.borrar();
        if (t!=null) {
            System.out.println("Vamos a crear jugadores:");
            j1 = Options.menuJugador(null,t);
            Tool.borrar();
            j2 = Options.menuJugador(j1,t);
            Tool.borrar();
            startMatch(t,j1,j2);
        }
        System.out.println("QUE MALOS SOIS LOS DOS MIRA QUE OS CUESTA JAJAJAJAJAJAJA");
    }

    public static void startMatch(Tablero t, Jugador j1, Jugador j2){
        Color turno = Color.WHITE;
        Cordenada cordenadaPiece;
        Cordenada cordenadaMove;
        System.out.println("Empieza el juego");
        while (!finishGame(t,turno)) {
            t = Tool.inicioDeTurno(turno,t);
            System.out.println(t);
            if (turno.equals(j1.getColor())) {
                System.out.println("Turno de " + j1.getNombre() + " que utiliza -> "+j1.getColor());
            } else {
                System.out.println("Turno de " + j2.getNombre() + " que utiliza ->" + j2.getColor());
            }
            cordenadaPiece = cordenadaSeleccionarPieza(t,turno);
            t.hightlight(t.getCelda(cordenadaPiece).getPiece().getNextMovements());
            Tool.borrar();
            System.out.println(t);
            cordenadaMove = cordenadaMoverPieza(t,turno);
            if (cordenadaMove!=null) {
                t.movePiece(cordenadaPiece, cordenadaMove);
                turno = turno.next();
            }
        }
    }

    private static boolean finishGame(Tablero t,Color turno) {
        return t.oneColorJakeMate(turno);
    }

    public static Cordenada cordenadaSeleccionarPieza(Tablero t,Color turno){
        System.out.println("Dime que ficha quieres mover con este formato (a1)");
        String aux = Tool.devuelveStringFormatoCelda(1);
        Cordenada cExit = new Cordenada(aux.charAt(0),Integer.parseInt(aux.substring(1)));
        while (!Tool.comprobarPiezaMia(t,turno,cExit)){
            aux = Tool.devuelveStringFormatoCelda(1);
            cExit = new Cordenada(aux.charAt(0),Integer.parseInt(aux.substring(1)));
        }
        return cExit;
    }

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
