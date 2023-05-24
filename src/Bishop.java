public class Bishop extends Piece{
    public Bishop(int color, Square location){
        super(color,location);
    }

    @Override
    public boolean canMove(String to){
        boolean validMove = false;
        Square targetLocation = location.getBoard().getSquareAt(to);

        int rowDistance = targetLocation.getRowDistance(location);
        int colDistance = targetLocation.getColDistance(location);

        //Bishop just can't go straight.

        if (rowDistance == colDistance){
            Square[] betweenSquares = location.getBoard().getSquaresBetween(location,targetLocation);
            for (Square square: betweenSquares){
                if (square.getPiece() != null){return validMove;}}
            if (targetLocation.getPiece() == null){
                validMove = true;
            }else if (color == ChessBoard.WHITE){
                validMove = targetLocation.getPiece().getColor() != ChessBoard.WHITE;
            }else{
                validMove = targetLocation.getPiece().getColor() != ChessBoard.BLACK;}
        }else {
            return validMove;
        }

        return validMove; }



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
        return color == ChessBoard.WHITE ? "B" : "b";
    }
}
