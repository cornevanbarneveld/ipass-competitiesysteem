package nl.hu.ipass.corne.competitiesysteem.Listeners;

import nl.hu.ipass.corne.competitiesysteem.persistence.*;
import nl.hu.ipass.corne.competitiesysteem.security.Gebruiker;
import reactor.core.scheduler.Schedulers;
import reactor.netty.http.HttpResources;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.Duration;

import static java.lang.System.out;

@WebListener
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        //Gebruiker gebruiker = new Gebruiker("sjaak1" , "wacht" , "admin");


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
