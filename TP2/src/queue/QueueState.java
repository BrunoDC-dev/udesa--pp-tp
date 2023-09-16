package queue;

public abstract class QueueState {
    public abstract boolean isEmpty();
    public abstract String head();
    public abstract QueueState take();
    public abstract QueueState add(String element);
    public abstract int size();
}
