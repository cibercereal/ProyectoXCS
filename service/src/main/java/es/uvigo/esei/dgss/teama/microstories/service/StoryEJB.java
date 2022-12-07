package es.uvigo.esei.dgss.teama.microstories.service;


import es.uvigo.esei.dgss.teama.microstories.domain.entities.Genre;
import es.uvigo.esei.dgss.teama.microstories.domain.entities.Story;
import es.uvigo.esei.dgss.teama.microstories.domain.entities.Theme;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
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

    public int calculatePagesSearch(String text, int maxItems) {
        long numStories = em
                .createQuery("SELECT COUNT(s) FROM Story s WHERE s.published = TRUE and"
                        + " (s.content LIKE :t or s.title LIKE :t) ", Long.class)
                .setParameter("t", "%" + text + "%").getSingleResult();

        if (numStories <= maxItems) {
            return 1;
        } else {
            double pages = (double) numStories / maxItems;
            return (int) Math.ceil(pages);
        }
    }
    /**
     * Search story that content or title contains a text
     *
     * @param text The story id to search.
     * @return The story that corresponds with the search text.
     */
    public List<Story> searchStory(String text,int page,int maxItems){
        if (maxItems <= 0 || page < 0) {
            throw new IllegalArgumentException("pagNumber or maxItems can not be 0 or less than 0 )");
        }

        return em.createQuery("SELECT s FROM Story  s WHERE s.published = TRUE " +
                        "and (s.content LIKE :t OR s.title Like :t) ",Story.class).setParameter("t", "%" + text + "%").setFirstResult((page - 1) * maxItems)
                .setMaxResults(maxItems).getResultList();

    }

    /**
     * Search story that matches with genre, theme and publication
     *
     * @param genre The story genre to search.
     * @param theme The story theme to search.
     * @param publication The story publication to search.
     * @return The story that corresponds with the search text.
     */
    public List<Story> exploreStory(Genre genre, Theme theme, Date publication, int page, int maxItems){
        return null;
    }

    /**
     * Search story that matches with genre, theme and publication
     *
     * @param genre The story genre to search.
     * @param theme The story theme to search.
     * @param publication The story publication to search.
     * @return The story that corresponds with the search text.
     */
    public List<Story> exploreStory(Genre genre, Theme theme, String publication, int page, int maxItems){
        return null;
    }
}