/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package another1;

/**
 *
 * @author user
 */
public class Another1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Q q = new Q();
        Producer p = new Producer(q, 1);
        Consumer c = new Consumer(q, 0);
        p.start();
        c.start();
    }

}

class Q {
    private int contents;
    private boolean avail = false;

    public synchronized int get() {
        while(avail == false) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        avail = false;
        notifyAll();
        return contents;
    }
    
    public synchronized void put(int value) {
        while(avail == true) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        contents = value;
        avail = true;
        notifyAll();
    }
}

class Consumer extends Thread {
    private Q q;
    private int number;
    
    public Consumer(Q q, int number) {
        this.q = q;
        this.number = number;
    }
    
    public void run() {
        int value = 0;
        for(int i=0;i<10;i++) {
            value = q.get();
            System.out.println("Consumer #" + this.number + " got: " + value);
        }
    }
}

class Producer extends Thread {
    private Q q;
    private int number;
    
    public Producer(Q q, int number) {
        this.q = q;
        this.number = number;
    }
    
    public void run() {
        for(int i=0;i<10;i++) {
            q.put(i);
            System.out.println("Producer #" + this.number + " put: " + i);
            try {
                Thread.sleep((int)(Math.random() * 100));
            } catch (InterruptedException e) {}
        }
    }
}
