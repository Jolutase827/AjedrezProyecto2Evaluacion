package es.ieslavereda.model;

public class Jugador {
    private String nombre;
    private Tablero tablero;

    public Jugador(String nombre,Tablero tablero){
        this.nombre = nombre;
        this.tablero = tablero;
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(nombre);
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof Jugador){
            Jugador j1 = (Jugador) obj;
            return nombre.equals(nombre);
        }
        return false;
    }
}
