package SubMarineProject.Depths;

public class Underwater extends SubmergedLevel {
    public boolean isInSurface(){
        return false;
    }
    public SubmergedLevel emerged(){
        return new Surface();
    }
    public SubmergedLevel submerged(){
        return new ToDeep(this);
    }

    public int depth(){
        return -1;
    }
}
