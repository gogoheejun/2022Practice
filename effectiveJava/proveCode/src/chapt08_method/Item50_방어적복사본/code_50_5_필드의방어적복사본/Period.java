package chapt08_method.Item50_방어적복사본.code_50_5_필드의방어적복사본;

import java.util.Date;

/*
50_3에서는 그냥 return start;로 끝나서 필드를 노출시켜서 문제가 생겼음.
그래서 필드를 returng하는것도 방어적 복사본을 만든다!
 */
public class Period {
    private final Date start;
    private final Date end;

    public Period(Date start, Date end){
        this.start = new Date(start.getTime()); // 복사!
        this.end = new Date(end.getTime()); // 복사!

        if(start.compareTo(end)>0)
            throw new IllegalArgumentException(
                    start+"가"+end+"보다 늦다."
            );
    }

    //아래처럼 복사!!!
    public Date start(){
        return new Date(start.getTime());
    }
    public Date end(){
        return new Date(end.getTime());
    }
}
