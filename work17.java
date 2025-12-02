package code;

public class work17 {
    public static void main(String[] args) {
        Box b = new Box();
        new Producer(b).start();
        new Consumer(b).start();
    }
}

class Box {
    private int d;
    private boolean f = false;

    public synchronized void put(int d) {
        while (f) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.d = d;
        f = true;
        notify();
    }

    public synchronized int get() {
        while (!f) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        f = false;
        notify();
        return d;
    }
}

class Producer extends Thread {
    private Box b;

    Producer(Box b) {
        this.b = b;
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            b.put(i);
            System.out.println("生产：" + i);
            try {
                sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer extends Thread {
    private Box b;

    Consumer(Box b) {
        this.b = b;
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            int d = b.get();
            System.out.println("消费：" + d);
            try {
                sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}