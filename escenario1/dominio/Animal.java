package escenario1.dominio;

//aplicar concepto de clase abstracta
//abstract a nivel de clase=no permite instanciarlo
abstract public class Animal {
    protected String nombre;
    protected int edad;


    //constructor
    public Animal(String nombre, int edad){
        this.nombre = nombre;
        this.edad = edad;
    }

    public abstract void hacerSonido();
    //abstract --> no permite implementacion "{}"

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

}
