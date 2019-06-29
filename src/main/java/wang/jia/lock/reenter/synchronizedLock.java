package wang.jia.lock.reenter;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @author: chenjiawang
 * @CreateDate: 2019/6/29 9:42
 */
public class synchronizedLock {

    public static void main(String[] args) throws Exception{

        Phone phone = new Phone();



        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    phone.sendEmail();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },"线程"+(i+2)+"-----").start();
        }

        new Thread(()->{
            try {
                phone.sendPhone();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"线程1").start();
    }
}


class Phone{

    public synchronized void sendPhone() throws Exception{

        System.out.println(Thread.currentThread().getName()+":发送手机》》》》》》》》》》》");
        sendEmail();
    }

    public synchronized void sendEmail() throws Exception{

        System.out.println(Thread.currentThread().getName()+":<<<<发送邮件++++++++++++");
    }
}