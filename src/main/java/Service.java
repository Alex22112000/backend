import jakarta.ws.rs.Path;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import model.dto.User;
import model.interfaces.in.IModel;
import model.interfaces.in.IModelProduct;
import model.interfaces.in.IModelUser;

import java.util.ArrayList;
import java.util.List;
import builder.Build.Built;
import controller.interceptor.TokenRequired;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbException;

@Path("/")
public class Service {
	@Context
	ContainerRequestContext requestContext;

	@Inject
	IModel model;

	@Inject
	@Built
	IModelUser modelUser;

	@Inject
	@Built
	IModelProduct modelProduct;

	@POST
	@Path("/auth")
	@Consumes("application/json")
	@Produces("text/plain")
	public Response signIn(String fileJSON) throws Exception {
		System.out.println("================" + fileJSON + "======================================");
		Jsonb jsonb = JsonbBuilder.create();
		List<String> logs = jsonb.fromJson(fileJSON, new ArrayList<String>() {
		}.getClass().getGenericSuperclass());
		boolean res = modelUser.checkAuth(logs.get(0), logs.get(1));

		if (res) {
			User user = modelUser.getUser(logs.get(0));
			return Response.ok(jsonb.toJson(model.createToken(user.getLogin(), user.getRole()))).build();
		}

		return Response.status(Response.Status.UNAUTHORIZED).build();

	}

	// отправка данных с браузера
	// путь к ресурсу
	// тип данных, которые придут от браузера
	// отправляемый тип данных
	@POST
	@Path("/reg")
	@Consumes("application/json")
	@Produces("application/json")
	// @Produces("text/plain")
	public Response signUp(String fileJSON) throws Exception {
		System.out.println("================" + fileJSON + "======================================");

		Jsonb jsonb = JsonbBuilder.create();
		List<String> logs = jsonb.fromJson(fileJSON, new ArrayList<String>() {
		}.getClass().getGenericSuperclass());
		boolean res = modelUser.addUser(logs.get(0), logs.get(1));

		if (res) {
			User user = modelUser.getUser(logs.get(0));
			return Response.ok(jsonb.toJson(model.createToken(user.getLogin(), user.getRole()))).build();
		}

		// return Response.status(Response.Status.UNAUTHORIZED).build();
		return Response.ok(res).build();
	}

	// смена пароля
	@POST
	@Path("/changePassword")
	public Response changePassword(String fileJSON) throws JsonbException, Exception {
		Jsonb jsonb = JsonbBuilder.create();
		List<String> logs = jsonb.fromJson(fileJSON, new ArrayList<String>() {
		}.getClass().getGenericSuperclass());

		boolean result = modelUser.changePassword(logs.get(0), logs.get(1));
		if (result) {
			User user = modelUser.getUser(logs.get(0));
			return Response.ok(jsonb.toJson(model.createToken(user.getLogin(), user.getRole()))).build();
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}

	// удаление пользователя
	@POST
	@Path("/deleteUser")
	public Response deleteUser(String fileJSON) {
		Jsonb jsonb = JsonbBuilder.create();
		List<String> logs = jsonb.fromJson(fileJSON, new ArrayList<String>() {
		}.getClass().getGenericSuperclass());

		boolean result = modelUser.deleteUser(logs.get(0));
		if (result) {
			return Response.ok().build();
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}

	// получение данных из сервера.
	@GET
	@Path("catalogs/product-catalog")
	@Produces("application/json")
	@TokenRequired
	public Response pushCatalog() throws Exception {

		String resultJSON;
		Jsonb jsonb = JsonbBuilder.create();

		resultJSON = jsonb.toJson(modelProduct.getFullCatalog());

		if (resultJSON != null)
			return Response.ok(resultJSON).build();
		else {
			return Response.status(Response.Status.BAD_REQUEST).entity("501").build();
		}
	}

	// отправляем данные на сервер.
	@POST
	@Path("catalogs/product-catalog/add")
	@Consumes("application/json")
	@Produces("text/plain")
	@TokenRequired
	public Response addNewProd(String fileJSON) throws Exception {

		System.out.println("================" + fileJSON + "======================================");
		Jsonb jsonb = JsonbBuilder.create();
		List<String> logs = jsonb.fromJson(fileJSON, new ArrayList<String>() {
		}.getClass().getGenericSuperclass());

		boolean res = modelProduct.addNewProduct(logs.get(0), logs.get(1), logs.get(2), logs.get(3));

		if (res) {
			return Response.ok().build();
		}
		return Response.status(Response.Status.BAD_REQUEST).entity("501").build();
	}

	@POST
	@Path("catalogs/product-catalog/del")
	@Consumes("application/json")
	@Produces("text/plain")
	@TokenRequired
	public Response delProd(String fileJSON) throws Exception {
		// System.out.println("================" + name + "======================================");

		Jsonb jsonb = JsonbBuilder.create();
		List<String> nameOfProd = jsonb.fromJson(fileJSON, new ArrayList<String>() {
		}.getClass().getGenericSuperclass());

		boolean res = modelProduct.delProduct(nameOfProd.get(0));

		if (res) {
			return Response.ok().build();
		}
		return Response.status(Response.Status.BAD_REQUEST).entity("501").build();
	}
}