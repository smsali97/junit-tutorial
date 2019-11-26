package com.unittesting;

import java.lang.reflect.Array;

public class HeapSort<T extends Comparable> {
    private int current_size = 0;
    private long time_Sort_start = 0;
    private long time_end=0;
    public T[] array;

    public HeapSort(T[] arr)
    {
        array =(T[]) new Comparable[arr.length+1];
        for (int i = 1; i < array.length; i++) {
            array[i]= arr[i-1];
        }
        current_size=array.length;
        buildandcreateheap();
    }
    public  void print()
    {
        for (int i = 1; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    public  void buildandcreateheap()
    {
        int last_parent = current_size/2;
        for (int i = last_parent; i >=1; i--) {
            heapify(i);
        }
    }
    public void heapify(int hole)
    {
        int child =0;
        for (int i = hole; i*2 <= current_size ; i = child) {
            child = i * 2;
            //System.out.println(child);
            if (child < current_size-1 && array[child+1].compareTo(array[child])>0)
                child++;
            //array[child] > array[i]

            if(child < current_size && array[child].compareTo(array[i])>0)
            {
                T temp = array[i];
                array[i]    = array[child];
                array[child]= temp;
            }
            else break;
        }
    }
    public void sort() {
        this.time_Sort_start = System.currentTimeMillis();
       while(true)
       {
           if (current_size==1)break;
           T temp = array[1];
           array[1] = array[--current_size];
           array[current_size] = temp;
           heapify(1);
       }
//        T temp = array[1];
//        array[1] = array[2];
//        array[2]=temp;
    this.time_end= System.currentTimeMillis();
    }
  public  long getTime()
    {
        return  this.time_end-this.time_Sort_start;
    }


}
