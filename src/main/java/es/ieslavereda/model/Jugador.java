package es.ieslavereda.model;
/**
 * @author José Luis Tárraga
 */
public class Jugador {
    private String nombre;
    private Tablero tablero;
    private Color color;

    public Jugador(String nombre,Tablero tablero, Color color){
        this.nombre = nombre;
        this.tablero = tablero;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(nombre);
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof Jugador){
            Jugador j1 = (Jugador) obj;
            return j1.nombre.equals(nombre)&&j1.color.equals(color);
        }
        return false;
    }
}
