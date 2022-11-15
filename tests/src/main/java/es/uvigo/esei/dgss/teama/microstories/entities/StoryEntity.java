package es.uvigo.esei.dgss.teama.microstories.entities;


import org.junit.Test;
import java.util.Date;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * This class contains the unit tests for the Story entity
 * @author Julio Patricio Da Silva (jpsilva) Brais Domínguez Álvarez (bdalvarez)
 */
public class StoryEntity {

    @Test
    public void testStoryDataType() throws Exception {
        final int id = 1;
        final String title = "Title Test";
        final String content = "Text Test";
        final Genre genre = Genre.STORY;
        final Theme mainTheme = Theme.ADVENTURE;
        final Theme secondaryTheme = Theme.SUSPENSE;
        final String author = "Antonio";
        final Date date = new Date();
        final boolean published = true;

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
    @Test
    public void testStoryLengthStory() throws Exception {
        final int id = 1;
        final String title = "Title Test";
        final String content = "Text Test";
        final Genre genre = Genre.STORY;
        final Theme mainTheme = Theme.ADVENTURE;
        final Theme secondaryTheme = Theme.SUSPENSE;
        final String author = "Antonio";
        final Date date = new Date();
        final boolean published = true;

        Story localStory = new Story(id, date, title, content, genre, mainTheme, secondaryTheme, author, published);


    }

    @Test
    public void testStoryLengthNanoStory() throws Exception {
        final int id = 1;
        final String title = "Title Test";
        final String content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur non eros nec purus scelerisque varius vitae non mauris. Nunc malesuada efficitur quam, quis rhoncus leo efficitur lacinia. Proin enim quam, faucibus nec leo eget, euismod tincidunt risus. Suspendisse tristique et tortor vitae feugiat. Nulla tristique semper diam at molestie. In cursus tempor sem quis ultrices. Vestibulum vestibulum purus non pulvinar aliquam. Morbi sit amet ipsum eu ex pulvinar accumsan at at dolor. Nunc eu auctor orci. Sed maximus metus dui, ut commodo nisi dignissim sit amet. Sed ut nunc elementum, gravida nibh at, volutpat dui. Quisque fringilla mi sapien, ac blandit risus tincidunt sit amet. Aenean pulvinar, felis a pellentesque aliquet, mi erat scelerisque dui, pellentesque molestie est ex non lectus. Curabitur rhoncus libero sem, id malesuada arcu volutpat a. Fusce feugiat enim in justo ornare, non condimentum magna dignissim. Morbi neque tellus, viverra sit amet blandit eu, commodo at nisl. ";
        final Genre genre = Genre.NANOSTORY;
        final Theme mainTheme = Theme.ADVENTURE;
        final Theme secondaryTheme = Theme.SUSPENSE;
        final String author = "Antonio";
        final Date date = new Date();
        final boolean published = true;

        Story localStory = new Story(id, date, title, content, genre, mainTheme, secondaryTheme, author, published);


    }
}
