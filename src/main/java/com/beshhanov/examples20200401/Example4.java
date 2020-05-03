package main.java.com.beshhanov.examples20200401;

import java.util.concurrent.TimeUnit;

public class Example4 {
    private static long  value = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread counter = new Thread(() -> {
            Thread current = Thread.currentThread();
            for (long i = 0; i < 10000000000L; i++) {
                //2. В этом потоке мы можем проверять флаг - попытался ли нас кто-то прервать - двумя способами
                //current.isInterrupted() - возвращает значение
                //Thread.interrupted() - возвращает значение, при этом сбрасывает его в false
                ++value;
            }
            System.out.println(value);
        });
        counter.start();

        TimeUnit.SECONDS.sleep(1);
        //1.Если есть ссылка на поток, то можно послать ему сигнал interrupt из текущего потока
        counter.interrupt();

        System.out.println(value);
    }
}
