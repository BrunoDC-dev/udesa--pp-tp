package queue;
class EmptyQueue extends QueueState {
    private String errorMessage ="Queue is empty";
    public boolean isEmpty() {
        return true;
    }

    
    public String head() {
        throw new Error(errorMessage);
    }

    
    public QueueState take() {
        throw new Error(errorMessage);
    }

    
    public QueueState add(String element) {
        return new NonEmptyQueue(element, this);
    }

    
    public int size() {
        return 0;
    }
}
