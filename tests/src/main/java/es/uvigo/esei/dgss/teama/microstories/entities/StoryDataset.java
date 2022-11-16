package es.uvigo.esei.dgss.teama.microstories.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Set;

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
                    new Story(1, formatter.parse("2006-02-01 01:01:01"), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.ROMANCE, Theme.HORROR, "Juan Manuel Lopez", true),
                    new Story(2, formatter.parse("2006-02-01 01:01:01"), "Integer aliquam adipiscing lacus.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.CHILDISH, Theme.HORROR, "Juan Manuel Lopez", true),
                    new Story(3, formatter.parse("2006-02-01 01:01:01"), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.STORY, Theme.HORROR, "Juan Manuel Lopez", true),
                    new Story(4, formatter.parse("2006-02-01 01:01:01"), "sed pede. Cum sociis", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.HORROR, Theme.HORROR, "Juan Manuel Lopez", true),
                    new Story(5, formatter.parse("2006-02-01 01:01:01"), "eleifend. Cras sed leo.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.ROMANCE, Theme.HORROR, "Juan Manuel Lopez", true),
                    new Story(6, formatter.parse("2006-02-01 01:01:01"), "Aliquam ultrices iaculis odio.", "rutrum lorem ac risus. Morbi metus. Vivamus euismod urna. Nullam lobortis quam a felis", Genre.NANOSTORY, Theme.SUSPENSE, Theme.HORROR, "Juan Manuel Lopez", true),
                    new Story(7, formatter.parse("2006-02-01 01:01:01"), "In nec orci. Donec", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.STORY, Theme.HORROR, "Juan Manuel Lopez", true),
                    new Story(8, formatter.parse("2012-12-01 01:01:01"), "Aliquam ultrices iaculis odio.", "et ultrices posuere cubilia Curae Donec tincidunt. Donec vitae erat vel pede blandit congue.", Genre.POETRY, Theme.STORY, Theme.HORROR, "Juan Manuel Lopez", true),
                    new Story(9, formatter.parse("2012-12-01 01:01:01"), "erat. Etiam vestibulum massa", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.SCIFI, Theme.HORROR, "Juan Manuel Lopez", true),
                    new Story(10, formatter.parse("2012-12-01 01:01:01"), "consectetuer mauris id sapien.", "lacus. Aliquam rutrum lorem ac risus. Morbi metus. Vivamus euismod urna. Nullam lobortis quam", Genre.STORY, Theme.HORROR, Theme.HORROR, "Juan Manuel Lopez", true),
                    new Story(11, formatter.parse("2012-12-01 01:01:01"), "accumsan laoreet ipsum. Curabitur", "sit amet ornare lectus justo eu arcu. Morbi sit amet massa. Quisque porttitor eros", Genre.POETRY, Theme.ROMANCE, Theme.HORROR, "Juan Manuel Lopez", true),
                    new Story(12, formatter.parse("2012-12-01 01:01:01"), "dapibus quam quis diam.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.ROMANCE, Theme.HORROR, "Juan Manuel Lopez", true),
                    new Story(13, formatter.parse("2012-12-01 01:01:01"), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.SUSPENSE, Theme.HORROR, "Juan Manuel Lopez", true),
                    new Story(14, formatter.parse("2012-12-01 01:01:01"), "In tincidunt congue turpis.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.STORY, Theme.HORROR, "Enrique Fernandez", true),
                    new Story(15, formatter.parse("2014-03-01 01:01:01"), "Aliquam ultrices iaculis odio.", "senectus et netus et malesuada fames ac turpis egestas. Fusce aliquet magna a neque.", Genre.POETRY, Theme.ROMANCE, Theme.HORROR, "Enrique Fernandez", true),
                    new Story(16, formatter.parse("2014-03-01 01:01:01"), "mattis. Cras eget nisi", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.SUSPENSE, Theme.HORROR, "Enrique Fernandez", true),
                    new Story(17, formatter.parse("2014-03-01 01:01:01"), "Aenean eget metus. In", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.SUSPENSE, Theme.HORROR, "Enrique Fernandez", true),
                    new Story(18, formatter.parse("2014-03-01 01:01:01"), "ligula consectetuer rhoncus. Nullam", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.CHILDISH, Theme.HORROR, "Enrique Fernandez", true),
                    new Story(19, formatter.parse("2014-03-01 01:01:01"), "magna nec quam. Curabitur", "dolor vitae dolor. Donec fringilla. Donec feugiat metus sit amet ante. Vivamus non lorem", Genre.POETRY, Theme.STORY, Theme.HORROR, "Enrique Fernandez", true),
                    new Story(20, formatter.parse("2014-03-01 01:01:01"), "Aliquam ultrices iaculis odio.", "mauris a nunc. In at pede. Cras vulputate velit eu sem. Pellentesque ut ipsum", Genre.POETRY, Theme.SCIFI, Theme.HORROR, "Enrique Fernandez", true),
                    new Story(21, formatter.parse("2014-03-01 01:01:01"), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.HORROR, Theme.HORROR, "Enrique Fernandez", true),
                    new Story(22, formatter.parse("2014-03-01 01:01:01"), "egestas. Duis ac arcu.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.SCIFI, Theme.HORROR, "Enrique Fernandez", true),
                    new Story(23, formatter.parse("2014-03-01 01:01:01"), "ante dictum cursus. Nunc", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.CHILDISH, Theme.HORROR, "Enrique Fernandez", true),
                    new Story(24, formatter.parse("2014-03-01 01:01:01"), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.CHILDISH, Theme.HORROR, "Enrique Fernandez", true),
                    new Story(25, formatter.parse("2014-03-01 01:01:01"), "Aliquam ultrices iaculis odio.", "neque et nunc. Quisque ornare tortor at risus. Nunc ac sem ut dolor dapibus", Genre.POETRY, Theme.SCIFI, Theme.HORROR, "Enrique Fernandez", true),
                    new Story(26, formatter.parse("2014-03-01 01:01:01"), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.CHILDISH, Theme.HORROR, "Enrique Fernandez", true),
                    new Story(27, formatter.parse("2022-05-01 01:01:01"), "sed pede. Cum sociis", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.STORY, Theme.HORROR, "Enrique Fernandez", true),
                    new Story(28, formatter.parse("2022-05-01 01:01:01"), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.HORROR, Theme.HORROR, "Enrique Fernandez", true),
                    new Story(29, formatter.parse("2022-05-01 01:01:01"), "Integer vitae nibh. Donec", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.CHILDISH, Theme.HORROR, "Enrique Fernandez", true),
                    new Story(30, formatter.parse("2022-05-01 01:01:01"), "neque non quam. Pellentesque", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.ROMANCE, Theme.HORROR, "Enrique Fernandez", true),
                    new Story(31, formatter.parse("2022-05-01 01:01:01"), "Nam ac nulla. In", "ridiculus mus. Aenean eget magna. Suspendisse tristique neque venenatis lacus. Etiam bibendum fermentum metus.", Genre.POETRY, Theme.ROMANCE, Theme.HORROR, "Enrique Fernandez", true),
                    new Story(32, formatter.parse("2022-05-01 01:01:01"), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.HORROR, Theme.HORROR, "Ana Miguez", true),
                    new Story(33, formatter.parse("2022-05-01 01:01:01"), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.HORROR, Theme.HORROR, "Ana Miguez", true),
                    new Story(34, formatter.parse("2022-05-01 01:01:01"), "ultrices. Duis volutpat nunc", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.STORY, Theme.HORROR, "Ana Miguez", true),
                    new Story(35, formatter.parse("2022-05-01 01:01:01"), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.SCIFI, Theme.HORROR, "Ana Miguez", true),
                    new Story(36, formatter.parse("2022-05-01 01:01:01"), "Aliquam ultrices iaculis odio.", "Phasellus ornare. Fusce mollis. Duis sit amet diam eu dolor egestas rhoncus. Proin nisl", Genre.STORY, Theme.ROMANCE, Theme.HORROR, "Ana Miguez", true),
                    new Story(37, formatter.parse("2022-05-01 01:01:01"), "Aliquam ultrices iaculis odio.", "erat. Etiam vestibulum massa rutrum magna. Cras convallis convallis dolor. Quisque tincidunt pede ac", Genre.POETRY, Theme.STORY, Theme.HORROR, "Ana Miguez", true),
                    new Story(38, formatter.parse("2022-05-01 01:01:01"), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.ROMANCE, Theme.HORROR, "Ana Miguez", true),
                    new Story(39, formatter.parse("2022-05-01 01:01:01"), "Aliquam ultrices iaculis odio.", "ligula. Aenean euismod mauris eu elit. Nulla facilisi. Sed neque. Sed eget lacus. Mauris", Genre.STORY, Theme.SCIFI, Theme.HORROR, "Marta Estevez", true),
                    new Story(40, formatter.parse("2022-05-01 01:01:01"), "Phasellus vitae mauris sit", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.STORY, Theme.HORROR, "Marta Estevez", true),
                    new Story(41, formatter.parse("2022-05-01 01:01:01"), "justo eu arcu. Morbi", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.SUSPENSE, Theme.HORROR, "Marta Estevez", true),
                    new Story(42, formatter.parse("2022-05-01 01:01:01"), "egestas blandit. Nam nulla", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.HORROR, Theme.HORROR, "Marta Estevez", true),
                    new Story(43, formatter.parse("2022-05-01 01:01:01"), "urna suscipit nonummy. Fusce", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.CHILDISH, Theme.HORROR, "Marta Estevez", true),
                    new Story(44, formatter.parse("2022-05-01 01:01:01"), "sociis natoque penatibus et", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.HORROR, Theme.HORROR, "Marta Estevez", true),
                    new Story(45, formatter.parse("2022-05-01 01:01:01"), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.SCIFI, Theme.HORROR, "Marta Estevez", true),
                    new Story(46, formatter.parse("2022-05-01 01:01:01"), "ornare sagittis felis. Donec", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.ROMANCE, Theme.HORROR, "Marta Estevez", true),
                    new Story(47, formatter.parse("2022-05-01 01:01:01"), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.SCIFI, Theme.HORROR, "Marta Estevez", true),
                    new Story(48, formatter.parse("2022-05-01 01:01:01"), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.SUSPENSE, Theme.HORROR, "Marta Estevez", true),
                    new Story(49, formatter.parse("2022-05-01 01:01:01"), "Lorem ipsum dolor sit", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.SCIFI, Theme.HORROR, "Marta Estevez", true),
                    new Story(50, formatter.parse("2022-05-01 01:01:01"), "In tincidunt congue turpis.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.SUSPENSE, Theme.HORROR, "Marta Estevez", true),
            };
            return stories;
        } catch (ParseException | IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }
}
