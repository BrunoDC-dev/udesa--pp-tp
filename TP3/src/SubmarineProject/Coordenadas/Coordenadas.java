package SubMarineProject.Coordenadas;

public class Coordenadas {
    private int Xcoord = 0 ;
    private int Ycoord = 0 ;
    public int getXcoord() {
        return Xcoord;
    }
    public void addXcoord(int x) {
        Xcoord += x;
    }
    public void minusXcordo(int x) {
        Xcoord -= x;
    }
    public int getYcoord() {
        return Ycoord;
    }
    public void addYcoord(int y) {
        Ycoord += y;
    }
    public void minusYcoord(int y) {
        Ycoord -= y;
    }
    public int[] getCoordenates() {
        return new int[]{Xcoord, Ycoord};
    }
}
