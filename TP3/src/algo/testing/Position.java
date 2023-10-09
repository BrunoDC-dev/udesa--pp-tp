package algo.testing;

public class Position extends Nemo {
    
    private int x;
    private int y;
    private int angle;
    private int depth;
    
    public Position() {
        angle = 0;
        depth = 0;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getAngle() {
        return angle;
    }

    public int getDepth() {
        return depth;
    }

    // east = 0, north = 90, west = 180, south = 270
    public void moveForward() {
        if (this.angle == 0) {
            this.x++;
        } else if (this.angle == 90) {
            this.y++;
        } else if (this.angle == 180) {
            this.x--;
        } else if (this.angle == 270) {
            this.y--;
        }
    }

    public void rotateLeft() {
        this.angle = (this.angle + 90) % 360;
    }

    public void rotateRight() {
        this.angle = (this.angle - 90) % 360;
    }

    public void ascend() {
        if (this.depth > 0) {
            this.depth--;
        }
    }

    public void descend() {
        this.depth++;
    }
}
