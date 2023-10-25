package SubMarineProject;

public class Coordinates {
    private Point position;
    public Coordinates(Point point) {
        this.position = point;
    }
    public Point getPosition() {
        return position;
    }
    public void updateCoordinates(Point point) {
        int x = this.position.getXcoord() + point.getXcoord();
        int y = this.position.getYcoord() + point.getYcoord();
        this.position = new Point(x, y);
    }
}
