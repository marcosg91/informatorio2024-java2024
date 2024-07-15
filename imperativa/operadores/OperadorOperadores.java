package imperativa.operadores;

public class OperadorOperadores {
    public static void main(String[] args) {
        int resultado = 5 + 3 * 2;
        int resultado2 = 20/4 * 2 + 3;

        //operador ternario
        //>< = >= <=
        boolean resultado3 = (resultado2 > resultado) ? true : false;

        int value2;
        int value = 5;
        value2 =value++;//asigna luego incrementa
        value2 =++value;//incrementa value luego asigna
        value2 =--value;//decrementa value luego asigna
    }
}
