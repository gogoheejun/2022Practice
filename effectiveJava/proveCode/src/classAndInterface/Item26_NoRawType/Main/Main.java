package classAndInterface.Item26_NoRawType.Main;

import java.util.ArrayList;
import java.util.List;

/*
다음 코드는 unsaefAdd에서 List를 로타입으로 선언시 문제가 런타임 오류 발생할 수 있음을 보여준다.
unsafeAdd의 매개변수로 차라리, List<Object> 또는 List<?>로 선언해주면 컴파일 시 미리 문제를 파악할 수 있다.
 */
public class Main {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>(); //만약 List<Object>를 하면 컴파일 오류로 잡아준다

        unsafeAdd(strings, Integer.valueOf(42));
        //===여기까진 실행되나
        String s = strings.get(0);//여기서 컴파일은 되지맘 런타임 시 ClassCastException 터짐
    }

    private static void unsafeAdd(List list, Object o){
        list.add(o);
    }
}
