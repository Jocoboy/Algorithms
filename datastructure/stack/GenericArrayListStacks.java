package datastructure.stack;

import java.util.ArrayList;

/**
 * A GenericArrayListStack data structure functions the same as any specific-typed stack.
 * The GenericArrayListStack holds elemets of types to-be-specified at runtime.
 * The elements that are added first are the last to be removed (FILO).
 * New elements are added to the top of the stack. 
 * 
 * @param <T>
 */
class GenericArrayListStack<T>{

    private ArrayList<T> arrayListStack = new ArrayList<T>();

    public boolean isEmpty(){
        return arrayListStack.isEmpty();
    }

    public T push(T element){
        arrayListStack.add(element);
        return element;
    }

    public T pop(){
        if(!isEmpty()){
            return arrayListStack.remove(arrayListStack.size()-1);
        }
        return null;
    }

    public T peek(){
        return arrayListStack.get(arrayListStack.size()-1);
    }
}

public class GenericArrayListStacks {
    public static void main(String[] args) {
        GenericArrayListStack<Integer> stack = new GenericArrayListStack<Integer>();

        stack.push(5);
        stack.push(8);
        stack.push(2);
        stack.push(9);

        System.out.println("ArrayList<T> Stack Implementation.");
        System.out.println(stack.isEmpty()); // false
        System.out.println(stack.peek()); // 9
        System.out.println(stack.pop()); // 9
        System.out.println(stack.peek()); // 2
    }
}