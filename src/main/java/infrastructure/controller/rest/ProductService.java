package infrastructure.controller.rest;

import application.product.IModelProduct;
import application.product.Product;
import infrastructure.builder.Build.Built;
import infrastructure.interceptor.TokenRequired;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/product")
public class ProductService {

    @Inject @Built
    private IModelProduct modelProduct;

    private Jsonb jsonb = JsonbBuilder.create();
    
    @GET
	@Produces("application/json")
	@TokenRequired
	public Response pushCatalog(){
		String resultJSON;
		resultJSON = jsonb.toJson(modelProduct.getFullCatalog());
		if (resultJSON != null)
			return Response.ok(resultJSON).build();
		else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

    @POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("text/plain")
	@TokenRequired
	public Response addNewProd(String dataJSON){
		Product product = jsonb.fromJson(dataJSON, Product.class);
		boolean res = modelProduct.addNewProduct(product.getName(), product.getCost(), product.getCount(), product.getImg());
		if (res) {
			return Response.ok().build();
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
	}

    
	@POST
	@Path("/delete")
	@Consumes("application/json")
	@Produces("text/plain")
	@TokenRequired
	public Response delProd(String dataJSON){
        Product product = jsonb.fromJson(dataJSON, Product.class);
		boolean res = modelProduct.delProduct(product.getName());
		if (res) {
			return Response.ok().build();
		}
		return Response.status(Response.Status.BAD_REQUEST).entity("501").build();
	}
}
