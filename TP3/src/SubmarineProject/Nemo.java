package SubMarineProject;
import SubMarineProject.Coordinates.*;
import SubMarineProject.Depths.Depth;
import SubMarineProject.Directions.*;
import SubMarineProject.Messages.*;


public class Nemo {
    private Direction direction;
    public Coordinates coordenadas;
    private Depth depth;
    

    public Nemo( int x, int y){
        this.direction = new East();
        this.coordenadas = new Coordinates(new Point(x, y)); 
        this.depth =  new Depth();
    }

    public void receiveMessage(String string) {
        string.chars().forEach(letter -> receiveChar((char) letter));
     
    }
    public void receiveChar(char letter) {
        Message.getAvailableMessages(letter).Execute(this);
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
        this.depth.Submerged();
    }
    public void moveUp(){
        this.depth.Emerged();
    }
    public  void liberateCapsule(){
        depth.LiberateCapsule();
    }
    public boolean isInSurface(){
        return depth.isInSurface();
    }
    
    public void updatePosition (Point point){
        this.coordenadas.updateCoordinates(point);
    }
    public int getDepth(){
        return this.depth.getDepthLevel();
    }
    public Direction getDirection(){
        return this.direction;
    }
    public Point getCoordinates(){
        return this.coordenadas.getPosition();
    }

}
