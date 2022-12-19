package es.uvigo.esei.dgss.teama.microstories.domain.entities;


import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * This class contains the unit tests for the Story entity
 *
 * @author Julio Patricio Da Silva (jpsilva) Brais Domínguez Álvarez (bdalvarez)
 */
public class StoryEntityTest {

    @Test
    public void testStoryDataType() {
        final int id = 1;
        final String title = "Title Test";
        final String content = "Text Test";
        final Genre genre = Genre.STORY;
        final Theme mainTheme = Theme.ADVENTURE;
        final Theme secondaryTheme = Theme.SUSPENSE;
        final Date date = new Timestamp(new Date().getTime());
        final boolean published = true;
        final User author = new User("user1", "12345");

        Story localStory = new Story(id, date, title, content, genre, mainTheme, secondaryTheme, author, published);

        assertThat(localStory.getId(), is(equalTo(id)));
        assertThat(localStory.getDate(), is(equalTo(date)));
        assertThat(localStory.getTitle(), is(equalTo(title)));
        assertThat(localStory.getContent(), is(equalTo(content)));
        assertThat(localStory.getGenre(), is(equalTo(genre)));
        assertThat(localStory.getMainTheme(), is(equalTo(mainTheme)));
        assertThat(localStory.getSecondaryTheme(), is(equalTo(secondaryTheme)));
        assertThat(localStory.getAuthor(), is(equalTo(author)));
        assertThat(localStory.isPublished(), is(equalTo(published)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStoryLengthNanoStory() {
        final int id = 1;
        final String title = "Title Test";
        final String content = new String(new char[151]);
        final Genre genre = Genre.NANOSTORY;
        final Theme mainTheme = Theme.ADVENTURE;
        final Theme secondaryTheme = Theme.SUSPENSE;
        final Date date = new Date();
        final boolean published = true;
        final User author = new User("Antonio", "12345");

        new Story(id, date, title, content, genre, mainTheme, secondaryTheme, author, published);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testStoryLengthPoetryStory() {
        final int id = 1;
        final String title = "Title Test";
        final String content = new String(new char[501]);
        final Genre genre = Genre.POETRY;
        final Theme mainTheme = Theme.ADVENTURE;
        final Theme secondaryTheme = Theme.SUSPENSE;
        final Date date = new Date();
        final boolean published = true;
        final User author = new User("Antonio", "12345");

        new Story(id, date, title, content, genre, mainTheme, secondaryTheme, author, published);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStoryLengthStory() {
        final int id = 1;
        final String title = "Title Test";
        final String content = new String(new char[1001]);
        final Genre genre = Genre.STORY;
        final Theme mainTheme = Theme.ADVENTURE;
        final Theme secondaryTheme = Theme.SUSPENSE;
        final Date date = new Date();
        final boolean published = true;
        final User author = new User("Antonio", "12345");

        new Story(id, date, title, content, genre, mainTheme, secondaryTheme, author, published);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStoryNullTitle() {
        final int id = 1;
        final String title = null;
        final String content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur non eros nec purus scelerisque varius vitae non mauris. Nunc malesuada efficitur quam, quis rhoncus leo efficitur lacinia. Proin enim quam, faucibus nec leo eget, euismod tincidunt risus. Suspendisse tristique et tortor vitae feugiat. Nulla tristique semper diam at molestie. In cursus tempor sem quis ultrices. Vestibulum vestibulum purus non pulvinar aliquam. Morbi sit amet ipsum eu ex pulvinar accumsan at at dolor. Nunc eu auctor orci. Sed maximus metus dui, ut commodo nisi dignissim sit amet. Sed ut nunc elementum, gravida nibh at, volutpat dui. Quisque fringilla mi sapien, ac blandit risus tincidunt sit amet. Aenean pulvinar, felis a pellentesque aliquet, mi erat scelerisque dui, pellentesque molestie est ex non lectus. Curabitur rhoncus libero sem, id malesuada arcu volutpat a. Fusce feugiat enim in justo ornare, non condimentum magna dignissim. Morbi neque tellus, viverra sit amet blandit eu, commodo at nisl. ";
        final Genre genre = Genre.NANOSTORY;
        final Theme mainTheme = Theme.ADVENTURE;
        final Theme secondaryTheme = Theme.SUSPENSE;
        final Date date = new Date();
        final boolean published = true;
        final User author = new User("Antonio", "12345");

        new Story(id, date, title, content, genre, mainTheme, secondaryTheme, author, published);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testStoryLengthTitleMore70() {
        final int id = 1;
        final String title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla non gravida lacus, et porta ligula. Integer id nisl ac risus fermentum malesuada nec id est. Maecenas quis tristique purus, in lacinia arcu. Cras placerat arcu erat, non sodales nisi blandit ut. Fusce ac magna posuere, dapibus nulla sit amet, pulvinar turpis. Morbi vitae diam sit amet felis pellentesque pharetra. Curabitur sollicitudin suscipit gravida. Duis vehicula nisi nunc. Pellentesque sodales bibendum sem. ";
        final String content = "Lorem ipsum dolor sit amet, consectetud tincidunt";
        final Genre genre = Genre.NANOSTORY;
        final Theme mainTheme = Theme.ADVENTURE;
        final Theme secondaryTheme = Theme.SUSPENSE;
        final Date date = new Timestamp(new Date().getTime());
        final boolean published = true;
        final User author = new User("Antonio", "12345");

        new Story(id, date, title, content, genre, mainTheme, secondaryTheme, author, published);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStoryEmptyTitle() {
        final int id = 1;
        final String title = "";
        final String content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur non eros nec purus scelerisque varius vitae non mauris. Nunc malesuada efficitur quam, quis rhoncus leo efficitur lacinia. Proin enim quam, faucibus nec leo eget, euismod tincidunt risus. Suspendisse tristique et tortor vitae feugiat. Nulla tristique semper diam at molestie. In cursus tempor sem quis ultrices. Vestibulum vestibulum purus non pulvinar aliquam. Morbi sit amet ipsum eu ex pulvinar accumsan at at dolor. Nunc eu auctor orci. Sed maximus metus dui, ut commodo nisi dignissim sit amet. Sed ut nunc elementum, gravida nibh at, volutpat dui. Quisque fringilla mi sapien, ac blandit risus tincidunt sit amet. Aenean pulvinar, felis a pellentesque aliquet, mi erat scelerisque dui, pellentesque molestie est ex non lectus. Curabitur rhoncus libero sem, id malesuada arcu volutpat a. Fusce feugiat enim in justo ornare, non condimentum magna dignissim. Morbi neque tellus, viverra sit amet blandit eu, commodo at nisl. ";
        final Genre genre = Genre.NANOSTORY;
        final Theme mainTheme = Theme.ADVENTURE;
        final Theme secondaryTheme = Theme.SUSPENSE;
        final Date date = new Date();
        final boolean published = true;
        final User author = new User("Antonio", "12345");

        new Story(id, date, title, content, genre, mainTheme, secondaryTheme, author, published);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testStoryNullAuthor() {
        final int id = 1;
        final String title = "Title test";
        final String content = "Lneque tellus, viverra sit amet blandit eu, commodo at nisl. ";
        final Genre genre = Genre.NANOSTORY;
        final Theme mainTheme = Theme.ADVENTURE;
        final Theme secondaryTheme = Theme.SUSPENSE;
        final Date date = new Date();
        final boolean published = true;
        final User author = new User(null, "12345");

        new Story(id, date, title, content, genre, mainTheme, secondaryTheme, author, published);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testStoryEmptyAuthor() {
        final int id = 1;
        final String title = "";
        final String content = "Lneque tellus, viverra sit amet blandit eu, commodo at nisl. ";
        final Genre genre = Genre.NANOSTORY;
        final Theme mainTheme = Theme.ADVENTURE;
        final Theme secondaryTheme = Theme.SUSPENSE;
        final Date date = new Date();
        final boolean published = true;
        final User author = new User("", "12345");

        new Story(id, date, title, content, genre, mainTheme, secondaryTheme, author, published);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testStoryNullContent() {
        final int id = 1;
        final String title = "Title test";
        final String content = null;
        final Genre genre = Genre.NANOSTORY;
        final Theme mainTheme = Theme.ADVENTURE;
        final Theme secondaryTheme = Theme.SUSPENSE;
        final Date date = new Date();
        final boolean published = true;
        final User author = new User("Pepe", "12345");

        new Story(id, date, title, content, genre, mainTheme, secondaryTheme, author, published);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testStoryEmptyContent() {
        final int id = 1;
        final String title = "";
        final String content = "";
        final Genre genre = Genre.NANOSTORY;
        final Theme mainTheme = Theme.ADVENTURE;
        final Theme secondaryTheme = Theme.SUSPENSE;
        final Date date = new Date();
        final boolean published = true;
        final User author = new User("Pepe", "12345");

        new Story(id, date, title, content, genre, mainTheme, secondaryTheme, author, published);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testStoryNullMainTheme() {
        final int id = 1;
        final String title = "Title test";
        final String content = "Lneque tellus, viverra sit amet blandit eu, commodo at nisl. ";
        final Genre genre = Genre.NANOSTORY;
        final Theme mainTheme = null;
        final Theme secondaryTheme = Theme.SUSPENSE;
        final Date date = new Date();
        final boolean published = true;
        final User author = new User("Pepe", "12345");

        new Story(id, date, title, content, genre, mainTheme, secondaryTheme, author, published);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testStoryNullSecondaryTheme() {
        final int id = 1;
        final String title = "Title test";
        final String content = "Lneque tellus, viverra sit amet blandit eu, commodo at nisl. ";
        final Genre genre = Genre.NANOSTORY;
        final Theme mainTheme = Theme.ADVENTURE;
        final Theme secondaryTheme = null;
        final Date date = new Date();
        final boolean published = true;
        final User author = new User("Pepe", "12345");

        new Story(id, date, title, content, genre, mainTheme, secondaryTheme, author, published);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testStoryNullDate() {
        final int id = 1;
        final String title = "Title test";
        final String content = "Lneque tellus, viverra sit amet blandit eu, commodo at nisl. ";
        final Genre genre = Genre.NANOSTORY;
        final Theme mainTheme = Theme.HISTORIC;
        final Theme secondaryTheme = Theme.SUSPENSE;
        final Date date = null;
        final boolean published = true;
        final User author = new User("Pepe", "12345");

        new Story(id, date, title, content, genre, mainTheme, secondaryTheme, author, published);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testStoryNullGenre() {
        final int id = 1;
        final String title = "Title test";
        final String content = "Lneque tellus, viverra sit amet blandit eu, commodo at nisl. ";
        final Genre genre = null;
        final Theme mainTheme = Theme.HISTORIC;
        final Theme secondaryTheme = Theme.SUSPENSE;
        final Date date = new Date();
        final boolean published = true;
        final User author = new User("Pepe", "12345");

        new Story(id, date, title, content, genre, mainTheme, secondaryTheme, author, published);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testStoryNullUser() {
        final int id = 1;
        final String title = "Title test";
        final String content = "Lneque tellus, viverra sit amet blandit eu, commodo at nisl. ";
        final Genre genre = Genre.NANOSTORY;
        final Theme mainTheme = Theme.HISTORIC;
        final Theme secondaryTheme = Theme.SUSPENSE;
        final Date date = new Date();
        final boolean published = true;
        final User author = null;

        new Story(id, date, title, content, genre, mainTheme, secondaryTheme, author, published);

    }

    @Test
    public void testSetVisitDate() {
        final int id = 1;
        final String title = "Title 1";
        final String content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur non eros nec purus scelerisque varius vitae non mauris. Nunc malesuada efficitur quam, quis rhoncus leo efficitur lacinia. Proin enim quam, faucibus nec leo eget, euismod tincidunt risus. Suspendisse tristique et tortor vitae feugiat. Nulla tristique semper diam at molestie. In cursus tempor sem quis ultrices. Vestibulum vestibulum purus non pulvinar aliquam. Morbi sit amet ipsum eu ex pulvinar accumsan at at dolor. Nunc eu auctor orci. Sed maximus metus dui, ut commodo nisi dignissim sit amet. Sed ut nunc elementum, gravida nibh at, volutpat dui. Quisque fringilla mi sapien, ac blandit risus tincidunt sit amet. Aenean pulvinar, felis a pellentesque aliquet, mi erat scelerisque dui, pellentesque molestie est ex non lectus. Curabitur rhoncus libero sem, id malesuada arcu volutpat a. Fusce feugiat enim in justo ornare, non condimentum magna dignissim. Morbi neque tellus, viverra sit amet blandit eu, commodo at nisl. ";
        final Genre genre = Genre.STORY;
        final Theme mainTheme = Theme.ADVENTURE;
        final Theme secondaryTheme = Theme.SUSPENSE;
        final Date date = new Date();
        final boolean published = true;
        final User author = new User("Antonio", "12345");

        final Story story = new Story(id, date, title, content, genre, mainTheme, secondaryTheme, author, published);

        final List<Date> visitDate = new ArrayList<>();
        final Date newVisitDate = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
        visitDate.add(newVisitDate);
        story.setVisitDate(visitDate);

        assertThat(story.getVisitDate(), is(equalTo(visitDate)));
    }
}