package estructurado;

public class EstructuraElseIf {
    public static void main(String[] args) {



        //siempre se deben escribir las variables o tipo de clases en forma de pregunta: es un dia de clases?
        boolean esUnDiaDeClases = true;
        final String DIA = "Lunes";

        if (esUnDiaDeClases && DIA == "Lunes") {
            System.out.println("Hoy es: " + DIA + " y no hay clases");
        }else if (!esUnDiaDeClases){ //! signo de negacion = no es un dia de clases
            System.out.println("Hoy es: " + DIA + "y no hay clases");
        }else{
            System.out.println("Pasamos al else");

            if (DIA == "Viernes"){
                System.out.println("Hoy no hay clases!");
            } else if (DIA == "Martes") {
                System.out.println("Ayer tuvimos clases");
            }
        }
    }
}
