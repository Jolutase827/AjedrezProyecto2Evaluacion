package es.ieslavereda.entrada;

import es.ieslavereda.model.Color;
import es.ieslavereda.model.Jugador;
import es.ieslavereda.model.Tablero;
import com.diogonunes.jcolor.Attribute;

import java.io.*;
import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;
/**
 * @author José Luis Tárraga
 */
public class Options {
    /**
     * Create board
     * @return Board with the characteristic that the user want;
     */
    public static String menuTablero(){
        String opcion = "";
        System.out.println("Selecciona que quieres hacer: ");
        System.out.println("[1] Cargar partida");
        System.out.println("[2] Crear nueva partida");
        System.out.println("[3] Salir del juego");
        opcion = numero1al3();
        return opcion;
    }




    /**
     * Metodo para que el usuario introduzca los datos de cada jugador;
     * @param j jugador rival
     * @param t Tablero
     * @return Jugador con los datos definidos
     */
    public static Jugador menuJugador(Jugador j,Tablero t){
        Scanner sc = new Scanner(System.in);
        String valor;
        int i = (j==null)? 1:2;
        System.out.println("Dime el nombre que tiene el jugador"+i);
        if (i==2){
            valor = comprobarNombre(j.getNombre());
            System.out.println("El color que se le va a asignar por defecto al jugador 2 es el color "+((j.getColor().equals(Color.BLACK))?colorize(colorize("blanco",Attribute.BACK_COLOR(255,255,255)),Attribute.TEXT_COLOR(0,0,0)):colorize(colorize("negro",Attribute.TEXT_COLOR(0,0,0)),Attribute.BACK_COLOR(255,255,255)))+" pulsa enter para empezar");
            sc.nextLine();
            return (new Jugador(valor,t,j.getColor().next()));
        }else {
            Color c;
            valor= sc.nextLine();
            System.out.println("Dime el color que va a coger el jugador" + i);
            c = preguntarColor();
            return new Jugador(valor,t,c);
        }
    }

    /**
     * Metodo para que los jugadores no se llamen igual
     * @param nombreOtroJ Nombre del jugador rival
     * @return Nombre de jugador apto
     */
    public static String comprobarNombre(String nombreOtroJ){
        Scanner sc = new Scanner(System.in);
        String exit = sc.nextLine();
        while (exit.equals(nombreOtroJ)){
            System.out.println("Es aburrido jugar contra uno mismo escribe un nombre distinto al anterior");
            exit = sc.nextLine();
        }
        return exit;
    }

    /**
     * Metodo para que el usuario escoja un color de piezas
     * @return Un color apto para jugar
     */
    public static Color preguntarColor(){
        Scanner sc = new Scanner(System.in);
        String colorToString;
        colorToString = sc.nextLine();
        while (colorToString.compareToIgnoreCase("negro")!=0&&colorToString.compareToIgnoreCase("blanco")!=0){
            System.out.println("Eso que has escrito que es BLANCO O NEGRO");
            colorToString = sc.nextLine();
        }
        return (colorToString.compareToIgnoreCase("negro")==0)?Color.BLACK:Color.WHITE;
    }

    /**
     * Hace que el usuario seleccione un valor del 1 al 3
     * @return Un numero del uno al tres en formato String
     */
    public static String numero1al3(){
        Scanner sc = new Scanner(System.in);
        String exit = sc.nextLine();
        boolean valido = false;
        do {
            switch (exit){
                case ("1"):
                case ("2"):
                case ("3"):
                    valido = true;
                    break;
                default:
                    System.out.println("Valor no valido por favor inserte un valor del 1 al 3");
                    exit = sc.nextLine();
                    break;
            }
        }while (!valido);
        return exit;
    }



    public static String numero1al4(){
        Scanner sc = new Scanner(System.in);
        String exit = sc.nextLine();
        boolean valido = false;
        do {
            switch (exit){
                case ("1"):
                case ("2"):
                case ("3"):
                case ("4"):
                    valido = true;
                    break;
                default:
                    System.out.println("Valor no valido por favor inserte un valor del 1 al 4");
                    exit = sc.nextLine();
                    break;
            }
        }while (!valido);
        return exit;
    }

    /**
     * Metodo pausa de partida
     * @param t Tablero de Juego
     * @param j1 Jugador 1
     * @param j2 Jugador 2
     * @param turno Color al que le toca jugar
     * @return Un valor del 1 al tres en String
     */
    public static String opcionesDePausa(Tablero t, Jugador j1, Jugador j2, Color turno){
        String exit;
        boolean salir;
        do{
            System.out.println("----------------------PAUSA----------------------");
            System.out.println("[1]Salir del juego sin guardar");
            System.out.println("[2]Guardar partida y salir");
            System.out.println("[3]Reanudar");
            exit = numero1al3();
            salir = true;
            if (exit.equals("2"))
                salir = guardarPartida(t, j1, j2, turno);
        }while (!salir);
        return exit;
    }

    /**
     * Metodo para guardar los datos de la partida en un fichero
     * @param t Tablero
     * @param j1 Jugador 1
     * @param j2 Jugador 2
     * @param turno Color del turno al que le toca jugar
     */
    public static boolean guardarPartida(Tablero t, Jugador j1, Jugador j2, Color turno){
        String partida = Tool.ficheroVacio();
        if (!partida.equals("4")) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(partida))) {
                oos.writeObject(t);
                oos.writeObject(j1);
                oos.writeObject(j2);
                oos.writeObject(turno);
                System.out.println("Los datos se han guardado correctamente");
                return true;
            } catch (IOException e) {
                System.out.println("No se puede guardar el archivo");
                return false;
            }
        }
        return false;

    }


    public static String selccionarPartida() {
        Tool.borrar();
        mostrarPartidas();
        System.out.println("[1]Partida 1");
        System.out.println("[2]Partida 2");
        System.out.println("[3]Partida 3");
        System.out.println("[4]Atras");
        String option = numero1al4();
        return ((option.equals("1"))?Tool.partida1():(option.equals("2"))?Tool.partida2():(option.equals("3"))?Tool.partida3():"");
    }

    public static String selccionarPartidaParaCargar(){
        Scanner sc = new Scanner(System.in);
        String exit = selccionarPartida();
        while (!Tool.partidaNoVacia(exit)){
            if (exit.equals("4")){
                return exit;
            }
            System.out.println("Esa partida no esta disponible");
            sc.nextLine();
            exit = selccionarPartida();
        }
        return selccionarPartida();
    }

    public static void mostrarPartidas(){
        for (int i =0;i<3;i++) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream((i==0)?Tool.partida1():(i==1)?Tool.partida2():Tool.partida3()))) {

                Tablero t = (Tablero) ois.readObject();
                Jugador j1 = (Jugador) ois.readObject();
                Jugador j2 = (Jugador) ois.readObject();
                Color turno = (Color) ois.readObject();
                System.out.println("Partida "+ (i+1) + ":" );
                System.out.println();
                System.out.println("TABLERO:");
                System.out.println(t);
                System.out.println();
                System.out.println("Jugador 1: "+ j1.getNombre());
                System.out.println("Jugador 2: "+ j2.getNombre());
                System.out.println("Turno de " + ((turno.equals(j1.getColor()))?j1.getNombre():j2.getNombre()));
                System.out.println("________________________________________________________________________________________");


            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Partida "+ (i+1)+":");
                System.out.println("---------------Vacia-----------------");
                System.out.println("________________________________________________________________________________________");
            }
            System.out.println();
            System.out.println();
        }
    }

}
