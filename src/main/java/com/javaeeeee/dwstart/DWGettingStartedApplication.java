package com.javaeeeee.dwstart;

import com.javaeeeee.dwstart.model.*;

import com.javaeeeee.dwstart.resources.LoginResource;
import com.javaeeeee.dwstart.resources.StudentResource;
import com.javaeeeee.dwstart.resources.SubjectResource;
import com.javaeeeee.dwstart.resources.TeacherResource;
import com.javaeeeee.dwstart.resources.UploadResource;
import com.javaeeeee.dwstart.resources.WorkResource;
import io.dropwizard.Application;
import io.dropwizard.cli.ServerCommand;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import javax.ws.rs.client.Client;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class DWGettingStartedApplication
        extends Application<DWGettingStartedConfiguration> {

    private final HibernateBundle<DWGettingStartedConfiguration> hibernateBundle
            = new HibernateBundle<DWGettingStartedConfiguration>(
                    Student.class, Work.class, Subject.class, Teacher.class, Upload.class
            ) {

        @Override
        public DataSourceFactory getDataSourceFactory(
                DWGettingStartedConfiguration configuration
        ) {
            return configuration.getDataSourceFactory();
        }

    };

    public static void main(final String[] args) throws Exception {
        new DWGettingStartedApplication().run(args);
    }

    @Override
    public String getName() {
        return "DWGettingStarted";
    }

    @Override
    public void initialize(
            final Bootstrap<DWGettingStartedConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(new ViewBundle());
        bootstrap.addCommand(new ServerCommand<>(this));
        //bootstrap.addBundle(new AssetsBundle("/assets", "/assets"));
    }
    
    private void configureCors(Environment environment) {
        FilterRegistration.Dynamic filter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        filter.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        filter.setInitParameter("allowedHeaders", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
        filter.setInitParameter("allowCredentials", "true");
    }

    @Override
    public void run(final DWGettingStartedConfiguration configuration,
            final Environment environment) {
        //Create Jersey client.
        final Client client = new JerseyClientBuilder(environment)
                .using(configuration.getJerseyClientConfiguration())
                .build(getName());
        //Register authenticator.
        environment.jersey().register(MultiPartFeature.class);
        
        configureCors(environment);

        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new LoginResource());
        
        environment.jersey().register(new StudentResource(hibernateBundle.getSessionFactory()));
        environment.jersey().register(new TeacherResource(hibernateBundle.getSessionFactory()));
        environment.jersey().register(new SubjectResource(hibernateBundle.getSessionFactory()));
        environment.jersey().register(new WorkResource(hibernateBundle.getSessionFactory()));
        environment.jersey().register(new UploadResource(hibernateBundle.getSessionFactory()));
        
        /*//Register a resource using Jersey client.
        environment.jersey().register(
                new ConverterResource(
                        client,
                        configuration.getApiURL(),
                        configuration.getApiKey())
        );*/
    }

}
