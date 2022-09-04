package classAndInterface.Item23_NoTagClass;

/*
이렇게 추상클래스를 만들고 계층구조를 만드는 것이 훨씬 좋다.
 */
abstract class OkFigure {
    abstract double area();
}

class Circle extends OkFigure{

    final double radius;
    Circle(double radius){
        this.radius = radius;
    }

    @Override
    double area() {
        return Math.PI * (radius*radius);
    }
}

class Rectangle extends OkFigure{
    final double length;
    final double width;

    Rectangle(double length, double width){
        this.length = length;
        this.width = width;
    }

    @Override
    double area() {
        return length*length;
    }
}