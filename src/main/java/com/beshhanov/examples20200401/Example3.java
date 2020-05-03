package main.java.com.beshhanov.examples20200401;

public class Example3 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() ->
        {
            try {
                while (true) {
                    Thread.sleep(500);
                    System.out.println("Hello from daemon!");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally { //это один из немногих случаев, когда блок finally не отработает, потому что активный поток-демон
                        // никак не влияет на работу JVM, JVM завершает работу приложения как только завершает свою работу
                        //последний поток НЕ демон. Демоны могут умереть в любой момент, не стоит возлагать на них важную логику
                System.out.println("Hello from finally");
            }
        });

        //задать потоку свойство daemon нужно обязательно до старта самого потока, иначе будет IllegalThreadStateException
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(1500);
    }
}
