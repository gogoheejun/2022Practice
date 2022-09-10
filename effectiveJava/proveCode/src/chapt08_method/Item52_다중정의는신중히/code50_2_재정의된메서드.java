package chapt08_method.Item52_다중정의는신중히;

import java.util.List;

public class code50_2_재정의된메서드 {
    public static void main(String[] args) {
        List<Wine> wineList = List.of(
                new Wine(), new SparklignWine(), new Champagne()
        );

        /*예상대로 포도주-스파클링-샴페인 이 출력됨.
        왜냐면, 재정의(오버라이드)한 경우에는
        "가장 하위에서 정의한 메서드"가 실행되기 때문임.
        */
        for(Wine wine: wineList){
            System.out.println(wine.name());
        }
    }
}

class Wine{
    String name(){return "포도준";}
}
class SparklignWine extends Wine{
    @Override
    String name() {
        return "스파클링";
    }
}

class Champagne extends SparklignWine{
    @Override
    String name() {
        return "샴페인";
    }
}