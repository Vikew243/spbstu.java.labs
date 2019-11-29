package ru.spbstu.telematics.lab1;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReaderFromTwoFiles {

    public static BufferedReader CreateReader(String name) throws FileNotFoundException {
        try {
            File file = new File("C:\\Users\\tanya\\Documents\\" + name + ".txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            return reader;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader reader = null;
        return reader;
    }

    public static String ReadString(BufferedReader reader) throws IOException {
        try{
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        try {

            BufferedReader reader1 = CreateReader("Hello1");
            BufferedReader reader2 = CreateReader("Hello2");
            if(reader1 != null && reader2 != null) {
                String line1 = ReadString(reader1);
                String line2 = ReadString(reader2);

                while ((line1 != null) || (line2 != null)) {

                    if (line1 != null) {
                        System.out.println(line1);
                        line1 = ReadString(reader1);
                    }

                    if (line2 != null) {
                        System.out.println(line2);
                        line2 = ReadString(reader2);
                    }
                }
            }
            else {
                System.out.println("Error: One or two files not found");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
