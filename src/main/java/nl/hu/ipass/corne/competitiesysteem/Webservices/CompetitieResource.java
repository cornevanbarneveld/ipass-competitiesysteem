package nl.hu.ipass.corne.competitiesysteem.Webservices;

import nl.hu.ipass.corne.competitiesysteem.domeinlaag.Club;
import nl.hu.ipass.corne.competitiesysteem.domeinlaag.Competitie;
import nl.hu.ipass.corne.competitiesysteem.domeinlaag.Team;
import nl.hu.ipass.corne.competitiesysteem.domeinlaag.Wedstrijd;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Path("/competitie")
public class CompetitieResource {

    @GET
    @Path("{club}/{team}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompetities(@PathParam("club") String club, @PathParam("team")  String team) {

        Club c = Club.getClubOpNaam(club);
        if (c != null) {
            Team t = Team.getTeamOpNaamEnClub(team, c);
            if (t != null) {
                ArrayList<Competitie> competities = new ArrayList<Competitie>();
                competities.addAll(Competitie.getCompetitiesVoorTeam(t));
                if (competities.toArray().length != 0) {
                    ArrayList<String> competitienamen = new ArrayList<String>();
                    for (Competitie comp: competities) {
                        competitienamen.add(comp.getNaam());
                    }
                    return Response.ok(competitienamen).build();
                }

            }
        }

        Map<String, String> messages = new HashMap<>();
        messages.put("error" , "geen competities gevonden");
        return Response.status(Response.Status.NOT_FOUND).entity(messages).build();
    }

    @POST
    @Path("{club}/{team}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getStandElkTeam(@PathParam("club") String club,
                                 @PathParam("team") String team,
                                 @FormParam("competitie") String competitie) {



        Club club1 = Club.getClubOpNaam(club);
        if (club1 != null) {
            Team t = Team.getTeamOpNaamEnClub(team, club1);
            if (t != null) {
                ArrayList<Competitie> competities = new ArrayList<>(Competitie.getCompetitiesVoorTeam(t));
                for (Competitie comp : competities) {
                    if (comp.getNaam().equals(competitie)) {

                        JsonArrayBuilder jab = Json.createArrayBuilder();
                        for (Team team3 : comp.getTeams()) {
                            String clubEnTeamnaam = (team3.getClub().Getnaam() + " " + team3.getNaam());
                            int gespeeld = 0;
                            int punten = 0;
                            int gewonnen = 0;
                            int gelijk = 0;
                            int verloren = 0;
                            int dptv = 0;
                            int dptt = 0;
                            int doelsaldo = 0;

                            for (Wedstrijd w: comp.getAlleWedstrijden()) {
                                if (w.getGespeeld()) {
                                    if (w.getThuisTeam().equals(team3)) {
                                        if (w.getScoreThuisTeam() > w.getScoreUitTeam()) {
                                            gespeeld += 1;
                                            punten += 3;
                                            gewonnen += 1;
                                            dptt += w.getScoreUitTeam();
                                            dptv += w.getScoreThuisTeam();


                                        } if (w.getScoreThuisTeam() == w.getScoreUitTeam()) {
                                            gespeeld += 1;
                                            punten += 1;
                                            gelijk += 1;
                                            dptt += w.getScoreUitTeam();
                                            dptv += w.getScoreThuisTeam();

                                        } if (w.getScoreThuisTeam() < w.getScoreUitTeam()) {
                                            gespeeld += 1;
                                            verloren += 1;
                                            dptt += w.getScoreUitTeam();
                                            dptv += w.getScoreThuisTeam();

                                        }
                                    } if (w.getUitTeam().equals(team3)) {

                                        if (w.getScoreThuisTeam() < w.getScoreUitTeam()) {
                                            gespeeld += 1;
                                            punten += 3;
                                            gewonnen += 1;
                                            dptt += w.getScoreThuisTeam();
                                            dptv += w.getScoreUitTeam();


                                        } if (w.getScoreThuisTeam() == w.getScoreUitTeam()) {
                                            gespeeld += 1;
                                            punten += 1;
                                            gelijk += 1;
                                            dptt += w.getScoreThuisTeam();
                                            dptv += w.getScoreUitTeam();

                                        } if (w.getScoreThuisTeam() > w.getScoreUitTeam()) {
                                            gespeeld += 1;
                                            verloren += 1;
                                            dptt += w.getScoreThuisTeam();
                                            dptv += w.getScoreUitTeam();

                                        }
                                    }
                                }


                            }
                            doelsaldo = dptv -dptt;
                            System.out.println(comp.getNummer());
                            JsonObjectBuilder job = Json.createObjectBuilder()
                                    .add("clubEnTeamnaam", clubEnTeamnaam)
                                    .add("gespeeld", gespeeld)
                                    .add("punten", punten)
                                    .add("gewonnen", gewonnen)
                                    .add("gelijk", gelijk)
                                    .add("punten", punten)
                                    .add("verloren", verloren)
                                    .add("dptt", dptt)
                                    .add("dptv", dptv)
                                    .add("doelsaldo", doelsaldo)
                                    .add("nummer", comp.getNummer());
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
    @Path("/maak/{competitienaam}/{klasse}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompetitieTeamsEnClub(@PathParam("competitienaam") String competitienaam,
                                             @PathParam("klasse") String klasse) {


        if (competitienaam != null && klasse != null) {
            Competitie competitie = new Competitie(competitienaam , klasse , 2020 , 2021);

            return Response.ok(competitie.getNummer()).build();
        }


        return Response.status(Response.Status.NOT_FOUND).build();

    }

    @POST
    @Path("/voegtoe/{nummer}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response VoegTeamToe(@PathParam("nummer") int nummer,
                                @FormParam("club") String club,
                                @FormParam("team")String team) {



        for (Club c: Club.getalleClubs()) {
            if (club.equals(c.Getnaam())) {
                for (Team t : c.getTeams()) {
                    if (t.getNaam().equals(team)) {
                        for (Competitie comp : Competitie.getCompetities()) {
                            if (comp.getNummer() == nummer) {
                                if(!comp.getTeams().contains(t)) {
                                    comp.AddTeam(t);
                                    return Response.ok().build();
                                }
                            }
                        }
                    }
                }
            }
        }





        return Response.status(Response.Status.NOT_FOUND).build();

    }

    @POST
    @Path("/voegwedstrijdtoe/{nummer}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response VoegWedstrijdToe(@PathParam("nummer") int nummer,
                                     @FormParam("thuisclub") String thuisclub,
                                     @FormParam("thuisteam")String thuisteam,
                                     @FormParam("uitclub") String uitclub,
                                     @FormParam("uitteam")String uitteam,
                                     @FormParam("datum") String datum,
                                     @FormParam("veld") int veld) {



        LocalDateTime dateTime = LocalDateTime.parse(datum);


        Club thuisclub1 = Club.getClubOpNaam(thuisclub);
        Club uitClub1 = Club.getClubOpNaam(uitclub);

        if (thuisclub1 != null && uitClub1 != null & veld != 0 ) {
            Team thuisTeam1 = Team.getTeamOpNaamEnClub(thuisteam , thuisclub1);
            Team uitTeam1 = Team.getTeamOpNaamEnClub(uitteam, uitClub1);
            if (thuisTeam1 != null && uitTeam1 != null && !thuisTeam1.equals(uitTeam1) ) {
                Wedstrijd wedstrijd = new Wedstrijd(dateTime, veld, thuisTeam1, uitTeam1);


                for (Competitie comp : Competitie.getCompetities()) {
                    if (comp.getNummer() == nummer) {
                        for (Wedstrijd wed : comp.getAlleWedstrijden()) {
                            if (wed.equals(wedstrijd)) {
                                return Response.status(Response.Status.NOT_FOUND).build();
                            }
                        }

                        comp.AddWedstrijd(wedstrijd);
                        return Response.ok().build();


                    }
                }
            }


        }





        return Response.status(Response.Status.NOT_FOUND).build();

    }




}

