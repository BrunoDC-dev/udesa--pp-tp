package SubMarineProject.Messages;

import SubMarineProject.Nemo;

public abstract class Message {
    public abstract Nemo Execute(Nemo nemo);
    public abstract String getMessage();
    public String name;
    public boolean applies(String command){
        return command.equals(name);
    }
}
