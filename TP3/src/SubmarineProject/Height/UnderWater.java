package SubMarineProject.Height;

public class UnderWater extends SubmergedLevel {
    private SubmergedLevel previousLevel;
    // ^ sacarlo
    //     v
    public UnderWater(SubmergedLevel previousLevel){
        this.previousLevel = previousLevel;
    }

    public boolean isInSurface(){
        return false;
    }
    public SubmergedLevel Emerged(){
        return previousLevel;
    }
    // public SubmergedLevel Emerged(){
    //     return new Surface();
    // }
    public SubmergedLevel Submerged(){
        return new ToDeep(this);
    }
    public int Height(){
        return previousLevel.Height() - 1;
    }
    // public int Height(){
    //     return -1;
    // }
}
