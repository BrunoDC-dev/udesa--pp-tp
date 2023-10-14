package SubMarineProject.Messages;

import SubMarineProject.Nemo;

public abstract class Message {
    public abstract void Execute(Nemo nemo);
    public abstract String getMessage();
    public Message(char name) {
        this.name = name;
    }
    public char name;
    public boolean applies(char command){
        System.out.println("Comparing " + command + " with " + name);
        return command == name;
    }
}
