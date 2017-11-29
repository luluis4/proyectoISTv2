package enterpriseBeans;

import entidades.Cliente;
import entidades.Libro;
import entidades.LibroVendido;
import entidades.Pedido;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jrvidal
 */
@Stateful
public class CarroCompraEJB {

    private List<LibroEnCarro> libros = new ArrayList<>();
    private float total;
    @PersistenceContext(unitName = "TiendaPU")
    private EntityManager em;

    public void ponEnCarro(Libro libro) {
        if (!isVacio()) {
            Iterator<LibroEnCarro> i = libros.iterator();
            while (i.hasNext()) {
                LibroEnCarro libroEnCarro = i.next();
                if (libroEnCarro.getLibro().equals(libro)) {
                    libroEnCarro.incCantidad();
                    return;
                }
            }
        }
        LibroEnCarro libroEnCarro = new LibroEnCarro(libro);
        getLibros().add(libroEnCarro);
    }    

    @RolesAllowed("clientes")
    public void hacePedido(Cliente cliente) {
        System.out.println("pedido");
        if (!isVacio()) {
            Pedido pedido = new Pedido();
            Iterator<LibroEnCarro> i = libros.iterator();
            while (i.hasNext()) {
                LibroEnCarro enCarro = i.next();
                Libro libro = enCarro.getLibro();
                enCarro.setCantidad(Math.min(libro.getInventario(), enCarro.getCantidad()));
                libro.setInventario(libro.getInventario() - enCarro.getCantidad());
                if (libro.getInventario() == 0) {
                    libro.setDisponible(false);
                }
                em.merge(libro);
                LibroVendido vendido = new LibroVendido();
                vendido.setLibro(libro);
                vendido.setCantidad(enCarro.getCantidad());
                vendido.setImporte(enCarro.getSubTotal());
                vendido.setPedido(pedido);
                pedido.addLibro(vendido);
            }
            pedido.setImporte(getTotal());
            pedido.setEstado("pendiente");
            pedido.setCliente(cliente);
            GregorianCalendar calendar = new GregorianCalendar();
            pedido.setFecha(calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR) + ",  "
                    + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
            cliente.addPedido(pedido);
            em.merge(cliente);
            vaciaCarro();
        }
    }

    public void vaciaCarro() {
        libros.clear();
    }

    public boolean isVacio() {
        if (getLibros().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public float getTotal() {
        total = Float.valueOf("0");
        Iterator<LibroEnCarro> i = libros.iterator();
        while (i.hasNext()) {
            total += i.next().getSubTotal();
        }
        return (total);
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public List<LibroEnCarro> getLibrosEnCarro() {
        return getLibros();
    }

    public void setLibrosEnCarro(List<LibroEnCarro> libros) {
        this.setLibros(libros);
    }

    public List<LibroEnCarro> getLibros() {
        return libros;
    }

    public void setLibros(List<LibroEnCarro> libros) {
        this.libros = libros;
    }
}