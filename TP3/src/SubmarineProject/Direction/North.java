package SubMarineProject.Direction;

import SubMarineProject.Nemo;

public class North extends Direction{
    public String name = "North";
    public Direction turnRight(){
        return new East();
    }
    public Direction turnLeft(){
       return new West();
    }
    public void move(Nemo nemo){
        nemo.fowardInY();
    }
    }

