package wang.jia.lock.queue;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @author: chenjiawang
 * @CreateDate: 2019/7/1 12:56
 */
public class QueueTest3 {

    public static void main(String[] args) {

        SharData sharData = new SharData();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                sharData.add();
            }
        }, "AA").start();
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                sharData.del();
            }
        }, "BB").start();
    }


    static class SharData {
        private Lock lock = new ReentrantLock();
        private AtomicInteger atomicInteger = new AtomicInteger(0);
        //
        private Condition condition = lock.newCondition();

        //        private volatile boolean key=true;
        private volatile int i = 0;

        private void add() {
            lock.lock();
            try {
                if (i != 0) {
                    condition.await();
                }
                i = 1;
                System.out.println(Thread.currentThread().getName() + " \t 加1");
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }


        private void del() {
            lock.lock();
            try {
                if (i != 1) {
                    condition.await();
                }
                i = 0;
                System.out.println(Thread.currentThread().getName() + " \r ------- 减1");
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }
}
