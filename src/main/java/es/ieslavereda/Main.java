package es.ieslavereda;

import es.ieslavereda.model.Cordenada;
import es.ieslavereda.model.Tablero;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Tablero t = new Tablero();
        Game.start(t);
    }
}