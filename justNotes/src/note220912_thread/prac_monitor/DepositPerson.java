package note220912_thread.prac_monitor;

public final class DepositPerson {
    private MoneySafe safe;
    private String name;

    public DepositPerson(String name, MoneySafe safe) {
        this.name = name;
        this.safe = safe;
    }

    // TODO : μκΈνλ€
    public void deposit(int money) {
        safe.deposit(money, name);
    }
}
