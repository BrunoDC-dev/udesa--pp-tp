package SubMarineProject.Height;

public class ToDeep extends SubmergedLevel {
    String explosion_message = "Nemo exploded";

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
        throw new RuntimeException(explosion_message);
    }
}
