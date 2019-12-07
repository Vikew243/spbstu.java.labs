package ru.spbstu.telematics.lab2;

import org.junit.Test;
import static ru.spbstu.telematics.lab2.MyStack.*;

import static org.junit.Assert.*;

public class MyStackTest {
    MyStack myStack = new MyStack(3);
    @Test
    public void getSize() {
        myStack.addElement(3);
        assertEquals(1, myStack.getSize());
    }

    @Test
    public void getTop() {
        myStack.addElement(3);
        assertEquals(3, myStack.getTop());
    }

    @Test
    public void popTop() {
        myStack.addElement(3);
        myStack.addElement(4);
        myStack.popTop();
        assertEquals(3, myStack.getTop());
    }

    @Test
    public void isEmpty() {
        myStack.addElement(3);
        assertNotEquals(true, myStack.isEmpty());
    }

    @Test
    public void iterator() {
        myStack.addElement(3);
        myStack.addElement(4);
        myStack.addElement(5);
        myStack.addElement(6);
        Object[] arr = new Object[myStack.capacity];
        int i = 0;
        for (Object v : myStack) {
            arr[i] = v;
            i++;
        }
        assertArrayEquals(arr, myStack.vals);
    }
}