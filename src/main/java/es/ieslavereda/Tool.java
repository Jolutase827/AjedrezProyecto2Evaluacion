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

    public static Cordenada[] merge(Cordenada[] c1, Cordenada[] c2){
        Cordenada[] aux = new Cordenada[c1.length+c2.length];
        int x =0;
        for (int i = 0; i< c1.length;i++)
            aux[x++] = c1[i];
        for (int j = 0; j< c2.length;j++)
            aux[x++] = c2[j];
        return aux;
    }
}
