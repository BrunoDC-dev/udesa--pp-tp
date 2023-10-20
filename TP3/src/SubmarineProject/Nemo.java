package SubMarineProject;
import SubMarineProject.Coordenates.*;
import SubMarineProject.Direction.*;
import SubMarineProject.Messages.*;
import SubMarineProject.Height.Height;


import java.util.Arrays;
import java.util.List;

public class Nemo {
    private Direction direction;
    public Coordenates coordenadas;
    private Height height;
    private List<Message> possibleMessages = Arrays.asList(new Forward(), new Down(), new Up(), new Left(), new Right(), new LiberateCapsule());
    

    public Nemo( int x, int y){
        this.direction = new East();
        this.coordenadas = new Coordenates(new Point(x, y)); 
        this.height =  new Height();
    }

    public void recieveMessage(String string) {
        string.chars().mapToObj(letter -> (char) letter).forEach(this::recieveChar);
     
    }
    public void recieveChar(char letter) {
        possibleMessages.stream().filter(message -> message.applies(letter)).forEach(message-> message.Execute(this));
    }
    public void foward (){
         this.direction.move(this);
    }
    public void turnRight() {
        this.direction = this.direction.turnRight();
    }
    
    public void turnLeft(){
        this.direction = this.direction.turnLeft();
    }
    public void moveDown(){        
        this.height.Submerged();
    }
    public void moveUp(){
        this.height.Emerged();
    }
    public  void liberateCapsule(){
        height.LiberateCapsule();
    }
    public boolean isInSurface(){
        return height.isInSurface();
    }

    public int getHeight(){
        return this.height.getHeight();
    }
    public Direction getDirection(){
        return this.direction;
    }
    public Point getCoordenadas(){
        return this.coordenadas.getPoint();
    }
    public void add(Point point){
        this.coordenadas.add(point);
    }
}
