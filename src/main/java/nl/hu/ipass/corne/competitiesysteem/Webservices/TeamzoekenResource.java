package nl.hu.ipass.corne.competitiesysteem.Webservices;

import nl.hu.ipass.corne.competitiesysteem.domeinlaag.Club;
import nl.hu.ipass.corne.competitiesysteem.domeinlaag.Speler;
import nl.hu.ipass.corne.competitiesysteem.domeinlaag.Team;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



@Path("/teamzoek")
public class TeamzoekenResource {

    @GET
    @Path("{club}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response clubenteams(@PathParam("club") String club) {
        for (Club clb: Club.getalleClubs()) {
            if (club.equals(clb.Getnaam())){
                ArrayList<String> teamnamen =  new ArrayList<String>();
                for (Team t: clb.getTeams()) {
                    if (!teamnamen.contains(t.getNaam())) {
                        teamnamen.add(t.getNaam());
                    }
                }
                return Response.ok(teamnamen).build();
            }
        }
        Map<String, String> messages = new HashMap<>();
        messages.put("error" , "geen clubs gevonden");
        return Response.status(Response.Status.NOT_FOUND).entity(messages).build();
    }

    @POST
    @Path("/team")
    @Produces(MediaType.APPLICATION_JSON)
    public Response spelersophalen(@FormParam("team") String team) {
        for (Team t: Team.getAlleTeams()) {
            if (t.getNaam().equals(team)) {
                ArrayList<String> spelers = new ArrayList<String>();
                for (Speler s: t.getSpelers() ) {
                    spelers.add(s.getNaam());
                }
                return Response.ok(spelers).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
