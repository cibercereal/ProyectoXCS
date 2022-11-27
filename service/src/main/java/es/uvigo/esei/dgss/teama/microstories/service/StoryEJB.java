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
    final int PAGE_SIZE = 9;
  
    @PersistenceContext
    private EntityManager em;

    @PermitAll
    public List<Story> getRecentStories() {
        final int LIMIT_STORIES = 6;
        return em.createQuery("SELECT s FROM Story s WHERE s.published IS TRUE ORDER BY s.date DESC, s.id ASC",
                Story.class).setMaxResults(LIMIT_STORIES).getResultList();
    }

    @PermitAll
    public Story getById(final int id) {
        return em.find(Story.class, id);
    }
    
    public List<Story> getStoriesContainingText(String text, int pageNumber) {
      Query query = em.createQuery("SELECT s FROM Story s WHERE s.title like :text OR s.content like :text ORDER BY s.date DESC, s.id ASC", Story.class);
      query.setParameter("text", "%" + text + "%");
      query.setFirstResult((pageNumber-1) * PAGE_SIZE); 
      query.setMaxResults(PAGE_SIZE);
      
      return query.getResultList();
      
  }
}
