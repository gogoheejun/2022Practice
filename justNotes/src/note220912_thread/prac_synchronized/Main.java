package note220912_thread.prac_synchronized;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Safe safe = new Safe(5000);

        Person personA = new Person("PersonA", safe);
        Person personB = new Person("PersonB", safe);

        Thread threadA = new Thread(()->{
            personA.withDraw(1000);
            personA.withDraw(3000);
            personA.withDraw(10000);
            personA.withDraw(2000);
            personA.withDraw(3000);
        });

        threadA.start();

        Thread threadB = new Thread(()->{
            personB.withDraw(4000);
            personB.deposit(1000);
            personB.withDraw(1000);
            personB.withDraw(1000);
            personB.withDraw(1000);
        });

        threadB.start();
        threadA.join();
        threadB.join();

        safe.printBalance();
    }
}
