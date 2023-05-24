import java.util.WeakHashMap;

public class Rook extends Piece{
    public Rook(int color, Square location){
        super(color,location);
    }

    @Override
    public boolean canMove(String to){
        boolean validMove = false;
        Square targetLocation = location.getBoard().getSquareAt(to);

        int rowDistance = targetLocation.getRowDistance(location);
        int colDistance = targetLocation.getColDistance(location);

        //Rook only just can move horizontally or vertically.

        if (rowDistance == 0 || colDistance == 0){
            Square[] betweenSquares = location.getBoard().getSquaresBetween(location,targetLocation);
        for (Square square: betweenSquares){
            if (square.getPiece() != null){return validMove;}}
        if (targetLocation.getPiece() == null) {
            validMove = true;
        }else if (color == ChessBoard.WHITE){
            validMove = targetLocation.getPiece().getColor() != ChessBoard.WHITE;
        }else {
            validMove = targetLocation.getPiece().getColor() != ChessBoard.BLACK;
            }
        }else {
            return validMove;
        }
        return validMove;
    }

    public void move(String to){
        Square targetLocation = location.getBoard().getSquareAt(to);
        targetLocation.setPiece(this);
        location.clear();
        location = targetLocation;
        location.getBoard().nextPlayer();
    }

    @Override
    public String toString(){
        return color == ChessBoard.WHITE ? "R" : "r";
    }


}
