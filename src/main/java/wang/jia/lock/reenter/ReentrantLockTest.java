package wang.jia.lock.reenter;


import java.sql.SQLOutput;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @author: chenjiawang
 * @CreateDate: 2019/6/29 9:55
 */
public class ReentrantLockTest {


    public static void main(String[] args) {


        Thread thread = new Thread(new Phone2(),"线程1");
        Thread thread2 = new Thread(new Phone2(),"线程2");
//        Thread thread3 = new Thread(new Phone2());
//        Thread thread4 = new Thread(new Phone2());

        thread.start();
        thread2.start();
//        thread3.start();
//        thread4.start();
    }




}
class Phone2 implements Runnable {

    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        get();
    }

    private void get(){
        lock.lock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+": get方法》》》》》》");
            set();
        } finally {
            lock.unlock();
//            lock.unlock();
        }
    }

    private void set(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+":<<<<<<set方法《《》《》《》《");
        } finally {
            lock.unlock();
        }
    }
}