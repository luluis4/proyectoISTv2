package enterpriseBeans;

import entidades.Tema;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author jrvidal
 */
@Stateless
public class CatalogoEJB {
    
    @PersistenceContext(unitName = "TiendaPU")
    private EntityManager em;

    public List<Tema> todosLosTemas() {
        TypedQuery<Tema> query = em.createNamedQuery("todosLosTemas", Tema.class);
        return query.getResultList();
    }   
}
