package es.uvigo.esei.dgss.teama.microstories.service;

import es.uvigo.esei.dgss.teama.microstories.domain.entities.Story;
import es.uvigo.esei.dgss.teama.microstories.domain.entities.StoryDataset;
import es.uvigo.esei.dgss.teama.microstories.service.util.security.RoleCaller;

import es.uvigo.esei.dgss.teama.microstories.service.util.security.TestPrincipal;
import org.hamcrest.collection.IsEmptyCollection;
import org.jboss.arquillian.container.test.api.Deployment;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.ShouldMatchDataSet;

import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;


import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static es.uvigo.esei.dgss.teama.microstories.domain.entities.IsEqualToStory.containsStoriesInOrder;
import static es.uvigo.esei.dgss.teama.microstories.domain.entities.StoryDataset.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(Arquillian.class)
@UsingDataSet("stories.xml")
public class TestStoryEJB {

    @Inject
    private StoryEJB storyEJB;

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
    //todo vacio , cuando hay menos de seis,mas de seis  con la misma fecha de publlicacion
    @Test
    @UsingDataSet("storiesEmpty.xml")
    @ShouldMatchDataSet("storiesEmpty.xml")
    public void testRecentStoriesEmpty() {
        final List<Story> dbStories = storyEJB.getRecentStories();
        assertThat(dbStories, is(IsEmptyCollection.empty()));
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
        final List<Story> storiesContainingText = getStoriesContainingText(text);
        final List<Story> dbStoriesPage1 = storyEJB.getStoriesContainingText(text, 1);
        final List<Story> dbStoriesPage2 = storyEJB.getStoriesContainingText(text, 2);
        
        List<Story> expectedStoriesPage1 = storiesContainingText.subList(0, 9);
        List<Story> expectedStoriesPage2 = storiesContainingText.subList(9, 18);
 
        assertThat(dbStoriesPage1, containsStoriesInOrder(expectedStoriesPage1));
        assertThat(dbStoriesPage2, containsStoriesInOrder(expectedStoriesPage2));
    }
    
    @Test
    @UsingDataSet("stories.xml")
    @ShouldMatchDataSet("stories.xml")
    public void testGetStoriesContainingTextNotFound() {
        String text = "text not found";

        final List<Story> dbStoriesPage1 = storyEJB.getStoriesContainingText(text, 1);
        
        assertThat(dbStoriesPage1, is(new ArrayList<>()));

    }

}
