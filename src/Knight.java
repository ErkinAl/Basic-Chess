public class Knight extends Piece{
    public Knight(int color, Square location){
        super(color,location);
    }

    @Override
    public boolean canMove(String to){

        boolean validMove = false;
        Square targetLocation = location.getBoard().getSquareAt(to);
        int rowDistance = targetLocation.getRowDistance(location);
        int columnDistance = targetLocation.getColDistance(location);
        rowDistance = Math.abs(rowDistance);
        columnDistance = Math.abs(columnDistance);

        // Jumps like L

        if((rowDistance == 2 && columnDistance == 1) || (rowDistance == 1 && columnDistance == 2)){
            if(targetLocation.getPiece()== null){
                validMove = true;
            }else if(color == ChessBoard.BLACK){
                validMove = targetLocation.getPiece().getColor() != ChessBoard.BLACK;
            }else{
                validMove = targetLocation.getPiece().getColor() != ChessBoard.WHITE;
            }
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
        return color == ChessBoard.WHITE ? "N" : "n";
    }

}
