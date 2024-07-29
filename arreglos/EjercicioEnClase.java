package arreglos;

public class EjercicioEnClase {
    public static void main(String[] args) {
        /*
        Dado un arreglo de 4 posiciones crear un programa en java que permita tomar los dos valores del medio eliminando los extremos.
        Mostrar por pantalla el resultado.
        {1,2,3,4}-->{2,3}
         */

        int[] arreglo = {1,2,3,4};
        for (int i = 1; i <((arreglo.length) - 1); i++ ){
            System.out.println(arreglo[i]);
        }
    }
}
