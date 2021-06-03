package nl.hu.ipass.corne.competitiesysteem.Listeners;

import nl.hu.ipass.corne.competitiesysteem.domeinlaag.Club;
import nl.hu.ipass.corne.competitiesysteem.domeinlaag.Gebruiker;
import nl.hu.ipass.corne.competitiesysteem.domeinlaag.Team;
import nl.hu.ipass.corne.competitiesysteem.persistence.PersistanceClubs;
import nl.hu.ipass.corne.competitiesysteem.persistence.PersistanceManager;
import nl.hu.ipass.corne.competitiesysteem.persistence.PersistanceTeams;
import reactor.core.scheduler.Schedulers;
import reactor.netty.http.HttpResources;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.Duration;
import java.time.temporal.TemporalAmount;

import static java.lang.System.out;

@WebListener
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {




        try { PersistanceManager.loadWorldFromAzure();
            out.println("World loaded from Azure...");
        } catch (Exception e) {
            out.println("Error loading world: " + e.getMessage());
        }

        try { PersistanceTeams.loadWorldFromAzure();
            out.println("World loaded from Azure...");
        } catch (Exception e) {
            out.println("Error loading world: " + e.getMessage());
        }

        try { PersistanceClubs.loadWorldFromAzure();
            out.println("World loaded from Azure...");
        } catch (Exception e) {
            out.println("Error loading world: " + e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try { PersistanceManager.saveWorldToAzure();
            out.println("World saved to Azure...");
        } catch (Exception e) {
            out.println("Error saving world: " + e.getMessage());
        }

        try { PersistanceTeams.saveWorldToAzure();
            out.println("World saved to Azure...");
        } catch (Exception e) {
            out.println("Error saving world: " + e.getMessage());
        }

        try {PersistanceClubs.saveWorldToAzure();
            out.println("World saved to Azure...");
        } catch (Exception e) {
            out.println("Error saving world: " + e.getMessage());
        }

        Schedulers.shutdownNow();
        HttpResources.disposeLoopsAndConnectionsLater(Duration.ZERO, Duration.ZERO).block();
    }
}
