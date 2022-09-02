package classAndInterface.해결책_Composition;



import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;


public class Main {
    public static void main(String[] args) {
        InstrumentedSet<String> s = new InstrumentedSet<>(new HashSet<String>());
        s.addAll(List.of("aa","bb","cc"));
        int expectThree = s.getAddCount();
        System.out.println(expectThree);// 이제는 3이 나온다!!
        /* 이유: 데코레이션 페턴을 썼기 때문임.
        ForwardingSet의 add가 Set의 add를 오버라이딩한 게 아님.
        필드로 Set<E> s를 따로 가지고, 얘가 s.add를 수행한 것이므로
        ForwardingSet이 addAll을 할때 s.addAll이 수행되지만 Set의 add는 오버라이드 되지 않았으므로 addCount++가 중복수행되지 않음
        * */
    }
}
