package SubMarineProject.Brujula;

import SubMarineProject.Nemo;

public class North extends Brujula{
    public String name = "North";
    public Brujula turnRight(){
        return new East();
    }
    public Brujula turnLeft(){
       return new West();
    }
    public void move(Nemo nemo){
        nemo.fowardInY();
    }
    }

