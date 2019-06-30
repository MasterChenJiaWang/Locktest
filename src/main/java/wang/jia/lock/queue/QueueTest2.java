package wang.jia.lock.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @author: chenjiawang
 * @CreateDate: 2019/6/30 18:26
 */
public class QueueTest2 {

    public static void main(String[] args) throws Exception {

        BlockingQueue<Object> queue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "   1添加成功");
                queue.put("1");

                System.out.println(Thread.currentThread().getName() + "  2添加成功");
                queue.put("2");

                System.out.println(Thread.currentThread().getName() + "  3添加成功");
                queue.put("3");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程AA").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(Thread.currentThread().getName() + "......移除成功  "+  queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(Thread.currentThread().getName() + "......移除成功  "+  queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(Thread.currentThread().getName() + "......移除成功  "+  queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程BB").start();


    }


}
