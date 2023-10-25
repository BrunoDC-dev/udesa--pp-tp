package SubMarineProject;


public class North extends Direction{
    public String name = "North";
    public Direction turnRight(){
        return new East();
    }
    public Direction turnLeft(){
       return new West();
    }
    public void move(Nemo nemo){
        nemo.updatePosition(new Point(0, 1));
    }
    }