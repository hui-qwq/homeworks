package code;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class NumTask implements Runnable {
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
            try {Thread.sleep(100);}
            catch (InterruptedException e) {return;}
        }
    }
}

class CharTask implements Runnable {
    public void run() {
        for (char c = 'A'; c <= 'J'; c++) {
            System.out.println(Thread.currentThread().getName() + ":" + c);
            try {Thread.sleep(100);}
            catch (InterruptedException e) {return;}
        }
    }
}

public class work11 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new NumTask(), "N");
        Thread t2 = new Thread(new CharTask(), "C");
        t1.start();
        t2.start();
    }
}