package SubMarineProject.Coordenates;

public class North extends Coordenates{
    public String name = "North";
    public Coordenates turnRight(){
        return new East();
    }
    public Coordenates turnLeft(){
       return new West();
    }
    public int[] move(){
        int[]  coordenadas = {0,1};
        return coordenadas;
    }

}
