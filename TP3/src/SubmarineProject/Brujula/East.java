package SubMarineProject.Brujula;

import SubMarineProject.Nemo;

/**
 * East
 */
public class East extends Brujula{
    public String name = "East";    
    public Brujula turnRight(){
    return new South();
    }
    public Brujula turnLeft(){
       return new North();
    }
    public void move(Nemo nemo){
        nemo.fowardInX();
    }

    
}