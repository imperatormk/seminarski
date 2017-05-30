package com.fikt.seminarski;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthFilter;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.PolymorphicAuthDynamicFeature;
import io.dropwizard.auth.PolymorphicAuthValueFactoryProvider;
import io.dropwizard.auth.chained.ChainedAuthFilter;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import javax.ws.rs.client.Client;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import com.fikt.seminarski.auth.CustomAuthFilter;
import com.fikt.seminarski.auth.CustomAuthenticator;
import com.fikt.seminarski.auth.OptionalAuthFilter;
import com.fikt.seminarski.auth.OptionalAuthenticator;
import com.fikt.seminarski.model.*;
import com.fikt.seminarski.persistence.TokenDAO;
import com.fikt.seminarski.persistence.UserDAO;
import com.fikt.seminarski.persistence.implementation.TokenDAOImpl;
import com.fikt.seminarski.persistence.implementation.UserDAOImpl;
import com.fikt.seminarski.provider.RuntimeExceptionMapper;
import com.fikt.seminarski.resources.LoginResource;
import com.fikt.seminarski.resources.MainResource;
import com.fikt.seminarski.resources.StudentResource;
import com.fikt.seminarski.resources.SubjectResource;
import com.fikt.seminarski.resources.TeacherResource;
import com.fikt.seminarski.resources.UploadResource;
import com.fikt.seminarski.resources.WorkResource;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;
import java.util.List;

public class MainApplication
        extends Application<MainConfiguration> {

    private final HibernateBundle<MainConfiguration> hibernateBundle
            = new HibernateBundle<MainConfiguration>(
                    Student.class, Work.class, Subject.class, Teacher.class, Upload.class, Token.class, User.class, OptionalUser.class
            ) {

        @Override
        public DataSourceFactory getDataSourceFactory(
                MainConfiguration configuration
        ) {
            return configuration.getDataSourceFactory();
        }

    };

    public static void main(final String[] args) throws Exception {
        new MainApplication().run(args);
    }

    @Override
    public String getName() {
        return "Seminarski";
    }

    @Override
    public void initialize(
            final Bootstrap<MainConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(new ViewBundle());
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
    public void run(final MainConfiguration configuration,
            final Environment environment) {
        //Create Jersey client.
        final Client client = new JerseyClientBuilder(environment)
                .using(configuration.getJerseyClientConfiguration())
                .build(getName());
        //Register authenticator.
        environment.jersey().register(MultiPartFeature.class);
        
        configureCors(environment);

        environment.jersey().register(new MainResource(hibernateBundle.getSessionFactory()));
        environment.jersey().register(new LoginResource(hibernateBundle.getSessionFactory()));
        
        environment.jersey().register(new StudentResource(hibernateBundle.getSessionFactory()));
        environment.jersey().register(new TeacherResource(hibernateBundle.getSessionFactory()));
        environment.jersey().register(new SubjectResource(hibernateBundle.getSessionFactory()));
        environment.jersey().register(new WorkResource(hibernateBundle.getSessionFactory()));
        environment.jersey().register(new UploadResource(hibernateBundle.getSessionFactory()));
        
        UserDAO userDAO = new UserDAOImpl(hibernateBundle.getSessionFactory());
        TokenDAO tokenDAO = new TokenDAOImpl(hibernateBundle.getSessionFactory(), userDAO);
        
        setupAuth(environment, tokenDAO, userDAO);
        environment.jersey().register(new RuntimeExceptionMapper());
    }
    
  //Setup custom auth
    private void setupAuth(Environment environment, TokenDAO tokenDAO, UserDAO userDAO) {
        CustomAuthenticator authenticatorCus = new UnitOfWorkAwareProxyFactory(hibernateBundle)
          .create(CustomAuthenticator.class, new Class<?>[]{TokenDAO.class, UserDAO.class}, new Object[]{tokenDAO, userDAO});
        
        OptionalAuthenticator authenticatorOpt = new UnitOfWorkAwareProxyFactory(hibernateBundle)
                .create(OptionalAuthenticator.class, new Class<?>[]{TokenDAO.class, UserDAO.class}, new Object[]{tokenDAO, userDAO});
        
        CustomAuthFilter filterCus = new CustomAuthFilter(authenticatorCus);
        OptionalAuthFilter filterOpt = new OptionalAuthFilter(authenticatorOpt);
        
        //List<AuthFilter> filters = Lists.newArrayList(CustomAuthFilter.class, CustomAuthFilter.class);
        //environment.jersey().register(new AuthDynamicFeature(new ChainedAuthFilter(filters)));

        //environment.jersey().register(new AuthDynamicFeature(filter));
        //environment.jersey().register(new AuthDynamicFeature(filterCus));
        //environment.jersey().register(new AuthDynamicFeature(filterOpt));
        
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        //environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
        //environment.jersey().register(new AuthValueFactoryProvider.Binder<>(OptionalUser.class));
        
        
        
        final PolymorphicAuthDynamicFeature<User> feature = new PolymorphicAuthDynamicFeature<>(
        	    ImmutableMap.of(
        	        User.class, filterCus,
        	        OptionalUser.class, filterOpt));
        	final AbstractBinder binder = new PolymorphicAuthValueFactoryProvider.Binder<>(
        	    ImmutableSet.of(User.class, OptionalUser.class));

        	environment.jersey().register(feature);
        	environment.jersey().register(binder);
      }
}
