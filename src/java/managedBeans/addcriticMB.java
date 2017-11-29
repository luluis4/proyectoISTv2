/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import enterpriseBeans.addcriticEJB;
import entidades.addcritic;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
/**
 *
 * @author luism
 */
@Named(value = "addcriticMB")
@SessionScoped
public class addcriticMB implements Serializable {

    @EJB
    private addcriticEJB addcriticEJB;    
    private String errorMessage;
    private String libro;
    private String critica;
    private String login;

    /**
     * Creates a new instance of addcriticMB
     */
    public addcriticMB() {
    }
    
}
