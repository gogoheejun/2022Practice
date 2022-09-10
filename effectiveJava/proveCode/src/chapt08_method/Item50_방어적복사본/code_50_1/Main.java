package chapt08_method.Item50_방어적복사본.code_50_1;

import java.util.Date;

/*
Period클래스는 불변으로 만들려고 하였으나,
아래처럼 Date를 이용하면 가변이 된다.
즉, Date대신 Instant를 사용하는 것이 좋음.
Date는 낡은 API이므로 더이상 사용금지..
 */
public class Main {
    public static void main(String[] args) {
        Date start = new Date();
        Date end = new Date();
        Period p = new Period(start,end);
        end.setYear(78);//p의 내부가 수정됨
    }
}
