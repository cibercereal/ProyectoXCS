package es.uvigo.esei.dgss.teama.microstories.service;

import es.uvigo.esei.dgss.teama.microstories.domain.entities.Genre;
import es.uvigo.esei.dgss.teama.microstories.domain.entities.Publication;
import es.uvigo.esei.dgss.teama.microstories.domain.entities.Story;
import es.uvigo.esei.dgss.teama.microstories.domain.entities.StoryDataset;
import es.uvigo.esei.dgss.teama.microstories.domain.entities.Theme;
import es.uvigo.esei.dgss.teama.microstories.domain.entities.User;
import es.uvigo.esei.dgss.teama.microstories.service.util.security.RoleCaller;
import es.uvigo.esei.dgss.teama.microstories.service.util.security.TestPrincipal;
import org.hamcrest.CoreMatchers;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static es.uvigo.esei.dgss.teama.microstories.domain.entities.IsEqualToStory.containsStoriesInOrder;
import static es.uvigo.esei.dgss.teama.microstories.domain.entities.IsEqualToStory.equalToStory;
import static es.uvigo.esei.dgss.teama.microstories.domain.entities.StoryDataset.*;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(Arquillian.class)
@UsingDataSet("stories.xml")
@Ignore
public class TestStoryEJB {

    @Inject
    private StoryEJB storyEJB;

    @Inject
    private TestPrincipal principal;

    @EJB(beanName = "author-caller")
    private RoleCaller asAuthor;

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(Story.class.getPackage())
                .addClasses(StoryEJB.class, StoryDataset.class)
                .addPackage(RoleCaller.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("jboss-web.xml")
                .addAsResource("arquillian.extension.persistence.properties")
                .addAsResource("arquillian.extension.persistence.dbunit.properties")
                .addAsWebInfResource("beans.xml", "beans.xml");
    }

    @Test
    @ShouldMatchDataSet("stories.xml")
    public void testRecentStories() {
        final List<Story> recentStories = recentStories();
        final List<Story> dbStories = storyEJB.getRecentStories();

        assertThat(recentStories, containsStoriesInOrder(dbStories));
    }

    @Test
    @ShouldMatchDataSet("stories.xml")
    public void testEmptyStories() {
        final List<Story> recentStories = recentStories();
        final List<Story> dbStories = storyEJB.getRecentStories();

        assertThat(recentStories, containsStoriesInOrder(dbStories));
    }

    @Test
    @UsingDataSet("storiesEmpty.xml")
    @ShouldMatchDataSet("storiesEmpty.xml")
    public void testRecentStoriesEmpty() {
        final List<Story> dbStories = storyEJB.getRecentStories();
        assertThat(dbStories, is(empty()));
    }

    @Test
    @UsingDataSet("storiesLess.xml")
    @ShouldMatchDataSet("storiesLess.xml")
    public void testRecentStoriesLess() {
        final List<Story> recentStoriesLess = recentStoriesLess();
        final List<Story> dbStories = storyEJB.getRecentStories();
        System.out.println(recentStoriesLess);
        System.out.println(dbStories);
        assertThat(recentStoriesLess, containsStoriesInOrder(dbStories));
    }

    @Test
    @UsingDataSet("storiesSameDate.xml")
    @ShouldMatchDataSet("storiesSameDate.xml")
    public void testRecentStoriesSameDate() {
        final List<Story> recentStories = recentStoriesSameDate();
        final List<Story> dbStories = storyEJB.getRecentStories();
        System.out.println(recentStories);
        System.out.println(dbStories);
        assertThat(recentStories, containsStoriesInOrder(dbStories));
    }

    @Test
    @UsingDataSet("stories.xml")
    @ShouldMatchDataSet("stories.xml")
    public void testGetStoriesContainingText() {
        String text = "Aliquam";
        final List<Story> dbStoriesPage1 = storyEJB.getStoriesByText(text, 0, 9);
        final List<Story> dbStoriesPage2 = storyEJB.getStoriesByText(text, 1, 9);

        List<Story> expectedStoriesPage1 = getStoriesSubListByText(text, 0, 9);
        List<Story> expectedStoriesPage2 = getStoriesSubListByText(text, 9, 18);

        assertThat(dbStoriesPage1, containsStoriesInOrder(expectedStoriesPage1));
        assertThat(dbStoriesPage2, containsStoriesInOrder(expectedStoriesPage2));
    }

    @Test(expected = EJBTransactionRolledbackException.class)
    @UsingDataSet("stories.xml")
    public void testGetNullStoriesContainingText() {
        String text = "Aliquam";
        storyEJB.getStoriesByText(text, 0, 0);
    }

    @Test
    @UsingDataSet("stories.xml")
    @ShouldMatchDataSet("stories.xml")
    public void testGetStoriesByTextNotFound() {
        String text = "text not found";

        final List<Story> dbStoriesPage1 = storyEJB.getStoriesByText(text, 1, 9);

        assertThat(dbStoriesPage1, is(empty()));
    }

    @Test
    @UsingDataSet("stories.xml")
    @ShouldMatchDataSet("stories.xml")
    public void testGetStoryById() {
        final int storeId = 1;
        final Story expectedStory = storyWithId(storeId);
        final Story story = storyEJB.getById(storeId);

        assertThat(story, CoreMatchers.is(instanceOf(Story.class)));
        assertThat(story, is(equalToStory(expectedStory)));
    }

    @Test
    @UsingDataSet("stories.xml")
    @ShouldMatchDataSet("stories.xml")
    public void testGetStoryByNonExistentId() {
        assertNull(storyEJB.getById(100));
    }

    @Test
    @UsingDataSet("stories.xml")
    @ShouldMatchDataSet("stories.xml")
    public void testCalculatePagesSearch() {
        String text = "Integer";
        int maxItems = 4;
        int pages = storyEJB.calculatePagesSearch(text, maxItems);

        assertThat(pages, is(1));
    }

    @Test
    @UsingDataSet("stories.xml")
    @ShouldMatchDataSet("stories.xml")
    public void testSearchStories() {
        String text = "Integer";

        int maxItems = 4;

        List<Story> searc1 = storyEJB.searchStory(text, 1, maxItems);
        List<Story> searc2 = storyEJB.searchStory(text, 2, maxItems);

        assertThat(searc1.size(), is(2));
        assertThat(searc2.size(), is(0));

    }

    @Test
    @UsingDataSet("stories.xml")
    @ShouldMatchDataSet("stories.xml")
    public void testSearchStoriesTextNotExist() {
        String text = "ahkajshdkjash";

        int maxItems = 4;

        List<Story> searc1 = storyEJB.searchStory(text, 1, maxItems);
        List<Story> searc2 = storyEJB.searchStory(text, 2, maxItems);

        assertThat(searc1.size(), is(0));
        assertThat(searc2.size(), is(0));

    }

    @Test
    @ShouldMatchDataSet(value = "stories.xml")
    public void testFindStoryHottest() {
        final Calendar cal = Calendar.getInstance();
        cal.set(2022, Calendar.MAY, 1, 0, 0, 0);
        final Date startDate = cal.getTime();
        cal.set(Calendar.DAY_OF_MONTH, 30);
        final Date referenceDate = cal.getTime();

        final List<Story> expectedStories = hottestStories(Genre.POETRY, startDate, referenceDate, 0, 10);
        final List<Story> queriedStories = storyEJB.findHottestStories(Genre.POETRY, startDate, referenceDate, 10);

        assertThat(expectedStories, containsStoriesInOrder(queriedStories));
    }

    @Test
    @ShouldMatchDataSet(value = {"stories.xml", "stories-new-visit.xml"}, excludeColumns = "VISITDATE.visitDate")
    public void testGetStory() {
        final Story existentStory = existentStory();

        final Story actualStory = storyEJB.getById(existentStory.getId());

        assertThat(actualStory, is(equalToStory(existentStory)));
    }

    @Test
    @UsingDataSet("stories.xml")
    @ShouldMatchDataSet("stories.xml")
    public void testSearchExploreWithoutParameters() {
        int maxItems = 4;

        List<Story> explore = storyEJB.exploreStory(null, null, null, 0, maxItems);

        assertThat(explore.size(), is(4));

    }

    @Test
    @UsingDataSet("stories.xml")
    @ShouldMatchDataSet("stories.xml")
    public void testSearchExploreWithParameters() {
        int maxItems = 4;

        List<Story> explore = storyEJB.exploreStory(Genre.NANOSTORY, Theme.SCIENCE_FICTION, Publication.THIS_YEAR, 0, maxItems);

        assertThat(explore.size(), is(1));
        assertThat(explore.get(0).getGenre(), is(Genre.NANOSTORY));
        assertThat(explore.get(0).getMainTheme(), is(Theme.SCIENCE_FICTION));

    }

    @Test
    @UsingDataSet("stories.xml")
    @ShouldMatchDataSet("stories.xml")
    public void testGetTotalPagesSearchTextMoreThanOnePage() {
        int totalPages = storyEJB.getTotalPagesSearchText("Aliquam", 9);

        assertThat(totalPages, is(2));
    }

    @Test
    @UsingDataSet("stories.xml")
    @ShouldMatchDataSet("stories.xml")
    public void testGetTotalPagesSearchTextSinglePage() {
        int totalPages = storyEJB.getTotalPagesSearchText("magna", 9);

        assertThat(totalPages, is(1));
    }

    @Test
    @UsingDataSet("stories.xml")
    @ShouldMatchDataSet("stories.xml")
    public void testGetTotalPagesSearchTextNoResults() {
        int totalPages = storyEJB.getTotalPagesSearchText("no results", 9);

        assertThat(totalPages, is(1));
    }

    @Test
    @ShouldMatchDataSet(value = "stories-add.xml", excludeColumns = "visitdate.visitDate")
    public void testCreateStory() {
        String username = "Marta Estevez";
        principal.setName(username);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
        try {
            Date date = formatter.parse("2021-04-01 01:01:01");
            asAuthor.run(() -> this.storyEJB.createStory(date, "In tincidunt congue turpis.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.ADVENTURE, Theme.HORROR, true));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    @ShouldMatchDataSet(value = "stories-edit.xml", excludeColumns = "visitdate.visitDate")
    public void testModifyStoryData() {
        String username = "user1";
        principal.setName(username);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
        try {
            Date date = formatter.parse("2006-02-01 01:01:01");
            final Story story = new Story(1, date, "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.ROMANCE, Theme.HORROR, "Juan Manuel Lopez", true, new User(username, "827ccb0eea8a706c4c34a16891f84e7b"));
            asAuthor.run(() -> this.storyEJB.editStory(1, story));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = EJBTransactionRolledbackException.class)
    @ShouldMatchDataSet("stories.xml")
    public void testModifyNonExistentStory() {
        String username = "user1";
        principal.setName(username);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);

        try {
            Date date = formatter.parse("2006-02-01 01:01:01");
            final Story story = new Story(55, date, "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.ROMANCE, Theme.HORROR, "Juan Manuel Lopez", true, new User(username, "827ccb0eea8a706c4c34a16891f84e7b"));
            asAuthor.run(() -> this.storyEJB.editStory(102, story));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}