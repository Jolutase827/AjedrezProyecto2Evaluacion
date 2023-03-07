package es.ieslavereda.entrada;

import es.ieslavereda.model.Color;
import es.ieslavereda.model.Jugador;
import es.ieslavereda.model.Tablero;
import com.diogonunes.jcolor.Attribute;
import java.util.Scanner;
import java.util.Set;

import static com.diogonunes.jcolor.Ansi.colorize;
/**
 * @author José Luis Tárraga
 */
public class Options {
    public static Tablero menuTablero(){
        Jugador j1;
        Jugador j2;
        String opcion = "";
        System.out.println("Selecciona que quieres hacer: ");
        System.out.println("[1] Cargar partida");
        System.out.println("[2] Crear nueva partida");
        System.out.println("[3] Salir del juego");
        opcion = numero1al3();
        return hacer(opcion);
    }


    public static Tablero hacer(String opcion){
        if (opcion.equals("1"))
            return new Tablero();
        else if (opcion.equals("2"))
            return new Tablero();
        else
            return null;
    }

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


    public static String comprobarNombre(String nombreOtroJ){
        Scanner sc = new Scanner(System.in);
        String exit = sc.nextLine();
        while (exit.equals(nombreOtroJ)){
            System.out.println("Es aburrido jugar contra uno mismo escribe un nombre distinto al anterior");
            exit = sc.nextLine();
        }
        return exit;
    }

    public static Color preguntarColor(){
        Scanner sc = new Scanner(System.in);
        String colorToString;
        System.out.println("Que color vas a escoger negro o blanco?");
        colorToString = sc.nextLine();
        while (colorToString.compareToIgnoreCase("negro")!=0&&colorToString.compareToIgnoreCase("blanco")!=0){
            System.out.println("Eso que has escrito que es BLANCO O NEGRO");
            colorToString = sc.nextLine();
        }
        return (colorToString.compareToIgnoreCase("negro")==0)?Color.BLACK:Color.WHITE;
    }


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
                    System.out.println("Valor no valido por favor inserte un valor del 1 al 3?");
                    exit = sc.nextLine();
                    break;
            }
        }while (!valido);
        return exit;
    }


}
