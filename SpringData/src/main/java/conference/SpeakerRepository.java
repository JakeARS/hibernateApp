package conference;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class SpeakerRepository {

    @Autowired
    @PersistenceContext
    private EntityManager em;


    public List<Speaker> findByName(String name) {
        Query query = em.createQuery("select s from Speaker as s where s.name = :name");
        return query.setParameter("name", name).getResultList();
    }

    public List<Speaker> getAllSpeakers() {
        return em.createQuery("select s from Speaker as s").getResultList();
    }

    @Transactional
    public void save(List<Speaker> speakers) {
        for (Speaker speaker:
             speakers) {

            //em.persist(speaker);
            //em.getTransaction().commit();

            saveSpeaker(speaker);
        }
    }

    @Transactional
    public void saveSpeaker(Speaker speaker) {
        /*Session session = em.unwrap(Session.class);
        session.saveOrUpdate(speaker);
        session.flush();*/
        em.persist(speaker);
        em.flush();
    }

    public long count() {
        return (long) em.createQuery("select count(s.name) from Speaker as s").getSingleResult();
    }

    public void deleteAll() {
        //em.createQuery("delete from Speaker").executeUpdate();
    }
}
