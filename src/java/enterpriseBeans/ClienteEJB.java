package enterpriseBeans;

import entidades.Cliente;
import entidades.Grupo;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author jrvidal
 */
@Stateful
public class ClienteEJB {

    @PersistenceContext(unitName = "TiendaPU")
    private EntityManager em;
    private Cliente cliente;

    public void login(String login) {
        TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c WHERE c.login='" + login + "'", Cliente.class);
        try {
            cliente = query.getSingleResult();            
        } catch (Exception e) {
        }
    }

    public void logout() {
        cliente = null;
    }

    public void registra(String nombre, String direccion, String mail,String login, String password) {
        cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setDireccion(direccion);
        cliente.setLogin(login);
        cliente.setMail(mail);
        cliente.setPwd(DigestUtils.sha512Hex(password));
        TypedQuery<Grupo> query = em.createQuery("SELECT g FROM Grupo g WHERE g.nombre= 'clientes'", Grupo.class);
        Grupo grupo = query.getSingleResult();
        cliente.addGrupo(grupo);
        em.persist(cliente);
    }
        public void edita(String nombre, String direccion, String mail, String password) {       
        cliente.setNombre(nombre);
        cliente.setDireccion(direccion);
        cliente.setMail(mail);
        cliente.setPwd(DigestUtils.sha512Hex(password));
        em.merge(cliente);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
