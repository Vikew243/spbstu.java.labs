package ru.spbstu.telematics.lab3;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static ru.spbstu.telematics.lab3.Berries.*;

public class BerriesTest {

    @Test
    public void check() throws InterruptedException {
        Berries.Neibor N1 = new Berries.Neibor();
        Berries.Neibor N2 = new Berries.Neibor();
        Thread T1 = new Thread(N1);
        Thread T2 = new Thread(N2);
        T1.start();
        T2.start();
        T1.join();
        T2.join();
        int result = field.getQuantityOfBerries() + N1.getBerries() + N2.getBerries();
        assertEquals(200, result);
    }

}