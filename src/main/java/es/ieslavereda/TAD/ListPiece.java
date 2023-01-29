package es.ieslavereda.TAD;

import es.ieslavereda.model.Piece;

public class ListPiece {
    private int size;

    private NodePiece head;

    private NodePiece tail;

    public ListPiece(){
        size =0;
        head = null;
        tail = null;
    }

    public void addHead(Piece p){
        NodePiece np = new NodePiece(p);
        if (head==null){
            head = np;
            tail = np;
        }else {
            np.setNext(head);
            head = np;
        }
        size++;
    }

    public int countTypeOfPieces(Piece.Type pt){
        Piece p;
        int count=0;
        NodePiece aux = head;
        while(aux.getNext()!=null){
            p = aux.getInfo();

            if (p.getType()==pt)
                count++;

            aux = aux.getNext();
        }
        p = aux.getNext().getInfo();
        if (p.getType()==pt)
            count++;

        return count;
    }

    public Piece remove(int position){
        if (position>=size||position<0)
            return null;

        Piece removed;

        if (position==0){
            removed = head.getInfo();
            head = head.getNext();
        }else {
            NodePiece aux1 = head;
            NodePiece aux2 = aux1.getNext();
            while (position>1){
                aux1 = aux2;
                aux2 = aux2.getNext();
                position--;
            }
            removed = aux2.getInfo();
            aux1 = aux2.getNext();
        }
        size--;
        return removed;
    }

    @Override
    public String toString(){
        return  "ListPiece{ size: "+size+" Pieces: "+head;
    }

}
