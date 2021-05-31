package nl.hu.ipass.corne.competitiesysteem.Listeners;

import nl.hu.ipass.corne.competitiesysteem.domeinlaag.Club;
import nl.hu.ipass.corne.competitiesysteem.domeinlaag.Gebruiker;
import nl.hu.ipass.corne.competitiesysteem.domeinlaag.Team;
import nl.hu.ipass.corne.competitiesysteem.persistence.PersistanceManager;
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
        Gebruiker testgebruiker = new Gebruiker("1" , "1" , "admin");
        Club testclub = new Club("Chelsea");
        Team testTeam1 = new Team("jo-16-1" , "jongens");
        Team testTeam2 = new Team("jo-16-2" , "jongens");
        testclub.voegTeamToe(testTeam1);
        testclub.voegTeamToe(testTeam2);


        for (Gebruiker g: Gebruiker.getAlleGebruikers()) {
            out.println(g);
        }
        try {
            PersistanceManager.loadWorldFromAzure();
            out.println("World loaded from Azure...");


        } catch (Exception e) {
            out.println("Error loading world: " + e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            PersistanceManager.saveWorldToAzure();
            out.println("World saved to Azure...");
        } catch (Exception e) {
            out.println("Error saving world: " + e.getMessage());
        }

        Schedulers.shutdownNow();
        HttpResources.disposeLoopsAndConnectionsLater(Duration.ZERO, Duration.ZERO).block();
    }
}
