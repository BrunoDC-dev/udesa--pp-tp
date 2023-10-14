package SubMarineProject.Height;

public class ToDeep extends SubmergedLevel {

    private SubmergedLevel previousLevel;


    public ToDeep(SubmergedLevel previousLevel){
        this.previousLevel = previousLevel;
    }
    public boolean isInSurface(){
        return false;
    }
    public SubmergedLevel Emerged(){
        return previousLevel;
    }
    public SubmergedLevel Submerged(){
        return new ToDeep(this);
    }
    public int Height(){
        return previousLevel.Height() - 1;
    }
    public int LiberateCapsule(){
        throw new RuntimeException("Nemo exploded");
    }
}
