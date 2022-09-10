package chapt08_method.Item50_방어적복사본.code_50_3_매개변수방어적복사본;

import java.util.Date;

/*
Item50_방어적복사본.code_50_1에서처럼 객체를 수정하는 것을 방지하기 위해..
매개변수를 복사한 다음에 유효성검사를 함.
 */
public class Period {
    private final Date start;
    private final Date end;

    public Period(Date start, Date end){
        // 복사를 한 후 유효성 검사를 해야 함. 만약 멀티스레딩이라면 유효성 검사하는 찰나에 원본객체 수정될 위험 있기에..
        this.start = new Date(start.getTime()); // 복사!
        this.end = new Date(end.getTime()); // 복사!

        if(start.compareTo(end)>0)
            throw new IllegalArgumentException(
                    start+"가"+end+"보다 늦다."
            );
    }

    public Date start(){
        return start;
    }
    public Date end(){
        return end;
    }
}
