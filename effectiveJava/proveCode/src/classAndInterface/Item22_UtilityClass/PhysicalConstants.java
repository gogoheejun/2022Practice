package classAndInterface.Item22_UtilityClass;

/*
인터페이스는 오직 자신을 구현한 클래스의 인스턴스를 참조할 수 있는 타입의 역할로만 사용되어야 함.
책 코드22-1에 있는 상수인터페이스는 안티패턴임.상수를 뜻하는 static final필드로만 가득차고,
이를 구현하는 클래스들이 공통으로 이 상수들을 쓸 수 있도록 하는 것임.
그러나 이는 "안티패턴"임. 쓰면 안됨.

public interface PhysicalConstants{
    static final double AAA = 324.23432;
    static final double BBB = 2341.12334;
}

이를 대신할 방법은 바로 유틸리티 클래스를 사용하는 것임.
 */
public class PhysicalConstants {
    private PhysicalConstants(){} //인스턴스화 방지

    public static final double AA = 5.321_324_344;
    public static final double BB = 4.321_324_344;
    public static final double CC = 3.321_324_344;
}







