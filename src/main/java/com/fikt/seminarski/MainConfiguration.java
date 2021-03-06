package com.fikt.seminarski;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.server.DefaultServerFactory;
import io.dropwizard.server.ServerFactory;

import javax.validation.Valid;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.NotEmpty;

public class MainConfiguration extends Configuration {

    @NotEmpty
    private String login;

    @NotEmpty
    private String password;

    @NotEmpty
    private String apiURL;

    @NotEmpty
    private String apiKey;

    @NotNull
    @Valid
    private final DataSourceFactory dataSourceFactory
            = new DataSourceFactory();
    
    @Valid
    @NotNull
    private ServerFactory serverFactory = new DefaultServerFactory();

    @Valid
    @NotNull
    private final JerseyClientConfiguration jerseyClientConfiguration
            = new JerseyClientConfiguration();

    @JsonProperty("server")
    public ServerFactory getServerFactory() {
        return serverFactory;
    }
    
    @JsonProperty("jerseyClient")
    public JerseyClientConfiguration getJerseyClientConfiguration() {
        return jerseyClientConfiguration;
    }

    @JsonProperty
    public String getLogin() {
        return login;
    }

    @JsonProperty
    public String getPassword() {
        return password;
    }

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
    }

    @JsonProperty
    public String getApiURL() {
        return apiURL;
    }

    @JsonProperty
    public String getApiKey() {
        return apiKey;
    }

}
