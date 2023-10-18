package SubMarineProject.Direction;

import SubMarineProject.Nemo;

public class East extends Direction{
    public String name = "East";    
    public Direction turnRight(){
    return new South();
    }
    public Direction turnLeft(){
       return new North();
    }
    public void move(Nemo nemo){
        nemo.forwardInX();
    }

    
}