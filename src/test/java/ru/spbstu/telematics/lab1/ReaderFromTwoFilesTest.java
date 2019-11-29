package ru.spbstu.telematics.lab1;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;
import static ru.spbstu.telematics.lab1.ReaderFromTwoFiles.CreateReader;
import static ru.spbstu.telematics.lab1.ReaderFromTwoFiles.ReadString;

public class ReaderFromTwoFilesTest {

    @Test
    public void createReader1() throws FileNotFoundException {
        BufferedReader bf = CreateReader("Hello1");
        assertNotNull(bf);
    }

    @Test
    public void createReader2() throws FileNotFoundException {
        BufferedReader bf = CreateReader("Hello3");
        assertNull(bf);
    }

    @Test
    public void readString() throws IOException {
        BufferedReader bf = CreateReader("Hello1");
        String line = ReadString(bf);
        assertNotEquals("Not right test", line);
    }

    @Test
    public void readString2() throws IOException {
        BufferedReader bf = CreateReader("Hello1");
        String line = ReadString(bf);
        assertEquals("It's file for lab", line);
    }
}