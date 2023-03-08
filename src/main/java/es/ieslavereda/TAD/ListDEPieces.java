package es.ieslavereda.TAD;

import es.ieslavereda.model.Piece;


import es.ieslavereda.model.Piece;

import java.io.Serializable;

/**
 * @author José Luis Tárraga, Feat Joaquin
 */
public class ListDEPieces implements Serializable {
    private int size;

    private Node head;

    private Node tail;

    public ListDEPieces() {
        size = 0;
        head = null;
        tail = null;
    }


    public void addHead(Piece piece) {
        Node node = new Node(piece);
        if (size == 0) {
            head = node;
            tail = node;
        } else {
            head.setPrevius(node);
            node.setNext(head);
            head = node;
        }
        size++;
    }


    public void addTail(Piece piece) {
        Node node = new Node(piece);
        if (size == 0) {
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            node.setPrevius(tail);
            tail = node;
        }
        size++;
    }

    public int countTypeP(Piece.Type pt) {
        int count = 0;
        Node aux = head;
        Piece.Type ptNode;

        while (aux != null) {
            ptNode = aux.getInfo().getType();
            if (pt.equals(ptNode))
                count++;

            aux = aux.getNext();
        }

        return count;
    }


    public Piece removeHead() {

        if (size == 0) {
            return null;
        }


        Piece removed = head.getInfo();

        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.getNext();
            head.setPrevius(null);
        }

        size--;
        return removed;
    }

    public Piece removeTail() {

        if (size == 0) {
            return null;
        }

        Piece removed = head.getInfo();

        if (size == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.getPrevius();
            tail.setNext(null);
        }

        size--;
        return removed;
    }


    @Override
    public String toString() {
        String output = "Lista{ Size: " + size + ", Values: ";
        Node aux = head;
        while (aux != null) {
            output += aux + " ";
            aux = aux.getNext();
        }
        return output + "}";
    }


    public String toStringReverse() {
        String output = "Lista{ Size: " + size + ", Values: ";
        Node aux = tail;
        while (aux != null) {
            output += aux + " ";
            aux = aux.getPrevius();
        }
        return output + "}";
    }


    class Node implements Serializable{
        private Piece info;

        private Node next;

        private Node previus;


        public Node(Piece info) {
            this.info = info;
            next = null;
            previus = null;
        }


        public Node getNext() {
            return next;
        }

        public Node getPrevius() {
            return previus;
        }

        public Piece getInfo() {
            return info;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setPrevius(Node previus) {
            this.previus = previus;
        }

        @Override
        public String toString() {
            return String.valueOf(info);
        }
    }
}
