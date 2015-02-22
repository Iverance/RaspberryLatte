package edu.sjsu.cab.algorithm;

import java.util.ArrayList;
import java.util.List;

public class MaxHeap {
    //ArrayList to hold the heap
    List h = new ArrayList();
    public MaxHeap(){

    }
    //Constructs the heap - heapify
    public MaxHeap(List<Integer> e) {
        for(int i=0; i<e.size();i++)
        {
            add(e.get(i));
        }
    }
    
    private int intGet(int key){return new Integer(h.get(key).toString()).intValue();}
    
    public void add(int key){
        h.add(null);
        int k = h.size()-1;
        while (k>0){
            int parent = (k-1)/2;
            int parentValue = new Integer(h.get(parent).toString()).intValue();
            //MaxHeap -
            //for minheap - if(key > parentValue)
            if(key <= parentValue){
                break;
            }
            h.set(k,parentValue);
            k=parent;
        }
        h.set(k,key);
    }
    
    public int getMax(){ return new Integer(h.get(0).toString()).intValue();}
    
    public void percolateUp(int k, int key){
        if(h.isEmpty())
            return ;

        while(k < h.size() /2){
            int child = 2*k + 1; //left child
            if(child < h.size() -1 &&
               (new Integer(h.get(child).toString()).intValue() <
                new Integer(h.get(child+1).toString()).intValue()    )){
                child++;
            }
            if(key >= new Integer(h.get(child).toString()).intValue()){
                break;
            }
            h.set(k,new Integer(h.get(child).toString()).intValue());
            k=child;
        }
        h.set(k,key);
    }
    
    public int remove()
    {
        int removeNode = new Integer(h.get(0).toString()).intValue();
        int lastNode = new Integer(h.remove(h.size()-1).toString()).intValue();
        percolateUp(0,lastNode);
        return removeNode;
    }
    
    public boolean isEmpty() { return h.isEmpty();}
    
}