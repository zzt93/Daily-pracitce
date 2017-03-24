package so_test;

/**
 * Created by zzt on 12/18/16.
 * <p>
 * <h3></h3>
 */
class MyRunnable1 implements Runnable {

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Thread-1"))
            method1();
        else
            method2();
    }

    synchronized void method1() {
        System.out.println(Thread.currentThread().getName()
                + " in synchronized void method1() started");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()
                + " in synchronized void method1() ended");
    }

    synchronized void method2() {
        System.out.println(this);
        System.out.println(Thread.currentThread().getName()
                + " in synchronized void method2() started");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() +
                " in synchronized void method2() ended");
    }

}

/**
 * Copyright (c), AnkitMittal JavaMadeSoEasy.com
 */
public class MyClass {
    public static void main(String args[]) throws InterruptedException {

        MyRunnable1 myRunnable1 = new MyRunnable1();

        Thread thread1 = new Thread(myRunnable1, "Thread-1");
        Thread thread2 = new Thread(myRunnable1, "Thread-2");
        thread1.start();
        Thread.sleep(10);//Just to ensure Thread-1 starts before Thread-2
        thread2.start();


    }

}
