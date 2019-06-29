package wang.jia.lock.spin;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description: 自旋锁测试
 * @author: chenjiawang
 * @CreateDate: 2019/6/29 10:38
 */
public class SpinLockTest {



    public static void main(String[] args) {

        SpinLockTest myLock = new SpinLockTest();

        new Thread(()->{
            myLock.addLock();
            //
            try {
                System.out.println("AAAA 线程A 暂停5秒，这5秒锁都是我的 谁也抢不走，我不给你不能要！");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myLock.unLock();
        },"线程AA").start();
        //
        try {
            System.out.println("AAAA 线程MAIN     暂停2秒");
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            myLock.addLock();
//            //
//            System.out.println("线程bbbbbb  我获得锁了！@！@");
//            try {
//                System.out.println("bbbbb 线程b   我先暂停1秒  免得太快，其他线程还没有打印 释放锁 ");
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            myLock.unLock();
        },"线程bbbbbb").start();
    }


    // 原子引用
    AtomicReference<Thread> threadAtomicReference = new AtomicReference<>();

    public void addLock() {
        Thread thread =Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+": ++++++添加锁");
        while (!threadAtomicReference.compareAndSet(null, thread)) {
//            System.out.println(Thread.currentThread().getName()+"： 呜呜 其他线程先进来了，锁不是我的，我要循环等待获取 锁");
        }

    }

    public void unLock(){
        // 当前线程
        Thread thread =Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+": 唉 我准备释放锁了");
        // 释放当前线程锁
        threadAtomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName()+":--------- 释放锁");
    }

}


