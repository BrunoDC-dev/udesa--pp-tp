package SubMarineProject;



public class Nemo {
    private boolean isInSurface;
    private int heigth;
    private int direction;
    private int amountOfCapsules;
    private int[] coordenates;

    public Nemo(){
        this.isInSurface = true;
        this.heigth = 0;
        this.direction = 0;
        this.amountOfCapsules = 0;
        this.coordenates = new int[]{0, 0};
    }
    public void recieveMessage(String... messages){
        for(String message : messages){
            this.execute(message);
        }
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
            this.coordenates[0] += 1;
        }else if(this.direction == 90){
            this.coordenates[1] += 1;
        }else if(this.direction == 180){
            this.coordenates[0] -= 1;
        }else if(this.direction == 270){
            this.coordenates[1] -= 1;
        }
    }
    public void turnRight() {
        this.direction = (this.direction - 90 + 360) % 360;
    }
    
    public void turnLeft(){
        this.direction = (this.direction + 90 + 360) % 360;
    }
    public void moveDown(){
        this.heigth -= 1;
        this.isInSurface = false;
    }
    public void moveUp(){
        this.heigth += 1;
        if (getHeigth() == 0){
            this.isInSurface = true;
        } 
    }
    public void liberateCapsule(){
        if(getHeigth() >=-1){
            this.amountOfCapsules += 1;
        }else{
            throw new RuntimeException("Nemo exploded");
        }
    }
    public boolean isInSurface(){
        return this.isInSurface;
    }

    public int getHeigth(){
        return this.heigth;
    }
    public int getDirection(){
        return this.direction;
    }
    public int getAmountOfCapsules(){
        return this.amountOfCapsules;
    }
    public int[] getCoordenates(){
        return this.coordenates;
    }    
}
