package managedBeans;

import entidades.Libro;
import entidades.Tema;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import enterpriseBeans.CatalogoEJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author jrvidal
 */
@Named(value = "catalogoMB")
@SessionScoped
public class CatalogoMB implements Serializable {

    @EJB
    private CatalogoEJB catalogoEJB;
    private Tema tema;
    private Libro libro;

    public List<Tema> getTemas() {
        return catalogoEJB.todosLosTemas();
    }

    public String verTema(Tema tema) {
        setTema(tema);
        return "listaLibros";
    }

    public String verLibro(Libro libro) {
        setLibro(libro);
        return "detallesLibro";
    }

    public boolean isDisponible() {
        return (getLibro().isDisponible());
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
}
