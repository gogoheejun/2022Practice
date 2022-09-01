package equals;

import java.awt.*;

public class ColorPoint extends Point{

    private final Color color;
    public ColorPoint(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Point)) return false;

        //obj가 Point면 색상무시하고 비교
        if(!(obj instanceof ColorPoint)) return obj.equals(this);

        //obj가 ColorPoint면 색상까지 비교
        return super.equals(obj) && ((ColorPoint) obj).color == color;
    }
}
