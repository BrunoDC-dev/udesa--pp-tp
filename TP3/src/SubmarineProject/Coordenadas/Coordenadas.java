package SubMarineProject.Coordenadas;

public class Coordenadas {
    private int Xcoord = 0 ;
    private int Ycoord = 0 ;
    public int getXcoord() {
        return Xcoord;
    }
    public void addXcoord( ) {
        Xcoord += 1;
    }
    public void minusXcordo() {
        Xcoord -= 1;
    }
    public int getYcoord() {
        return Ycoord;
    }
    public void addYcoord() {
        Ycoord += 1;
    }
    public void minusYcoord() {
        Ycoord -= 1;
    }
    public int[] getCoordenates() {
        return new int[]{Xcoord, Ycoord};
    }
}
