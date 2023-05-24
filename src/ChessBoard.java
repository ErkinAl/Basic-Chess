public class ChessBoard {
    public static final int SIZE = 8;
    public static final int WHITE = 0;
    public static final int BLACK = 1;

    private Square[][] squares; // We will use a matrix for our board and connections.
    private boolean whitePlaying;

    public ChessBoard(){
        //squares = new Square[8][8]; // Our board will include 64 squares
        squares = new Square[SIZE][SIZE];
        whitePlaying = true;
        initializeBoard();
    }

    public boolean isWhitePlaying(){
        return whitePlaying;
    }

    public boolean isGameEnded(){ // We will check is the game ended with using counters.
        int whitePieceCount = 0;
        int blackPieceCount = 0;
        // Count the number of remaining pieces for each color
        for (int row = 0; row < SIZE; row++){
            for (int col = 0; col<SIZE; col++){
                Piece piece = squares[row][col].getPiece();
                if (piece != null){
                    if (piece.getColor() == WHITE){
                        whitePieceCount++;
                    }else {
                        blackPieceCount++;
                    }
                }
            }
        }
        if (whitePieceCount == 0){ //When counter downs zero game is over
            System.out.println("Game ended");
            System.out.println("Black Wins");
        } else if (blackPieceCount == 0){
            System.out.println("Game ended");
            System.out.println("White Wins");
        }
    return whitePieceCount == 0 || blackPieceCount == 0;
    }

    //public boolean isValidSquare(int row, int col){
    //    return row >= 0 && row < SIZE && col >= 0  && col < SIZE;}

    public int charToInt(char to){
        switch (to){
            case 'a':
                return 0;
            case 'b':
                return 1;
            case 'c':
                return 2;
            case 'd':
                return 3;
            case 'e':
                return 4;
            case 'f':
                return 5;
            case 'g':
                return 6;
            case 'h':
                return 7;
            default:
                return 999999;
        }
    }

    public Piece getPieceAt(String location){
        int col = charToInt(location.charAt(0));
        int row = 8 - Character.getNumericValue(location.charAt(1));
            return squares[row][col].getPiece();
            }


    public Square getSquareAt(String location) {
        int col = charToInt(location.charAt(0));
        int row = 8 - Character.getNumericValue(location.charAt(1));
        return squares[row][col];
    }

    public Square[][] getSquares(){
        return squares;
    }

    public void nextPlayer(){
        whitePlaying = !whitePlaying; // Next player command
    }

    private void initializeBoard() {
        // Initialize the chessboard with squares and pieces
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                squares[row][col] = new Square(row, col);
            }
        }

        //* White pieces
        squares[0][0].setPiece(new Rook(BLACK, squares[0][0]));
        squares[0][1].setPiece(new Knight(BLACK, squares[0][1]));
        squares[0][2].setPiece(new Bishop(BLACK, squares[0][2]));
        squares[0][3].setPiece(new Queen(BLACK, squares[0][3]));
        squares[0][4].setPiece(new King(BLACK, squares[0][4]));
        squares[0][5].setPiece(new Bishop(BLACK, squares[0][5]));
        squares[0][6].setPiece(new Knight(BLACK, squares[0][6]));
        squares[0][7].setPiece(new Rook(BLACK, squares[0][7]));

        for (int col = 0; col < SIZE; col++) {
            squares[1][col].setPiece(new Pawn(BLACK, squares[1][col]));
        }

         //Black pieces
        squares[7][0].setPiece(new Rook(WHITE, squares[7][0]));
        squares[7][1].setPiece(new Knight(WHITE, squares[7][1]));
        squares[7][2].setPiece(new Bishop(WHITE, squares[7][2]));
        squares[7][3].setPiece(new Queen(WHITE, squares[7][3]));
        squares[7][4].setPiece(new King(WHITE, squares[7][4]));
        squares[7][5].setPiece(new Bishop(WHITE, squares[7][5]));
        squares[7][6].setPiece(new Knight(WHITE, squares[7][6]));
        squares[7][7].setPiece(new Rook(WHITE, squares[7][7]));

        for (int col = 0; col < SIZE; col++) {
            squares[6][col].setPiece(new Pawn(WHITE, squares[6][col]));
        }
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
    }

    public Square getSquareAt(int row, int col){
        if (isValidPosition(row,col)){
            return squares[row][col];
        }else {
            return null;
        }
    }

    /*
    public Square[] getSquaresBetween(Square location, Square targetLocation) {
        if (location.isAtSameColumn(targetLocation)) {
            int numberOfSquaresBetween = targetLocation.getRow() - location.getRow();
            if (numberOfSquaresBetween < 0) {
                numberOfSquaresBetween = numberOfSquaresBetween * (-1);
            }

            Square[] tempArr = new Square[numberOfSquaresBetween];
            int counter = location.getRow() > targetLocation.getRow() ? targetLocation.getRow() : location.getRow();
            for (int i = 0; i < numberOfSquaresBetween; i++) {
                if (location.getPiece().color == ChessBoard.BLACK) {
                    tempArr[i] = this.board[counter + 1][location.getRow()];
                } else {
                    tempArr[i] = this.board[counter][location.getRow()];
                }
                counter++;
            }
            return tempArr;

        } else if (location.getRow() == targetLocation.getRow()) {
            int numberOfSquaresBetween = targetLocation.getColumn() - location.getColumn();
            numberOfSquaresBetween = numberOfSquaresBetween < 0 ? -1 : numberOfSquaresBetween;

            Square[] tempArr = new Square[numberOfSquaresBetween];
            int counter = location.getColumn() > targetLocation.getColumn() ? targetLocation.getColumn() : location.getColumn();
            for (int i = 0; i < numberOfSquaresBetween; i++) {
                tempArr[i] = board[location.getRow()][counter];
                counter++;
            }
            return tempArr;
        } else {
            return null;
        }
    } */

    public Square[] getSquaresBetween(Square location,Square targetLocation){
        Square[] array = new Square[9];
        if (location.isAtSameColumn(targetLocation)) {
            Square[] squaresBetween = new Square[Math.abs(location.getRow() - targetLocation.getRow())-1];
            int index = 0;
            if (location.getRow() > targetLocation.getRow()) {
                for (int i = targetLocation.getRow() + 1; i < location.getRow(); i++) {
                    squaresBetween[index] = squares[i][location.getColumn()];
                    index++;
                }
            } else if (location.getRow() < targetLocation.getRow()) {
                for (int i = targetLocation.getRow() - 1; i > location.getRow(); i--) {
                    squaresBetween[index] = squares[i][location.getColumn()];
                    index++;
                }
            }
            return squaresBetween;
        }else if(location.isAtSameRow(targetLocation)){
            Square[] squaresBetween = new Square[Math.abs(location.getColumn() - targetLocation.getColumn())-1];
            int index = 0;
            if (location.getColumn() > targetLocation.getColumn()){
                for (int i = targetLocation.getColumn() + 1; i < location.getColumn(); i++){
                    squaresBetween[index] = squares[location.getRow()][i];
                    index++;
                }
            }else if(location.getColumn() < targetLocation.getColumn()){
                for (int i = targetLocation.getColumn() - 1; i > location.getColumn(); i--){
                    squaresBetween[index] = squares[location.getRow()][i];
                    index++;
                }
            }
            return squaresBetween;
        }
        if (location.isInDiagonalWith(targetLocation)) {
            int rowDiff = Math.abs(targetLocation.getRow() - location.getRow());
            int colDiff = Math.abs(targetLocation.getColumn() - location.getColumn());
            int numSquaresBetween = Math.min(rowDiff, colDiff) - 1;

            Square[] squaresBetween = new Square[numSquaresBetween];
            int index = 0;

            int rowDirection = targetLocation.getRow() > location.getRow() ? 1 : -1;
            int colDirection = targetLocation.getColumn() > location.getColumn() ? 1 : -1;

            int currentRow = location.getRow() + rowDirection;
            int currentCol = location.getColumn() + colDirection;

            for (int i = 0; i < numSquaresBetween; i++) {
                squaresBetween[index] = squares[currentRow][currentCol];
                index++;
                currentRow += rowDirection;
                currentCol += colDirection;
            }

            return squaresBetween;
        }
        return array;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder = new StringBuilder("    A   B   C   D   E   F   G   H\n");
        stringBuilder.append("  ---------------------------------\n");
        for (int i = 0; i< 8; i++){
            stringBuilder.append(8 - i).append(" ");
            for (int k = 0; k < 8 ; k++){
                stringBuilder.append("| ");
                if (squares[i][k].isEmpty()){
                    stringBuilder.append("  ");
                }else{
                    stringBuilder.append(squares[i][k].getPiece().toString()).append(" ");
                }
            }
            stringBuilder.append("| ").append(8 - i).append(" ");
            stringBuilder.append("\n");
            stringBuilder.append("  ---------------------------------\n");
        }
        stringBuilder.append("    A   B   C   D   E   F   G   H");
        return stringBuilder.toString();
    }

}










































