package es.ieslavereda.TAD;

import es.ieslavereda.model.Cordenada;

public class Node {
    public Cordenada info;

    public Node next;

    public Node(Cordenada info){
        this.info = info;
        next = null;
    }

    public Node getNext() {
        return next;
    }

    public Cordenada getInfo() {
        return info;
    }

    public void setNext(Node next) {
        this.next = next;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Node){
            Node aux = (Node) obj;
            return aux.info == info;
        }

        return false;

    }
    @Override
    public String toString(){
        return info.toString()+((next!=null)?" "+next.toString():"");
    }

}
