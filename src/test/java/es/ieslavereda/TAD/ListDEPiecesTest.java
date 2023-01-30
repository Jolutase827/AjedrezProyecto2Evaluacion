package es.ieslavereda.TAD;

import es.ieslavereda.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class ListDEPiecesTest {

    private static Tablero board;


    @BeforeAll
    public static void initialize(){
        board = new Tablero();
    }
    @Test
    void addHead() {
        ListDEPieces listDEPieces = new ListDEPieces();
        listDEPieces.addHead(new WhiteQueen(new Celda(board,new Cordenada('a',4))));
        listDEPieces.addHead(new BlackQueen(new Celda(board,new Cordenada('a',4))));
        listDEPieces.addHead(new BlackRook(new Celda(board,new Cordenada('a',4))));
        listDEPieces.addHead(new WhiteRook(new Celda(board,new Cordenada('a',4))));

        assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                System.out.println(listDEPieces);
                System.out.println(listDEPieces.toStringReverse());
            }
        });

    }

    @Test
    void removeHead() {
        ListDEPieces listDEPieces = new ListDEPieces();
        listDEPieces.addHead(new WhiteQueen(new Celda(board,new Cordenada('a',4))));
        listDEPieces.addHead(new BlackQueen(new Celda(board,new Cordenada('a',4))));
        listDEPieces.addHead(new BlackRook(new Celda(board,new Cordenada('a',4))));
        listDEPieces.addHead(new WhiteRook(new Celda(board,new Cordenada('a',4))));

        listDEPieces.removeHead();

        assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                System.out.println(listDEPieces);
                System.out.println(listDEPieces.toStringReverse());
            }
        });

    }

    @Test
    void removeTail() {
        ListDEPieces listDEPieces = new ListDEPieces();
        listDEPieces.addHead(new WhiteQueen(new Celda(board,new Cordenada('a',4))));
        listDEPieces.addHead(new BlackQueen(new Celda(board,new Cordenada('a',4))));
        listDEPieces.addHead(new BlackRook(new Celda(board,new Cordenada('a',4))));
        listDEPieces.addHead(new WhiteRook(new Celda(board,new Cordenada('a',4))));

        listDEPieces.removeTail();

        assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                System.out.println(listDEPieces);
                System.out.println(listDEPieces.toStringReverse());
            }
        });


    }
}