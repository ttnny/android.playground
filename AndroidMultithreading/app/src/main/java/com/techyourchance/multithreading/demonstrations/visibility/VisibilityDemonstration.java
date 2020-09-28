package com.techyourchance.multithreading.demonstrations.visibility;

import java.util.concurrent.atomic.AtomicBoolean;

public class VisibilityDemonstration {

    private static final AtomicBoolean PRODUCER_FLAG = new AtomicBoolean(false);
    private volatile static int sCount = 0;

    public static void main(String[] args) {
        new Consumer().start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            return;
        }

        new Producer().start();

        synchronized (PRODUCER_FLAG) {
            while (!PRODUCER_FLAG.get()) {
                try {
                    PRODUCER_FLAG.wait();
                } catch (InterruptedException e) {
                    return;
                }
            }
        }

        System.out.println("Main: returns");
    }

    static class Consumer extends Thread {
        @Override
        public void run() {
            int localValue = -1;
            while (true) {
                if (localValue != sCount) {
                    System.out.println("Consumer: detected count change " + sCount);
                    localValue = sCount;
                }
                if (sCount >= 5) {
                    break;
                }
            }
            System.out.println("Consumer: terminating");
        }
    }

    static class Producer extends Thread {
        @Override
        public void run() {
            while (sCount < 5) {
                int localValue = sCount;
                localValue++;
                System.out.println("Producer: incrementing count to " + localValue);
                sCount = localValue;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
            }
            System.out.println("Producer: terminating");

            synchronized (PRODUCER_FLAG) {
                PRODUCER_FLAG.set(true);
                PRODUCER_FLAG.notifyAll();
            }
        }
    }

}