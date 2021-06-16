package nl.hu.ipass.corne.competitiesysteem.Webservices;

import nl.hu.ipass.corne.competitiesysteem.domeinlaag.Gebruiker;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.lang.System.out;

@Path("/login")
public class InlogResource {



    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response gebruikersnaam(@FormParam("username") String username,
                                   @FormParam("password") String password) {

        out.println("aaaa");


        for (Gebruiker g: Gebruiker.getAlleGebruikers()) {
            out.println(g.getName());
            if(username.equals(g.getName()) && password.equals(g.getWachtwoord())) {
                return Response.ok().build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();

    }
}
