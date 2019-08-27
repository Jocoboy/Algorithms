package datastructure.queue;

import java.util.ArrayList;

/**
 * A GenericArrayListQueue data structure functions the same as any specific-typed queue.
 * The GenericArrayListQueue holds elemets of types to-be-specified at runtime.
 * The elements that are added first are the first to be removed (FIFO).
 * New elements are added to the back/rear of the queue. 
 * 
 * @param <T>
 */
class GenericArrayListQueue<T> {
    
    private ArrayList<T> queue = new ArrayList<T>();

    public boolean hasElemnt(){
        return !queue.isEmpty();
    }

    public boolean offer(T element){
        return queue.add(element);
    }

    public T poll(){
        T temp = null;
        if(this.hasElemnt()){
            temp = queue.remove(0);
        }
        return temp;   
    }

    public T peek(){
        T temp = null;
        if(this.hasElemnt()){
            temp = queue.get(0);
        }
        return temp;   
    }

    public int getSize(){
        return queue.size();
    }

    public static void main (String[] args){
        GenericArrayListQueue<Integer> queue = new GenericArrayListQueue<Integer>();
        System.out.println("Running...");
        assert queue.peek() == null;
        assert queue.poll() == null;
        assert queue.offer(1) == true;
        assert queue.peek() == 1;
        assert queue.offer(2) == true;
        assert queue.peek() == 1;
        assert queue.poll() == 1;
        assert queue.peek() == 2;
        assert queue.poll() == 2;
        assert queue.peek() == null;
        assert queue.poll() == null;
        System.out.println("Finished.");
    }
}
