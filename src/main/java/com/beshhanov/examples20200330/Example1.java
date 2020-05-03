package main.java.com.beshhanov.examples20200330;

import java.util.concurrent.TimeUnit;

public class Example1 {
    public static void main(String[] args) {
        StringBuilder[][] stringBuilders = new StringBuilder[1000_000][];
        for (int i = 0; i < 1000; i++) {
            StringBuilder[] array= new StringBuilder[1000_000];
            for (int j = 0; j < array.length; j++) {
                array[j] = new StringBuilder();
            }
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        //ctrl + pause -> thread dump

        //jstack [pid] - выводит thread dump конкретного процесса
        //jps - пробегается по всем запущенным процессам и выводит java процессы
        //ключи: -m - выводит class-path
        //       -v - выводит все ключи, с которыми был запущен java-процесс
        //       -l - выводит класс, который является точкой входа в приложение (main)

        //jvisualvm - позволяет подключиться к виртуальной машине и отслеживать состояния выпоняемых процессов
        System.out.println(Thread.currentThread());
    }
}
