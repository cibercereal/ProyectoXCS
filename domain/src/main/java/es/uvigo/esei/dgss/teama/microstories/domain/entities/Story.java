package es.uvigo.esei.dgss.teama.microstories.domain.entities;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The Story entity  represents the content of a story. This can be a Story, a nano story or a poem.
 * It also contains the author of said story and the topic and subtopic
 *
 * @author Julio Patricio Da Silva (jpsilva) Brais Domínguez Álvarez (bdalvarez)
 */
@Entity
public class Story implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(length = 70)
    private String title;
    @Column(length = 1000)
    private String content;

    @Enumerated(EnumType.STRING)
    private Genre genre;
    @Enumerated(EnumType.STRING)
    private Theme mainTheme;
    @Enumerated(EnumType.STRING)
    private Theme secondaryTheme;

    private String author;

    private boolean published;

    @ElementCollection
    @CollectionTable(
            name = "VisitDate",
            joinColumns = @JoinColumn(name = "storyId")
    )
    @Column(name = "visitDate")
    private List<Date> visitDate;

    @ManyToOne
    @JoinColumn(name = "login", referencedColumnName = "login", nullable = false)
    private User user;

    public Story() {
    }

    public Story(int id, Date date, String title, String content, Genre genre, Theme mainTheme, Theme secondaryTheme, String author, boolean published, User user) throws IllegalArgumentException {

        if (date == null) {
            throw new IllegalArgumentException("Error: Content for a date cannot be null");
        }

        if (title == null || title.length() == 0) {
            throw new IllegalArgumentException("Error: Content for a title cannot be empty or null");
        }

        if (title.length() > 70) {
            throw new IllegalArgumentException("Error: Content for a title cannot exceed 70 characters");
        }

        if (content == null || content.length() == 0) {
            throw new IllegalArgumentException("Error: Content for a content cannot be empty or null");
        }

        if (genre == null) {
            throw new IllegalArgumentException("Error: Content for a genre cannot be null");
        }

        switch (genre) {
            case STORY:
                if (content.length() > 1000)
                    throw new IllegalArgumentException("Error: Content for a story cannot exceed 1000 characters");
                break;
            case POETRY:
                if (content.length() > 500)
                    throw new IllegalArgumentException("Error: Content for a poetry cannot exceed 500 characters");
                break;
            case NANOSTORY:
                if (content.length() > 150)
                    throw new IllegalArgumentException("Error: Content for a nano-story cannot exceed 150 characters");
                break;
        }

        if (mainTheme == null) {
            throw new IllegalArgumentException("Error: Content for a mainTheme cannot be null");
        }

        if (secondaryTheme == null) {
            throw new IllegalArgumentException("Error: Content for a secondaryTheme cannot be null");
        }
        if (author == null || author.length() == 0) {
            throw new IllegalArgumentException("Error: Content for a author cannot be empty");
        }
        if (author.length() > 255) {
            throw new IllegalArgumentException("Error: Content for a author cannot exceed 255 characters");
        }

        if (user == null) {
            throw new IllegalArgumentException("Error: Content for a user cannot be null");
        }

        this.id = id;
        this.date = date;
        this.title = title;
        this.content = content;
        this.genre = genre;
        this.mainTheme = mainTheme;
        this.secondaryTheme = secondaryTheme;
        this.author = author;
        this.published = published;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return new Date(date.getTime());
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Genre getGenre() {
        return genre;
    }

    public Theme getMainTheme() {
        return mainTheme;
    }

    public Theme getSecondaryTheme() {
        return secondaryTheme;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isPublished() {
        return published;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return The list of the visit date.
     */
    @XmlTransient
    public List<Date> getVisitDate() {
        if (visitDate == null) visitDate = new ArrayList<>();
        return visitDate;
    }

    /**
     * Sets the list of visit date.
     *
     * @param visitDate The new list of visit date.
     */
    public void setVisitDate(List<Date> visitDate) {
        this.visitDate = visitDate;
    }

    /**
     * Get the number of visits in the range between startDate and endDate.
     *
     * @param startDate The start date.
     * @param endDate   The end date.
     * @return The number of visits in the range between startDate and endDate.
     */
    @XmlTransient
    public long getVisitCountInDateRange(Date startDate, Date endDate) {
        return getVisitDate().stream()
                .filter(i -> i.after(startDate))
                .filter(i -> i.before(endDate))
                .count();
    }

    @Override
    public String toString() {
        return "Story{" +
                "id=" + id +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", genre=" + genre +
                ", mainTheme=" + mainTheme +
                ", secondaryTheme=" + secondaryTheme +
                ", author='" + author + '\'' +
                ", published=" + published +
                '}';
    }
}
