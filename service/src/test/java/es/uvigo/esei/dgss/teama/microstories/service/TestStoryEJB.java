package es.uvigo.esei.dgss.teama.microstories.service;

import es.uvigo.esei.dgss.teama.microstories.entities.Story;
import es.uvigo.esei.dgss.teama.microstories.entities.StoryDataset;

import es.uvigo.esei.dgss.teama.microstories.service.util.security.TestPrincipal;
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
import java.util.List;

import static es.uvigo.esei.dgss.teama.microstories.entities.IsEqualToStory.containsStoriesInOrder;
import static es.uvigo.esei.dgss.teama.microstories.entities.StoryDataset.recentStories;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Arquillian.class)
@UsingDataSet("stories.xml")
public class TestStoryEJB {

    @Inject
    private StoryEJB storyEJB;



    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war").addClasses(StoryEJB.class, StoryDataset.class)
                .addPackage(es.uvigo.esei.dgss.teama.microstories.service.util.security.RoleCaller.class.getPackage())
                .addPackage(Story.class.getPackage()).addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("jboss-web.xml").addClasses(TestPrincipal.class).addAsResource("arquillian.extension.persistence.properties")
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
}