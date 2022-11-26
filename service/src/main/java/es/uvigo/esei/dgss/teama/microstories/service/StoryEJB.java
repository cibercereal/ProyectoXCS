package es.uvigo.esei.dgss.teama.microstories.service;


import es.uvigo.esei.dgss.teama.microstories.domain.entities.Story;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@PermitAll
public class StoryEJB {
    @PersistenceContext
    private EntityManager em;

    @PermitAll
    public List<Story> getRecentStories() {
        final int LIMIT_STORIES = 6;
        return em.createQuery("SELECT s FROM Story s WHERE s.published IS TRUE ORDER BY s.date DESC, s.id ASC ",
                Story.class).setMaxResults(LIMIT_STORIES).getResultList();
    }

    @PermitAll
    public Story getById(final int id) {
        return em.find(Story.class, id);
    }
}
