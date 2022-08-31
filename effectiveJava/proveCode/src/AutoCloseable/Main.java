package AutoCloseable;



public class Main {
    public static void main(String[] args) throws Exception {
        try(Room myRoom = new Room(7)){
            System.out.println("안녕!");
        }

        //=============아래에서는 "방 청소"를 기대할 수 없다.
        //System.gc()로 강제로 청소하면 할 수 있지만, 보장할 수 없다.
//        new Room(99);
//        System.out.println("아무렴");
//        System.gc();
    }
}