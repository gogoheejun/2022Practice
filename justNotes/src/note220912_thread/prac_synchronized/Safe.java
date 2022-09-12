package note220912_thread.prac_synchronized;

public final class Safe {
    private int money;
    private final MasterKey key;

    public Safe(int money) {
        this.money = money;
        this.key = new MasterKey();
    }

    public void deposit(int money){
        synchronized (key){
            this.money += money;
        }
    }

    public int withDraw(int money){
        synchronized (key){
            // this.money < money 부분을 동기화한 이유는
            // this.money 변수를 읽을 때
            // 누군가가 key를 이용하여 deposit 메서드를 호출하면
            // 변경되지 않은 값을 읽은 상태로
            // 조건문에서 판단하는 문제가 생기기 때문입니다
            if(this.money < money){
                System.out.println("잔고부족!");
                return 0;
            }

            this.money -= money;
            return money;
        }
    }

    public void printBalance() {
        synchronized(key) {
            System.out.printf("현재 잔고는 %d입니다\n", this.money);
        }
    }
}
