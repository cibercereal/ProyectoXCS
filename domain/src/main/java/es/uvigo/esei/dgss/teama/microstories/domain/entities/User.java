package es.uvigo.esei.dgss.teama.microstories.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * The User entity represents an author of stories that can create, modify(a non publish story),
 * delete and publish his ows stories
 *
 * @author Álvaro Suárez Feijoo (asfeijoo)
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 70)
    private String login;

    @Column(length = 70)
    private String password;

    @OneToMany(
            mappedBy = "user",
            targetEntity = Story.class,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Collection<Story> stories;

    public User() {
    }

    public User(String login, String password) {

        if (login == null || login.length() == 0) {
            throw new IllegalArgumentException("Error: Content for a login cannot be empty or null");
        }

        if (login.length() > 70) {
            throw new IllegalArgumentException("Error: Content for a login cannot exceed 70 characters");
        }

        if (password == null || password.length() == 0) {
            throw new IllegalArgumentException("Error: Content for a password cannot be empty or null");
        }

        if (password.length() > 70) {
            throw new IllegalArgumentException("Error: Content for a password cannot exceed 70 characters");
        }

        this.login = login;
        this.password = password;
    }

    public User(String login, String password, Collection<Story> stories) {

        if (login == null || login.length() == 0) {
            throw new IllegalArgumentException("Error: Content for a login cannot be empty or null");
        }

        if (login.length() > 70) {
            throw new IllegalArgumentException("Error: Content for a login cannot exceed 70 characters");
        }

        if (password == null || password.length() == 0) {
            throw new IllegalArgumentException("Error: Content for a password cannot be empty or null");
        }

        if (password.length() > 70) {
            throw new IllegalArgumentException("Error: Content for a password cannot exceed 70 characters");
        }

        if (stories == null || stories.size() == 0) {
            throw new IllegalArgumentException("Error: Content for a stories cannot be empty or null");
        }
        this.login = login;
        this.password = password;
        this.stories = stories;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Story> getStories() {
        return stories;
    }

    public void setStories(Collection<Story> stories) {
        this.stories = stories;
    }
}
