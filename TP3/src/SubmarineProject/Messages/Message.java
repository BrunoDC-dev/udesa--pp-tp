package SubMarineProject.Messages;


import java.util.Arrays;
import java.util.List;

import SubMarineProject.Nemo;

public abstract class Message {
    public abstract void Execute(Nemo nemo);
    private static List<Message> availableMessages  = Arrays.asList(new Forward(), new Down(), new Up(), new Left(), new Right(), new LiberateCapsule());
    public Message(char name) {
        this.name = name;
    }
    public char name;
    public boolean applies(char command){
        return command == name;
    }
    public static Message getAvailableMessages( char letter){
        return availableMessages.stream()
        .filter(message -> message.applies(letter))
        .findFirst().orElse(null);
    }
}
