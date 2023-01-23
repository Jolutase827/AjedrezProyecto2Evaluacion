package es.ieslavereda;

import es.ieslavereda.model.Cordenada;

public class Tool {
    public static Cordenada[] add(Cordenada[] cordenadas, Cordenada c){
        Cordenada[] aux = new Cordenada[cordenadas.length+1];
        for (int i = 0; i<cordenadas.length;i++)
            aux[i] = cordenadas[i];

        aux[cordenadas.length] = c;
        return aux;
    }
}
