package ar.com.instituto.domain;

import java.util.ArrayList;
import java.util.List;

//en las clases de entidad se dejan los atributos, metodos para construir el objeto y metodos propios de la construccion de la entidad
public class CursoPractico extends Curso{
    private List<Recursos> recursos = new ArrayList();

    public List<Recursos> getRecursos() {
        return recursos;
    }

    public void setRecursos(List<Recursos> recursos) {
        this.recursos = recursos;
    }
}
