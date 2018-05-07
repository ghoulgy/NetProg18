/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threatmethod;

/**
 *
 * @author user
 */
public class Threatmethod {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ThreadDemo t2 = new ThreadDemo("Thread-2");
        t2.setPriority(Thread.NORM_PRIORITY - 2);
        t2.start();
        ThreadDemo t1 = new ThreadDemo("Thread-1");
        t1.setPriority(Thread.NORM_PRIORITY + 2);
        t1.start();
        
    }
    
}

class ThreadDemo extends Thread {
    private Thread t;
    private String threadName;

    public ThreadDemo(String name) {
        threadName = name;
        System.out.println("Creating: " + threadName);
    }
    
    public void run() {
        System.out.println("Running "+ threadName ) ;
        try {
            for(int i=4;i>0;i--) {
                System.out.println("Thread " + threadName + ", " + i);
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println("Thread " + threadName + " exiting.");
    }

    public void start() {
        System.out.println("Starting " + threadName);
        if(t == null) {
            t = new Thread (this, threadName);
            t.start();
        }
    }
}
