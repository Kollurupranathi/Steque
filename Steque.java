/*
 *  File: Steque.java
 *  Author: 
 *  Date: 18th Nov, 2021
 *  ---------------------------------------
 *  Steque is stack-ended queue data structure which supports
 *  stack operations: pop and push, along with queue enqueue 
 *  operation.
 *  
 *  Salient features:
 *  1. Operations like push, pop, enqueue are supported.
 *  2. NullPointerException is thrown when null element is inserted.
 *  3. UnsupportedOperationException is thrown when using remove() method.
 *  4. The data structure is iterable and is implemented for generic type.
 *  
 */
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * 
 * Steque is a stack-ended data structure which 
 * supports stack operations as well as queue's 
 * enqueue operation.
 * 
 * @author 
 * @version 1.0
 *
 */
public class Steque<Item> implements Iterable<Item> {
    private Item[] s;
    private int N;
    private int INIT_CAPACITY=10;
    private int first;      // index of first element of queue
    private int last;
    /**
     * constructs a steque object.
     */
    public Steque() {
        N=0;
        s = (Item[]) new Object[INIT_CAPACITY];
        first=0;
        last=0;
    }
    
    
    /**
     * inserts an item in the steque in queue fashion. Time complexity: O(1), Space complexity: O(1)
     * @param item Item to be inserted.
     */
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (N == s.length) resize(2*s.length);
        if (first==0) first=s.length;
        s[first-1]=item;
        first=first-1;
        N++;
    }
    
    
    /**
     * inserts an item in the steque in stack fashion. Time complexity: O(1), Space complexity: O(1)
     * @param item Item to be inserted.
     */
    public void push(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (N == s.length) resize(2*s.length);
        s[last]=item;
        last++;
        if (last==s.length) last=0;
        N++;
    }
    
    /**
     * pops a least recent item in steque. Time complexity: O(1), Space complexity: O(1)
     * @return Item object from steque.
     */
    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        if (last==0) last=s.length;
        Item item=s[last-1];
        s[last-1]=null;
        last=last-1;
        N--;
        if (N > 0 && N == s.length/4) resize(s.length/2);
        return item;
    }
    
    /**
     * checks to see if steque is empty. Time complexity: O(1), Space complexity: O(1)
     * @return true if steque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return N==0;
    }
    
    /**
     * return the number of elements currently in the steque. Time complexity: O(1), Space complexity: O(1)
     * @return size as integer.
     */
    public int size() {
        return N;
    }
    // resize the underlying array holding the elements Time complexity: O(n), Space complexity: O(n)
    private void resize(int capacity) {
        assert capacity >= N;
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i< N; i++) {
            copy[i] = s[(first + i) % s.length];
            }
        s = copy;
        first = 0;
        last  = N;
    }
    
    /**
     * returns an iterator over the elements Time complexity: O(n), Space complexity: O(n)
     * stored in steque.
     * 
     */
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ReverseArrayIterator implements Iterator<Item> {
        
    }
}