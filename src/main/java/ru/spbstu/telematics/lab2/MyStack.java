package ru.spbstu.telematics.lab2;

import java.util.Iterator;

public class MyStack<T> implements Iterable<T> {

    int capacity;
    int size;
    T top;
    Object[] vals;

    public MyStack(){
        super();
        this.capacity = 5;
        vals = new Object[this.capacity];
    }

    public MyStack(int capacity){
        super();
        this.capacity = capacity;
        vals = new Object[this.capacity];
    }

    public int getSize(){
        return size;
    }

    public T getTop(){
        return top;
    }

    public void addElement(T newElement){
        size++;
        if(size<capacity) {
            vals[size-1] = newElement;
            top = newElement;
        }
        else{
            capacity *= 2;
            Object tempVals[] = new Object[capacity];
            for(int i = 0; i < size-1; i++) {
                tempVals[i] = vals[i];
            }
            tempVals[size-1] = newElement;
            vals = tempVals;
        }
    }

    public T popTop(){
        T temp = top;
        size--;
        if(size>=0)
            top = (T) vals[size-1];
        else
            top = null;
        return temp;
    }

    public boolean isEmpty(){
        if(size == 0)
            return true;
        else
            return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int cnt = 0;

            @Override
            public boolean hasNext() {
                return cnt < size-1;
            }

            @Override
            public T next() {
                T result = (T) vals[cnt];
                cnt++;
                return result;
            }
            @Override
            public void remove() {

            }
        };
    }
}
