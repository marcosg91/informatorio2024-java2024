package poo.dominio;
// se crea el objeto
public class Persona {
        // atributos y metodos (responsabilidades)
        private String nombre;
        private String apellido;
        private int edad;
        private int DNI;

        // relaciones

        private Auto auto;

        // constructor vacio
        public Persona(){}

        // constructor completo
        public Persona(String nombre, String apellido, int DNI, int edad){
                this.nombre = nombre;
                this.apellido = apellido;
                this.edad = edad;
                this.DNI = DNI;
        }
        public Persona(String nombre){
                this.nombre = nombre;
        }

        // [clasificador] [tipoDeRetorno] nombreDelMetodo ([<expresiones>]){}

        // setters para guardar informacion
        public void setNombre(String nombre){
                // this es una forma de apuntar a atributos y metodos de la clase
                boolean esNombreValido = validarNombre(nombre);

                if (esNombreValido) {
                        this.nombre = nombre;
                }else{
                        System.out.println("Nombre invalido");
                }
        }
        private boolean validarNombre(String nombre){
                if (nombre == null || nombre.length() == 0) {
                        return false;
                }
                return true;
        }
        public void setApellido(String apellido){
                this.apellido = apellido;
        }
        public void setEdad(int edad){
                this.edad = edad;
        }
        public void setDNI(int DNI){
                this.DNI = DNI;
        }

        public void setAuto(Auto auto) {
                this.auto = auto;
        }

        // getters para devolver informacion
        public String getNombre() {
                return this.nombre;
        }

        public String getApellido() {
                return this.apellido;
        }

        public int getEdad() {
                return this.edad;
        }

        public int getDNI() {
                return this.DNI;
        }

        public Auto getAuto() {
                return auto;
        }
}
