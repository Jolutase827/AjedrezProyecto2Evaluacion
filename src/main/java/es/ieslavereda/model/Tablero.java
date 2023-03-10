package es.ieslavereda.model;


import java.io.Serializable;
import java.util.*;

/**
 * @author José Luis Tárraga, Feat Joaquin
 */
public class Tablero implements Serializable {

    private Map<Cordenada,Celda> celdas;

    private IDeletePieceManager deletePieceManager;


    public Tablero(){
        this.deletePieceManager = new DeletePieceManagerList();
        celdas = new LinkedHashMap<>();
        for (int fila =0 ; fila<8; fila++)
            for ( int columna =0 ; columna< 8;columna++)
                celdas.put(new Cordenada((char)('A'+columna),1+fila),new Celda(this, new Cordenada((char)('A'+columna),1+fila)));

        placePieces();
    }

    public void placePieces(){
        new BlackKnight(getCelda(new Cordenada('G',1)));
        new BlackKnight(getCelda(new Cordenada('B',1)));
        new WhiteKnight(getCelda(new Cordenada('B',8)));
        new WhiteKnight(getCelda(new Cordenada('G',8)));
        new WhiteRook(getCelda(new Cordenada('H',8)));
        new WhiteRook(getCelda(new Cordenada('A',8)));
        new BlackRook(getCelda(new Cordenada('A',1)));
        new BlackRook(getCelda(new Cordenada('H',1)));
        new WhiteBishop(getCelda(new Cordenada('C',8)));
        new WhiteBishop(getCelda(new Cordenada('F',8)));
        new BlackBishop(getCelda(new Cordenada('C',1)));
        new BlackBishop(getCelda(new Cordenada('F',1)));
        new WhiteQueen(getCelda(new Cordenada('D',8)));
        new BlackQueen(getCelda(new Cordenada('D',1)));
        new WhiteKing(getCelda(new Cordenada('E',8)));
        new BlackKing(getCelda(new Cordenada('E',1)));
        for (int i = 0; i<8; i++) {
            new BlackPawn(getCelda(new Cordenada((char)('A'+i), 2)));
            new WhitePawn(getCelda(new Cordenada((char)('A'+i), 7)));
        }
    }

    public Celda getCelda(Cordenada cordenada){
        return celdas.get(cordenada);
    }


    public boolean oneColorJakeMate(Color color){
            return movementsValid(color).size()==0;
    }

    public Set<Cordenada> movementsValid(Color color){
        Set<Cordenada> cordenadas= new HashSet<>();
        for (Cordenada c : celdas.keySet()){
            if (!getCelda(c).isEmpty())
                if (getCelda(c).getPiece().getType().getColor().equals(color))
                    cordenadas.addAll(movementsSalveKing(c,color));
        }
        return cordenadas;
    }


    public Set<Cordenada> movementsSalveKing(Cordenada c,Color color){
        Set<Cordenada> cordenadasExit = new HashSet<>();
        Piece p;
        for (Cordenada aux : getCelda(c).getPiece().getNextMovements()){
            p=null;
            if (!getCelda(aux).isEmpty())
                p = getCelda(aux).getPiece();
            getCelda(c).getPiece().falseMoveto(aux);
            if (!oneColorJake(color))
                cordenadasExit.add(aux);
            resetMovement(c,aux,p);
        }
        return cordenadasExit;
    }

    public Set<Cordenada> piecesSalveKing(Cordenada c,Color color){
        Set<Cordenada> cordenadasExit = new HashSet<>();
        Piece p;
        for (Cordenada aux : getCelda(c).getPiece().getNextMovements()){
            p=null;
            if (!getCelda(aux).isEmpty())
                p = getCelda(aux).getPiece();
            getCelda(c).getPiece().falseMoveto(aux);
            if (!oneColorJake(color))
                cordenadasExit.add(c);
            resetMovement(c,aux,p);
        }
        return cordenadasExit;
    }

    public void resetMovement(Cordenada retorno, Cordenada movida,Piece p){
        getCelda(movida).getPiece().falseMoveto(retorno);
        if (p!=null)
            p.putInYourPlace();
    }


    public  boolean oneColorJake(Color color){
        return getAllOneColorMovements(color.next()).contains(getKingPosition(color));
    }


    public Set<Cordenada> getAllOneColorMovements(Color color){
        Set<Cordenada> exit = new HashSet<>();
        for (Cordenada c: celdas.keySet()){
            if (!getCelda(c).isEmpty())
                if (getCelda(c).getPiece().getType().getColor().equals(color))
                    exit.addAll(getCelda(c).getPiece().getNextMovements());

        }
        return exit;
    }


    public Cordenada getKingPosition(Color color){
        for (Cordenada c : celdas.keySet()){
            if (!getCelda(c).isEmpty())
                if (getCelda(c).getPiece().getType().getShape() == '♚' && getCelda(c).getPiece().getType().getColor().equals(color))
                    return c;
        }
        return null;
    }



    public void hightlight(Set<Cordenada> setcordenada){
        List<Cordenada> listCordenada = new LinkedList<>(setcordenada);
        for (Cordenada c : listCordenada){
            getCelda(c).highLight();
        }
    }

    public void resetColors(){
        for (Celda c : celdas.values())
                c.resetColor();
    }

    public boolean kingIsDead(){
        return deletePieceManager.count(Piece.Type.WHITE_KING)>0||deletePieceManager.count(Piece.Type.BLACK_KING)>0;
    }
    public void movePiece(Cordenada cPiece, Cordenada cMovement) {
        Celda celdaPiece = getCelda(cPiece);
        Celda celdaMovenet = getCelda(cMovement);
        if (celdaPiece != null && celdaMovenet != null) {
            if (!celdaPiece.isEmpty()) {
                if (!celdaMovenet.isEmpty())
                    deletePieceManager.addPiece(celdaMovenet.getPiece());
                else if (celdaPiece.getPiece() instanceof King) {
                    if (cMovement.equals(cPiece.left().left())) {
                        getCelda(cMovement.left().left()).getPiece().moveTo(cPiece.left());
                    } else if (cMovement.equals(cPiece.right().right())) {
                        getCelda(cMovement.right()).getPiece().moveTo(cPiece.right());
                    }
                }
                celdaPiece.getPiece().moveTo(cMovement);
            }
        }
    }

    @Override
    public String toString(){
        String salida = "            A  B  C  D  E  F  G  H\n";

        for (int i = 0; i<8;i++) {
            salida += "         " + (i + 1) + " ";
            for (int j = 0; j <8; j++)
                salida += celdas.get(new Cordenada((char)('A'+j),1+i));
            salida += " " + (i+1)+ "\n";
        }

        salida += "            A  B  C  D  E  F  G  H\n\n\n" + deletePieceManager;


        return salida;
    }
}
