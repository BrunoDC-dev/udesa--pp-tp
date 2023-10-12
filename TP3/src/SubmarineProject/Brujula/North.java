package SubMarineProject.Brujula;

public class North extends Brujula{
    public String name = "North";
    public Brujula turnRight(){
        return new East();
    }
    public Brujula turnLeft(){
       return new West();
    }
    public int[] move(){
        int[]  coordenadas = {0,1};
        return coordenadas;
    }

}
