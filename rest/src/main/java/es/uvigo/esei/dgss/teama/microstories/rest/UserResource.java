package es.uvigo.esei.dgss.teama.microstories.rest;

import es.uvigo.esei.dgss.teama.microstories.domain.entities.Story;
import es.uvigo.esei.dgss.teama.microstories.service.StoryEJB;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Resource that represents the user information in the application.
 *
 * @author Nicolás Cid Gómez (ncgomez17)
 */

@Path("user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    @EJB
    private StoryEJB storyEJB;

    /**
     * Find the stories that have been most read between the given dates.
     *
     * @param login      The user login.
     * @param pageNumber The number of the page.
     * @param maxItems   The maximum of stories to return by page.
     * @return The list of user microstories .
     */
    @GET
    @Path("{login}/microstory")
    public Response getUserStories(@PathParam("login") String login,
                                   @DefaultValue("0") @QueryParam("pageNumber") int pageNumber,
                                   @DefaultValue("10") @QueryParam("maxItems") int maxItems) {
        List<Story> stories = null;
        //autenticar luego llamar al ejb
        //stories = this.storyEJB.getUserStories(login,pageNumber,maxItems);

        if (stories == null)
            throw new BadRequestException();
        else
            return Response.ok(stories).build();
    }

}
