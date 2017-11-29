package managedBeans;

import enterpriseBeans.CarroCompraEJB;
import enterpriseBeans.LibroEnCarro;
import entidades.Cliente;
import entidades.Libro;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author jrvidal
 */
@Named(value = "carroCompraMB")
@SessionScoped
public class CarroCompraMB implements Serializable {

    @EJB
    private CarroCompraEJB carroCompraEJB;
    @Inject
    private ClienteMB clienteMB;    
  
    public String poneEnCarro(Libro libro) {
        carroCompraEJB.ponEnCarro(libro);        
        return "carroCompra";
    }

    public String vaciaCarro() {
        carroCompraEJB.vaciaCarro();       
        return "listaTemas";
    }

    public String hacePedido()
    {
        Cliente cliente = clienteMB.getCliente();
        if (cliente == null) {           
            return ("login");
        }
        carroCompraEJB.hacePedido(cliente);
        return "listaPedidos";
    }

    public boolean isVacio() {
        return carroCompraEJB.isVacio();
    }

    public int getNumArticulos() {
        return carroCompraEJB.getLibros().size();
    }

    public List<LibroEnCarro> getLibros() {
        return carroCompraEJB.getLibros();
    }

    public float getTotal() {
        return carroCompraEJB.getTotal();
    }
    
    public void setClienteMB(ClienteMB clienteMB) {
        this.clienteMB = clienteMB;
    }

    public ClienteMB getClienteMB() {
        return clienteMB;
    }   
}
