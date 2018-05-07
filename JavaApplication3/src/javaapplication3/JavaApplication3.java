/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

/**
 *
 * @author user
 */
public class JavaApplication3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        counter c = new counter();
        
        Thread a = new Thread(
            new Runnable() {
                public void run() {
                    c.increment("1");
                }
            }
        );
        
        Thread b = new Thread(
            new Runnable() {
                public void run() {
                    c.increment("2");
                }
            }
        );
        
        a.start();
        a.join(1000);
        b.start();
    }
    
}

class counter {
    int count;
    
    public void increment(String classifier) {
        for(int i=0;i<5;i++) {
            System.out.println(count);
            count++;
        }
    }
    
}
