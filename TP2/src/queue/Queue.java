
    package queue;

    import java.util.LinkedList;
    import java.util.NoSuchElementException;
    
    public class Queue {
    
        private LinkedList<Object> cargoList = new LinkedList<>();
    
        public boolean isEmpty() {
            return cargoList.isEmpty();
        }
    
        public Queue add(Object cargo) {
            cargoList.addLast(cargo);
            return this;
        }
    
        public Object take() {
            try {
                return cargoList.removeFirst();
            } catch (NoSuchElementException e) {
                throw new Error("Queue is empty");
            }
        }
    
        public Object head() {
            try {
                return cargoList.getFirst();
            } catch (NoSuchElementException e) {
                throw new Error("Queue is empty");
            }
        }
    
        public int size() {
            return cargoList.size();
        }
    }
