package note220912_thread.prac_lockfree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.LockSupport;

/*
lock-free 방식은 모니터 방식을 사용하지 않는다.따라서 synchronized를 사용하지 않는다.
 */

public class MoneySafe {
    private int money;

    // thread를 담는 Queue
    private final Queue<Thread> threadQueue;

    public MoneySafe(int money) {
        this.money = money;
        this.threadQueue = new LinkedList<>();
    }

    public void deposit(int money, String name) {
        this.money += money;
        System.out.printf("%s가 %d를 입금했습니다.\n", name, money);

        while (!threadQueue.isEmpty()) {
            // TODO : queue에 저장된 Thread를 꺼낸 후
            // Thread를 깨운다.
            LockSupport.unpark(threadQueue.poll());
        }
    }

    public void withDraw(int money, String name) {
        while (this.money < money) {
            // TODO : 잔고에 출금하려는 돈보다 적으면 일단 기다려야 한다.

            // TODO : 현재 이 메서드를 실행하고 있는 Thread를
            // thread 인스턴스를 보관하는 queue에 저장한다.
            threadQueue.add(Thread.currentThread());

            // TODO : 현재 실행되고 있는 Thread를 대기시킨다.
            LockSupport.park();
        }

        this.money -= money;
        System.out.printf("%s가 %d를 출금했습니다.\n", name, money);
    }

    public void printBalance() {
        System.out.printf("현재 잔고는 %d입니다\n", this.money);
    }
}
