package SubMarineProject;

public class ToDeep extends SubmergedLevel {
    public String explosion_message = "Nemo exploded";

    private SubmergedLevel previousLevel;

    public ToDeep(SubmergedLevel previousLevel){
        this.previousLevel = previousLevel;
    }
    public boolean isInSurface(){
        return false;
    }
    public SubmergedLevel emerge(){
        return previousLevel;
    }
    public SubmergedLevel submerge(){
        return new ToDeep(this);
    }
    public int getDepth(){
        return previousLevel.getDepth() - 1;
    }
    public int liberateCapsule(){
        throw new RuntimeException(explosion_message);
    }
}
