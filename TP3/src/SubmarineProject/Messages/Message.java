package SubMarineProject.Messages;

import SubMarineProject.Nemo;

public abstract class Message {
    public abstract void Execute(Nemo nemo);
    public Message(char name) {
        this.name = name;
    }
    public char name;
    public boolean applies(char command){
        return command == name;
    }
}
