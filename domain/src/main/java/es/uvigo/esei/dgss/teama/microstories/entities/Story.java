package es.uvigo.esei.dgss.teama.microstories.entities;

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
 * @author Julio Patricio Da Silva (jpsilva) Brais Domínguez Álvarez (bdalvarez)
 */
@Entity
public class Story implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Temporal(TemporalType.DATE)
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

    public Story(int id, Date date, String title, String content, Genre genre, Theme mainTheme, Theme secondaryTheme, String author,boolean published) throws Exception {
        this.id = id;
        this.date = date;
        this.title = title;

        switch (genre) {
            case STORY:
                if(content.length() >1000)
                    throw new IllegalArgumentException("Error: Content for a story cannot exceed 1000 characters");
                break;
            case POETRY:
                if(content.length() >500)
                    throw new IllegalArgumentException("Error: Content for a poetry cannot exceed 500 characters");
                break;
            case NANOSTORY:
                if(content.length() >150)
                    throw new IllegalArgumentException("Error: Content for a nanostory cannot exceed 150 characters");
                break;
        }
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
        return date;
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


}
