package wang.jia.lock.CountDownLatch;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Description:
 * @author: chenjiawang
 * @CreateDate: 2019/6/29 14:47
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("已经集齐7颗龙珠了》》：》：》：》：");
        });

        for (int i = 0; i < 7; i++) {
            final int key=i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+":集齐一颗龙珠了。。。");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, key+"").start();
        }
    }
}
