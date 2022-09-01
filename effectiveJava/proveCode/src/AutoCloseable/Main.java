package AutoCloseable;



public class Main {
    public static void main(String[] args) throws Exception {

        try( Room myRoom = new Room(7)){
            System.out.println("안녕!");
        }
        //try with resource구문이 끝나면서 자동으로 close()메서드가 실행되면서
        //cleanable.clean()을 실행하면 run()이 실행되면서

        System.out.println("끝");


        //=============아래에서는 "방 청소"를 기대할 수 없다.
        //System.gc()로 강제로 청소하면 할 수 있지만, 보장할 수 없다.
//        new Room(99);
//        System.out.println("아무렴");
//        System.gc();
    }
}