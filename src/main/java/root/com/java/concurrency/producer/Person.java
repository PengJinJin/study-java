package root.com.java.concurrency.producer;

import java.util.concurrent.TimeUnit;

public class Person {
    private String name;
    private int age;
    // 表示共享资源对象是否为空，如果为 true，表示需要生产，如果为 false，则有数据了，不要生产
    private boolean isEmpty = true;

    /**
     * 生产者存
     */
    public synchronized void push(String name, int age) {

        try {
            while (!isEmpty) {
                wait();
            }
            // 开始生产
            this.name = name;
            TimeUnit.MILLISECONDS.sleep(10);
            this.age = age;
            // 生产完成
            isEmpty = false;
            notifyAll();// 唤醒所有的消费者
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 消费者取
     */
    public synchronized void pop() {
        try {
            while (isEmpty) {
                wait();
            }
            // 消费开始
            TimeUnit.MILLISECONDS.sleep(10);
            System.out.println(this);
            // 消费结束
            isEmpty = true;
            notifyAll();// 唤醒生产者
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return this.name + ": " + this.age;
    }
}
