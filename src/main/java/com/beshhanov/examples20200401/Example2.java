package main.java.com.beshhanov.examples20200401;

public class Example2 {
    private static long value;
    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (long i = 0; i < 1_000_000L; i++) {
                ++value;
            }
        };

        Thread thread = new Thread(task);
        thread.start();

        //чтобы не крутиться в цикле, можем вызвать
        thread.join();
        //Когда поток main присоединится к потоку thread, main перейдет в состояние WAITING, до тех пор пока thread не перейдет
        //из RUNNABLE в TERMINATED (все возможные состояния потока: NEW, RUNNABLE, WAITING, TIMED_WAITING, BLOCKED, TERMINATED)


        //можно также вызвать join(long timeout) с параметром, чтобы ожидать завершение не больше какого-то времени
        //Проблема в том, что не будет понятно действительно ли поток завершился или же мы вылетели по тоймауту
        System.out.println(value);
    }
}
