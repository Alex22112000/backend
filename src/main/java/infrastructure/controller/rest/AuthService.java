package infrastructure.controller.rest;

import application.auth.service.IAuth;
import application.auth.user.User;
import infrastructure.builder.Build.Built;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/user")
public class AuthService {

    @Inject @Built
    private IAuth auth;

    private Jsonb jsonb = JsonbBuilder.create();
    
    @POST
	@Path("/auth")
	@Consumes("application/json")
	@Produces("text/plain")
	public Response signIn(String dataJSON){
        User user = jsonb.fromJson(dataJSON, User.class);
		boolean isAuth = auth.login(user);
		if(isAuth){
            String token = auth.createToken(user.getLogin());
			return Response.ok(jsonb.toJson(token)).build();
		}
		return Response.status(Response.Status.UNAUTHORIZED).build();
	}

    @POST
	@Path("/reg")
	@Consumes("application/json")
	@Produces("application/json")
	public Response signUp(String dataJSON){
		User user = jsonb.fromJson(dataJSON, User.class);
		boolean isReg = auth.registration(user);
		if(isReg){
			String token = auth.createToken(user.getLogin());
			return Response.ok(jsonb.toJson(token)).build();
		}
		return Response.status(Response.Status.UNAUTHORIZED).build();
	}

    @POST
	@Path("/changePassword")
    @Consumes("application/json")
	@Produces("application/json")
	public Response changePassword(String dataJSON){
		User user = jsonb.fromJson(dataJSON, User.class);
		boolean isChange = auth.changePassword(user);
		if (isChange) {
			String token = auth.createToken(user.getLogin());
			return Response.ok(jsonb.toJson(token)).build();
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}

    @POST
	@Path("/delete")
    @Consumes("application/json")
	@Produces("application/json")
	public Response deleteUser(String dataJSON) {
		User user = jsonb.fromJson(dataJSON, User.class);
		boolean isDelete = auth.delete(user);
		if(isDelete) {
		    return Response.ok().build();
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
}
