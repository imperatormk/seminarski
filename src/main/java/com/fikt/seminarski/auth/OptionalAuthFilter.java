package com.fikt.seminarski.auth;

import io.dropwizard.auth.AuthFilter;
import io.dropwizard.auth.AuthenticationException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.fikt.seminarski.model.OptionalUser;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@PreMatching
@Priority(Priorities.AUTHENTICATION)
public class OptionalAuthFilter extends AuthFilter<CustomCredentials, OptionalUser> {
  private OptionalAuthenticator authenticator;

  public OptionalAuthFilter(OptionalAuthenticator authenticator) {
    this.authenticator = authenticator;
  }

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {
    Optional<OptionalUser> authenticatedUser;

    try {
      CustomCredentials credentials = getCredentials(requestContext);
      authenticatedUser = authenticator.authenticate(credentials);
    } catch (AuthenticationException e) {
      throw new WebApplicationException("Unable to validate credentials.", Response.Status.UNAUTHORIZED);
    }

    if (authenticatedUser.isPresent()) {
      SecurityContext securityContext = new OptionalSecurityContext(authenticatedUser.get(), requestContext.getSecurityContext());
      requestContext.setSecurityContext(securityContext);
    } else {
      throw new WebApplicationException("Credentials not valid", Response.Status.UNAUTHORIZED);
    }
  }

  private CustomCredentials getCredentials(ContainerRequestContext requestContext) {
    CustomCredentials credentials = new CustomCredentials();

    if (requestContext.getCookies().containsKey("auth_token") && requestContext.getCookies().containsKey("auth_token")) {
      String rawToken = requestContext
        .getCookies()
        .get("auth_token")
        .getValue();

      String rawUserId = requestContext
        .getCookies()
        .get("auth_user")
        .getValue();

      credentials.setToken(UUID.fromString(rawToken));
      credentials.setUserId(Integer.valueOf(rawUserId));
    }

    return credentials;
  }
}