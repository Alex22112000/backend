package application.product;

import java.util.List;

public interface IProductRepository {
    public List<Product> findAll();
    public boolean addProduct(Product product);
    public boolean deleteProduct(String name);
}
