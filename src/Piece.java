public abstract class Piece {

    int color;
    Square location;

    public Piece(int color, Square location) {
        this.color = color;
        this.location = location;
    }

    public int getColor(){
        return color;
    }

    public Square getLocation(){
        return location;
    }

    public void setLocation(Square location){
        this.location = location;
    }

    public abstract boolean canMove(String to);

    public abstract void move(String to);

    public abstract String toString();


}
