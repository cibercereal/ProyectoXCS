package es.uvigo.esei.dgss.teama.microstories.entities;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;

public class IsEqualToStory extends IsEqualToEntity<Story> {

    public IsEqualToStory(Story story) {
        super(story);
    }

    @Override
    protected boolean matchesSafely(Story actual) {
        this.clearDescribeTo();

        if (actual == null) {
            this.addTemplatedDescription("actual", expected.toString());
            return false;
        } else {
            return checkAttribute("author", Story::getAuthor, actual)
                    && checkAttribute("content", Story::getContent, actual)
                    && checkAttribute("date", Story::getDate, actual)
                    && checkAttribute("id", Story::getId, actual)
                    && checkAttribute("title", Story::getTitle, actual)
                    && checkAttribute("genre", Story::getGenre, actual)
                    && checkAttribute("mainTheme", Story::getMainTheme, actual)
                    && checkAttribute("secondaryTheme", Story::getSecondaryTheme, actual)
                    && checkAttribute("published", Story::isPublished, actual);
        }
    }

    @Factory
    public static IsEqualToStory equalToStory(Story story) {
        return new IsEqualToStory(story);
    }

    @Factory
    public static IsEqualToStory equalToStoryWithoutRelations(Story story) {
        return new IsEqualToStory(story);
    }

    @Factory
    public static Matcher<Iterable<? extends Story>> containsStoriesInAnyOrder(Story ... stories) {
        return containsEntityInAnyOrder(IsEqualToStory::equalToStory, stories);
    }

    @Factory
    public static Matcher<Iterable<? extends Story>> containsStoriesWithoutRelationsInAnyOrder(Story ... stories) {
        return containsEntityInAnyOrder(IsEqualToStory::equalToStoryWithoutRelations, stories);
    }

    @Factory
    public static Matcher<Iterable<? extends Story>> containsStoriesInAnyOrder(Iterable<Story> stories) {
        return containsEntityInAnyOrder(IsEqualToStory::equalToStory, stories);
    }

    @Factory
    public static Matcher<Iterable<? extends Story>> containsStoriesWithoutRelationsInAnyOrder(Iterable<Story> stories) {
        return containsEntityInAnyOrder(IsEqualToStory::equalToStoryWithoutRelations, stories);
    }
}