package com.k9509.demo08.demo07;

public class MyThread implements Runnable {
    private int num = 100;
    //创建锁对象
    private Object obj = new Object();

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //同步代码块,当一个线程执行完并返回结果前另一个线程不能进入同步代码块
            synchronized (obj) {
                if (num > 0) {
                    System.out.println(Thread.currentThread().getName() + "正在卖第" + num-- + "张票");
                }
            }
        }
    }
}

class TestClass01 {
    public static void main(String[] args) {
        //案例
        // 某电影院目前正在上映贺国庆大片，共有100张票，而它有3个售票窗口售票，
        // 请设计一个程序模拟该电影院售票。
        //创建接口实例
        Runnable runnable = new MyThread();
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
