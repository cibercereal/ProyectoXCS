package es.uvigo.esei.dgss.teama.microstories.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.TemporalType;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import java.io.Serializable;
import java.util.Date;

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

    public Story() {
    }

    public Story(int id, Date date, String title, String content, Genre genre, Theme mainTheme, Theme secondaryTheme, String author, boolean published) throws IllegalArgumentException, NullPointerException {

        if (date == null) {
            throw new NullPointerException("Error: Content for a date cannot be null");
        }

        if (title.length() > 70) {
            throw new IllegalArgumentException("Error: Content for a title cannot exceed 70 characters");
        } else if (title.length() == 0) {
            throw new IllegalArgumentException("Error: Content for a title cannot be empty");
        } else if (title == null) {
            throw new NullPointerException("Error: Content for a title cannot be null");
        }

        if (content.length() == 0) {
            throw new IllegalArgumentException("Error: Content for a content cannot be empty");
        } else if (content == null) {
            throw new NullPointerException("Error: Content for a content cannot be null");
        }

        if (genre == null) {
            throw new NullPointerException("Error: Content for a genre cannot be null");
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
            throw new NullPointerException("Error: Content for a mainTheme cannot be null");
        }

        if (secondaryTheme == null) {
            throw new NullPointerException("Error: Content for a secondaryTheme cannot be null");
        }

        if (author.length() > 255) {
            throw new IllegalArgumentException("Error: Content for a author cannot exceed 255 characters");
        } else if (author.length() == 0) {
            throw new IllegalArgumentException("Error: Content for a author cannot be empty");
        } else if (author == null) {
            throw new NullPointerException("Error: Content for a author cannot be null");
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
