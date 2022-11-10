package Basics;

class NormalThread implements Runnable
{
    @Override
    public void run()
    {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Normal Thread finished.");
    }
}

class DaemonThread implements Runnable
{
    @Override
    public void run()
    {
        while(true)
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Daemon Thread finished.");
        }
    }

    }


public class TypesOfThread {

    public static void main(String[] args)
    {

        // main thread residing in main() (takes care of staring and shutting down of the operation )
        System.out.println(Thread.currentThread().getName());

        // will exit when run() method completes
        Thread thread_normal = new Thread(new NormalThread());

        // will exit when run() completes or normal_threads execution gets over
        // used as background threads
        Thread thread_daemon = new Thread(new DaemonThread());
        thread_daemon.setDaemon(true);

        thread_normal.start();
        thread_daemon.start();

    }
}
