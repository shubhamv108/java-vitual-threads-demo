package org.code.shubham;

import java.util.concurrent.atomic.AtomicInteger;

public class Example1 {

    void execute(Runnable r) throws InterruptedException {

        var vt1 = Thread.ofVirtual();
        Thread t1 = vt1.start(r);

        Thread t2 = Thread.startVirtualThread(r);
        t1.join();
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger i = new AtomicInteger(0);
        new Example1().execute(() -> System.out.println(i.incrementAndGet()));
    }

}
