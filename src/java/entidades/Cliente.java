package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author jrvidal
 */
@Entity
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    private String nombre;
    private String mail;
    private String direccion;
    private String login;
    private String pwd;
    private List<Grupo> grupos;
    private List<Pedido> pedidos;

    @Id
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Column(nullable = false, length = 128) //sha-512 + hex
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @ManyToMany
    public List<Grupo> getGrupos() {
        return grupos;
    }
    
    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public void addGrupo(Grupo grupo) {
        if (grupos == null) {
            this.grupos = new ArrayList();
        }
        this.grupos.add(grupo);
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public void addPedido(Pedido pedido) {
        pedidos.add(pedido);
    }
}
