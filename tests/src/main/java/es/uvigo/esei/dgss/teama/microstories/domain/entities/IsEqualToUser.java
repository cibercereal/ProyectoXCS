package es.uvigo.esei.dgss.teama.microstories.domain.entities;


import org.hamcrest.Factory;
import org.hamcrest.Matcher;

public class IsEqualToUser extends IsEqualToEntity<User> {
    private final boolean withoutRelations;

    public IsEqualToUser(User User, boolean withoutRelations) {
        super(User);
        this.withoutRelations = withoutRelations;
    }

    @Override
    protected boolean matchesSafely(User actual) {
        this.clearDescribeTo();

        if (actual == null) {
            this.addTemplatedDescription("actual", expected.toString());
            return false;
        } else {
            return checkAttribute("login", User::getLogin, actual)
                    && checkAttribute("password", User::getPassword, actual)
                    && (withoutRelations || checkIterableAttribute("stories", User::getStories, actual, IsEqualToStory::containsStoriesWithoutRelationsInAnyOrder));
        }
    }

    @Factory
    public static IsEqualToUser equalToUser(User user) {
        return new IsEqualToUser(user, false);
    }

    @Factory
    public static IsEqualToUser equalToUserWithoutRelations(User user) {
        return new IsEqualToUser(user, true);
    }

    @Factory
    public static Matcher<Iterable<? extends User>> containsUsersInAnyOrder(User... users) {
        return containsEntityInAnyOrder(IsEqualToUser::equalToUser, users);
    }

    @Factory
    public static Matcher<Iterable<? extends User>> containsUsersWithoutRelationsInAnyOrder(User... users) {
        return containsEntityInAnyOrder(IsEqualToUser::equalToUserWithoutRelations, users);
    }

    @Factory
    public static Matcher<Iterable<? extends User>> containsUsersInAnyOrder(Iterable<User> users) {
        return containsEntityInAnyOrder(IsEqualToUser::equalToUser, users);
    }

    @Factory
    public static Matcher<Iterable<? extends User>> containsUsersWithoutRelationsInAnyOrder(Iterable<User> users) {
        return containsEntityInAnyOrder(IsEqualToUser::equalToUserWithoutRelations, users);
    }

    @Factory
    public static Matcher<Iterable<? extends User>> containsUsersInOrder(Iterable<User> users) {
        return containsEntityInOrder(IsEqualToUser::equalToUser, users);
    }
}
