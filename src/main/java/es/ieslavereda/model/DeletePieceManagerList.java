package es.ieslavereda.model;

import com.diogonunes.jcolor.Attribute;

import java.util.LinkedList;
import java.util.List;

import static com.diogonunes.jcolor.Ansi.colorize;

public class DeletePieceManagerList implements IDeletePieceManager{
    private List<Piece> list;

    public DeletePieceManagerList(){
        list = new LinkedList<>();
    }

    @Override
    public void addPiece(Piece p) {
        list.add(p);
    }

    @Override
    public int count(Piece.Type pt) {
        int i=0;
        for (Piece p :list){
            if (p.getType()==pt){
                i++;
            }
        }
        return i;
    }

    @Override
    public Piece removeLast() {
        return list.remove(0);
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
