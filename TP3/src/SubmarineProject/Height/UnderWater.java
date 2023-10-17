package SubMarineProject.Height;

public class UnderWater extends SubmergedLevel {
    public boolean isInSurface(){
        return false;
    }
    public SubmergedLevel Emerged(){
        return new Surface();
    }
    public SubmergedLevel Submerged(){
        return new ToDeep(this);
    }

    public int Height(){
        return -1;
    }
}
