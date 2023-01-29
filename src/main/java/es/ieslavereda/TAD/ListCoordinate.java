package es.ieslavereda.TAD;

import es.ieslavereda.model.Cordenada;

public class ListCoordinate {
    private int size;

    private NodeCoordinate head;

    private NodeCoordinate tail;

    public ListCoordinate(){
        head = null;
        tail = null;
        size=0;
    }
    public void add(Cordenada cordenada) {
        NodeCoordinate node = new NodeCoordinate(cordenada);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.setNext(head);
            head = node;
        }
        size++;

    }

    public boolean isEmpty(){
        return head==null;
    }


    public int size(){
        return size;
    }

    public Cordenada[] toArray(){
        Cordenada[] array = new Cordenada[size];
        NodeCoordinate aux = head;
        for (int i = 0; i<size;i++,aux=aux.getNext()){
            array[i] = aux.getInfo();
        }

        return array;
    }

    public Cordenada remove(int i){
        Cordenada removed;
        if (i>=size || i<0)
            return null;

        if(i == 0){
            removed = head.getInfo();
            head=head.getNext();

        }else {
            NodeCoordinate aux1 = head;
            NodeCoordinate aux2 = aux1.getNext();
            while (i > 1) {
                aux1 = aux2;
                aux2 = aux2.getNext();
                i--;
            }
            removed = aux2.getInfo();
            aux1 = aux2.getNext();
        }

        size--;
        return removed;
    }


    public boolean addAll(ListCoordinate l){
        if (l.head==null)
            return false;

//        tail.setNext(l.head);
//        tail = l.tail;
//        size += l.size;
        NodeCoordinate aux = l.head;
        while (aux!=null){
            addCola(aux.getInfo());
            aux = aux.getNext();
        }

        return true;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean contains(Cordenada c) {
        boolean encontrado = false;
        NodeCoordinate aux = head;
        while (aux != null && !encontrado) {
            if (aux.getInfo() == c)
                encontrado = true;
            aux = aux.getNext();
        }
        return encontrado;
    }

    public Cordenada get(int i) {
        boolean encontrado = false;
        if (i >= size || i < 0)
            return null;
        NodeCoordinate aux = head;
        while (i>0) {
            aux = aux.getNext();
            i--;
        }

        return aux.getInfo();
    }

    public void addCola(Cordenada cordenada) {
        NodeCoordinate node = new NodeCoordinate(cordenada);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            tail = node;
        }
        size++;

    }

    @Override
    public String toString() {
        return "ListCordinate{" +
                "size = " + size +
                ", valor = " + head + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ListCoordinate){
            ListCoordinate list = (ListCoordinate) obj;
            if (size != list.size())
                return false;

            boolean encontrado = false;
            NodeCoordinate aux1 = head;
            NodeCoordinate aux2 = list.head;
            while (!encontrado&&aux1!=null) {
                if (!aux1.equals(aux2))
                    encontrado = true;

                aux1 = aux1.getNext();
                aux2 = aux2.getNext();
            }

            return !encontrado;
        }
        return false;
    }



}
