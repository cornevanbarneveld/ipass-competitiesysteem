package nl.hu.ipass.corne.competitiesysteem.Listeners;

import nl.hu.ipass.corne.competitiesysteem.domeinlaag.*;
import nl.hu.ipass.corne.competitiesysteem.persistence.*;
import reactor.core.scheduler.Schedulers;
import reactor.netty.http.HttpResources;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static java.lang.System.out;

@WebListener
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        Gebruiker gebruiker = new Gebruiker("sjaak1" , "wacht" , "admin");

        Club club1 = new Club("Barcelona");
        Club club2 = new Club("Real Madrid");
        Club club3 = new Club("Atletico Madrid");

        Team team = new Team("mannen-1");
        Team team1 = new Team("mannen-2");
        Team team2 = new Team("jo-16-1");
        Team team3 = new Team("vr-14-1");
        Team team4 = new Team("mannen1");
        Team team5 = new Team("mannen1");

        Speler speler1 = new Speler("Messi");
        Speler speler2 = new Speler("Dembele");
        Speler speler3 = new Speler("Ter Stegen");

        Speler speler4 = new Speler("Benzema");
        Speler speler5 = new Speler("Valverde");
        Speler speler6 = new Speler("Courtois");

        Speler speler7 = new Speler("suarez");
        Speler speler8 = new Speler("felix");
        Speler speler9 = new Speler("oblak");

        team.VoegspelerToe(speler1);
        team.VoegspelerToe(speler2);
        team.VoegspelerToe(speler3);

        team4.VoegspelerToe(speler4);
        team4.VoegspelerToe(speler5);
        team4.VoegspelerToe(speler6);

        team5.VoegspelerToe(speler7);
        team5.VoegspelerToe(speler8);
        team5.VoegspelerToe(speler9);

        club1.voegTeamToe(team);
        club1.voegTeamToe(team1);
        club1.voegTeamToe(team2);
        club1.voegTeamToe(team3);

        club2.voegTeamToe(team4);

        club3.voegTeamToe(team5);

        Competitie competitie = new Competitie("beker" , "Tweede Klasse" , 2020 , 2021);

        competitie.AddTeam(team);
        competitie.AddTeam(team4);
        competitie.AddTeam(team5);

        LocalDateTime date1 = LocalDateTime.of(2021, 1 , 4 , 9 , 30 , 0);
        LocalDateTime date2 = LocalDateTime.of(2021, 11 , 20 , 9 , 30 , 0);
        Wedstrijd wedstrijd = new Wedstrijd(date1 , 1 , team, team4 );
        Wedstrijd wedstrijd1 = new Wedstrijd(date2 , 1 , team5, team4 );
        Wedstrijd wedstrijd2 = new Wedstrijd(date1 , 1 , team4, team );

        competitie.AddWedstrijd(wedstrijd);
        competitie.AddWedstrijd(wedstrijd1);
        competitie.AddWedstrijd(wedstrijd2);




        try { PersistanceGebruiker.loadWorldFromAzure();
        } catch (Exception e) {
            out.println("Error loading world:GEBRUIKER " + e.getMessage());
        }
        try { PersistanceSpelers.loadWorldFromAzure();
        } catch (Exception e) {
            out.println("Error loading world:SPELERS " + e.getMessage());
        }
        try { PersistanceTeams.loadWorldFromAzure();
        } catch (Exception e) {
            out.println("Error loading world:TEAMS " + e.getMessage());
        }
        try { PersistanceClubs.loadWorldFromAzure();
        } catch (Exception e) {
            out.println("Error loading world:CLUB " + e.getMessage());
        }
        try { PersistanceCompetitie.loadWorldFromAzure();
        } catch (Exception e) {
            out.println("Error loading world:COMP " + e.getMessage());
        }
        try { PersistanceWedstrijden.loadWorldFromAzure();
        } catch (Exception e) {
            out.println("Error loading world:WED " + e.getMessage());
        }

    }



    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try { PersistanceGebruiker.saveWorldToAzure();
        } catch (Exception e) {
            out.println("Error saving world:GEBR " + e.getMessage());
        }

        try { PersistanceTeams.saveWorldToAzure();
        } catch (Exception e) {
            out.println("Error saving world:TEAM " + e.getMessage());
        }

        try { PersistanceSpelers.saveWorldToAzure();
        } catch (Exception e) {
            out.println("Error saving world:SPELER " + e.getMessage());
        }

        try {PersistanceClubs.saveWorldToAzure();
        } catch (Exception e) {
            out.println("Error saving world:CLUB " + e.getMessage());
        }

        try { PersistanceCompetitie.saveWorldToAzure();
        } catch (Exception e) {
            out.println("Error saving world:COMP " + e.getMessage());
        }
        try {
            PersistanceWedstrijden.saveWorldToAzure();
        } catch (Exception e) {
            out.println("Error saving world:WED " + e.getMessage());
        }

        Schedulers.shutdownNow();
        HttpResources.disposeLoopsAndConnectionsLater(Duration.ZERO, Duration.ZERO).block();
    }
}
