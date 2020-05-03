package main.java.com.beshhanov.examples20200330;

import java.util.concurrent.TimeUnit;

public class Example3 {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        long start = System.nanoTime();
        Runnable runnable = () -> {
            long value = 0;
            for (long i = 0; i < 1000000000L; i++) {
                value++;
            }
            System.out.println(value);
        };
        Thread task1 = new Thread(runnable);
        Thread task2 = new Thread(runnable);
        Thread task3 = new Thread(runnable);
        Thread task4 = new Thread(runnable);
        Thread task5 = new Thread(runnable);

        task1.start();
        task2.start();
        task3.start();
        task4.start();
        task5.start();
        //Как дождаться окончания выполнения потока?
        // Если у нас есть ссылка на поток, выполнение которого мы хотим дождаться в текущем потоке,
        // то нужно в текущем потоке на этой ссылке выполнить метод join().
        try {
            task1.join();
            task2.join();
            task3.join();
            task4.join();
            task5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Thread.yield() говорит jvm что текущий поток готов уступить место другим потокам
        long end = System.nanoTime();
        System.out.println(TimeUnit.NANOSECONDS.toMillis(end - start));
    }
}