package escenario1;

import escenario1.dominio.*;

import java.util.ArrayList;
import java.util.List;


public class App {
    public static void main(String[] args) {//la clase main es la clase del punto de entrada. inicializa ciertos objetos y ejecuta el programa
        List<ZooInterface> listaZoo = new ArrayList<ZooInterface>();

        Perro perro = new Perro("Firulais", 10);
        Perro perro2 = new Perro("Marcos", 33);
        Gato gato = new Gato("Michi", 10, "Calle");

        //lista de animales
        List<Animal> animales = new ArrayList<>();
        animales.add(perro);
        animales.add(perro2);
        animales.add(gato);


        //constructor
        Zoo zoo = new Zoo(animales);
        Perro perroSp = new Perro("Firulais", 10);
        Perro perro2Sp = new Perro("Marcos", 33);
        Gato gatoSp = new Gato("Michi", 10, "Calle");

        //lista de animales
        List<Animal> animalesSp = new ArrayList<>();
        animales.add(perroSp);
        animales.add(perro2Sp);
        animales.add(gatoSp);

        ZooSaenzPena zooSaenzPena = new ZooSaenzPena(animalesSp);

        listaZoo.add(zoo);
        listaZoo.add(zooSaenzPena);

        for (ZooInterface zooSon : listaZoo){
            zooSon.hacerSonidoAnimal();
        }

    }
}
