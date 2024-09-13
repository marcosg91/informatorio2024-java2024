package lambda.streams;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;

public class StreamsStructure {
    public static void main(String[] args) {
        List<String>nombres = new ArrayList<>();

        nombres.add("Juan");
        nombres.add("Maria");
        nombres.add("Marisol");
        nombres.add("Antonio");
        nombres.add("Luis");
        nombres.add("Marcos");
        nombres.add("Fabiana");

        nombres.stream()
                .map(x -> x.length())
                .filter(x -> x > 5)
                .forEach(x -> System.out.println(x));
    }
}
