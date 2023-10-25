package SubMarineProject;

public class Nemo {
    private Direction direction;
    public Coordinates coordenadas;
    private SubmergedLevel level;
    

    public Nemo( Point point, Direction direction){
        this.direction = direction;
        this.coordenadas = new Coordinates(point); 
        this.level =  new Surface();
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
