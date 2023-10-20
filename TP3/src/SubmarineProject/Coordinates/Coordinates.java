package SubMarineProject.Coordinates;

public class Coordinates {
    private Point position;
    public Coordinates(Point point) {
        this.position = point;
    }
    public Point getPosition() {
        return position;
    }
    public void updateCoordinates(Point point) {
        int x = this.position.getXcoord() + position.getXcoord();
        int y = this.position.getYcoord() + position.getYcoord();
        this.position = new Point(x, y);
    }
}
