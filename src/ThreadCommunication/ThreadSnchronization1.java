package ThreadCommunication;

public class ThreadSnchronization1 {
    public static int count1 = 0;
    public static int count2 = 0;

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();


    public static void incrementCount1(String threadName)
    {

        for(int i=0;i<10;i++) {
            count1++;
            System.out.println(threadName + "->" + count1);
        }
    }

    /* one thread at a time will execute this function at a time
    Problem with this is : thread has acquired intrinsic lock of the app and other thread will have to wait before acquiring lock to same method
     And if we have  another independent method : still thread2 has to wait for thread1 because Object has only one intrinsic lock
     */
    public static synchronized void incrementCount2(String threadName)
    {
        for(int i=0;i<10;i++) {
            count2++;
            System.out.println(threadName + "->" + count2);
        }
    }

    // class level synchronization (for static functions)
    public static void incrementClassLevelCount2(String threadName)
    {
        for(int i=0;i<10;i++) {
            synchronized (ThreadSnchronization1.class) {
                count2++;
                System.out.println(threadName + "->" + count2);
            }
        }
    }

    // object level synchronization
    // acquiring lock for specific block only
    public void incrementObjectLevelCount2(String threadName)
    {
        for(int i=0;i<10;i++) {
            synchronized (ThreadSnchronization1.this) {
                count2++;
                System.out.println(threadName + "->" + count2);
            }
        }
    }

    // custom object level locking (threads dont have to wait for other thread to release single intrinsic Object  thread to resume its operation)
    public void incrementCustomObjectLevelCount1(String threadName)
    {
        for(int i=0;i<10;i++) {
            synchronized (lock1) {
                count2++;
                System.out.println(threadName + "->" + count2);
            }
        }
    }

    public void incrementCustomObjectLevelCount2(String threadName)
    {
        for(int i=0;i<10;i++) {
            synchronized (lock2) {
                count2++;
                System.out.println(threadName + "->" + count2);
            }
        }
    }

    public static void processUnsync()
    {
        Thread t1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {   Thread.currentThread().setName("Thread1");
                incrementCount1(Thread.currentThread().getName());


            }
        });

        Thread t2 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {   Thread.currentThread().setName("Thread2");
                incrementCount1(Thread.currentThread().getName());
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void processSync()
    {
        Thread t1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {   Thread.currentThread().setName("Thread1");
                //incrementCount2(Thread.currentThread().getName());
                incrementClassLevelCount2(Thread.currentThread().getName());

            }
        });

        Thread t2 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {   Thread.currentThread().setName("Thread2");
                // incrementCount2(Thread.currentThread().getName());
                incrementClassLevelCount2(Thread.currentThread().getName());
            }
        });

        t1.start();
        t2.start();

       try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args)  {

        //processUnsync();
        processSync(); // you will see if thread1 takes control it will execute then thread2 takes control
    }
}
