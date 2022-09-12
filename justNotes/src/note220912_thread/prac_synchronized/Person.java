package note220912_thread.prac_synchronized;

public final class Person {
    private Safe safe;
    private String name;

    public Person(String name, Safe safe){
        this.name = name;
        this.safe = safe;
    }

    public void deposit(int money){
        safe.deposit(money);
        System.out.printf("%s가 %d를 입근했습니다.\n", name, money);
    }

    public void withDraw(int money) {
        int withDrawnMoney = safe.withDraw(money);
        System.out.printf("%s가 %d를 출금했습니다.\n", name, withDrawnMoney);
    }
}
