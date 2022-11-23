package es.uvigo.esei.dgss.teama.microstories.domain.entities;


import org.junit.Test;
import java.util.Date;

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
    public void testStoryDataType() throws IllegalArgumentException {
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
 

    @Test(expected = IllegalArgumentException.class)
    public void testStoryLengthNanoStory() throws IllegalArgumentException {
        final int id = 1;
        final String title = "Title Test";
        final String content = new String(new char[151]);
        final Genre genre = Genre.NANOSTORY;
        final Theme mainTheme = Theme.ADVENTURE;
        final Theme secondaryTheme = Theme.SUSPENSE;
        final String author = "Antonio";
        final Date date = new Date();
        final boolean published = true;

        Story localStory = new Story(id, date, title, content, genre, mainTheme, secondaryTheme, author, published);

    }

    
    @Test(expected = IllegalArgumentException.class)
    public void testStoryLengthPoetryStory() throws IllegalArgumentException {
        final int id = 1;
        final String title = "Title Test";
        final String content = new String(new char[501]);
        final Genre genre = Genre.POETRY;
        final Theme mainTheme = Theme.ADVENTURE;
        final Theme secondaryTheme = Theme.SUSPENSE;
        final String author = "Antonio";
        final Date date = new Date();
        final boolean published = true;

        Story localStory = new Story(id, date, title, content, genre, mainTheme, secondaryTheme, author, published);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testStoryLengthStory() throws IllegalArgumentException {
        final int id = 1;
        final String title = "Title Test";
        final String content = new String(new char[1001]);
        final Genre genre = Genre.STORY;
        final Theme mainTheme = Theme.ADVENTURE;
        final Theme secondaryTheme = Theme.SUSPENSE;
        final String author = "Antonio";
        final Date date = new Date();
        final boolean published = true;

        Story localStory = new Story(id, date, title, content, genre, mainTheme, secondaryTheme, author, published);
    }
}
