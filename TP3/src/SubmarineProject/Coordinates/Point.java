package SubMarineProject.Coordinates;

public class Point {
    private int Xcoord = 0 ;
    private int Ycoord = 0 ;
    public Point(int x, int y) {
        Xcoord = x;
        Ycoord = y;
    }
    public int getXcoord() {
        return Xcoord;
    }
    public int getYcoord (){
        return Ycoord;
    }
}
