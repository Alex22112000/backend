package infrastructure.interceptor;

import java.io.IOException;

import application.auth.service.IAuth;
import infrastructure.builder.Build.Built;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
@TokenRequired
public class Interceptor implements ContainerRequestFilter {

    @Inject @Built
    private IAuth auth;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String login = requestContext.getHeaderString("login");
        String token = requestContext.getHeaderString("token");
        if (!auth.checkToken(login, token)) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }
    } 
}