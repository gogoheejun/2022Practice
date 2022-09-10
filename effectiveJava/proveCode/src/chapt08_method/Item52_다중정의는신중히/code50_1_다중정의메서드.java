package chapt08_method.Item52_다중정의는신중히;

import java.util.*;
/*
main에서 의도한 것은 집합-리스트-그외 였겠지만,
실제로는 그외-그외-그외만 나온다.
그 이유는 오버로드(다중정의)한 경우 메서드 선택기준은 런타임이 아니라, 컴파일타임이기 때문임.
 */
public class code50_1_다중정의메서드 {

    public static String classify(Set<?> s){
        return "집합";
    }

    public static String classify(List<?> list){
        return "리스트";
    }

    public static String classify(Collection<?> c){
        return "그외";
    }

    public static void main(String[] args) {
        Collection<?>[] collections = {
                new HashSet<String>(),
                new ArrayList<String>(),
                new HashMap<String, String>().values()
        };

        for(Collection<?> c: collections){
            System.out.println(classify(c));
        }
    }
}

