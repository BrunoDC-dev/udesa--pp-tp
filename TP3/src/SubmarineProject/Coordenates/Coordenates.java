package SubMarineProject.Coordenates;

public class Coordenates {
    private Point point;
    public Coordenates(Point point) {
        this.point = point;
    }
    public Point getPoint() {
        return point;
    }
    public void add(Point point) {
        int x = this.point.getXcoord() + point.getXcoord();
        int y = this.point.getYcoord() + point.getYcoord();
        this.point = new Point(x, y);
    }
}
