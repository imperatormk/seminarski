package com.javaeeeee.dwstart;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.db.DataSourceFactory;
import javax.validation.Valid;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.NotEmpty;

public class DWGettingStartedConfiguration extends Configuration {

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
    private final JerseyClientConfiguration jerseyClientConfiguration
            = new JerseyClientConfiguration();

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
