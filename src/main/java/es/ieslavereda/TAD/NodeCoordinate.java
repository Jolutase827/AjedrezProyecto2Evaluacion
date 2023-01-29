package es.ieslavereda.TAD;

import es.ieslavereda.model.Cordenada;

public class NodeCoordinate {
    public Cordenada info;

    public NodeCoordinate next;

    public NodeCoordinate(Cordenada info){
        this.info = info;
        next = null;
    }

    public NodeCoordinate getNext() {
        return next;
    }

    public Cordenada getInfo() {
        return info;
    }

    public void setNext(NodeCoordinate next) {
        this.next = next;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof NodeCoordinate){
            NodeCoordinate aux = (NodeCoordinate) obj;
            return aux.info == info;
        }

        return false;

    }
    @Override
    public String toString(){
        return info.toString()+((next!=null)?" "+next.toString():"");
    }

}
