package es.uvigo.esei.dgss.teama.microstories.service;


import es.uvigo.esei.dgss.teama.microstories.domain.entities.Story;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@PermitAll
public class StoryEJB {

    @PersistenceContext
    private EntityManager em;

    public List<Story> getRecentStories() {
        final int LIMIT_STORIES = 6;
        return em.createQuery("SELECT s FROM Story s WHERE s.published IS TRUE ORDER BY s.date DESC, s.id ASC",
                Story.class).setMaxResults(LIMIT_STORIES).getResultList();
    }

    /**
     * Search for a story by its primary key.
     *
     * @param id The story id to search.
     * @return The story that corresponds with the search id.
     */
    public Story getById(final int id) {
        return em.find(Story.class, id);
    }

    public List<Story> getStoriesByText(String text, int pageNumber, int maxItems) {
        if (maxItems <= 0 || pageNumber < 0) {
            throw new IllegalArgumentException("pagNumber or maxItems can not be 0 or less than 0 )");
        }

        Query query = em.createQuery("SELECT s FROM Story s WHERE s.title like :text OR s.content like :text ORDER BY s.date DESC, s.id ASC", Story.class);
        query.setParameter("text", "%" + text + "%");

        if(pageNumber == 0){
            query.setFirstResult(0);
        }else{
            query.setFirstResult(pageNumber * maxItems);
        }

        query.setMaxResults(maxItems);

        return query.getResultList();
    }
}