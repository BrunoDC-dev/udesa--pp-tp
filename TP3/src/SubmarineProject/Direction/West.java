package SubMarineProject.Direction;

import SubMarineProject.Nemo;

/**
 * West
 */
public class West extends Direction{
    public String name = "West";
    public Direction turnRight(){
       return new North();
    }
    public Direction turnLeft(){
        return new South();
    }
    public void move(Nemo nemo){
        nemo.backInX();
    }
}