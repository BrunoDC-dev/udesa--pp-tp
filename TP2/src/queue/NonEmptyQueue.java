package queue;

class NonEmptyQueue extends QueueState {
    private  String head;
    private  QueueState tail;

    public NonEmptyQueue(String head, QueueState tail) {
        this.head = head;
        this.tail = tail;
    }

    
    public boolean isEmpty() {
        return false;
    }

    
    public String head() {
        return head;
    }
    
    public QueueState take() {
        return tail;
    }

    
    public QueueState add(String element) {
        
        return new NonEmptyQueue(head, tail.add(element));
    }  

    
    public int size() {
        return 1 + tail.size();
    }
}