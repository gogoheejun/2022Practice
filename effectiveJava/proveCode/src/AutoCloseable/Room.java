package AutoCloseable;

import java.lang.ref.Cleaner;

public class Room implements AutoCloseable{
    private static final Cleaner cleaner = Cleaner.create();

    private  class State implements Runnable{
        int numJunkPiles;//방에 있는 쓰렉기 수

        State(int numJunkPiles){
            this.numJunkPiles = numJunkPiles;
        }

        @Override
        public void run() { //close메서드나 cleaner가 호출
            System.out.println("**방 청소**");
            numJunkPiles = 0;
        }
    }

    private final State state;//방의 상태
    private final Cleaner.Cleanable cleanable;

    public Room(int numJunkPiles){
        state = new State(numJunkPiles);
        cleanable = cleaner.register(this, state);
        System.out.println("aa");
    }

    @Override
    public void close() throws Exception {
        System.out.println("청소 전");
        Thread.sleep(1000);
        cleanable.clean();
        System.out.println("청소 후");
    }
}
