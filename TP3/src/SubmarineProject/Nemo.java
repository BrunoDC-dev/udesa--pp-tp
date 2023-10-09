package SubMarineProject;
import java.util.Arrays;


public class Nemo {
    private boolean isInSurface;
    private int height;
    private int direction;
    private int amountOfCapsules;
    private int[] coordenates;
    private int Xcoord;
    private int Ycoord;

    public Nemo(){
        this.isInSurface = true;
        this.height = 0;
        this.direction = 0;
        this.amountOfCapsules = 0;
        this.Xcoord = 0;
        this.Ycoord = 0;
        this.coordenates = new int[]{Xcoord, Ycoord};
    }
    public void recieveMessage(String... messages){
        Arrays.stream(messages).forEach(this::execute);
        // for(String message : messages){
        //     this.execute(message);
        // }
    }
    public void execute (String message){
        if(message.equals("d")){
            this.moveDown();
        }else if (message.equals("u")){
            this.moveUp();
        }else if(message.equals("l")){
            this.turnLeft();
        }else if(message.equals("r")){
            this.turnRight();
        }else if(message.equals("f")){
            this.move();
        }else if(message.equals("m")){
            this.liberateCapsule();
        }
    }
    public void move(){
        if(this.direction == 0){
            this.Xcoord += 1;
        }else if(this.direction == 90){
            this.Ycoord += 1;
        }else if(this.direction == 180){
            this.Xcoord -= 1;
        }else if(this.direction == 270){
            this.Ycoord -= 1;
        }
    }
    public void turnRight() {
        this.direction = (this.direction - 90 + 360) % 360;
    }
    
    public void turnLeft(){
        this.direction = (this.direction + 90 + 360) % 360;
    }
    public void moveDown(){
        this.height -= 1;
        this.isInSurface = false;
    }
    public void moveUp(){
        this.height += 1;
        if (getHeight() == 0){
            this.isInSurface = true;
        } 
    }
    public void liberateCapsule(){
        if(getHeight() >=-1){
            this.amountOfCapsules += 1;
        }else{
            throw new RuntimeException("Nemo exploded");
        }
    }
    public boolean isInSurface(){
        return this.isInSurface;
    }

    public int getHeight(){
        return this.height;
    }
    public int getDirection(){
        return this.direction;
    }
    public int getAmountOfCapsules(){
        return this.amountOfCapsules;
    }
    public int getXcoord(){
        return this.Xcoord;
    }
    public int getYcoord(){
        return this.Ycoord;
    }
    public int[] getCoordenates(){
        return this.coordenates;
    }

}
