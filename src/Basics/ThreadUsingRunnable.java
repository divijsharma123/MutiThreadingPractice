package Basics;

class Runner1 implements Runnable
{
    @Override
    public void run()
    {
        for(int i=0;i<10;i++)
        {
            System.out.println("Runner1:" + i);
        }
    }

}

class Runner2 implements Runnable
{
    @Override
    public void run()
    {
        for(int i=0;i<10;i++)
        {
            System.out.println("Runner2:" + i);
        }
    }

}


public class ThreadUsingRunnable {

    public static void main(String[] args)
    {
        Thread t1 = new Thread(new Runner1());
        Thread t2 = new Thread(new Runner2());

        t1.start();
        t1.setPriority(Thread.MAX_PRIORITY); // It depends on Thread scheduler how to interpret this priority in time slicing Algo
        t2.start();

    }



}
