package SubMarineProject.Height;

public abstract  class SubmergedLevel {
    public abstract boolean isInSurface();
    public abstract SubmergedLevel Emerged();
    public abstract SubmergedLevel Submerged();
    public abstract int Height();
    public int LiberateCapsule(){
        return 1;
    }
}
