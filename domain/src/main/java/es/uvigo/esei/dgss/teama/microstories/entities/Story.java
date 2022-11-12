package es.uvigo.esei.dgss.teama.microstories.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
public class Story implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Date date;

    private String title;
    @Column(length = 1000)
    private String content;

    @Enumerated(EnumType.STRING)
    private Genre genre;
    @Enumerated(EnumType.STRING)
    private Theme mainTheme;

    private Theme secondaryTheme;

    private String author;
    private boolean published;
    public Story() {
    }

    public Story(int id, Date date, String title, String content, Genre genre, Theme mainTheme, Theme secondaryTheme, String author,boolean published) {
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
