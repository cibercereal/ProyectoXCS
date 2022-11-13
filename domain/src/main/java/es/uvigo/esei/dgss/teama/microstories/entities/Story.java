package es.uvigo.esei.dgss.teama.microstories.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

public class Story implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;

    private Date date;

    private String title;

    private String content;

    private String genre;

    private String mainTheme;

    private String secondaryTheme;

    private String author;

    public Story() {
    }

    public Story(int id, Date date, String title, String content, String genre, String mainTheme, String secondaryTheme, String author) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.content = content;
        this.genre = genre;
        this.mainTheme = mainTheme;
        this.secondaryTheme = secondaryTheme;
        this.author = author;
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

    public String getGenre() {
        return genre;
    }

    public String getMainTheme() {
        return mainTheme;
    }

    public String getSecondaryTheme() {
        return secondaryTheme;
    }

    public String getAuthor() {
        return author;
    }
}
