package SubMarineProject;
import SubMarineProject.Coordenates.*;
import SubMarineProject.Direction.*;
import SubMarineProject.Messages.*;
import SubMarineProject.Height.Height;


import java.util.Arrays;
import java.util.List;

public class Nemo {
    private Direction direction;
    private Coordenates coordenadas;
    private Height height;
    private List<Message> possibleMessages = Arrays.asList(new Foward(), new Down(), new Up(), new Left(), new Right(), new LiberateCapsule());
    

    public Nemo(){
        this.direction = new East();
        this.coordenadas = new Coordenates(new Point(0, 0)); 
        this.height = new Height();
    }
    public void recieveMessage(String string) {
        string.chars()
              .mapToObj(letter -> (char) letter)
              .flatMap(letter -> possibleMessages.stream().filter(message -> message.applies(letter)))
              .forEach(message-> message.Execute(this));
    }
    public void move(){
         this.direction.move(this);
    }
    public Nemo turnRight() {
        this.direction = this.direction.turnRight();
        return this;
    }
    
    public Nemo turnLeft(){
        this.direction = this.direction.turnLeft();
        return this;
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
    public int getAmountOfCapsules(){
        return this.height.getAmountOfCapsules();
    }
    public Point getCoordenadas(){
        return this.coordenadas.getPoint();
    }
    public void fowardInX (){
        this.coordenadas.add(new Point(1, 0));
    }
    public void backInX (){
        this.coordenadas.add(new Point(-1, 0));
    }
    public void fowardInY (){
        this.coordenadas.add(new Point(0, 1));
    }
    public void backIny (){
        this.coordenadas.add(new Point(0, -1));
    }
}
