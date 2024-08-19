package escenario2.dominio;

import java.util.ArrayList;
import java.util.List;

public class CursoPractico extends Curso{
    private List<Recursos> recursos = new ArrayList();

    public List<Recursos> getRecursos() {
        return recursos;
    }

    public void setRecursos(List<Recursos> recursos) {
        this.recursos = recursos;
    }
}
