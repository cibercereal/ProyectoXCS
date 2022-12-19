package es.uvigo.esei.dgss.teama.microstories.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import static java.util.Objects.requireNonNull;

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

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private Role role;

    @OneToMany(
            mappedBy = "author",
            targetEntity = Story.class,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Collection<Story> stories;

    public User() {
    }

    public User(String login, String password) {
        this(login, password, new ArrayList<>());
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

        if (stories == null) {
            throw new IllegalArgumentException("Error: Content for a stories cannot be null");
        }
        this.login = login;
        this.password = password;
        this.stories = stories;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Collection<Story> getStories() {
        return stories;
    }

    public void addStory(Story story){
        requireNonNull(story, "story can`t be null");

        if(!this.ownsStory(story)){
            story.setAuthor(this);
        }
    }

    public void removeStory(Story story) {
        requireNonNull(story, "story can't be null");

        if (this.ownsStory(story)) {
            story.setAuthor(null);
        } else {
            throw new IllegalArgumentException("pet doesn't belong to this owner");
        }
    }

    public boolean ownsStory(Story story) {
        return this.stories.contains(story);
    }

    void internalAddStory(Story story) {
        requireNonNull(story, "story can't be null");

        if (!this.ownsStory(story))
            this.stories.add(story);
    }

    void internalRemoveStory(Story story) {
        requireNonNull(story, "story can't be null");

        this.stories.remove(story);
    }
}
