package builder;

import builder.Build.Built;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import model.interfaces.in.IModel;
import model.interfaces.in.IModelProduct;
import model.interfaces.in.IModelUser;
import model.interfaces.out.IProductRepository;
import model.interfaces.out.IUserRepository;

public class Builder {
	@Inject
	@Default
	private IModel model;

	@Inject
	@Default
	private IModelUser modelUser;

	@Inject
	@Default
	private IModelProduct modelProduct;

	@Inject
	@Default
	private IProductRepository productRepository;

	@Inject
	@Default
	private IUserRepository userRepository;

	@Produces
	@Built
	public IModelUser buildIModelUser() {
		modelUser.setRepository(userRepository);
		return modelUser;
	}

	@Produces
	@Built
	public IModelProduct buildIModelProduct() {
		modelProduct.setRepository(productRepository);
		return modelProduct;
	}

}
