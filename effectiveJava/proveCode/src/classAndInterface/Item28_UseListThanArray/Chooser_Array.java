package classAndInterface.Item28_UseListThanArray;

import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Chooser_Array<T> {
    private final T[] choiceArray;

    public Chooser_Array(Collection<T> choices){
//        choiceArray = choices.toArray();// 이러면 컴파일오류

        choiceArray = (T[])choices.toArray();//Object배열을 T배열로 형변환..
        /*
        그러나 위에선 경고가 뜬다. T가 뭔지 모르니 런타임시 안전보장을 못한다는 것임.
         */

    }

    public Object choose(){
        Random rnd = ThreadLocalRandom.current();
        return choiceArray[rnd.nextInt(choiceArray.length)];
    }
}
