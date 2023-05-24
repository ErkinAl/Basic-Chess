public class Queen extends Piece{
    boolean initialLocation = true;
    public Queen(int color, Square location){
        super(color,location);
    }

    public boolean canMove(String to){
        boolean validMove = false;
        Square targetLocation = location.getBoard().getSquareAt(to);
        int rowDistance = targetLocation.getRowDistance(location);
        int colDistance = targetLocation.getColDistance(location);

        //Queen can go anywhere she wants(Because she is our queen). Unless can't jump like knight through pieces.

        if (rowDistance == colDistance ||  rowDistance == 0 || colDistance == 0){
            Square[] betweenSquares = location.getBoard().getSquaresBetween(location,targetLocation);
        for (Square square: betweenSquares){
            if (square.getPiece() != null){return validMove;}}
        if (targetLocation.getPiece() == null){
            validMove = true;
        } else if (color == ChessBoard.WHITE){
            validMove = targetLocation.getPiece().getColor() != ChessBoard.WHITE;
        }else {
            validMove = targetLocation.getPiece().getColor() != ChessBoard.BLACK;
        }
        }else {
            return validMove;
        }
        return validMove;
    }

    @Override
    public void move(String to){
        Square targetLocation = location.getBoard().getSquareAt(to);
        targetLocation.setPiece(this);
        location.clear();
        location = targetLocation;
        location.getBoard().nextPlayer();
    }

    @Override
    public String toString(){
        return color == ChessBoard.WHITE ? "Q" : "q";
    }

}
