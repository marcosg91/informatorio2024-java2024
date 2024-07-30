package escenario1.dominio;


//se usa extends para heredar de la clase madre
public class Perro extends Animal{

    public Perro(String nombre, int edad){
        super(nombre, edad);//super: para acceder a los metodos y atributos de la clase madre
    }

    //implementando el metodo abstracto
    @Override
    public void hacerSonido() {
        System.out.println("Guau guau...");
    }
}
