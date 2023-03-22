package builder;

import DBlayer.IDBController;
import builder.Build.Built;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import model.modelLayer.IModel;

public class Builder {
	 @Inject @Default
	 private IModel model;

	 @Inject @Default
	 private IDBController DBadapter;
	 
	 @Produces @Built
	 public IModel buildModel() {
		 model.injectIDBController(DBadapter);
		 return model;
	 }
	 
}
