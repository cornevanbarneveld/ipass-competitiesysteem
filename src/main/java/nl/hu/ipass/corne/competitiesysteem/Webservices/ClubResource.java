package nl.hu.ipass.corne.competitiesysteem.Webservices;

import nl.hu.ipass.corne.competitiesysteem.domeinlaag.Club;
import nl.hu.ipass.corne.competitiesysteem.domeinlaag.Team;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/club")
public class ClubResource {
    @POST
    @Path("/clubmaak/{club}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompetitieTeamsEnClub(@PathParam("club") String club) {

        boolean waarde = true;
        for (Club c: Club.getalleClubs()) {
            if (c.Getnaam().equals(club)) {
                waarde = false;
            }
        }
        if (waarde) {
            Club club1 = new Club(club);
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();

    }
}
