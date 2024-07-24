package estructurado;

public class EstructuraSwitchCase {
    public static void main(String[] args) {
        int calificacion1 = 10;
        int calificaicon2 = 5;
        String mensaje;

        int division = (calificacion1 + calificaicon2) / 2;
        System.out.println("Valor: " + division);

        switch ( division ){
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                mensaje = "desaprobado";
                break;
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                mensaje = "aprobado";
                break;
            default:                              //siempre tiene que estar presente
                mensaje = "valor no valido";
                break;
        }

        System.out.println(mensaje);
    }
}
