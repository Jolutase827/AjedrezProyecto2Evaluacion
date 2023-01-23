package es.ieslavereda.model;

import com.diogonunes.jcolor.Attribute;

public enum Color {
    BLACK(Attribute.BLACK_TEXT()),
    WHITE(Attribute.TEXT_COLOR(255,255,255));

    private Attribute attribute;

    Color(Attribute attribute){
        this.attribute = attribute;
    }

    public Attribute getAttribute(){
        return attribute;
    }

    public Color next(){
        if (this.equals(WHITE))
            return BLACK;
        return WHITE;
        //return values()[(ordinal()+1)% values().length];
    }



}
