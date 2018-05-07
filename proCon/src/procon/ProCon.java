/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procon;

/**
 *
 * @author user
 */
public class ProCon {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
      CubbyHole c = new CubbyHole();
      Producer p1 = new Producer(c, 1);
      Consumer c1 = new Consumer(c, 1);
      p1.start(); 
      c1.start();
      p1.join();
      c1.join();
      
      getOdd go = new getOdd(c);
      System.out.println("Odd List:");
      go.printList();
      System.out.println("");
      getEven ge = new getEven(c);
      System.out.println("Even List:");
      ge.printList();
    }
}

class CubbyHole {
   private int contents;
   private boolean available = false;
   private int sumAll;
   int[] a = new int[101];
   
   public synchronized int get() {
      while (available == false) {
         try {
            wait();
         } catch (InterruptedException e) {}
      }
      available = false;
      sumAll += a[contents];
      notifyAll();
      return contents;
   }
   
   public synchronized void put(int value) {
      while (available == true) {
         try {
            wait();
         } catch (InterruptedException e) { } 
      }
      contents = value;
      a[contents] = value;
      available = true;
      notifyAll();
   }
   
   public int getOddEven(int num) {
       return a[num];
   }
}

class Consumer extends Thread {
   private CubbyHole cubbyhole;
   private int number;
   private int sumAll;
   
   public Consumer(CubbyHole c, int number) {
      cubbyhole = c;
      this.number = number;
   }
   
   public void run() {
      int value = 0;
      for (int i = 1; i <= 100; i++) {
         value = cubbyhole.get();
         sumAll += value;
         System.out.println("Consumer" + " got: " + value);
      }
        System.out.println("Sum of array: " + sumAll);
   }
}

class Producer extends Thread {
   private CubbyHole cubbyhole;
   private int number;
   
   public Producer(CubbyHole c, int number) {
      cubbyhole = c;
      this.number = number;
   } 
   public void run() {
      for (int i = 1; i <= 100; i++) {
         cubbyhole.put(i);
         System.out.println("Producer" + " put: " + i);
         try {
            sleep((int)(Math.random() * 100));
         } catch (InterruptedException e) { }
      } 
   }
}

class getOdd extends Thread {
    Thread t;
    private CubbyHole cubbyhole;
    private int [] oddList = new int [50];
    private int j=0;
    
    public getOdd(CubbyHole c) {
      t = new Thread(this, "Odd");
      cubbyhole = c;
      t.start();
   }
    
    public void run() {
        for(int i=1; i<=100; i+=2) {
            oddList[j] = cubbyhole.getOddEven(i);
            j++;
            try {
                Thread.sleep(10);
            } catch (Exception e) {
            }
        }
    }
    
    public void printList() throws InterruptedException {
        t.join();
        for (int i = 0; i < oddList.length ; i++) {
            System.out.print(oddList[i] + " ");
        }
    }
}

class getEven extends Thread{
    Thread t;
    private CubbyHole cubbyhole;
    private int [] evenList = new int [50];
    private int j=0;
    
    public getEven(CubbyHole c) {
      t = new Thread(this, "Even");
      cubbyhole = c;
      t.start();
   }
    
    public void run() {
        for(int i=2; i<=100; i+=2) {
            evenList[j] = cubbyhole.getOddEven(i);
            j++;
            try {
                Thread.sleep(10);
            } catch (Exception e) {
            }
        }     
    }
    
    public void printList() throws InterruptedException {
        t.join();
        for (int i = 0; i < evenList.length; i++) {
            System.out.print(evenList[i]+" ");
        }
    }
}