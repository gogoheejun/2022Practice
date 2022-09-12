package note220912_thread.prac_monitor;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        MoneySafe safe = new MoneySafe(0);

        WithdrawPerson personA = new WithdrawPerson("출금자", safe);
        DepositPerson personB = new DepositPerson("입금자", safe);

        // Lambda 방식으로 Runnable 익명 클래스 생성
        Thread threadA = new Thread(() -> {
            personA.withDraw(1000);
            personA.withDraw(500);
            personA.withDraw(1000);
            personA.withDraw(2000);
        });
        threadA.start();

        // Lambda 방식으로 Runnable 익명 클래스 생성
        Thread threadB = new Thread(() -> {
            personB.deposit(1000);
            personB.deposit(1000);
            personB.deposit(2000);
            personB.deposit(3000);
            personB.deposit(5000);
        });
        threadB.start();

        threadA.join();
        threadB.join();
        // 잔고확인 출력
        safe.printBalance();
    }

}
