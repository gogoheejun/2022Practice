package chapt08_method.Item50_방어적복사본.code_50_3_매개변수방어적복사본;

import java.util.Date;

/*
아니 근데 이렇게 해도 내부 수정이 되어버림.
 */
public class Main {
    public static void main(String[] args) {
        Date start = new Date();
        Date end = new Date();
        Period p = new Period(start,end);
        p.end().setYear(78); // p내부 변경했다!!
    }
}
