package es.ieslavereda.salida;

import es.ieslavereda.Colorines;
import es.ieslavereda.entrada.Options;
import es.ieslavereda.entrada.Tool;
import es.ieslavereda.model.Color;
import es.ieslavereda.model.Cordenada;
import es.ieslavereda.model.Jugador;
import es.ieslavereda.model.Tablero;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import static es.ieslavereda.salida.Game.cordenadaMoverPieza;
import static es.ieslavereda.salida.Game.cordenadaSeleccionarPieza;

public class OnlineGame {
    public static void optionsPartidaOnline(){
        System.out.println(Colorines.BLACK_BACKGROUND+Colorines.PURPLE_BOLD+"----------------PARTIDA ONLINE------------------------"+Colorines.RESET);
        System.out.println(Colorines.BLACK_BACKGROUND+Colorines.PURPLE_BOLD+"[1] Crear partida"+Colorines.RESET);
        System.out.println(Colorines.BLACK_BACKGROUND+Colorines.PURPLE_BOLD+"[2] Unirse a partida"+Colorines.RESET);
        System.out.println(Colorines.BLACK_BACKGROUND+Colorines.PURPLE_BOLD+"[3] Atras"+Colorines.RESET);
        switch (Options.numero1al3()){
            case("1"):
                createOnlineGame();
                break;
            case("2"):
                unirseAPartida();
                break;
            default:
                Options.menuTablero();
                break;
        }
    }


    public static void createOnlineGame(){
        Scanner sc = new Scanner(System.in);
        int puerto ;
        ObjectInputStream entrada;
        ObjectOutputStream salida;
        Socket socket;
        ServerSocket serverSocket;
        Tablero t = new Tablero();
        Jugador j2;
        Jugador j1;
        j1 = Options.menuJugador(null, t);
        System.out.println(Colorines.BLACK_BACKGROUND+Colorines.PURPLE_BOLD+"Ecrible el codigo de partida que quiere crear"+Colorines.RESET);
        puerto = pedirInteger();
        Tool.borrar();
        try {
            serverSocket = new ServerSocket(puerto);
            System.out.println(Colorines.BLACK_BACKGROUND+Colorines.PURPLE_BOLD+"Su codigo de partida es "+puerto+" compartela con el jugador con el que quiere jugar"+Colorines.RESET);
            socket = serverSocket.accept();
            System.out.println(Colorines.BLACK_BACKGROUND+Colorines.PURPLE_BOLD+"Alguien a entrado en la partida esperando a que se de identifique"+Colorines.RESET);
            entrada = new ObjectInputStream(socket.getInputStream());
            salida = new ObjectOutputStream(socket.getOutputStream());
            salida.writeObject(j1);
            salida.writeObject(t);
            j2 = (Jugador) entrada.readObject();
            Color turno = Color.WHITE;
            salida.writeObject(turno);
            Cordenada cordenadaPiece;
            Cordenada cordenadaMove;
            String valorDeOpciones = "3";
            System.out.println(Colorines.BLACK_BACKGROUND+Colorines.PURPLE_BOLD+"Empieza el juego"+Colorines.RESET);
            while (!Game.finishGame(t,turno,valorDeOpciones)) {
                t = Tool.inicioDeTurno(turno,t);
                System.out.println(t);
                if (turno.equals(j1.getColor())) {
                    System.out.println(Colorines.BLACK_BACKGROUND+Colorines.GREEN_BOLD+"Turno de " + j1.getNombre() + " que utiliza -> "+j1.getColor()+Colorines.RESET);
                } else {
                    System.out.println(Colorines.BLACK_BACKGROUND+Colorines.GREEN_BOLD+"Turno de " + j2.getNombre() + " que utiliza ->" + j2.getColor()+Colorines.RESET);
                    System.out.println(Colorines.BLACK_BACKGROUND+Colorines.GREEN_BOLD+"Esperando movimientos......."+Colorines.RESET);
                }
                if (turno.equals(j1.getColor())) {
                    cordenadaPiece = cordenadaSeleccionarPieza(t, turno);
                    if (cordenadaPiece != null) {
                        t.hightlight(t.getCelda(cordenadaPiece).getPiece().getNextMovements());
                        Tool.borrar();
                        System.out.println(t);
                        cordenadaMove = cordenadaMoverPieza(t, turno);
                        if (cordenadaMove != null) {
                            t.movePiece(cordenadaPiece, cordenadaMove);
                            turno = turno.next();
                            salida.writeObject(t);
                        }

                    } else
                        valorDeOpciones = Options.opcionesDePausa(t, j1, j2, turno);
                }else {
                    t = (Tablero) entrada.readObject();
                    turno = turno.next();
                }
            }
            if (t.oneColorJake(j1.getColor())){
                System.out.println(Colorines.BLACK_BACKGROUND+Colorines.PURPLE_BOLD+"El ganador es "+ j2.getNombre()+Colorines.RESET);
            }
            if (t.oneColorJake(j2.getColor())){
                System.out.println(Colorines.BLACK_BACKGROUND+Colorines.PURPLE_BOLD+"El ganador es "+ j1.getNombre()+Colorines.RESET);
            }


            serverSocket.close();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(Colorines.BLACK_BACKGROUND+Colorines.RED_BOLD+"Se ha acabado la partida parece que el otro ribal no quiere Jugar contigo y ha cerrado la sesión"+Colorines.RESET);
            sc.nextLine();
            Tool.borrar();
        }

    }


    public static void unirseAPartida(){
        Scanner sc = new Scanner(System.in);
        String usuario = "10.1.0.13";
        int puerto;
        System.out.println(Colorines.BLACK_BACKGROUND+Colorines.GREEN_BOLD+"Dime que partida quieres entrar:"+Colorines.RESET);
        puerto = pedirInteger();
        Socket socket;
        Tablero t = new Tablero();
        Jugador j2;
        Jugador j1;
        Color turno;
        String valorDeOpciones = "3";
        ObjectInputStream entrada;
        ObjectOutputStream salida;
        Cordenada cordenadaPiece;
        Cordenada cordenadaMove;
        try {
            socket = new Socket(usuario, puerto);
            entrada = new ObjectInputStream(socket.getInputStream());
            salida = new ObjectOutputStream(socket.getOutputStream());
            j1 = (Jugador) entrada.readObject();
            t = (Tablero) entrada.readObject();
            j2 = Options.menuJugador(j1,t);
            turno = (Color) entrada.readObject();

            while (!Game.finishGame(t,turno,valorDeOpciones)) {
                t = Tool.inicioDeTurno(turno,t);
                System.out.println(t);
                if (turno.equals(j1.getColor())) {
                    System.out.println(Colorines.BLACK_BACKGROUND+Colorines.GREEN_BOLD+"Turno de " + j1.getNombre() + " que utiliza -> "+j1.getColor()+Colorines.RESET);
                } else {
                    System.out.println(Colorines.BLACK_BACKGROUND+Colorines.GREEN_BOLD+"Turno de " + j2.getNombre() + " que utiliza ->" + j2.getColor()+Colorines.RESET);
                    System.out.println(Colorines.BLACK_BACKGROUND+Colorines.GREEN_BOLD+"Esperando movimientos......."+Colorines.RESET);
                }
                if (turno.equals(j2.getColor())) {
                    cordenadaPiece = cordenadaSeleccionarPieza(t, turno);
                    if (cordenadaPiece != null) {
                        t.hightlight(t.getCelda(cordenadaPiece).getPiece().getNextMovements());
                        Tool.borrar();
                        System.out.println(t);
                        cordenadaMove = cordenadaMoverPieza(t, turno);
                        if (cordenadaMove != null) {
                            t.movePiece(cordenadaPiece, cordenadaMove);
                            turno = turno.next();
                            salida.writeObject(t);
                        }

                    } else
                        valorDeOpciones = Options.opcionesDePausa(t, j1, j2, turno);
                }else {
                    t = (Tablero) entrada.readObject();
                    turno = turno.next();
                }
            }
            if (t.oneColorJake(j1.getColor())){
                System.out.println(Colorines.BLACK_BACKGROUND+Colorines.PURPLE_BOLD+"El ganador es "+ j2.getNombre()+Colorines.RESET);
                sc.nextLine();
            }
            if (t.oneColorJake(j2.getColor())){
                System.out.println(Colorines.BLACK_BACKGROUND+Colorines.PURPLE_BOLD+"El ganador es "+ j1.getNombre()+Colorines.RESET);
                sc.nextLine();
            }

            socket.close();

        } catch (UnknownHostException e) {
            System.out.println(Colorines.BLACK_BACKGROUND+Colorines.RED_BOLD+"Numero de partida no encontrada"+Colorines.RESET);
            sc.nextLine();
            Tool.borrar();
        } catch (IOException e) {
            System.out.println(Colorines.BLACK_BACKGROUND+Colorines.RED_BOLD+"El cobarde de tu amigo se ha rendido"+Colorines.RESET);
            sc.nextLine();
            Tool.borrar();
        } catch (ClassNotFoundException e) {
            System.out.println(Colorines.BLACK_BACKGROUND+Colorines.RED+"La cague"+Colorines.RESET);
            sc.nextLine();
            Tool.borrar();
        }


    }

    public static int pedirInteger(){
        Scanner sc = new Scanner(System.in);
        String numeroString = sc.nextLine();
        int exit;
        while (!esUnNumero(numeroString)){
            System.out.println(Colorines.BLACK_BACKGROUND+Colorines.RED+"Porfavor pasame un codigo de partida valido es un numero"+Colorines.RESET);
            numeroString = sc.nextLine();
        }
        exit = Integer.parseInt(numeroString);
        return exit;
    }

    public static boolean esUnNumero(String string){
        for (int i = 0; i<string.length();i++){
            if (string.charAt(i)<'0'||string.charAt(i)>'9')
                return false;
        }
        return true;
    }
}
