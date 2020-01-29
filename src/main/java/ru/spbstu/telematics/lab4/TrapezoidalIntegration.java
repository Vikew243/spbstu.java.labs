package ru.spbstu.telematics.lab4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


public class TrapezoidalIntegration {

    static BufferedReader reader;

    static {
        try {
            reader = new BufferedReader(new FileReader(new File("C:\\Users\\tanya\\Documents\\" + "in" + ".txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    static int[] array = reader.lines().mapToInt(Integer::parseInt).toArray();


    static int firstDot = array[0];
    static int secondDot = array[1];
    static int quantityOfThreads = array[2];
    static double lengthOfSection = 0.001;
    static int quantityOfIntervals = (int)(((double)(secondDot - firstDot))/ (lengthOfSection));

    static double function(double x){
        return (Math.sin(Math.pow(x,2)) / x);
    }
    static public double result = 0;

    static long begin;

    static class sectionIntegrator{

        double firstPoint, secondPoint;
        int sectionNumber;

        sectionIntegrator(double first, double second, int number){
            firstPoint = first;
            secondPoint = second;
            sectionNumber = number;
        }

        public double run() {
            return (function(firstPoint)+function(secondPoint))*lengthOfSection/2;
        }
    }

    static ExecutorService threadPool = Executors.newFixedThreadPool(quantityOfThreads);
    static List<Future<Double>> futures = new ArrayList<>();

    static void integrate() throws ExecutionException, InterruptedException {
        //begin=System.currentTimeMillis();

        for (int i = 0; i < quantityOfIntervals; i++) {
            final int j = i;
            double first = firstDot + lengthOfSection * i;
            double second = firstDot + lengthOfSection * (i+1);
            sectionIntegrator integrator = new sectionIntegrator(first, second, j);
            futures.add(
                    CompletableFuture.supplyAsync(
                            () -> integrator.run(),
                            threadPool
                    ));
        }

        double value = 0;
        for (Future<Double> future : futures) {
            result += future.get();
        }
        System.out.println("result = " + result);

        threadPool.shutdown();
        //long end=System.currentTimeMillis();
        //System.out.println("Расчёт окончен, время "+ (end - begin) + " мс");

    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        begin=System.currentTimeMillis();
        integrate();
        long end=System.currentTimeMillis();
        System.out.println("Расчёт окончен, время "+ (end - begin) + " мс");
    }
}
