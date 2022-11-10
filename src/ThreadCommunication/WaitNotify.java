package ThreadCommunication;


// wait and notify must happen on synchronized blocks only for lock handling
// sleep need not be sync block and can be called on Thread Class directly (does not manupulate intrinsic lock of the App)
class Process
{
    public void produce() throws InterruptedException
    {
        synchronized (this)
        {
            System.out.println("Producer section first time");
            wait(); // transition thread to waiting state (releases the intrinsic lock)
            System.out.println("Producer section Second  time");
        }
    }

    public void consume() throws InterruptedException
    {
        synchronized (this)
        {   // Sleep So that produce calling thread gets control first
            Thread.sleep(2000);
            System.out.println("Consumer section First  time");
            notify();  //notify other thread that it can resume its operation
            // below code after notify() gets executed before transferring control to other thread
            Thread.sleep(3000);
            System.out.println("Consumer section Second  time");
        }
    }


}
public class WaitNotify {

    public static void main(String[] args)
    {
      Process p = new Process();

      Thread t1 = new Thread(new Runnable(){
          @Override
          public void run()
          {
              try {
                  p.produce();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
      });

        Thread t2 = new Thread(new Runnable(){
            @Override
            public void run()
            {
                try {
                    p.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();


    }
}
