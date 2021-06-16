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

import static java.lang.System.out;


@Path("/spelers")
public class SpelersResource {

    @GET
    @Path("{club}/{team}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlleSpelers(@PathParam("club") String club, @PathParam("team")  String team) {




        ArrayList<Team> teams = new ArrayList<Team>(Team.getAlleTeams());

        for (Club c: Club.getalleClubs()) {
            out.println(c.Getnaam());
            for (Team t :c.getTeams()) {
                out.println(t.getNaam());
                for (Speler s: t.getSpelers()) {
                    out.println(s.getNaam());
                }
            }

        }



        for (Team t: teams) {
            if(club.equals(t.getClub().Getnaam()) && team.equals(t.getNaam())) {
                if (t.getSpelers().toArray().length != 0) {

                    System.out.println(t.getSpelers());
                    System.out.println((t.getSpelers()).toArray().length);

                    ArrayList<String> spelersnamen = new ArrayList<String >();
                    for (Speler spel: t.getSpelers()) {
                        spelersnamen.add(spel.getNaam());
                    }
                    return Response.ok(spelersnamen).build();
                }

            }
        }

        Map<String, String> messages = new HashMap<>();
        messages.put("error" , "geen spelers gevonden");
        return Response.status(Response.Status.NOT_FOUND).entity(messages).build();
    }


}
