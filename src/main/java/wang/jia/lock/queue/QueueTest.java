package wang.jia.lock.queue;

import sun.management.counter.Units;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @author: chenjiawang
 * @CreateDate: 2019/6/30 18:26
 */
public class QueueTest {

    public static void main(String[] args) throws Exception {

        BlockingQueue<Object> queue = new ArrayBlockingQueue<>(3);

        System.out.println(queue.offer("AA"));
        System.out.println(queue.offer("bb"));
        System.out.println(queue.offer("cc",2, TimeUnit.SECONDS));
        System.out.println("queue.offer(\"cc\") = " + queue.offer("cc"));

//        ((ArrayBlockingQueue<Object>) queue).put("wwq");

        System.out.println("queue.remove(\"aa\") = " + queue.remove("312"));
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
//        System.out.println(queue.take());
        System.out.println(queue.peek());
        System.out.println(queue.poll());
    }
}
