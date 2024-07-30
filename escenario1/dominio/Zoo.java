package escenario1.dominio;

import java.util.ArrayList;
import java.util.List;

public class Zoo implements ZooInterface {//utilizando interfaz
    private List<Animal> animales = new ArrayList<>();

    //constructor que recibe lista de animales
    public Zoo(List<Animal>animales){
        this.animales = animales;
    }


    //metodo, polimorfismo
    @Override
    public void hacerSonidoAnimal(){
        for (Animal animal : animales){
            animal.hacerSonido();
        }
    }

    public List<Animal> getAnimales() {
        return animales;
    }

    public void setAnimales(List<Animal> animales) {
        this.animales = animales;
    }
}
