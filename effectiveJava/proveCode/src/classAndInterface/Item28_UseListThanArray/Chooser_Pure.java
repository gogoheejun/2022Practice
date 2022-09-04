package classAndInterface.Item28_UseListThanArray;

import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/*
해당 클래스를 사용하려면, choose메서드를 호출할 때마다 반환된 Object를 형변환해서 사용해야 함.
 */
public class Chooser_Pure {
    private final Object[] choiceArray;

    public Chooser_Pure(Collection choices){
        this.choiceArray = choices.toArray();
    }

    public Object choose(){
        Random rnd = ThreadLocalRandom.current();
        return choiceArray[rnd.nextInt(choiceArray.length)];
    }
}
