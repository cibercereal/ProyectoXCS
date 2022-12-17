package es.uvigo.esei.dgss.teama.microstories.rest;

import es.uvigo.esei.dgss.teama.microstories.domain.entities.Story;
import es.uvigo.esei.dgss.teama.microstories.service.StoryEJB;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Principal;

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

    @Inject
    private Principal currentUser;

    /**
     * Find the stories that have been most read between the given dates.
     *
     * @param login      The user login.
     * @param id         The microstory id.
     * @param story      The edited story.
     * @return The list of user microstories .
     */
    @PUT
    @Path("{login}/microstory{id}")
    public Response modifymicroStory(@PathParam("login") String login,
                                     @PathParam("id") int id, @QueryParam("Story") Story story) {
        if(Boolean.FALSE.equals(this.currentUser.getName().equals(login))){
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        this.storyEJB.editStory(id, story);
        return Response.ok().build();
    }

}
