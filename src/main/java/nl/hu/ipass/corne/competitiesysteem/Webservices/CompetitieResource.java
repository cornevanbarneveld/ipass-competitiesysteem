package nl.hu.ipass.corne.competitiesysteem.Webservices;

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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Path("/competitie")
public class CompetitieResource {

    @GET
    @RolesAllowed({"toeschouwer","admin" , "scheidsrechter"})
    @Path("{club}/{team}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompetities(@PathParam("club") String club, @PathParam("team")  String team) {

        Club c = Club.getClubOpNaam(club);
        if (c != null) {
            Team t = Team.getTeamOpNaamEnClub(team, club);
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
    @RolesAllowed({"toeschouwer","admin" , "scheidsrechter"})
    @Path("{club}/{team}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getStandElkTeam(@PathParam("club") String club,
                                 @PathParam("team") String team,
                                 @FormParam("competitie") String competitie) {



        Club club1 = Club.getClubOpNaam(club);
        if (club1 != null) {
            Team t = Team.getTeamOpNaamEnClub(team, club);
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
    @RolesAllowed("admin")
    @Path("/maak/{competitienaam}/{klasse}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response maakCompetitieTeamsEnClub(@PathParam("competitienaam") String competitienaam,
                                             @PathParam("klasse") String klasse) {


        Date d=new Date();

        if (competitienaam != null && klasse != null) {
            Competitie competitie = new Competitie(competitienaam , klasse , d.getYear() + 1900, d.getYear() + 1901);

            return Response.ok(competitie.getNummer()).build();
        }


        return Response.status(Response.Status.NOT_FOUND).build();

    }

    @POST
    @RolesAllowed("admin")
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
    @RolesAllowed("admin")
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

        System.out.println(thuisclub);
        System.out.println(uitclub);
        System.out.println(veld);
        System.out.println(thuisteam);
        System.out.println(uitteam);


        Team thuisTeam1 = Team.getTeamOpNaamEnClub(thuisteam , thuisclub);

        Team uitTeam1 = Team.getTeamOpNaamEnClub(uitteam, uitclub);

        System.out.println(thuisTeam1);
        System.out.println(uitTeam1);


        System.out.println(dateTime);

        if (thuisTeam1 != null && uitTeam1 != null && !thuisTeam1.equals(uitTeam1)) {



            for (Competitie comp : Competitie.getCompetities()) {
                if (comp.getNummer() == nummer) {
                    for (Wedstrijd wed : comp.getAlleWedstrijden()) {
                        if (wed.getThuisTeam() != null && wed.getUitTeam() != null && wed.getDatum() != null) {
                            System.out.println(wed.getDatum());

                            System.out.println("thuis " +wed.getThuisTeam().getNaam());
                            System.out.println("thuis1 " + thuisTeam1.getNaam());
                            if (wed.getThuisTeam().equals(thuisTeam1)) {
                                System.out.println("thuissss");
                            }


                            System.out.println( "uit" +wed.getUitTeam().getNaam());
                            System.out.println( "uit1" + uitTeam1.getNaam());

                            if (wed.getUitTeam().equals(uitTeam1)) {
                                System.out.println("uittt");
                            }



                            if (wed.getThuisTeam().equals(thuisTeam1) && wed.getUitTeam().equals(uitTeam1) && wed.getDatum().equals(dateTime)) {
                                System.out.println("error");
                                return Response.status(Response.Status.NOT_FOUND).build();
                            }
                        }

                    }
                    Wedstrijd wedstrijd = new Wedstrijd(dateTime, veld,uitTeam1 ,thuisTeam1 );
                    comp.AddWedstrijd(wedstrijd);


                    System.out.println("gemaakt");

                    return Response.ok().build();


                }
            }
        }







        System.out.println("error2");
        return Response.status(Response.Status.NOT_FOUND).build();

    }




}

