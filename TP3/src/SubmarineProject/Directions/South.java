package SubMarineProject.Directions;

import SubMarineProject.Nemo;
import SubMarineProject.Coordinates.Point;

public class South extends Direction{
    public String name = "South";
    public Direction turnRight(){
        return new West();
    }
    public Direction turnLeft(){
        return new East();
    }
    public void move(Nemo nemo){

        nemo.updatePosition(new Point(0, -1));
    }
}