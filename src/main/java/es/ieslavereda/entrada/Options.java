package es.ieslavereda.entrada;

import es.ieslavereda.Colorines;
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
        System.out.println(Colorines.BLACK_BACKGROUND+Colorines.PURPLE_BOLD+"Selecciona que quieres hacer: "+Colorines.RESET);
        System.out.println(Colorines.BLACK_BACKGROUND+Colorines.PURPLE_BOLD+"[1] Cargar partida"+Colorines.RESET);
        System.out.println(Colorines.BLACK_BACKGROUND+Colorines.PURPLE_BOLD+"[2] Crear nueva partida"+Colorines.RESET);
        System.out.println(Colorines.BLACK_BACKGROUND+Colorines.PURPLE_BOLD+"[3] Partida online"+Colorines.RESET);
        System.out.println(Colorines.BLACK_BACKGROUND+Colorines.PURPLE_BOLD+"[4] Salir del juego"+Colorines.RESET);
        opcion = numero1al4();
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
        System.out.println(Colorines.BLACK_BACKGROUND+Colorines.GREEN_BOLD+"Dime el nombre que tiene el jugador"+i+Colorines.RESET);
        if (i==2){
            valor = comprobarNombre(j.getNombre());
            System.out.println(Colorines.BLACK_BACKGROUND+Colorines.GREEN_BOLD+"El color que se le va a asignar por defecto al jugador 2 es el color "+((j.getColor().equals(Color.BLACK))?colorize(colorize("blanco",Attribute.BACK_COLOR(255,255,255)),Attribute.TEXT_COLOR(0,0,0)):colorize(colorize("negro",Attribute.TEXT_COLOR(0,0,0)),Attribute.BACK_COLOR(255,255,255)))+Colorines.BLACK_BACKGROUND+Colorines.GREEN_BOLD+" pulsa enter para empezar"+Colorines.RESET);
            sc.nextLine();
            return (new Jugador(valor,t,j.getColor().next()));
        }else {
            Color c;
            valor= sc.nextLine();
            System.out.println(Colorines.BLACK_BACKGROUND+Colorines.GREEN_BOLD+"Dime el color que va a coger el jugador" + i+Colorines.RESET);
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
            System.out.println(Colorines.BLACK_BACKGROUND+Colorines.RED_BOLD+" El nombre del otro jugador es el mismo"+Colorines.RESET);
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
            System.out.println(Colorines.BLACK_BACKGROUND+Colorines.RED_BOLD+"Eso que has escrito que es BLANCO O NEGRO"+Colorines.RESET);
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
                    System.out.println(Colorines.BLACK_BACKGROUND+Colorines.RED_BOLD+"Valor no valido por favor inserte un valor del 1 al 3"+Colorines.RESET);
                    exit = sc.nextLine();
                    break;
            }
        }while (!valido);
        return exit;
    }


    /**
     * Pregunta un numero hasta que sea uno del 1 al 4
     * @return Numero del 1 al 4 en formato String
     */
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
                    System.out.println(Colorines.BLACK_BACKGROUND+Colorines.RED_BOLD+"Valor no valido por favor inserte un valor del 1 al 4"+Colorines.RESET);
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
            System.out.println(Colorines.BLACK_BACKGROUND+Colorines.PURPLE_BOLD+"----------------------PAUSA----------------------"+Colorines.RESET);
            System.out.println(Colorines.BLACK_BACKGROUND+Colorines.PURPLE_BOLD+"[1]Salir del juego sin guardar"+Colorines.RESET);
            System.out.println(Colorines.BLACK_BACKGROUND+Colorines.PURPLE_BOLD+"[2]Guardar partida y salir"+Colorines.RESET);
            System.out.println(Colorines.BLACK_BACKGROUND+Colorines.PURPLE_BOLD+"[3]Reanudar"+Colorines.RESET);
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
        Scanner sc = new Scanner(System.in);
        String partida = Tool.ficheroVacio();
        if (!partida.equals("4")) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(partida))) {
                oos.writeObject(t);
                oos.writeObject(j1);
                oos.writeObject(j2);
                oos.writeObject(turno);
                System.out.println(Colorines.BLACK_BACKGROUND+Colorines.PURPLE_BOLD+"Los datos se han guardado correctamente"+Colorines.RESET);
                sc.nextLine();
                Tool.borrar();
                return true;
            } catch (IOException e) {
                System.out.println(Colorines.BLACK_BACKGROUND+Colorines.RED_BOLD+"No se puede guardar el archivo"+Colorines.RESET);
                sc.nextLine();
                Tool.borrar();
                return false;
            }
        }
        Tool.borrar();
        return false;

    }

    /**
     * Hace que el usuario seleccione una Partida
     * @return El fichero de partida seleccionado
     */
    public static String selccionarPartida() {
        Tool.borrar();
        mostrarPartidas();
        System.out.println(Colorines.BLACK_BACKGROUND+Colorines.GREEN_BOLD+"[1]Partida 1"+Colorines.RESET);
        System.out.println(Colorines.BLACK_BACKGROUND+Colorines.GREEN_BOLD+"[2]Partida 2"+Colorines.RESET);
        System.out.println(Colorines.BLACK_BACKGROUND+Colorines.GREEN_BOLD+"[3]Partida 3"+Colorines.RESET);
        System.out.println(Colorines.BLACK_BACKGROUND+Colorines.GREEN_BOLD+"[4]Atras"+Colorines.RESET);
        String option = numero1al4();
        Tool.borrar();
        return ((option.equals("1"))?Tool.partida1():(option.equals("2"))?Tool.partida2():(option.equals("3"))?Tool.partida3():"4");
    }

    /**
     * Hace que el usuario seleccione una partida para Cargar
     * @return Fichero donde esta la partida que se va a cargar
     */
    public static String selccionarPartidaParaCargar(){
        Scanner sc = new Scanner(System.in);
        String exit = selccionarPartida();
        while (!Tool.partidaNoVacia(exit)){
            if (exit.equals("4")){
                return exit;
            }
            System.out.println(Colorines.BLACK_BACKGROUND+Colorines.RED_BOLD+"Esa partida no esta disponible"+Colorines.RESET);
            sc.nextLine();
            exit = selccionarPartida();
        }
        Tool.borrar();
        return exit;
    }

    /**
     * Muestra las partidas que existen
     */
    public static void mostrarPartidas(){
        for (int i =0;i<3;i++) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream((i==0)?Tool.partida1():(i==1)?Tool.partida2():Tool.partida3()))) {

                Tablero t = (Tablero) ois.readObject();
                Jugador j1 = (Jugador) ois.readObject();
                Jugador j2 = (Jugador) ois.readObject();
                Color turno = (Color) ois.readObject();
                System.out.println(Colorines.BLACK_BACKGROUND+Colorines.PURPLE_BOLD+"Partida "+ (i+1) + ":" +Colorines.RESET);
                System.out.println();
                System.out.println(Colorines.BLACK_BACKGROUND+Colorines.PURPLE_BOLD+"TABLERO:"+Colorines.RESET);
                System.out.println(t);
                System.out.println();
                System.out.println(Colorines.BLACK_BACKGROUND+Colorines.PURPLE_BOLD+"Jugador 1: "+ j1.getNombre()+Colorines.RESET);
                System.out.println(Colorines.BLACK_BACKGROUND+Colorines.PURPLE_BOLD+"Jugador 2: "+ j2.getNombre()+Colorines.RESET);
                System.out.println(Colorines.BLACK_BACKGROUND+Colorines.PURPLE_BOLD+"Turno de " + ((turno.equals(j1.getColor()))?j1.getNombre():j2.getNombre())+Colorines.RESET);
                System.out.println(Colorines.BLACK_BACKGROUND+Colorines.PURPLE_BOLD+"________________________________________________________________________________________"+Colorines.RESET);


            } catch (IOException | ClassNotFoundException e) {
                System.out.println(Colorines.BLACK_BACKGROUND+Colorines.PURPLE_BOLD+"Partida "+ (i+1)+":"+Colorines.RESET);
                System.out.println(Colorines.BLACK_BACKGROUND+Colorines.PURPLE_BOLD+"---------------Vacia-----------------"+Colorines.RESET);
                System.out.println(Colorines.BLACK_BACKGROUND+Colorines.PURPLE_BOLD+ "________________________________________________________________________________________"+Colorines.RESET);
            }
            System.out.println();
            System.out.println();
        }
    }

}
