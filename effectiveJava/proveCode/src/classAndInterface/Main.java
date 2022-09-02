package classAndInterface;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        InstrumentedHashSet<String> s = new InstrumentedHashSet<>();
        s.addAll(List.of("aa","bb","cc"));
        int expectThree = s.getAddCount();
        System.out.println(expectThree);// 3을 기대했으나, 6이 나옴...
        // 원인:
        /*
        HashSet의 addAll은 각 원소를 add메서드를 호출해서 추가함. 그런데 InstrumentedHashSet에서 add를 재정의해서 addCount에 값이 중복으로 더해짐.
         */

    }
}
