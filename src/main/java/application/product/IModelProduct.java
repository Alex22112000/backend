package application.product;

import java.util.List;

public interface IModelProduct {

    public void setRepository(IProductRepository productRepository);

    boolean addNewProduct(String name, Integer cost, Integer count, String img);

    boolean delProduct(String name);

    List<Product> getFullCatalog();
}
