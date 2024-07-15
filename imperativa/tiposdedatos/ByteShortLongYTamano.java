package imperativa.tiposdedatos;

public class ByteShortLongYTamano {
    public static void main(String[]args){
        //Byte = 8 bits
        //Clase wrapper de Byte
        System.out.println("Rango del valor del byte: "+Byte.MIN_VALUE+""+Byte.MAX_VALUE);

        //Primitivo
        byte minValue = -128;
        byte maxValue = 127;
        ////////////////////////////////////////////////////////////////////////

        //Short = 16 bits
        //Clase wrapper de Short

        System.out.println("Rango del valor del short: "+Short.MIN_VALUE+""+Short.MAX_VALUE);

        //Primitivo
        short minValueShort = -32768;
        short maxValueShort = 32767;
        ////////////////////////////////////////////////////////////////////////

        //Integer = 32 bits
        //Clase wrapper de Integer

        System.out.println("Rango del valor del integer: "+Integer.MIN_VALUE+""+Integer.MAX_VALUE);

        //Primitivo
        int minValueInt = -2147483648;
        int maxValueInt = 2147483647;
        ////////////////////////////////////////////////////////////////////////


        //Long
        //Clase wrapper de Integer

        System.out.println("Rango del valor del long: "+Long.MIN_VALUE+""+Long.MAX_VALUE);

        //Primitivo
        long ValueLong = 2147483647L;
        ////////////////////////////////////////////////////////////////////////

        final long SCREAMER_CAMEL_CASE = 2147483647L; // forma de declarar valores constantes
    }
}
