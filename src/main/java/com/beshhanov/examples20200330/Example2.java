package main.java.com.beshhanov.examples20200330;

public class Example2 {
    public static void main(String[] args) {
        Thread main = Thread.currentThread();
        System.out.println(main);

        HelloWorldThread task1 = new HelloWorldThread();
        task1.start();

        Runnable task2 = () -> System.out.println("Hello from Runnable!" + Thread.currentThread());
        Thread thread = new Thread(task2);
        thread.setName("myThread");
        thread.start();
    }
}

class HelloWorldThread extends Thread
{
    @Override
    public void run() {
        System.out.println("Hello from thread" + Thread.currentThread());
    }
}

