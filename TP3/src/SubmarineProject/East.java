package SubMarineProject;



public class East extends Direction{
    public String name = "East";    
    public Direction turnRight(){
    return new South();
    }
    public Direction turnLeft(){
       return new North();
    }
    public void move(Nemo nemo){
         nemo.updatePosition(new Point(1, 0));
    }

    
}