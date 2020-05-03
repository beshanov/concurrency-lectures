package main.java.com.beshhanov.examples20200417;

public class Example1 {
    private static volatile long value = 0;// ключевое слово volatile помимо того, что гарантирует, что после того как поток изменил значение переменной
                                           // её значение не будет сохранено в кэше ядра процессора, также решает другую проблему
                                            //- атомарности чтения/записи 8-байтных примитивов на 32-разрядных системах
    private static final Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread threadInc = new Thread(() ->
        {
            for (long i = 0; i < 1_000_000L; i++) {
                //Инкремент и дескримент это не атомарные операции(чтение-модификация-запись).
                // Между любыми двумя атомарными операциями может произойти что угодно и пройти сколько угодно времени.
                // Поэтому некоторые операции модификации могут затереться другим потоком, считавшим неактуальное значение переменной - RACE CONDITION
                // Эту проблему решает наличие критической секции, в которую заключается необходимая не атомарная операция
                synchronized (lock) {
                    ++value;
                }
            }
        });


        Thread threadDec = new Thread(() ->
        {
            for (long i = 0; i < 1_000_000L; i++) {
                synchronized (lock) {
                    --value;
                }
            }
        });

        threadDec.start();
        threadInc.start();

        threadDec.join();
        threadInc.join();

        System.out.println(value);
    }
}
