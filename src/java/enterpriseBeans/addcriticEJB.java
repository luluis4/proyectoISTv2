package enterpriseBeans;

import entidades.addcritic;
import entidades.Grupo;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author luism
 */
@Stateful
public class addcriticEJB {

    @PersistenceContext(unitName = "TiendaPU")
    private EntityManager em;
    private addcritic critic;
    


    public void registracritic(String login, String critica, int idLibro) {
        setCritic(new addcritic());
        getCritic().setLogin(login);
        getCritic().setCritica(critica);
        getCritic().setIdlibro(idLibro);

        em.persist(getCritic());
    }


    public addcritic getCritic() {
        return critic;
    }


    public void setCritic(addcritic critic) {
        this.critic = critic;
    }
}
