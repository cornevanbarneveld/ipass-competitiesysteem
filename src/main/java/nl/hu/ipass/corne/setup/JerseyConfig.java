package nl.hu.ipass.corne.setup;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import javax.ws.rs.ApplicationPath;




@ApplicationPath("restservices")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("nl.hu.bep.competitiesysteem.webservices" , "nl.hu.bep.competitiesysteem.security");
        //register(RolesAllowedDynamicFeature.class);
    }
}
