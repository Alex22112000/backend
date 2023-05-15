package infrastructure.builder;

import application.alert.IAlert;
import application.alert.ISender;
import application.auth.service.IAuth;
import application.auth.token.IToken;
import application.auth.user.IUserRepository;
import application.product.IModelProduct;
import application.product.IProductRepository;
import infrastructure.builder.Build.Built;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

public class Builder {

	@Inject @Default
	private IAuth auth;

	@Inject @Default
	private IUserRepository userRepository;

	@Inject @Default
	private IToken token;

	@Produces @Built
	public IAuth buildIAuth(){
		auth.setUserRepository(userRepository);
		auth.setTokenService(token);
		return auth;
	}

	@Inject @Default
	private IProductRepository productRepository;

	@Inject @Default
	private IModelProduct modelProduct;

	@Produces @Built
	public IModelProduct builModelProduct(){
		modelProduct.setRepository(productRepository);
		return modelProduct;
	}

	@Inject @Default
	private ISender sender;

	@Inject @Default
	private IAlert alert;

	@Produces @Built
	public IAlert buildAlert(){
		alert.setSender(sender);
		return alert;
	}
}
