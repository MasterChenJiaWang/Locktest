package wang.jia.lock.CountDownLatch;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @author: chenjiawang
 * @CreateDate: 2019/6/29 15:02
 */
public class SemaphoreTest {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 10; i++) {

            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+":抢到车位。。。。。");
                    try {
                        // 车位占用 3秒
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放车位
                    semaphore.release();
                }
            }, i+"线程").start();

        }
    }
}
