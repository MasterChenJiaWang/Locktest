package wang.jia.lock.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @author: chenjiawang
 * @CreateDate: 2019/6/30 18:26
 */
public class QueueTest4 {

    public static void main(String[] args) throws Exception {

        Locktest locktest = new Locktest();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    locktest.print5();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "线程A").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    locktest.print10();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "线程B").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    locktest.print15();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "线程C").start();
    }


    static class Locktest {

        private ReentrantLock reentrantLock = new ReentrantLock();
        private Condition c1 = reentrantLock.newCondition();
        private Condition c2 = reentrantLock.newCondition();
        private Condition c3 = reentrantLock.newCondition();
        // 1 =a 2 =b 3 =c
        private Integer number = 1;

        private void print() throws Exception {
            print5();
            print10();
            print15();
        }

        private void print5() throws Exception {
            reentrantLock.lock();
            try {
                if (number != 1) {
                    c1.await();
                }

                for (int i = 1; i <= 5; i++) {
                    System.out.println(Thread.currentThread().getName() + ">>>>>" + i);
                }
                number = 2;
                c2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        }


        private void print10() throws Exception {
            reentrantLock.lock();
            try {
                if (number != 2) {
                    c2.await();
                }

                for (int i = 1; i <= 10; i++) {
                    System.out.println(Thread.currentThread().getName() + "<<<<_+_+_+_+_  " + i);
                }
                number = 3;
                c3.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        }

        private void print15() throws Exception {
            reentrantLock.lock();
            try {
                if (number != 3) {
                    c3.await();
                }

                for (int i = 1; i <= 15; i++) {
                    System.out.println(Thread.currentThread().getName() + "?????????????" + i);
                }
                number = 1;
                c1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

}
