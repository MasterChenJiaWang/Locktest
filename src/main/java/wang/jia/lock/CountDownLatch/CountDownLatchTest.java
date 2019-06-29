package wang.jia.lock.CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @Description:
 * @author: chenjiawang
 * @CreateDate: 2019/6/29 14:23
 */
public class CountDownLatchTest {

    public static void main(String[] args) {

        try {
            CountDownLatch countDownLatch = new CountDownLatch(10);

            for (int i = 1; i <=10 ; i++) {
                new Thread(() -> {
                    System.out.println(Thread.currentThread().getName()+" : 线程 走出来的。。。。");
                    countDownLatch.countDown();
                        }, "线程"+i).start();
            }

            countDownLatch.await();
            System.out.println(Thread.currentThread().getName()+":最后 老大才走！！！");
        } catch (InterruptedException e) {
        }

    }
}
