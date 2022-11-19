package es.uvigo.esei.dgss.teama.microstories.rest;

import es.uvigo.esei.dgss.teama.microstories.service.StoryEJB;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Resource that represents the stories in the application.
 *
 * @author Bruno Cruz González (bcgonzalez4) Brais Domínguez Álvarez (bdalvarez)
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
}

