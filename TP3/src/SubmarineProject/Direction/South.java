package SubMarineProject.Direction;

import SubMarineProject.Nemo;
import SubMarineProject.Coordenates.Point;

public class South extends Direction{
    public String name = "South";
    public Direction turnRight(){
        return new West();
    }
    public Direction turnLeft(){
        return new East();
    }
    public void move(Nemo nemo){

        nemo.add(new Point(0, -1));
    }
}