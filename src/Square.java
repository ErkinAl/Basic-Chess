import java.util.Objects;

public class Square {
    private Piece piece;
    private int row;
    private int column;


    public Square(int row, int column){
        this.row = row;
        this.column = column;
        this.piece = null;
    }

    public int getRow(){
        return row;
    }

    public int getColumn(){
        return column;
    }

    public boolean isEmpty(){
        return piece == null;
    }

    public Piece getPiece(){
        return piece;
    }

    public void setPiece(Piece piece){
        this.piece = piece;
    }

    public void putNewQueen(int color){
        piece = new Queen(color, this);
    }

    public void clear(){
        piece = null;
    }

    public boolean isAtLastRow(int color){
        if (color == ChessBoard.BLACK){
            return row == ChessBoard.SIZE - 1;
        }else {
            return row == 0;
        }
    }

    @Override
    public String toString(){
        if (isEmpty()){
            return " ";
        }else {
            return piece.toString();
        }
    }

    public ChessBoard getBoard(){
        return Main.board;
    }

    public int getRowDistance(Square otherSquare){
        return otherSquare.getRow() - row;
    }

    public int getColDistance(Square otherSquare){
            return Math.abs(column - otherSquare.getColumn());
    }

    public boolean isAtSameColumn(Square otherSquare){
        return column == otherSquare.getColumn();
    }
    public boolean isAtSameRow(Square otherSquare){
        return row == otherSquare.getRow();
    }

    public boolean isNeighborColumn(Square otherSquare){
        int colDistance = Math.abs(column - otherSquare.getColumn());
        return colDistance == 1;
    }

    public boolean isInDiagonalWith(Square otherSquare) {
        int rowDiff = Math.abs(otherSquare.getRow() - this.getRow());
        int colDiff = Math.abs(otherSquare.getColumn() - this.getColumn());

        return rowDiff == colDiff;
    }


}
