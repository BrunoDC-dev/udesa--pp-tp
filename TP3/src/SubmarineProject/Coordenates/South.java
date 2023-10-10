package SubMarineProject.Coordenates;

public class South extends Coordenates{
    public String name = "South";
    public Coordenates turnRight(){
        return new West();
    }
    public Coordenates turnLeft(){
        return new East();
    }
    public int[] move(){
        int[]  coordenadas = {0,-1};
        return coordenadas;
    }
}
