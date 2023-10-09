package testing;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public abstract class Nemo {
    
    private Position position;
    private boolean capsuleLaunched;
    private boolean destroyed;
    //private String commands;
    
    public Nemo() {
        this.position = new Position();
        // this.capsuleLaunched = false;
        // this.destroyed = false;
    }

    public void executeCommands(String commands) {
        Map<Character, Consumer<Nemo>> commandMap = new HashMap<>();
        commandMap.put('f', Nemo::moveForward);
        commandMap.put('l', Nemo::rotateLeft);
        commandMap.put('r', Nemo::rotateRight);
        commandMap.put('u', Nemo::ascend);
        commandMap.put('d', Nemo::descend);
        commandMap.put('c', Nemo::launchCapsule);
        commandMap.put('x', Nemo::destroy);
        for (char command : commands.toCharArray()) {
            commandMap.get(command).accept(this);
        }
    }

    public void moveForward() {
        position.moveForward();
    }
    public void descend() {
        position.descend();
    }
    public void ascend() {
        position.ascend();
    }
    public void rotateLeft() {
        position.rotateLeft();
    }
    public void rotateRight() {
        position.rotateRight();
    }
    public void launchCapsule() {
        capsuleLaunched = true;
    }
    // public abstract void descend();

    // public abstract void ascend();
    
    // public abstract void rotateLeft();
    
    // public abstract void rotateRight();
    
    // public abstract void moveForward();
    
    // public abstract void launchCapsule();

    public int getHeading() {
        return position.getAngle();}
    
    public int getDepth() {
        return position.getDepth();
    }
    
    public Position getPosition() {
        return position;
    }
    
    public boolean isCapsuleLaunched() {
        return capsuleLaunched;
    }
    
    public void setCapsuleLaunched(boolean capsuleLaunched) {
        this.capsuleLaunched = capsuleLaunched;
    }
    
    public boolean isDestroyed() {
        return destroyed;
    }
    
    public void destroy() {
        this.destroyed = true;
    }
}