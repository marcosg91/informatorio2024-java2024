import poo.dominio.Auto;
import poo.dominio.Persona;

public class App {
    public static void main(String[] args) {

        //para instanciar
        //1.tipo de dato -> clase persona
        //2.variable para guardar el objeto
        //3.instanciar objeto con new NombreDeClase();

        // se guarda informacion en el objeto creado:

        Persona persona = new Persona();
        persona.setNombre("Marcos");
        persona.setApellido("Gonzalez");
        persona.setEdad(33);
        persona.setDNI(36106605);

        Auto auto = new Auto();
        auto.setColor("azul");
        auto.setMarca("marca");
        auto.setModelo("modelo");
        auto.setPlaca("JAVA123");
        auto.setCantDePuertas(5);

        persona.setAuto(auto);


        // se muestra por pantalla:

        System.out.println(persona.getNombre());
        System.out.println(persona.getApellido());
        System.out.println(persona.getEdad());
        System.out.println(persona.getDNI());

        System.out.println(persona.getAuto().toString());

        System.out.println(persona.getAuto().getMarca());

        Persona persona2 = new Persona("Lionel","Messi",36106605,37);
        Persona persona3 = new Persona("Marcos");

    }
}
