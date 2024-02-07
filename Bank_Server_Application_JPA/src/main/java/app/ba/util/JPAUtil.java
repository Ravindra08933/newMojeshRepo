package app.ba.util;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
public class JPAUtil
{


    static EntityManagerFactory ef=null;
    public static EntityManager em=null;
    public  static  EntityManager getEntityManager()
    {

        ef= Persistence.createEntityManagerFactory("JPA-PU");
        em=ef.createEntityManager();
        return em;
    }
}
