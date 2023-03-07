package es.ieslavereda.model;

import com.diogonunes.jcolor.Attribute;
import es.ieslavereda.TAD.ListDEPieces;

import static com.diogonunes.jcolor.Ansi.colorize;

/**
 * @author José Luis Tárraga, Feat Joaquin
 */
public class DeletePieceManagerTAD implements IDeletePieceManager{
    private ListDEPieces listDE;

    public DeletePieceManagerTAD(){
        listDE = new ListDEPieces();
    }

    @Override
    public void addPiece(Piece p) {
        listDE.addHead(p);
    }

    @Override
    public int count(Piece.Type pt) {
        return listDE.countTypeP(pt);
    }

    @Override
    public Piece removeLast() {
        return listDE.removeHead();
    }


    @Override
    public String toString() {
        String output="";
        Piece.Type[] pts = Piece.Type.values();
        for (Piece.Type pt : pts){
            if (pt.getColor()==Color.WHITE){
                output+= colorize(" "+pt.getShape()+" " ,pt.getColor().getAttribute(), Attribute.BACK_COLOR(100, 100, 100));
            }else {
                output += colorize(" "+pt.getShape()+" " , pt.getColor().getAttribute(),Attribute.BACK_COLOR(180, 180, 180));
            }
        }
        output+="\n";
        for (Piece.Type pt : pts){
            if (pt.getColor()==Color.BLACK){
                output+= colorize(" "+count(pt)+" " ,pt.getColor().getAttribute(), Attribute.BACK_COLOR(100, 100, 100));
            }else {
                output += colorize(" "+count(pt)+" ",pt.getColor().getAttribute() , Attribute.BACK_COLOR(180, 180, 180));
            }
        }

        return output;


    }
}
