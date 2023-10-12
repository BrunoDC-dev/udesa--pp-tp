package SubMarineProject.Brujula;

public class South extends Brujula{
    public String name = "South";
    public Brujula turnRight(){
        return new West();
    }
    public Brujula turnLeft(){
        return new East();
    }
    public int[] move(){
        int[]  coordenadas = {0,-1};
        return coordenadas;
    }
}
