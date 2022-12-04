package es.uvigo.esei.dgss.teama.microstories.rest;

import es.uvigo.esei.dgss.teama.microstories.domain.entities.Genre;
import es.uvigo.esei.dgss.teama.microstories.domain.entities.Story;
import es.uvigo.esei.dgss.teama.microstories.domain.entities.Theme;
import es.uvigo.esei.dgss.teama.microstories.service.StoryEJB;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Resource that represents the stories in the application.
 *
 * @author Bruno Cruz González (bcgonzalez4) Brais Domínguez Álvarez (bdalvarez) Álvaro Suárez Feijoo (asfeijoo)
 */
@Path("microstory")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StoryResource {

    @EJB
    private StoryEJB storyEJB;

    /**
     * Retrieves the most recent stories that are in published status.
     *
     * @return The list of recent stories.
     */
    @GET
    @Path("recent")
    public Response getRecentStories() {
        return Response.ok(storyEJB.getRecentStories()).build();
    }

    /**
     * Returns a history from its identifier. If the history is not found, an exception is thrown.
     *
     * @param id The id of the story to return.
     * @return The story with the searched id.
     */
    @GET
    @Path("{id}")
    public Response getStoryById(@PathParam("id") int id) {
        final Story story = this.storyEJB.getById(id);

        if (story == null)
            throw new BadRequestException();
        else
            return Response.ok(story).build();
    }

    /**
     * Returns a list stories from a text.
     *
     * @param text       The id of the story to return.
     * @param pageNumber The number of the page.
     * @param maxItems   The maximum of stories to return by page.
     * @return The list of stories.
     */
    @GET
    public Response getStoryByText(
            @QueryParam("contains") String text,
            @DefaultValue("0") @QueryParam("pageNumber") int pageNumber,
            @DefaultValue("10") @QueryParam("maxItems") int maxItems) {

        List<Story> storyList = this.storyEJB.getStoriesByText(text, pageNumber, maxItems);

        if (storyList == null)
            throw new BadRequestException();
        else
            return Response.ok(storyList).build();
    }
    /**
     * Returns a list stories from a Genre, publication or theme.
     *
     * @param genre       The Genre of the story to return.
     * @param theme      The Theme of the story to return.
     * @param publication       The publication of the story to return.
     * @param pageNumber The number of the page.
     * @param maxItems   The maximum of stories to return by page.
     * @return The list of stories.
     */
    @GET
    public Response getStoryByText(
            @QueryParam("genre") Genre genre,
            @QueryParam("theme")Theme theme,
            @QueryParam("publication") String publication,
            @DefaultValue("0") @QueryParam("pageNumber") int pageNumber,
            @DefaultValue("10") @QueryParam("maxItems") int maxItems) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
        Date publicationDate = formatter.parse(publication);
        List<Story> storyList = this.storyEJB.exploreStory(genre, theme, publicationDate,pageNumber,maxItems);

        if (storyList == null)
            throw new BadRequestException();
        else
            return Response.ok(storyList).build();
    }
}

