package SubMarineProject.Brujula;

import SubMarineProject.Nemo;

/**
 * West
 */
public class West extends Brujula{
    public String name = "West";
    public Brujula turnRight(){
       return new North();
    }
    public Brujula turnLeft(){
        return new South();
    }
    public void move(Nemo nemo){
        nemo.backInX();
    }
}