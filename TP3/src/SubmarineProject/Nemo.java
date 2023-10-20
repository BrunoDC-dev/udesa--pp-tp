package SubMarineProject;
import SubMarineProject.Coordinates.*;
import SubMarineProject.Depths.SubmergedLevel;
import SubMarineProject.Depths.Surface;
import SubMarineProject.Directions.*;
import SubMarineProject.Messages.*;

import java.util.Arrays;
import java.util.List;

public class Nemo {
    private Direction direction;
    public Coordinates coordenadas;
    private SubmergedLevel level;
    private List<Message> availableMessages  = Arrays.asList(new Forward(), new Down(), new Up(), new Left(), new Right(), new LiberateCapsule());
    

    public Nemo( int x, int y){
        this.direction = new East();
        this.coordenadas = new Coordinates(new Point(x, y)); 
        this.level =  new Surface();
    }

    public void receiveMessage(String string) {
        string.chars().mapToObj(letter -> (char) letter).forEach(this::receiveChar);
     
    }
    public void receiveChar(char letter) {
        availableMessages.stream()
                        .filter(message -> message.applies(letter))
                        .forEach(message-> message.Execute(this));
    }
    public void forward (){
         this.direction.move(this);
    }
    public void turnRight() {
        this.direction = this.direction.turnRight();
    }
    
    public void turnLeft(){
        this.direction = this.direction.turnLeft();
    }
    public void moveDown(){
        this.level = this.level.submerge();
    }
    public void moveUp(){
        this.level = this.level.emerge();
    }
    public  void liberateCapsule(){
        this.level.liberateCapsule();
    }
    public boolean isInSurface(){
        return level.isInSurface();
    }
    
    public void updatePosition (Point point){
        this.coordenadas.updateCoordinates(point);
    }
    public int getDepth(){
        return this.level.getDepth();
    }
    public Direction getDirection(){
        return this.direction;
    }
    public Point getCoordinates(){
        return this.coordenadas.getPosition();
    }
}
