package es.uvigo.esei.dgss.teama.microstories.service;


import es.uvigo.esei.dgss.teama.microstories.domain.entities.Genre;
import es.uvigo.esei.dgss.teama.microstories.domain.entities.Publication;
import es.uvigo.esei.dgss.teama.microstories.domain.entities.Story;
import es.uvigo.esei.dgss.teama.microstories.domain.entities.Theme;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
     * Search for a story by its primary key and add visit into table VISITDATE.
     *
     * @param id The story id to search.
     * @return The story that corresponds with the search id.
     */
    public Story getById(final int id) {
        Story story = em.find(Story.class, id);
        story.addVisit(new Date());
        em.persist(story);
        return story;
    }

    public List<Story> getStoriesByText(String text, int pageNumber, int maxItems) {
        if (pageNumber < 0 || maxItems <= 0) {
            throw new IllegalArgumentException("pagNumber or maxItems can not be 0 or less than 0 )");
        }

        Query query = em.createQuery("SELECT s FROM Story s WHERE s.title like :text OR s.content like :text ORDER BY s.date DESC, s.id ASC", Story.class);
        query.setParameter("text", "%" + text + "%");

        if (pageNumber == 0) {
            query.setFirstResult(0);
        } else {
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
     * @param page          The page number.
     * @param maxItems      The size of the page.
     * @return The story that corresponds with the search text.
     */
    public List<Story> searchStory(String text, int page, int maxItems) {
        if (maxItems <= 0 || page < 0) {
            throw new IllegalArgumentException("pagNumber or maxItems can not be 0 or less than 0 )");
        }

        return em.createQuery("SELECT s FROM Story  s WHERE s.published = TRUE " +
                        "and (s.content LIKE :t OR s.title Like :t) ", Story.class).setParameter("t", "%" + text + "%").setFirstResult((page - 1) * maxItems)
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
    public List<Story> exploreStory(Genre genre, Theme theme, Publication publication, int page, int maxItems){

        if (maxItems <= 0 || page < 0) {
            throw new IllegalArgumentException("pagNumber or maxItems can not be 0 or less than 0 )");
        }
        Date[] dates = new Date[2];
        if(Objects.nonNull(publication)) {
            dates = publication.getFrom();
        }
        Query query = em.createQuery("SELECT s"+
                " FROM Story s WHERE ( :genre IS NULL OR s.genre = :genre) and" +
                " (:theme IS NULL OR s.mainTheme = :theme OR s.secondaryTheme = :theme) and " +
                " (:startDate IS NULL OR :endDate IS NULL OR s.date between :startDate and :endDate) ORDER BY s.date DESC", Story.class);
        query.setParameter("genre",genre);
        query.setParameter("theme", theme);
        query.setParameter("starDate", dates[0]);
        query.setParameter("endDate", dates[1]);

        if(page == 0){
            query.setFirstResult(0);
        }else{
            query.setFirstResult(page * maxItems);
        }

        query.setMaxResults(maxItems);

        return query.getResultList();
    }

    /**
     * Find the stories that have been most read in the last month.
     *
     * @param genre         The genre of the story.
     * @param startDate     The date when start to search.
     * @param referenceDate The date on which the query is made.
     * @param page          The page number.
     * @param maxItems      The size of the page.
     * @return The list of the most read stories.
     */
    public List<Story> findHottestStories(Genre genre, Date startDate, Date referenceDate, int page, int maxItems) {
        if (maxItems <= 0 || page < 0) {
            throw new IllegalArgumentException("pagNumber or maxItems can not be 0 or less than 0");
        }

        return em.createQuery("SELECT s FROM Story s " +
                        "join s.visitDate vd " +
                        "WHERE s.published = TRUE " +
                        "AND s.genre = :genre " +
                        "AND vd > :startDate AND vd < :referenceDate " +
                        "GROUP BY s " +
                        "ORDER BY count(vd) DESC, s.id", Story.class)
                .setFirstResult(getStartPagination(page, maxItems))
                .setMaxResults(maxItems)
                .setParameter("genre", genre)
                .setParameter("startDate", startDate)
                .setParameter("referenceDate", referenceDate).getResultList();
    }

    /**
     * Validates that both the page and the page size are correct values and returns the number where to start the pagination.
     *
     * @param page The page number to search.
     * @param size The size of the page.
     * @return The number where to start the pagination.
     */
    private Integer getStartPagination(Integer page, Integer size) {
        page = checkPage(page);
        size = checkSize(size);
        return page * size;
    }

    /**
     * Check if the page number is valid. By default it returns the value 0.
     *
     * @param page The page number to check.
     * @return The page number to search.
     */
    private Integer checkPage(Integer page) {
        return (page == null || page < 0) ? 0 : page;
    }

    /**
     * Check if the size number is valid. By default it returns the value 10.
     *
     * @param size The size number to check.
     * @return The size number to search.
     */
    private Integer checkSize(Integer size) {
        return (size == null || size < 0) ? 10 : Math.min(size, 100);
    }
    
	public int getTotalPagesSearchText(String text, int maxItems) {
		Query query = em.createQuery("SELECT COUNT(s) FROM Story s WHERE s.title like :text OR s.content like :text", Long.class);
		query.setParameter("text", "%" + text + "%");

		long numStories = (long) query.getSingleResult();

		if (numStories <= maxItems) {
			return 1;
		}
		return (int) Math.ceil(numStories / maxItems);
	}
}