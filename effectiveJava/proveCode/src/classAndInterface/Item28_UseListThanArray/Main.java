package classAndInterface.Item28_UseListThanArray;

import java.util.ArrayList;
import java.util.List;
/*
# 차이1
배열: Sub가 Super의 하위타입이라면, Sub[]는 Super[]의 하위타입이 됨
리스트: List<Sub>은 List<Super> 은 서로 다른 타입임.

# 차이2
배열은 실체화된다.
즉, 배열은 런타임에도 자신이 담기로 한 원소의 타입을 체크함.
그러나 제네릭은 컴파일시에 검샇고, 런타임시에는 체크하지 않음.

 */
public class Main {
    public static void main(String[] args) {

        //코드 28-1 런타임 시 실패
        Object[] objectArray = new Long[1];
        objectArray[0] = "타입이 달라 넣을 수 없다";// 런타임 시 exception이 터진다.
    }
}

class Main2 {
    public static void main(String[] args) {
        //코드 28-2 컴파일에서 오류 발견
//        List<Object> ol = new ArrayList<Long>();//애초에 여기서 빨간줄이 생김.
//        ol.add("타입이 달라 넣을 수 없다");
    }
}