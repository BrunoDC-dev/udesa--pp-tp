package SubMarineProject.Direction;

import SubMarineProject.Nemo;
import SubMarineProject.Coordenates.Point;

public class North extends Direction{
    public String name = "North";
    public Direction turnRight(){
        return new East();
    }
    public Direction turnLeft(){
       return new West();
    }
    public void move(Nemo nemo){
        nemo.add(new Point(0, 1));
    }
    }