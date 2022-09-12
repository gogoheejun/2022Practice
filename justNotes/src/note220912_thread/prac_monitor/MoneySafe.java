package note220912_thread.prac_monitor;

public final class MoneySafe {
    private int money;
    private final MoneySafeKey key;

    public MoneySafe(int money){
        this.money = money;
        this.key = new MoneySafeKey();
    }

    public void deposit(int money, String name){
        synchronized (key){
            this.money += money;
            System.out.printf("%s가 %d를 입금했습니다.\n", name, money);

            key.notifyAll();
        }
    }

    public void withDraw(int money, String name){
        synchronized (key){
            try{
                while(this.money < money){
                    // TODO : 잔고에 출금하려는 돈보다 적으면 일단 기다려야 한다.
                    // MoneySafeKey key Section에 있는
                    // Key Owner 구역에 있는 스레드를 Wait-Set 구역으로 이동시킨다.

                    key.wait();
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            this.money -= money;
            System.out.printf("%s가 %d를 출금했습니다.\n", name, money);
        }
    }
    public void printBalance(){
        synchronized (key){
            System.out.printf("현재 잔고는 %d입니다\n", this.money);
        }
    }
}
