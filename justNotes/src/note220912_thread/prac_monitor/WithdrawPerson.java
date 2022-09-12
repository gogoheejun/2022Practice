package note220912_thread.prac_monitor;

public final class WithdrawPerson {
    private MoneySafe safe;
    private String name;

    public WithdrawPerson(String name, MoneySafe safe) {
        this.name = name;
        this.safe = safe;
    }

    // TODO : 출금한다
    public void withDraw(int money) {
        safe.withDraw(money, name);
    }
}
