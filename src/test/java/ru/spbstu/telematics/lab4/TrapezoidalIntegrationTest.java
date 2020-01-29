package ru.spbstu.telematics.lab4;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutionException;


public class TrapezoidalIntegrationTest {

    @Test
    public void integrate() throws ExecutionException, InterruptedException {
        TrapezoidalIntegration integral=new TrapezoidalIntegration();
        integral.integrate();
        Assert.assertTrue(Math.abs(integral.result - 0.312404)<0.00001);
    }
}