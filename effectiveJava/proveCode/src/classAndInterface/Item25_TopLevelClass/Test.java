package classAndInterface.Item25_TopLevelClass;

/*
똑같은 클래스를 가지고 있는 java파일이 있다면,
이런 톱래밸 클래스들(Utensil, Dessert)를 서로 다른 소스파일로 분리하면 가장 간단하게 해결됨.
근데 굳이 한 파일에 담고 싶으면 아래처럼 정적 멤버 클래스(static class)를 사용할 수 있다.
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(Utensil.NAME+Dessert.NAME);
    }

    private static class Utensil{
        static final String NAME = "pan";
    }

    private static class Dessert{
        static final String NAME = "cake";
    }
}
