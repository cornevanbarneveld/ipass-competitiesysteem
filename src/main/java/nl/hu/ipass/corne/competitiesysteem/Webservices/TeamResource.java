package nl.hu.ipass.corne.competitiesysteem.Webservices;


import nl.hu.ipass.corne.competitiesysteem.domeinlaag.Gebruiker;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static java.lang.System.out;

@Path("/team")
public class TeamResource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)

    public Response gebruikersnaam(@FormParam("team") String password) {
        return Response.ok().build();
    }


}
