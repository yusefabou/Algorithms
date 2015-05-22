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

class BFSTest{
    static final Integer infinity = Integer.MAX_VALUE;

    public static void main(String[] args){
        BFSTest searcher = new BFSTest();
        Scanner in = new Scanner(new InputStreamReader(System.in));
        int vertices = in.nextInt();
        int edges = in.nextInt();
        HashMap<Integer,HashMap<Integer,Integer>> graph = 
        new HashMap<Integer,HashMap<Integer,Integer>>();

        Integer[] distance = new Integer[vertices+1];
        int[] previous = new int[vertices+1];
        Integer v1;
        Integer v2;
        Integer weight;

        //Create directed graph, distances to infinity,  & 
        for(int i = 0; i < edges; i++){
            v1 = new Integer(in.nextInt());
            v2 = new Integer(in.nextInt());
            weight = new Integer(in.nextInt());
            distance[v1.intValue()] = infinity;
            distance[v2.intValue()] = infinity;
            previous[v1.intValue()] = -1;
            previous[v2.intValue()] = -1;
            HashMap<Integer,Integer> edgeList = new HashMap<Integer,Integer>();

            if(graph.containsKey(v1)){
                edgeList = graph.get(v1);
                edgeList.put(v2, weight);
                graph.put(v1,edgeList);
            }
            if(!graph.containsKey(v1)){
                edgeList.put(v2, weight);
                graph.put(v1,edgeList);
            }
        }


    }
}

class BinaryHeap<T extends Comparable<T>> implements AbstractQueue<T> {
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


    public String toString() {
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