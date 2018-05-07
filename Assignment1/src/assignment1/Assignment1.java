
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

/**
 *
 * @author user
 */
public class Assignment1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int [] a = new int[101];
        for(int i=1; i<=100; i++) {
            a[i] = i;
        }
        
        sumThread st1 = new sumThread(a);
        sumThread st2 = new sumThread(a);
        sumThread st3 = new sumThread(a);
        sumThread st4 = new sumThread(a);
        sumThread st5 = new sumThread(a);
        
        try {
            st1.t.join();
            st2.t.join();
            st3.t.join();
            st4.t.join();
            st5.t.join();
        } catch(InterruptedException e) {}
    }    
}

class Sum {
    private int sum;
   
    public synchronized int sumVal(int num[]) {
        sum = 0;
        for (int i = 0; i < num.length; i++) {
            sum += num[i];
//            try {
//                Thread.sleep(10);
//            } catch(InterruptedException e) {}
        }
        return sum;
    }
}

class sumThread extends Thread {
    Thread t;
    static Sum s = new Sum();
    int a[];
    int sumAll;

    public sumThread(int num[]) {
        t = new Thread(this);
        a = num;
        t.start();
    }
    
    public void run() {
        int sum;
        sumAll = s.sumVal(a);
        
        System.out.println("Sum: " + sumAll);
    }
}