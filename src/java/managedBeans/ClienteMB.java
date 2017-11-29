package managedBeans;

import javax.inject.Named;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import enterpriseBeans.ClienteEJB;
import entidades.Cliente;
import entidades.Pedido;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author jrvidal
 */
@Named(value = "clienteMB")
@SessionScoped
public class ClienteMB implements Serializable {

    @EJB
    private ClienteEJB clienteEJB;    
    private String errorMessage;
    private String nombre;
    private String mail;
    private String direccion;
    private String login;
    private String password;
    private String password2;
    private String loginTime;    

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.login(login, password);
            clienteEJB.login(login);
            nombre=clienteEJB.getCliente().getNombre();
            direccion=clienteEJB.getCliente().getDireccion();
            mail=clienteEJB.getCliente().getMail();
            GregorianCalendar calendar = new GregorianCalendar();
            loginTime = calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR) + ",  "
                    + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
        } catch (ServletException e) {
            return "";
        }      
        return "inicio";
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
            clienteEJB.logout();
            nombre=null;
            mail=null;
            direccion=null;
            login=null;
            password=null;
            password2=null;
        } catch (ServletException e) {
        }
        return "inicio";
    }

    public String registra() {
        if (nombre.isEmpty()) {
            setErrorMessage("El nombre no puede estar en blanco");
        } else if (direccion.isEmpty()) {
            setErrorMessage("La dirección no puede estar en blanco");
        } else if (mail.isEmpty()) {
            setErrorMessage("La dirección de correo no puede estar en blanco");
        } else if (login.isEmpty()) {
            setErrorMessage("El login no puede estar en blanco");
        } else if ((password.isEmpty()) || (!password.equals(password2))) {
            setErrorMessage("Las dos contraseñas introducidas no coinciden");
        } else {
            clienteEJB.registra(nombre, direccion, mail, login, password);
            return login();
        }
        return ("registroError");
    }
        public String editaCliente() {
        if (nombre.isEmpty()) {
            setErrorMessage("El nombre no puede estar en blanco");
        } else if (direccion.isEmpty()) {
            setErrorMessage("La dirección no puede estar en blanco");
        } else if (mail.isEmpty()) {
            setErrorMessage("La dirección de correo no puede estar en blanco");
        } else if (login.isEmpty()) {
            setErrorMessage("El login no puede estar en blanco");
        } else if ((password.isEmpty()) || (!password.equals(password2))) {
            setErrorMessage("Las dos contraseñas introducidas no coinciden");
        } else {
            clienteEJB.edita(nombre, direccion, mail, password);
            return "inicio";
        }
        return ("registroError");
    }

    public boolean isLogged() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        if (request.getUserPrincipal() != null) {
            return true;
        } else {
            return false;
        }
    }

    public Cliente getCliente() {        
        return clienteEJB.getCliente();
    }

    public List<Pedido> getPedidos() {
        return clienteEJB.getCliente().getPedidos();
    }

    public boolean isHayPedidos() {
        if (clienteEJB.getCliente()==null)return false;
        return !clienteEJB.getCliente().getPedidos().isEmpty();

    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
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

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }
}
