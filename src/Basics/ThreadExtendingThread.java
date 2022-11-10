package Basics;

class ThreadRunner1 extends Thread
{
    @Override
    public void run()
    {
        for(int i=0;i<10;i++)
        {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Runner1:" + i);
        }
    }

}

class ThreadRunner2 extends Thread
{
    @Override
    public void run()
    {
        for(int i=0;i<10;i++)
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Runner2:" + i);
        }
    }

}
public class ThreadExtendingThread {

    public static void main(String[] args) {

        // main thread residing in main() (takes care of staring and shutting down of the operation )
        System.out.println(Thread.currentThread().getName());

        Thread runner1 = new ThreadRunner1();

        Thread runner2 = new ThreadRunner2();
        runner1.start();
        runner2.start();
        try {
            runner1.join(); // waiting for runner1 to finish first
            int x = 10/0; // very important thing to note here runner2 still has resources even after runner 1 failed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("FINISHED EXECUTING THE runner1");
    }


}
