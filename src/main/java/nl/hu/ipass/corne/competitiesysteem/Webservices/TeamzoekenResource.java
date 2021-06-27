package nl.hu.ipass.corne.competitiesysteem.Webservices;

import nl.hu.ipass.corne.competitiesysteem.domeinlaag.*;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



@Path("/teamzoek")
public class TeamzoekenResource {

    @GET
    @RolesAllowed({"toeschouwer" , "admin" , "scheidsrechter"})
    @Path("{club}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response clubenteams(@PathParam("club") String club) {
        for (Club clb : Club.getalleClubs()) {
            if (club.equals(clb.Getnaam())) {
                ArrayList<String> teamnamen = new ArrayList<String>();
                for (Team t : clb.getTeams()) {
                    if (!teamnamen.contains(t.getNaam())) {
                        teamnamen.add(t.getNaam());
                    }
                }
                return Response.ok(teamnamen).build();
            }
        }
        Map<String, String> messages = new HashMap<>();
        messages.put("error", "geen clubs gevonden");
        return Response.status(Response.Status.NOT_FOUND).entity(messages).build();
    }

    @POST
    @RolesAllowed({"toeschouwer","admin" , "scheidsrechter"})
    @Path("/team")
    @Produces(MediaType.APPLICATION_JSON)
    public Response spelersophalen(@FormParam("team") String team) {
        for (Team t : Team.getAlleTeams()) {
            if (t.getNaam().equals(team)) {
                ArrayList<String> spelers = new ArrayList<String>();
                for (Speler s : t.getSpelers()) {
                    spelers.add(s.getNaam());
                }
                return Response.ok(spelers).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @RolesAllowed({"toeschouwer","admin" , "scheidsrechter"})
    @Path("/getteamenclub/{club}/{team}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompetitieTeamsEnClub(@PathParam("club") String club,
                                   @PathParam("team") String team,
                                   @FormParam("competitie") String competitie) {


        Club club1 = Club.getClubOpNaam(club);
        if (club1 != null) {
            Team t = Team.getTeamOpNaamEnClub(team, club);
            if (t != null) {
                ArrayList<Competitie> competities = new ArrayList<>(Competitie.getCompetitiesVoorTeam(t));
                for (Competitie comp : competities) {
                    if (comp.getNaam().equals(competitie)) {
                        ArrayList<String> teamnamenenclubnaam = new ArrayList<String>();
                        for (Team tm: comp.getTeams()) {
                            String clubnaam = tm.getClub().Getnaam();
                            teamnamenenclubnaam.add(clubnaam +" " + tm.getNaam());
                        }
                        return Response.ok(teamnamenenclubnaam).build();
                    }
                }
            }
        }


        return Response.status(Response.Status.NOT_FOUND).build();

    }

    @POST
    @RolesAllowed("admin")
    @Path("/teammaak/{club}/{team}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response maakTeam(@PathParam("club") String club,
                                             @PathParam("team") String team) {


        for (Club c: Club.getalleClubs()) {
            if (c.Getnaam().equals(club)) {
                boolean waarde = true;
                for (Team t: c.getTeams()) {

                    if (t.getNaam().equals(team)) {
                        waarde = false;
                        break;
                    }
                }
                if (waarde) {
                    Team team1 = new Team(team);
                    c.voegTeamToe(team1);
                    return Response.ok().build();
                }
            }
        }




        return Response.status(Response.Status.NOT_FOUND).build();

    }

}
