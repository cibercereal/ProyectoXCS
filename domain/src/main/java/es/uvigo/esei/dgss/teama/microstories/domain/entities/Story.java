package es.uvigo.esei.dgss.teama.microstories.domain.entities;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * The Story entity  represents the content of a story. This can be a Story, a nano story or a poem.
 * It also contains the author of said story and the topic and subtopic
 *
 * @author Julio Patricio Da Silva (jpsilva) Brais Domínguez Álvarez (bdalvarez)
 */
@Entity
@Table(name = "story")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author", referencedColumnName = "login", nullable = false)
    @XmlTransient
    private User author;

    private boolean published;

    @ElementCollection
    @CollectionTable(
            name = "visitdate",
            joinColumns = @JoinColumn(name = "storyId")
    )
    @Column(name = "visitdate")
    private List<Date> visitDate;

    public Story() {
    }

    public Story(int id, Date date, String title, String content, Genre genre, Theme mainTheme, Theme secondaryTheme, User author, boolean published) throws IllegalArgumentException {

        storyValidations(date, title, content, genre, mainTheme, secondaryTheme, author);

        this.id = id;
        this.date = date;
        this.title = title;
        this.content = content;
        this.genre = genre;
        this.mainTheme = mainTheme;
        this.secondaryTheme = secondaryTheme;
        this.author = author;
        this.published = published;
        this.visitDate = new ArrayList<>();
        this.author.addStory(this);
    }

    public Story(Date date, String title, String content, Genre genre, Theme mainTheme, Theme secondaryTheme, User author, boolean published) throws IllegalArgumentException {

        storyValidations(date, title, content, genre, mainTheme, secondaryTheme, author);

        this.date = date;
        this.title = title;
        this.content = content;
        this.genre = genre;
        this.mainTheme = mainTheme;
        this.secondaryTheme = secondaryTheme;
        this.author = author;
        this.published = published;
        this.visitDate = new ArrayList<>();
        this.author.addStory(this);
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

    public User getAuthor() {
        return author;
    }

    public boolean isPublished() {
        return published;
    }

    /**
     * @return The list of the visit date.
     */
    @XmlTransient
    public List<Date> getVisitDate() {
        return Collections.unmodifiableList(this.visitDate);
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

    public void addVisit(Date date) {
        if (visitDate == null) {
            visitDate = new ArrayList<>();
        }
        visitDate.add(date);

    }

    public void setAuthor(User author) {
        if (this.author != null)
            this.author.internalRemoveStory(this);

        this.author = author;

        if (this.author != null)
            this.author.internalAddStory(this);
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

    private void storyValidations(Date date, String title, String content, Genre genre, Theme mainTheme, Theme secondaryTheme, User author) {
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

        if (author == null) {
            throw new IllegalArgumentException("Error: Content for a user cannot be null");
        }
    }
}
