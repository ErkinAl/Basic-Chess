public class King extends Piece{
    public King(int color, Square location){
        super(color,location);
    }

    @Override
    public boolean canMove(String to) {
        boolean validMove = false;
        Square targetLocation = location.getBoard().getSquareAt(to);
        int rowDistance = targetLocation.getRowDistance(location);
        int colDistance = targetLocation.getColDistance(location);

        //King only move one square in any direction he wants.

        if (rowDistance <= 1 && colDistance <= 1){
            if (targetLocation.getPiece() == null){
                validMove = true;
            } else if (color == ChessBoard.WHITE) {
                validMove = targetLocation.getPiece().getColor() != ChessBoard.WHITE;
            }else {
                validMove = targetLocation.getPiece().getColor() != ChessBoard.BLACK;
            }
        }
    return validMove;}


    public void move(String to){
        Square targetLocation = location.getBoard().getSquareAt(to);
        targetLocation.setPiece(this);
        location.clear();
        location = targetLocation;
        location.getBoard().nextPlayer();
    }

    @Override
    public String toString(){
        return color == ChessBoard.WHITE ? "K" : "k";
    }



}
