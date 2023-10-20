package SubMarineProject.Depths;

public class Underwater extends SubmergedLevel {
    public boolean isInSurface(){
        return false;
    }
    public SubmergedLevel emerge(){
        return new Surface();
    }
    public SubmergedLevel submerge(){
        return new ToDeep(this);
    }

    public int getDepth(){
        return -1;
    }
}
