package es.uvigo.esei.dgss.teama.microstories.domain.entities;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toSet;

/**
 * Dataset for Story class.
 *
 * @author Bruno Cruz González (bcgonzalez4)
 */
public class StoryDataset {

    public static Story[] stories(String... logins) {
        final Set<String> loginSet = stream(logins).collect(toSet());

        return stream(stories()).filter(stories -> loginSet.contains(stories.getTitle())).toArray(Story[]::new);
    }

    public static Story[] stories() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
        try {
            Story[] stories = new Story[]{
                    new Story(1, new Timestamp(formatter.parse("2006-02-01 01:01:01").getTime()), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.ROMANCE, Theme.HORROR, "Juan Manuel Lopez", true, new User("user1", "12345")),
                    new Story(2, new Timestamp(formatter.parse("2006-02-01 01:01:01").getTime()), "Integer aliquam adipiscing lacus.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.CHILD, Theme.HORROR, "Juan Manuel Lopez", true, new User("user1", "12345")),
                    new Story(3, new Timestamp(formatter.parse("2006-02-01 01:01:01").getTime()), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.HISTORIC, Theme.HORROR, "Juan Manuel Lopez", true, new User("user1", "12345")),
                    new Story(4, new Timestamp(formatter.parse("2006-02-01 01:01:01").getTime()), "sed pede. Cum sociis", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.HORROR, Theme.HORROR, "Juan Manuel Lopez", true, new User("user1", "12345")),
                    new Story(5, new Timestamp(formatter.parse("2006-02-01 01:01:01").getTime()), "eleifend. Cras sed leo.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.ROMANCE, Theme.HORROR, "Juan Manuel Lopez", true, new User("user1", "12345")),
                    new Story(6, new Timestamp(formatter.parse("2006-02-01 01:01:01").getTime()), "Aliquam ultrices iaculis odio.", "rutrum lorem ac risus. Morbi metus. Vivamus euismod urna. Nullam lobortis quam a felis", Genre.NANOSTORY, Theme.SUSPENSE, Theme.HORROR, "Juan Manuel Lopez", true, new User("user1", "12345")),
                    new Story(7, new Timestamp(formatter.parse("2006-01-01 01:01:01").getTime()), "In nec orci. Donec", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.HISTORIC, Theme.HORROR, "Juan Manuel Lopez", true, new User("user1", "12345")),
                    new Story(8, new Timestamp(formatter.parse("2012-12-01 01:01:01").getTime()), "Aliquam ultrices iaculis odio.", "et ultrices posuere cubilia Curae Donec tincidunt. Donec vitae erat vel pede blandit congue.", Genre.POETRY, Theme.HISTORIC, Theme.HORROR, "Juan Manuel Lopez", true, new User("user1", "12345")),
                    new Story(9, new Timestamp(formatter.parse("2012-11-01 01:01:01").getTime()), "erat. Etiam vestibulum massa", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.SCIENCE_FICTION, Theme.HORROR, "Juan Manuel Lopez", true, new User("user1", "12345")),
                    new Story(10, new Timestamp(formatter.parse("2012-12-01 01:01:01").getTime()), "consectetuer mauris id sapien.", "lacus. Aliquam rutrum lorem ac risus. Morbi metus. Vivamus euismod urna. Nullam lobortis quam", Genre.STORY, Theme.HORROR, Theme.HORROR, "Juan Manuel Lopez", true, new User("user1", "12345")),
                    new Story(11, new Timestamp(formatter.parse("2012-12-01 01:01:01").getTime()), "accumsan laoreet ipsum. Curabitur", "sit amet ornare lectus justo eu arcu. Morbi sit amet massa. Quisque porttitor eros", Genre.POETRY, Theme.ROMANCE, Theme.HORROR, "Juan Manuel Lopez", true, new User("user2", "12345")),
                    new Story(12, new Timestamp(formatter.parse("2012-12-01 01:01:01").getTime()), "dapibus quam quis diam.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.ROMANCE, Theme.HORROR, "Juan Manuel Lopez", true, new User("user2", "12345")),
                    new Story(13, new Timestamp(formatter.parse("2012-12-01 01:01:01").getTime()), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.SUSPENSE, Theme.HORROR, "Juan Manuel Lopez", true, new User("user2", "12345")),
                    new Story(14, new Timestamp(formatter.parse("2012-12-01 01:01:01").getTime()), "In tincidunt congue turpis.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.HISTORIC, Theme.HORROR, "Enrique Fernandez", true, new User("user2", "12345")),
                    new Story(15, new Timestamp(formatter.parse("2014-03-01 01:01:01").getTime()), "Aliquam ultrices iaculis odio.", "senectus et netus et malesuada fames ac turpis egestas. Fusce aliquet magna a neque.", Genre.POETRY, Theme.ROMANCE, Theme.HORROR, "Enrique Fernandez", true, new User("user2", "12345")),
                    new Story(16, new Timestamp(formatter.parse("2014-03-01 01:01:01").getTime()), "mattis. Cras eget nisi", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.SUSPENSE, Theme.HORROR, "Enrique Fernandez", true, new User("user2", "12345")),
                    new Story(17, new Timestamp(formatter.parse("2014-03-01 01:01:01").getTime()), "Aenean eget metus. In", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.SUSPENSE, Theme.HORROR, "Enrique Fernandez", true, new User("user2", "12345")),
                    new Story(18, new Timestamp(formatter.parse("2014-03-01 01:01:01").getTime()), "ligula consectetuer rhoncus. Nullam", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.CHILD, Theme.HORROR, "Enrique Fernandez", true, new User("user2", "12345")),
                    new Story(19, new Timestamp(formatter.parse("2014-03-01 01:01:01").getTime()), "magna nec quam. Curabitur", "dolor vitae dolor. Donec fringilla. Donec feugiat metus sit amet ante. Vivamus non lorem", Genre.POETRY, Theme.HISTORIC, Theme.HORROR, "Enrique Fernandez", true, new User("user3", "12345")),
                    new Story(20, new Timestamp(formatter.parse("2014-03-01 01:01:01").getTime()), "Aliquam ultrices iaculis odio.", "mauris a nunc. In at pede. Cras vulputate velit eu sem. Pellentesque ut ipsum", Genre.POETRY, Theme.SCIENCE_FICTION, Theme.HORROR, "Enrique Fernandez", true, new User("user3", "12345")),
                    new Story(21, new Timestamp(formatter.parse("2014-03-01 01:01:01").getTime()), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.HORROR, Theme.HORROR, "Enrique Fernandez", true, new User("user3", "12345")),
                    new Story(22, new Timestamp(formatter.parse("2014-03-01 01:01:01").getTime()), "egestas. Duis ac arcu.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.SCIENCE_FICTION, Theme.HORROR, "Enrique Fernandez", true, new User("user3", "12345")),
                    new Story(23, new Timestamp(formatter.parse("2014-03-01 01:01:01").getTime()), "ante dictum cursus. Nunc", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.CHILD, Theme.HORROR, "Enrique Fernandez", true, new User("user3", "12345")),
                    new Story(24, new Timestamp(formatter.parse("2014-03-01 01:01:01").getTime()), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.CHILD, Theme.HORROR, "Enrique Fernandez", true, new User("user3", "12345")),
                    new Story(25, new Timestamp(formatter.parse("2014-03-01 01:01:01").getTime()), "Aliquam ultrices iaculis odio.", "neque et nunc. Quisque ornare tortor at risus. Nunc ac sem ut dolor dapibus", Genre.POETRY, Theme.SCIENCE_FICTION, Theme.HORROR, "Enrique Fernandez", true, new User("user3", "12345")),
                    new Story(26, new Timestamp(formatter.parse("2014-03-01 01:01:01").getTime()), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.CHILD, Theme.HORROR, "Enrique Fernandez", true, new User("user3", "12345")),
                    new Story(27, new Timestamp(formatter.parse("2022-05-01 01:01:01").getTime()), "sed pede. Cum sociis", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.HISTORIC, Theme.HORROR, "Enrique Fernandez", true, new User("user3", "12345")),
                    new Story(28, new Timestamp(formatter.parse("2022-05-01 01:01:01").getTime()), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.HORROR, Theme.HORROR, "Enrique Fernandez", true, new User("user3", "12345")),
                    new Story(29, new Timestamp(formatter.parse("2022-05-01 01:01:01").getTime()), "Integer vitae nibh. Donec", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.CHILD, Theme.HORROR, "Enrique Fernandez", true, new User("user4", "12345")),
                    new Story(30, new Timestamp(formatter.parse("2022-05-01 01:01:01").getTime()), "neque non quam. Pellentesque", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.ROMANCE, Theme.HORROR, "Enrique Fernandez", true, new User("user4", "12345")),
                    new Story(31, new Timestamp(formatter.parse("2022-05-01 01:01:01").getTime()), "Nam ac nulla. In", "ridiculus mus. Aenean eget magna. Suspendisse tristique neque venenatis lacus. Etiam bibendum fermentum metus.", Genre.POETRY, Theme.ROMANCE, Theme.HORROR, "Enrique Fernandez", true, new User("user4", "12345")),
                    new Story(32, new Timestamp(formatter.parse("2022-05-01 01:01:01").getTime()), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.HORROR, Theme.HORROR, "Ana Miguez", true, new User("user4", "12345")),
                    new Story(33, new Timestamp(formatter.parse("2022-05-01 01:01:01").getTime()), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.HORROR, Theme.HORROR, "Ana Miguez", true, new User("user4", "12345")),
                    new Story(34, new Timestamp(formatter.parse("2022-05-01 01:01:01").getTime()), "ultrices. Duis volutpat nunc", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.HISTORIC, Theme.HORROR, "Ana Miguez", true, new User("user4", "12345")),
                    new Story(35, new Timestamp(formatter.parse("2022-05-01 01:01:01").getTime()), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.SCIENCE_FICTION, Theme.HORROR, "Ana Miguez", true, new User("user4", "12345")),
                    new Story(36, new Timestamp(formatter.parse("2022-05-01 01:01:01").getTime()), "Aliquam ultrices iaculis odio.", "Phasellus ornare. Fusce mollis. Duis sit amet diam eu dolor egestas rhoncus. Proin nisl", Genre.STORY, Theme.ROMANCE, Theme.HORROR, "Ana Miguez", true, new User("user4", "12345")),
                    new Story(37, new Timestamp(formatter.parse("2022-05-01 01:01:01").getTime()), "Aliquam ultrices iaculis odio.", "erat. Etiam vestibulum massa rutrum magna. Cras convallis convallis dolor. Quisque tincidunt pede ac", Genre.POETRY, Theme.HISTORIC, Theme.HORROR, "Ana Miguez", true, new User("user4", "12345")),
                    new Story(38, new Timestamp(formatter.parse("2022-05-01 01:01:01").getTime()), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.ROMANCE, Theme.HORROR, "Ana Miguez", true, new User("user4", "12345")),
                    new Story(39, new Timestamp(formatter.parse("2022-05-01 01:01:01").getTime()), "Aliquam ultrices iaculis odio.", "ligula. Aenean euismod mauris eu elit. Nulla facilisi. Sed neque. Sed eget lacus. Mauris", Genre.STORY, Theme.SCIENCE_FICTION, Theme.HORROR, "Marta Estevez", true, new User("user4", "12345")),
                    new Story(40, new Timestamp(formatter.parse("2022-05-01 01:01:01").getTime()), "Phasellus vitae mauris sit", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.HISTORIC, Theme.HORROR, "Marta Estevez", true, new User("user5", "12345")),
                    new Story(41, new Timestamp(formatter.parse("2022-05-01 01:01:01").getTime()), "justo eu arcu. Morbi", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.SUSPENSE, Theme.HORROR, "Marta Estevez", true, new User("user5", "12345")),
                    new Story(42, new Timestamp(formatter.parse("2022-05-01 01:01:01").getTime()), "egestas blandit. Nam nulla", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.HORROR, Theme.HORROR, "Marta Estevez", true, new User("user5", "12345")),
                    new Story(43, new Timestamp(formatter.parse("2022-05-01 01:01:01").getTime()), "urna suscipit nonummy. Fusce", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.CHILD, Theme.HORROR, "Marta Estevez", true, new User("user5", "12345")),
                    new Story(44, new Timestamp(formatter.parse("2022-05-01 01:01:01").getTime()), "sociis natoque penatibus et", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.HORROR, Theme.HORROR, "Marta Estevez", true, new User("user5", "12345")),
                    new Story(45, new Timestamp(formatter.parse("2022-05-01 02:01:01").getTime()), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.SCIENCE_FICTION, Theme.HORROR, "Marta Estevez", true, new User("user5", "12345")),
                    new Story(46, new Timestamp(formatter.parse("2022-05-01 03:01:01").getTime()), "ornare sagittis felis. Donec", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.ROMANCE, Theme.HORROR, "Marta Estevez", true, new User("user5", "12345")),
                    new Story(47, new Timestamp(formatter.parse("2022-05-01 04:01:01").getTime()), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.SCIENCE_FICTION, Theme.HORROR, "Marta Estevez", true, new User("user5", "12345")),
                    new Story(48, new Timestamp(formatter.parse("2022-05-01 05:01:01").getTime()), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.SUSPENSE, Theme.HORROR, "Marta Estevez", true, new User("user5", "12345")),
                    new Story(49, new Timestamp(formatter.parse("2022-05-01 06:01:01").getTime()), "Lorem ipsum dolor sit", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.SCIENCE_FICTION, Theme.HORROR, "Marta Estevez", true, new User("user5", "12345")),
                    new Story(50, new Timestamp(formatter.parse("2022-05-01 07:01:01").getTime()), "In tincidunt congue turpis.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.SUSPENSE, Theme.HORROR, "Marta Estevez", true, new User("user5", "12345")),
            };

            addVisitDateToStories(formatter, stories);

            return stories;
        } catch (ParseException | IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Story[] storiesLess() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
        try {
            Story[] stories = new Story[]{
                    new Story(1, new Timestamp(formatter.parse("2009-02-01 01:01:01").getTime()), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.ROMANCE, Theme.HORROR, "Juan Manuel Lopez", true, new User("user1", "12345")),
                    new Story(2, new Timestamp(formatter.parse("2008-02-01 01:01:01").getTime()), "Integer aliquam adipiscing lacus.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.CHILD, Theme.HORROR, "Juan Manuel Lopez", true, new User("user1", "12345")),
                    new Story(3, new Timestamp(formatter.parse("2007-02-01 01:01:01").getTime()), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.HISTORIC, Theme.HORROR, "Juan Manuel Lopez", true, new User("user1", "12345")),
                    new Story(4, new Timestamp(formatter.parse("2006-02-01 01:01:01").getTime()), "sed pede. Cum sociis", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.HORROR, Theme.HORROR, "Juan Manuel Lopez", true, new User("user1", "12345")),
            };
            return stories;
        } catch (ParseException | IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Story[] storiesSameDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
        try {
            Story[] stories = new Story[]{
                    new Story(1, new Timestamp(formatter.parse("2006-02-01 01:01:01").getTime()), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.ROMANCE, Theme.HORROR, "Juan Manuel Lopez", true, new User("user1", "12345")),
                    new Story(2, new Timestamp(formatter.parse("2006-02-01 01:01:01").getTime()), "Integer aliquam adipiscing lacus.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.CHILD, Theme.HORROR, "Juan Manuel Lopez", true, new User("user1", "12345")),
                    new Story(3, new Timestamp(formatter.parse("2006-02-01 01:01:01").getTime()), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.HISTORIC, Theme.HORROR, "Juan Manuel Lopez", true, new User("user1", "12345")),
                    new Story(4, new Timestamp(formatter.parse("2006-02-01 01:01:01").getTime()), "sed pede. Cum sociis", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.HORROR, Theme.HORROR, "Juan Manuel Lopez", true, new User("user1", "12345")),
                    new Story(5, new Timestamp(formatter.parse("2006-02-01 01:01:01").getTime()), "eleifend. Cras sed leo.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.ROMANCE, Theme.HORROR, "Juan Manuel Lopez", true, new User("user1", "12345")),
                    new Story(6, new Timestamp(formatter.parse("2006-02-01 01:01:01").getTime()), "Aliquam ultrices iaculis odio.", "rutrum lorem ac risus. Morbi metus. Vivamus euismod urna. Nullam lobortis quam a felis", Genre.NANOSTORY, Theme.SUSPENSE, Theme.HORROR, "Juan Manuel Lopez", true, new User("user1", "12345")),
                    new Story(7, new Timestamp(formatter.parse("2006-02-01 01:01:01").getTime()), "In nec orci. Donec", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.HISTORIC, Theme.HORROR, "Juan Manuel Lopez", true, new User("user1", "12345")),
            };
            return stories;
        } catch (ParseException | IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Story> recentStories() {
        return stream(stories())
                .filter(Story::isPublished)
                .sorted(Comparator.comparing(Story::getDate).reversed())
                .limit(6)
                .collect(Collectors.toList());
    }

    public static List<Story> recentStoriesLess() {
        return stream(storiesLess())
                .filter(Story::isPublished)
                .sorted(Comparator.comparing(Story::getDate).reversed())
                .limit(4)
                .collect(Collectors.toList());
    }

    public static List<Story> recentStoriesSameDate() {
        return stream(storiesSameDate())
                .filter(Story::isPublished)
                .sorted(Comparator.comparing(Story::getDate).reversed())
                .limit(6)
                .collect(Collectors.toList());
    }

    public static List<Story> nullStories() {
        return stream(stories())
                .filter(Story::isPublished)
                .sorted(Comparator.comparing(Story::getDate).reversed())
                .limit(6)
                .collect(Collectors.toList());
    }

    public static List<Story> getStoriesSubListByText(String text, int indexI, int indexJ) {
        return stream(stories())
                .filter(story -> story.getTitle().contains(text) || story.getContent().contains(text))
                .sorted(Comparator.comparing(Story::getDate).reversed())
                .collect(Collectors.toList())
                .subList(indexI, indexJ);
    }

    /**
     * Returns the first story in the list of stories whose id matches with the searched one.
     *
     * @param id The id of the story to search.
     * @return The story with the id to search.
     * @throws IllegalArgumentException The exception thrown when the story searched by its id is not found.
     */
    public static Story storyWithId(int id) {
        return stream(Objects.requireNonNull(stories())).filter(story -> story.getId() == id)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static List<Story> searchStories(String text) {
        final List<Story> stories = new ArrayList<>();

        for (Story story : Objects.requireNonNull(stories())) {
            if ((story.getTitle().contains(text) || story.getContent().contains(text))
                    && story.isPublished()) {
                stories.add(story);
            }
        }
        return stories;
    }

    /**
     * Get the stories that have been most read between startDate and endDate.
     *
     * @param genre    The genre of the microstory.
     * @param initDate The initial date when start to search.
     * @param endDate  Te end date to stop the search.
     * @param page     The page number.
     * @param size     The size of the page.
     * @return The list of the most read stories.
     */
    public static List<Story> hottestStories(Genre genre, Date initDate, Date endDate, int page, int size) {
        return stream(stories())
                .filter(Story::isPublished)
                .filter(s -> s.getGenre().equals(genre))
                .filter(s -> s.getVisitCountInDateRange(initDate, endDate) > 0)
                .sorted(Comparator.comparing(s -> s.getVisitCountInDateRange(initDate, endDate)))
                .sorted((s1, s2) ->
                        (int) (s2.getVisitCountInDateRange(initDate, endDate) - s1.getVisitCountInDateRange(initDate, endDate)))
                .skip((long) page * size)
                .limit(size)
                .collect(Collectors.toList());
    }

    /**
     * Adds the value of the visit dates to the stories.
     *
     * @param formatter The date formatter.
     * @param stories   The list of the stories.
     * @throws ParseException The ParseException.
     */
    private static void addVisitDateToStories(SimpleDateFormat formatter, Story[] stories) throws ParseException {
        List<Date> dateStory0 = new ArrayList<>();
        dateStory0.add(formatter.parse("2022-02-10 23:31:01"));
        dateStory0.add(formatter.parse("2022-05-01 10:01:01"));
        dateStory0.add(formatter.parse("2022-05-22 11:40:01"));
        stories[1].setVisitDate(dateStory0);

        List<Date> dateStory1 = new ArrayList<>();
        dateStory1.add(formatter.parse("2022-09-06 06:11:22"));
        stories[2].setVisitDate(dateStory1);

        List<Date> dateStory2 = new ArrayList<>();
        dateStory2.add(formatter.parse("2022-05-30 01:01:01"));
        dateStory2.add(formatter.parse("2022-10-21 02:20:01"));
        stories[3].setVisitDate(dateStory2);

        List<Date> dateStory3 = new ArrayList<>();
        dateStory3.add(formatter.parse("2022-11-01 01:01:11"));
        dateStory3.add(formatter.parse("2022-12-05 08:45:07"));
        stories[4].setVisitDate(dateStory3);

        List<Date> dateStory4 = new ArrayList<>();
        dateStory4.add(formatter.parse("2022-06-08 15:01:30"));
        dateStory4.add(formatter.parse("2022-05-01 01:01:01"));
        stories[5].setVisitDate(dateStory4);
    }
}
