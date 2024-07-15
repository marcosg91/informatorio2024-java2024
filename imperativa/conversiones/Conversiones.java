package imperativa.conversiones;

public class Conversiones {
    public static void main(String[] args) {
        //promocion
        int value1 = 5;

        //contraccion
        int value2 = (int)5.69f;//cast
        System.out.println(value2);

        //implicita
        double value3 = 10 * 20.5d;
        System.out.println(10 * 20.5d);

        int value4 = (1 + 2) * 4/15 + 20 * 2;
        System.out.println(value4);
    }
}
