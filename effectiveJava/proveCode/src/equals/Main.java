package equals;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        CaseInsensitiveString cis = new CaseInsensitiveString("TEST");
        String s = "test";

        System.out.println("========추이성=========");
        //추이성 위배!!
        System.out.println(cis.equals(s));//true
        System.out.println(s.equals(cis));//false

        System.out.println("========대칭성=========");

        //대칭성 위배!
        ColorPoint p1 = new ColorPoint(1,2,Color.RED);
        Point p2 = new Point(1,2);
        ColorPoint p3 = new ColorPoint(1,2,Color.BLUE);

        System.out.println(p1.equals(p2));
        System.out.println(p2.equals(p3));//위두개는 색을 무시하고 비교하게에 true지만
        System.out.println(p1.equals(p3));//얘는 색을 비교하기에 false가 됨

    }
}
