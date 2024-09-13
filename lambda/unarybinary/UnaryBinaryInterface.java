package lambda.unarybinary;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class UnaryBinaryInterface {
    public static void main(String[] args) {

        //uanry operator <T> extiende de Function <T, T> es una interfaz funcional con un metodo abstracto:
        //T apply(T t)
        UnaryOperator<String> unaryOp = name -> "Mi nombre es " + name;
        System.out.println("Unary Operator : " + unaryOp.apply("Marcos"));

        //binary operator <T> extiende de BiFunction <T, T, T> es una interfaz funcional con un metodo abstracto:
        //T apply(T t1, T2 t2)
        BinaryOperator<String> binaryOp = (s1, s2) -> s1.concat(s2);
        System.out.println("Binary Operator : " + binaryOp.apply("Marcos", "Gonzalez"));
    }
}
