package main.java.com.beshhanov.examples20200401;

import java.util.concurrent.TimeUnit;

public class Example1 {
    private static long value;
    public static void main(String[] args) {
        Runnable task = () -> {
            for (long i = 0; i <1_000_000L ; i++) {
                ++value;
            }
        };

        Thread thread = new Thread(task);
        thread.start();
        //мы хотим в потоке main дождаться окончания выполнения потока thread и прочесть значение value
        //каким образом это можно сделать помимо thread.join()?
        //можно организовать цикл с опросом, у потока thread можно опрашивать его состояние (thread.isAlive())
        //и пока он перейдет в состояние TERMINATED не выходить из цикла в потоке main
        //Busy-wait
        while (thread.isAlive())
        {
            //Чтобы уменьшить нагрузку на ядро бессмысленым опросом в потоке main, можем опрашивать раз в какой-то интервал
            //времени
            try {
                TimeUnit.MILLISECONDS.sleep(500); //поток main уснёт МИНИМУМ на 500 миллисекунд, не обязательно ровно на 500
                //при вызове static метода Thread.sleep() поток, в котором он вызван перейдет в состояние TIMED_WAITING
                //Из этого состояния его могут вывести другие потоки вызван на нём метод interrupt, поэтому здесь необходимо обработать
                //возможное исключение, связанное с этим событием
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Добавив ожидание в полсекунды, мы разгрузили систему, но ухудшили другую её характеристику - увеличили latency.
            //Теперь даже, когда поток thread прекратит свою работу, поток main какое-то время не будет об этом знать
        }

        System.out.println(value);
    }
}
