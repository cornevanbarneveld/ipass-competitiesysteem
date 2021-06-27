package nl.hu.ipass.corne.competitiesysteem.Webservices;

import com.azure.core.annotation.Patch;
import nl.hu.ipass.corne.competitiesysteem.domeinlaag.Club;
import nl.hu.ipass.corne.competitiesysteem.domeinlaag.Competitie;
import nl.hu.ipass.corne.competitiesysteem.domeinlaag.Team;
import nl.hu.ipass.corne.competitiesysteem.domeinlaag.Wedstrijd;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Path("/wedstrijd")
public class WedstrijdResource {

    @POST
    @Path("{club}/{team}")
    @RolesAllowed({"toeschouwer","admin" , "scheidsrechter"})
    @Produces(MediaType.APPLICATION_JSON)
    public String getWedstrijdenvoorComp(@PathParam("club") String club,
                                   @PathParam("team") String team,
                                   @FormParam("competitie") String competitie) {



        Club club1 = Club.getClubOpNaam(club);
        if (club1 != null) {
            Team t = Team.getTeamOpNaamEnClub(team , club);
            if (t != null) {
                ArrayList<Competitie> competities = new ArrayList<>(Competitie.getCompetitiesVoorTeam(t));
                for (Competitie comp: competities) {
                    if (comp.getNaam().equals(competitie)) {

                        JsonArrayBuilder jab = Json.createArrayBuilder();
                        for (Wedstrijd w: comp.getAlleWedstrijden()) {

                            JsonObjectBuilder job1 = Json.createObjectBuilder();


                            JsonObjectBuilder job = Json.createObjectBuilder()
                                    .add("thuisclub" ,w.getThuisTeam().getClub().Getnaam())
                                    .add("uitclub" , w.getUitTeam().getClub().Getnaam())
                                    .add("datum" ,w.getDatum().toString())
                                    .add( "uitTeam" , w.getUitTeam().getNaam())
                                    .add( "thuisTeam" , w.getThuisTeam().getNaam())
                                    .add("gespeeld" , w.getGespeeld())
                                    .add( "scoreThuisTeam" , w.getScoreThuisTeam())
                                    .add( "scoreUitTeam" , w.getScoreUitTeam())
                                    .add("nummer" , comp.getNummer());


                            jab.add(job);
                        }

                        return jab.build().toString();
                    }
                }
            }
        }


        Map<String, String> messages = new HashMap<>();
        messages.put("error" , "geen competities gevonden");
        return Json.createObjectBuilder().add("error" , "er ging iets mis").build().toString();

    }

    @POST
    @Path("/vervangDatum")
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response vervangDatum(@FormParam("thuisclub") String thuisclub,
                                 @FormParam("thuisteam")String thuisteam,
                                 @FormParam("uitclub") String uitclub,
                                 @FormParam("uitteam")String uitteam,
                                 @FormParam("datum") String datum,
                                 @FormParam("nieuwedatum") String nieuwedatum
                                 ) {



        LocalDateTime dateTime = LocalDateTime.parse(datum);
        LocalDateTime nieuweDateTime = LocalDateTime.parse(nieuwedatum);







        Team thuisTeam1 = Team.getTeamOpNaamEnClub(thuisteam, thuisclub);
        Team uitTeam1 = Team.getTeamOpNaamEnClub(uitteam, uitclub);
        if (thuisTeam1 != null && uitTeam1 != null) {


            for (Wedstrijd w : Wedstrijd.getAlleWedstrijden()) {
                if (w.getThuisTeam() != null && w.getUitTeam() != null && w.getDatum() != null) {
                    if (w.getThuisTeam().equals(thuisTeam1) && w.getUitTeam().equals(uitTeam1) && (w.getDatum().toString().equals(dateTime.toString()))) {
                        w.verVangDatum(nieuweDateTime);
                        return Response.ok(nieuweDateTime).build();
                    }
                }

            }
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }


    @POST
    @Path("/setUitSlag")
    @RolesAllowed("scheidsrechter")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setScore(@FormParam("thuisclub") String thuisclub,
                                 @FormParam("thuisteam")String thuisteam,
                                 @FormParam("uitclub") String uitclub,
                                 @FormParam("uitteam")String uitteam,
                                 @FormParam("datum") String datum,
                                 @FormParam("scoreThuisteam") int scoreThuisteam,
                                 @FormParam("scoreUitteam") int scoreUitteam,
                                 @FormParam("nummer") int nummer)  {



        LocalDateTime dateTime = LocalDateTime.parse(datum);




        Team thuisTeam1 = Team.getTeamOpNaamEnClub(thuisteam , thuisclub);
        Team uitTeam1 = Team.getTeamOpNaamEnClub(uitteam, uitclub);





        if (thuisTeam1 != null && uitTeam1 != null) {

            for (Wedstrijd w: Wedstrijd.getAlleWedstrijden()) {




                if (w.getUitTeam() != null && w.getThuisTeam() != null) {

                    if (w.getThuisTeam().equals(thuisTeam1) && w.getUitTeam().equals(uitTeam1) && (w.getDatum().toString().equals(dateTime.toString() )) )  {

                        w.setScoreThuisTeam(scoreThuisteam);

                        w.setScoreUitTeam(scoreUitteam);

                        w.setGespeeld();


                        for (Competitie comp : Competitie.getCompetities()) {
                            if (comp.getNummer() == nummer) {
                                comp.AddWedstrijd(w);
                                return Response.ok().build();
                            }
                        }


                    }
                }


            }
        }


        return Response.status(Response.Status.NOT_FOUND).build();

    }


}
