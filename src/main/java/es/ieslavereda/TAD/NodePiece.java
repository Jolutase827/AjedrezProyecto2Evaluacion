package es.ieslavereda.TAD;

import es.ieslavereda.model.Piece;

public class NodePiece {
    private Piece info;
    private NodePiece next;

    public NodePiece(Piece p){
        this.info = p;
        next = null;
    }

    public void setNext(NodePiece next) {
        this.next = next;
    }

    public NodePiece getNext() {
        return next;
    }

    public Piece getInfo() {
        return info;
    }

    @Override
    public String toString(){
        return info+((next!=null)?" "+next.info:"");
    }
}
