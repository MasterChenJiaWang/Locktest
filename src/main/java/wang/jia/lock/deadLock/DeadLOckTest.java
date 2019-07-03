package wang.jia.lock.deadLock;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @author: chenjiawang
 * @CreateDate: 2019/7/3 13:17
 */
public class DeadLOckTest {

    /**
     *
     * 死锁分析：
     * 1、 jps -l
     *
     * 2.jstack 线程ID
     *
     *
     * @param args
     */

    public static void main(String[] args) {


        new Thread(new Test("AA", "BB"), "线程A").start();
        new Thread(new Test("BB", "AA"), "线程B").start();
    }



    static class Test implements Runnable{

        private String lockNameA;
        private String lockNameB;

        public Test(String lockNameA, String lockNameB) {
            this.lockNameA = lockNameA;
            this.lockNameB = lockNameB;
        }

        @Override
        public void run() {
            synchronized (lockNameA){
                System.out.println("持有线程："+lockNameA+": 进来了。。。"+"尝试获取线程:"+lockNameB);

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lockNameB){
                    System.out.println("持有线程:"+lockNameB+"。。。。。。尝试获取："+lockNameA);
                }
            }
        }
    }
}
