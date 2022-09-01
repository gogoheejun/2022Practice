package equals;

public class PhoneNumber {
    private final short a, b, c;

    public PhoneNumber(int a, int b, int c){
        this.a = rangeCheck(a, 999, "지역코드");
        this.b = rangeCheck(b, 999, "프리픽스");
        this.c = rangeCheck(c, 999, "가입자 번호");
    }

    private static short rangeCheck(int val, int max, String arg){
        if(val<0 || val>max){
            throw new IllegalArgumentException("");
        }
        return (short) val;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true; //1. ==으로 자기자신 참조인지 확인
        if(!(obj instanceof PhoneNumber)) return false;//2. 올바른 타입인지 확인
        PhoneNumber pn = (PhoneNumber) obj;
        return pn.a == a && pn.b == b && pn.c == c; // 3. 핵심필드들이 모두 일치하는지 하나씩 검사
    }
}
