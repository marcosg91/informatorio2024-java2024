package escenario1.dominio;

import java.util.ArrayList;
import java.util.List;

public class ZooSaenzPena implements ZooInterface {//utilizando interfaz
    private List<Animal> animales = new ArrayList<>();

    public ZooSaenzPena(List<Animal> animales) {
        this.animales = animales;
    }

    @Override
    public void hacerSonidoAnimal() {
        System.out.println("Zoologico Saenz Peña");

        for (Animal animal : animales){
            System.out.println("Animal : ");
            animal.hacerSonido();
        }
    }
}