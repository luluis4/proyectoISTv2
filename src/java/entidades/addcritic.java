/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author luism
 */
@Entity
public class addcritic implements Serializable {

    private int idLibro;
    private String critica;
    private String login;

    private static final long serialVersionUID = 1L;

    @Id

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCritica() {
        return critica;
    }

    public void setCritica(String critica) {
        this.critica = critica;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdlibro(int idLibro) {
        this.idLibro = idLibro;

    }
}
