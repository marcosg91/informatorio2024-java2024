package lambda.consumer;

import java.util.function.BiConsumer;

public class BiConsumerInterface {
    public static void main(String[] args) {

        BiConsumer<String, Integer> biConsumer = (x,y) -> System.out.println(x + " " + y);
        biConsumer.accept("Hola tengo : ", 50);
    }
}
