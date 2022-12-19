package es.uvigo.esei.dgss.teama.microstories.domain.entities;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;

public class IsEqualToStory extends IsEqualToEntity<Story> {
    private final boolean withoutRelations;

    public IsEqualToStory(Story story, boolean withoutRelations) {
        super(story);

        this.withoutRelations = withoutRelations;
    }

    @Override
    protected boolean matchesSafely(Story actual) {
        this.clearDescribeTo();

        if (actual == null) {
            this.addTemplatedDescription("actual", expected.toString());
            return false;
        } else {
            return checkAttribute("content", Story::getContent, actual)
                    && checkAttribute("date", Story::getDate, actual)
                    && checkAttribute("id", Story::getId, actual)
                    && checkAttribute("title", Story::getTitle, actual)
                    && checkAttribute("genre", Story::getGenre, actual)
                    && checkAttribute("mainTheme", Story::getMainTheme, actual)
                    && checkAttribute("secondaryTheme", Story::getSecondaryTheme, actual)
                    && checkAttribute("published", Story::isPublished, actual)
                    && (withoutRelations || checkAttribute("author", Story::getAuthor, actual, IsEqualToUser::equalToUserWithoutRelations));
        }
    }

    @Factory
    public static IsEqualToStory equalToStory(Story story) {
        return new IsEqualToStory(story, false);
    }

    @Factory
    public static IsEqualToStory equalToStoryWithoutRelations(Story story) {
        return new IsEqualToStory(story, true);
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
    @Factory
    public static Matcher<Iterable<? extends Story>> containsStoriesInOrder(Iterable<Story> stories) {
        return containsEntityInOrder(IsEqualToStory::equalToStory, stories);
    }
}