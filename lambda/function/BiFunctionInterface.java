package lambda.function;

import java.util.function.BiFunction;

public class BiFunctionInterface {
    public static void main(String[] args) {

        BiFunction<String, Integer, String> biFunction = (x,y) -> y + " " + x;

        System.out.println(biFunction.apply("Hola tengo ", 23));
        System.out.println(biFunction.apply("Hola tengo ", 25));
        System.out.println(biFunction.apply("Hola tengo ", 50));
        System.out.println(biFunction.apply("Hola tengo ", 30));
        System.out.println(biFunction.apply("Hola tengo ", 20));

        ejemploMetodo(biFunction, "Hola tengo ", 43);

    }

    private static void ejemploMetodo(BiFunction<String, Integer, String> biFunction, String mensaje, int edad){
        System.out.println(biFunction.apply("Hola tengo ", 20));
    }
}
