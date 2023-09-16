package queue;
class EmptyQueue extends QueueState {
    
    public boolean isEmpty() {
        return true;
    }

    
    public String head() {
        throw new Error("Queue is empty");
    }

    
    public QueueState take() {
        throw new Error("Queue is empty");
    }

    
    public QueueState add(String element) {
        return new NonEmptyQueue(element, this);
    }

    
    public int size() {
        return 0;
    }
}
