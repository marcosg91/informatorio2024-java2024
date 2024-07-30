package poo.dominio;

public class Auto {
    private String marca;
    private int cantDePuertas;
    private String modelo;
    private String color;
    private String placa;

    //setter
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setCantDePuertas(int cantDePuertas) {
        this.cantDePuertas = cantDePuertas;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    //getter
    public String getMarca() {
        return marca;
    }

    public int getCantDePuertas() {
        return cantDePuertas;
    }

    public String getModelo() {
        return modelo;
    }

    public String getColor() {
        return color;
    }

    public String getPlaca() {
        return placa;
    }

    //pisando un metodo que JAVA crea
    @Override
    public String toString() {
        return "Marca: "+marca
                + " Modelo: "+modelo
                + " Color: "+color
                + " Placa: "+placa;

    }
}
