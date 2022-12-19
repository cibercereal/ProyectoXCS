package es.uvigo.esei.dgss.teama.microstories.domain.entities;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * This class contains the unit tests for the User entity
 *
 * @author Álvaro Suárez Feijoo (asfeijoo)
 */
public class UserEntityTest {

    @Test
    public void testUserDataType() {
        final String login = "user1";
        final String password = "12345";


        User localUser = new User(login, password);

        assertThat(localUser.getLogin(), is(equalTo(login)));
        assertThat(localUser.getPassword(), is(equalTo(password)));
    }

    @Test
    public void testUserWithStoriesDataType() {
        final String login = "user1";
        final String password = "12345";


        User localUser = new User(login, password);

        assertThat(localUser.getLogin(), is(equalTo(login)));
        assertThat(localUser.getPassword(), is(equalTo(password)));

        final int id = 1;
        final String title = "Title Test";
        final String content = "Text Test";
        final Genre genre = Genre.STORY;
        final Theme mainTheme = Theme.ADVENTURE;
        final Theme secondaryTheme = Theme.SUSPENSE;
        final Date date = new Timestamp(new Date().getTime());
        final boolean published = true;
        final User author = new User("user1", "12345");
        final Collection<Story> stories = new ArrayList<Story>() {
        };
        Story localStory = new Story(id, date, title, content, genre, mainTheme, secondaryTheme, author, published);

        localUser.addStory(localStory);

        assertThat(localUser.getStories(), is(equalTo(stories)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUserNullLogin() {
        final String login = null;
        final String password = "12345";

        new User(login, password);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testUserEmptyLogin() {
        final String login = "";
        final String password = "12345";

        new User(login, password);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testUserNullPassword() {
        final String login = "user1";
        final String password = null;

        new User(login, password);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testUserEmptyPassword() {
        final String login = "user1";
        final String password = "";

        new User(login, password);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testUserNullStories() {
        final String login = "user1";
        final String password = "";
        final Collection<Story> stories = new ArrayList<>();
        new User(login, password, stories);

    }
}