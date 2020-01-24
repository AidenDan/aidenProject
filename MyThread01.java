package com.k9509.demo09;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyThread01 implements Runnable {
    private int num = 100;
    //创建lock锁
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            try {
                //线程调用sleep()方法时，不会主动释放锁
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //手动加锁来同步代码块
            lock.lock();
            if (num > 0) {
                System.out.println(Thread.currentThread().getName() + "正在卖第" + num-- + "张票");
            }
            //用完后手动释放锁
            lock.unlock();
        }
    }
}

class TestClass {
    public static void main(String[] args) {
        //创建接口实例
        Runnable runnable = new com.k9509.demo08.demo07.MyThread();
        //创建3个窗口
        Thread thread1 = new Thread(runnable, "窗口1");
        Thread thread2 = new Thread(runnable, "窗口2");
        Thread thread3 = new Thread(runnable, "窗口3");
        //启动线程
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
