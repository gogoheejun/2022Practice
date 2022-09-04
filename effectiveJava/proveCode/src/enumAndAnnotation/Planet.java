package enumAndAnnotation;

public enum Planet {
    MERCURY(12,31), //각각이 클래스이며 괄호안은 생성자 매개변수임
    VENUS(1,321),
    EARTH(12,331),
    MARS(132,31),
    JUPITER(142,31),
    SATURN(12,313),
    URANUS(124,31),
    NEPTUNE(12,331);

    private final double mass;
    private final double radius;
    private final double surfaceGravity;

    private static final double G = 6.123E-11;

    Planet(double mass, double radius){
        this.mass = mass;
        this.radius = radius;
        surfaceGravity = G*mass / (radius*radius);
    }

    public double mass()                {return mass;}
    public double radius()              {return radius;}
    public double getSurfaceGravity()   {return surfaceGravity;}

    public double surfaceWeight(double maass){
        return mass*surfaceGravity;
    }
}
