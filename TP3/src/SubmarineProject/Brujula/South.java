package SubMarineProject.Brujula;

import SubMarineProject.Nemo;

public class South extends Brujula{
    public String name = "South";
    public Brujula turnRight(){
        return new West();
    }
    public Brujula turnLeft(){
        return new East();
    }
    public void move(Nemo nemo){
        nemo.backIny();
    }
}