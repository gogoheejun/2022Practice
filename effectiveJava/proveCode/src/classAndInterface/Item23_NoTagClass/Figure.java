package classAndInterface.Item23_NoTagClass;

/*
해당 클래스는 태그가 달린 클래스이다.
Shape이 RECTANGLE인지 CIRCLE인지 switch로 구분하고 각각의 생성자도 가지고 있다.

이렇게 쓰면 얀됨.
이 클래스는 계층구조로 바꿔야 한다.
*/
public class Figure {
    enum Shape {RECTANGLE, CIRCLE};

    final Shape shape;

    //아래 두 필드는 사각형일 때만 쓰임.
    double length;
    double width;

    //아래필드는 원일 때만 쓰임
    double radius;

    Figure(double radius){
        shape = Shape.CIRCLE;
        this.radius = radius;
    }

    Figure(double length, double width){
        shape = Shape.RECTANGLE;
        this.length = length;
        this.width = width;
    }

    double area(){
        switch (shape){
            case RECTANGLE:
                return length*length;
            case CIRCLE:
                return Math.PI * (radius*radius);
            default:
                throw new AssertionError(shape);
        }
    }
}
