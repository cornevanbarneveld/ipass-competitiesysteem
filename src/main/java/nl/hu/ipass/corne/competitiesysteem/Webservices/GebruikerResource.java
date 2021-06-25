package nl.hu.ipass.corne.competitiesysteem.Webservices;

import nl.hu.ipass.corne.competitiesysteem.domeinlaag.Gebruiker;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.lang.System.out;

@Path("/Gebruiker")
public class GebruikerResource {



    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response registreerGebruiker(@FormParam("username") String username,
                                   @FormParam("password") String password) {


        if (password == null || username == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        for (Gebruiker g: Gebruiker.getAlleGebruikers()) {
            if (g.getName().equals(username)) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }


        Gebruiker gebruiker = new Gebruiker(username , password , "toeschouwer");
        return Response.ok(gebruiker).build();


    }

    @POST
    @Path("/scheidsrechter")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registreerScheidsRechter(@FormParam("username") String username,
                                        @FormParam("password") String password) {


        if (password == null || username == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        for (Gebruiker g: Gebruiker.getAlleGebruikers()) {
            if (g.getName().equals(username)) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }


        Gebruiker gebruiker = new Gebruiker(username , password , "scheidsrechter");
        return Response.ok(gebruiker).build();


    }

    @GET
    @Path("/role")
    @Produces(MediaType.APPLICATION_JSON)
    public String getrole(@Context SecurityContext sc) {

        if (sc.getUserPrincipal() instanceof Gebruiker) {
            Gebruiker gebruiker = (Gebruiker) sc.getUserPrincipal();

            JsonObjectBuilder job = Json.createObjectBuilder()
                    .add("type" , gebruiker.getType());

            return job.build().toString();

        }

        return Json.createObjectBuilder().add("error" , "er ging iets mis").build().toString();





    }

}
