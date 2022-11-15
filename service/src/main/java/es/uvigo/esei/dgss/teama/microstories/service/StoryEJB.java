package es.uvigo.esei.dgss.teama.microstories.service;


import es.uvigo.esei.dgss.teama.microstories.entities.Story;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class StoryEJB {
    @PersistenceContext
    private EntityManager em;
    public List<Story> getRecentStories() {
        final int LIMIT_STORIES = 6;
        return em.createQuery("SELECT s FROM Story s WHERE s.published IS TRUE ORDER BY s.date DESC ",
                Story.class).setMaxResults(LIMIT_STORIES).getResultList();
    }
}