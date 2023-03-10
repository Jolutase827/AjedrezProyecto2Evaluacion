package es.ieslavereda.model;


import es.ieslavereda.Colorines;

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

    /**
     * Comprueba si hay hacke mate hacia un color
     * @param color Color del que queremos comprobar si hay jaque mate
     * @return Si hay jaque mate o sin no en formato boolean
     */
    public boolean oneColorJakeMate(Color color){
            return movementsValid(color).size()==0;
    }

    /**
     * Comprueba que cantidad de movimientos son validos por parte de un color
     * @param color Color del jugador que queremos saber sus movimientos
     * @return Conjunto de movimientos
     */
    public Set<Cordenada> movementsValid(Color color){
        Set<Cordenada> cordenadas= new HashSet<>();
        for (Cordenada c : celdas.keySet()){
            if (!getCelda(c).isEmpty())
                if (getCelda(c).getPiece().getType().getColor().equals(color))
                    cordenadas.addAll(movementsSalveKing(c,color));
        }
        return cordenadas;
    }


    /**
     * Movimientos que salvan al rey de una pieza
     * @param c Cordenada de la pieza que queremos comprobar sus movimientos
     * @param color Color de la pieza que queremos saber su movimiento
     * @return Conjunto de movimientos que salvan al rey de la pieza
     */
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

    /**
     * Las piezas que salvan al rey
     * @param c Cordenada de la pieza que puede salvar al rey
     * @param color Color de la pieza
     * @return Las piezas que salvan al rey
     */
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

    /**
     * Reseta los movimientos echos
     * @param retorno Donde quiere volver la pieza movida
     * @param movida Donde esta la pieza movida
     * @param p Pieza que hay que revivir si la han matado
     */
    public void resetMovement(Cordenada retorno, Cordenada movida,Piece p){
        getCelda(movida).getPiece().falseMoveto(retorno);
        if (p!=null)
            p.putInYourPlace();
    }


    /**
     * Comprueba si hay jake hacia el rey de un color
     * @param color Color del rey que queremos ver si hay jaque
     * @return Si esta en jaque o no en boolean
     */
    public  boolean oneColorJake(Color color){
        return getAllOneColorMovements(color.next()).contains(getKingPosition(color));
    }

    /**
     * Coge todos los movimientos de un color
     * @param color Color del las piezas que queremos saber sus movimientos
     * @return Conjunto de posibles movimientos
     */
    public Set<Cordenada> getAllOneColorMovements(Color color){
        Set<Cordenada> exit = new HashSet<>();
        for (Cordenada c: celdas.keySet()){
            if (!getCelda(c).isEmpty())
                if (getCelda(c).getPiece().getType().getColor().equals(color))
                    exit.addAll(getCelda(c).getPiece().getNextMovements());

        }
        return exit;
    }

    /**
     * Busca la posición del rey
     * @param color Color del rey que queremos saber la posición
     * @return Cordenada donde se encuentra el rey
     */
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
        String salida = "       "+Colorines.BLACK_BACKGROUND+ "    A  B  C  D  E  F  G  H    "+Colorines.RESET+"\n";

        for (int i = 0; i<8;i++) {
            salida += "       "+Colorines.BLACK_BACKGROUND+ " " + (i + 1) + " "+Colorines.RESET;
            for (int j = 0; j <8; j++)
                salida += celdas.get(new Cordenada((char)('A'+j),1+i));
            salida += Colorines.BLACK_BACKGROUND+" " + (i+1)+" "+Colorines.RESET+"\n";
        }

        salida +=  "       " +Colorines.BLACK_BACKGROUND+"    A  B  C  D  E  F  G  H    "+Colorines.RESET+"\n\n\n"+ deletePieceManager ;


        return salida;
    }
}
