/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package priority;

/**
 *
 * @author user
 */
public class Priority {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
    
        Clicker hi = new Clicker(Thread.NORM_PRIORITY +4);
        Clicker lo = new Clicker(Thread.NORM_PRIORITY +3);
        lo.start();
        hi.start();
        
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {}
        lo.stop();
        hi.stop();
        
        try {
            hi.t.join();
            lo.t.join();
        } catch(InterruptedException e) {
            
        }
        System.out.println("Low : \t" + lo.click);
        System.out.println("High: \t" + hi.click);
    }
    
}

class Clicker implements Runnable {
    int click = 0;
    Thread t;
    private volatile boolean running = true;
    
    public Clicker(int p) {
        t = new Thread(this);
        t.setPriority(p);
    }
    
    public int prioNum() {
        return t.getPriority();
    }
    
    public void run() {
        while(running) {
            click ++;
        }
    }
    
    public void stop() {
        running = false;
    }
    
    public void start() {
        t.start();
    }
}