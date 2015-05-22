/**
 * CSE 373, Winter 2011, Jessica Miller
 * The BinaryHeap is an -generic- implementation of the PriorityQueue interface.  
 * This is a binary heap implementation of the priority queue ADT.
 */
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;
import java.lang.*;
import java.util.*;

class DFSTest{
    public static void main(String[] args){
        //Set up variables
        DFS searcher = new DFS();
        Scanner in = new Scanner(new InputStreamReader(System.in));
        int vertices = in.nextInt();
        int edges = in.nextInt();
        HashMap<Integer,LinkedList> graph = new HashMap<Integer,LinkedList>();
        boolean[] visited = new boolean[vertices+1];
        Integer v1;
        Integer v2;

        //Build a graph
        for(int i = 0; i < edges; i++){
            v1 = new Integer(in.nextInt());
            v2 = new Integer(in.nextInt());
            visited[v1.intValue()] = false;
            visited[v2.intValue()] = false;
            LinkedList list1 = new LinkedList();
            LinkedList list2 = new LinkedList();

            if(graph.containsKey(v1)){
                list1 = graph.get(v1);
                list1.add(v2);
                graph.put(v1,list1);
            }
            if(graph.containsKey(v2)){
                list2 = graph.get(v2);
                list2.add(v1);
                graph.put(v2,list2);
            }
            if(!graph.containsKey(v1)){
                list1.add(v2);
                graph.put(v1,list1);
            }
            if(!graph.containsKey(v2)){
                list2.add(v1);
                graph.put(v2,list2);
            }
        }
        //Search for a path
        Integer u = new Integer(in.nextInt());
        Integer v = new Integer(in.nextInt());
        
    }
}

private class BinaryHeap<T extends Comparable<T>> implements PriorityQueue<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] array;
    private int size;
    
    
    @SuppressWarnings("unchecked")
    private BinaryHeap () {
        // Java doesn't allow construction of arrays of placeholder data types 
        array = (T[])new Comparable[DEFAULT_CAPACITY];  
        size = 0;
    }
    
    
    private void add(T value) {
        // grow array if needed
        if (size >= array.length - 1) {
            array = this.resize();
        }        
        
        // place element into heap at bottom
        size++;
        int index = size;
        array[index] = value;
        
        bubbleUp();
    }
    
    
    private boolean isEmpty() {
        return size == 0;
    }

    
    private T peek() {
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        
        return array[1];
    }

    
    private T remove() {
        // what do want return?
        T result = peek();
        
        // get rid of the last leaf/decrement
        array[1] = array[size];
        array[size] = null;
        size--;
        
        bubbleDown();
        
        return result;
    }
    
    
    private String toString() {
        return Arrays.toString(array);
    }

    
    private void bubbleDown() {
        int index = 1;
        
        // bubble down
        while (hasLeftChild(index)) {
            // which of my children is smaller?
            int smallerChild = leftIndex(index);
            
            // bubble with the smaller child, if I have a smaller child
            if (hasRightChild(index)
                && array[leftIndex(index)].compareTo(array[rightIndex(index)]) > 0) {
                smallerChild = rightIndex(index);
            } 
            
            if (array[index].compareTo(array[smallerChild]) > 0) {
                swap(index, smallerChild);
            } else {
                // otherwise, get outta here!
                break;
            }
            
            // make sure to update loop counter/index of where last el is put
            index = smallerChild;
        }        
    }
    
    
    private void bubbleUp() {
        int index = this.size;
        
        while (hasParent(index)
                && (parent(index).compareTo(array[index]) > 0)) {
            // parent/child are out of order; swap them
            swap(index, parentIndex(index));
            index = parentIndex(index);
        }        
    }
    
    
    private boolean hasParent(int i) {
        return i > 1;
    }
    
    
    private int leftIndex(int i) {
        return i * 2;
    }
    
    
    private int rightIndex(int i) {
        return i * 2 + 1;
    }
    
    
    private boolean hasLeftChild(int i) {
        return leftIndex(i) <= size;
    }
    
    
    private boolean hasRightChild(int i) {
        return rightIndex(i) <= size;
    }
    
    
    private T parent(int i) {
        return array[parentIndex(i)];
    }
    
    
    private int parentIndex(int i) {
        return i / 2;
    }
    
    
    private T[] resize() {
        return Arrays.copyOf(array, array.length * 2);
    }
    
    
    private void swap(int index1, int index2) {
        T tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;        
    }
}